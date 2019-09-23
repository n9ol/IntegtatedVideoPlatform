package com.zzrenfeng.zhsx.task;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.model.OffLineRecordVideo;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleTime;
import com.zzrenfeng.zhsx.service.OffLineRecordVideoService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleTimeService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.SpringUtil;
import com.zzrenfeng.zhsx.util.UdpSend;

/**
 * 根据课程表安排定时发送开始录制指令任务
 * 
 * @author zhoujincheng
 * @date 20190304 15:38
 * @version 1.0.0
 */
public class SendStartRecordCourseQuartzJob extends QuartzJobBean {
	private final Logger LOGGER = LoggerFactory.getLogger(SendStartRecordCourseQuartzJob.class);
	
	//读取properties配置文件
    Properties prop = null;
    public SendStartRecordCourseQuartzJob() {
    	prop = new Properties();
    	try {
			prop.load(SendStartRecordCourseQuartzJob.class.getResourceAsStream("/commonConfig.properties"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	private CourseScheduleService courseScheduleService = SpringUtil.getBean("courseScheduleService");
	private CourseScheduleTimeService courseScheduleTimeService = SpringUtil.getBean("courseScheduleTimeService");
	private SysClassroomService sysClassroomService = SpringUtil.getBean("sysClassroomService");
	private OffLineRecordVideoService offLineRecordVideoService = SpringUtil.getBean("offLineRecordVideoService");
	private OffLineVideoResourcesService offLineVideoResourcesService = SpringUtil.getBean("offLineVideoResourcesService");
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String startTime = String.valueOf(context.getMergedJobDataMap().get("startTime"));    //获取开始执行时间参数
		LOGGER.debug("==============>>>> 【开始录制任务-" + startTime + "】执行UDP指令向流媒体服务器发送Public Start Record命令【开始】 <<<<==============");
		getTodayStartCourseListByStartTimeAndSendStartRecord(startTime);
		
		LOGGER.debug("==============>>>> 【开始录制任务-" + startTime + "】执行UDP指令向流媒体服务器发送Public Start Record命令【结束】 <<<<==============");
	}
	
	/**
	 * 通过开始时间获取当天开始的课程列表并给流媒体服务器发送开始录制指令
	 * 
	 * @param startTime
	 * @return
	 */
	private void getTodayStartCourseListByStartTimeAndSendStartRecord(String startTime) {
		CourseSchedule csTemp = new CourseSchedule();
		csTemp.setDayOfWeek(DateUtil.getIntWeekDay());
		csTemp.setStartTimeStr(startTime);
//		csTemp.setTimeSorting(Constant.COURSE_SCHEDULE_TIMESORTING_Z);
		List<CourseSchedule> onliveList = courseScheduleService.listCourseSchedule(csTemp);
		if(null != onliveList && !onliveList.isEmpty()) {
			for (CourseSchedule courseSchedule : onliveList) {
				//判断当前开始时间是大节的开始时间（也是该大节的第1小节的开始时间），还是该大节中第2小节的开始时间
				String sections = courseSchedule.getSectionOfDay();
				List<CourseScheduleTime> listCourseScheduletime = null;
				if(null != sections && !"".equals(sections)) {
					String[] sectionsArray = sections.split(",|，");
					if(sectionsArray.length == 2) {
						CourseScheduleTime courseScheduletime = null;
						if(sectionsArray[0].equals(sectionsArray[1])) {    //两者相等说明该节为小节课
							int section = Integer.valueOf(sectionsArray[0]);
							//根据该节次和开始时间到“上课节次时间表”【course_schedule_time】中查询是否存在记录，存在则发送开始录制指令
							courseScheduletime = new CourseScheduleTime();
							courseScheduletime.setSectionOfDay(section);
							courseScheduletime.setStartTimeStr(startTime);
							listCourseScheduletime = courseScheduleTimeService.findSelective(courseScheduletime);
							if(null != listCourseScheduletime && listCourseScheduletime.size() == 1) {    //存在1条记录则发送指令
								sendPublishStartRecordCommand(courseSchedule, startTime);
							}
						} else {    //两者不等说明该节为大节课
							int first = Integer.valueOf(sectionsArray[0]);
							int second = Integer.valueOf(sectionsArray[1]);
							courseScheduletime = new CourseScheduleTime();
							courseScheduletime.setSectionOfDay(first);
							courseScheduletime.setStartTimeStr(startTime);
							listCourseScheduletime = courseScheduleTimeService.findSelective(courseScheduletime);
							if(null != listCourseScheduletime && listCourseScheduletime.size() == 1) {    //存在1条记录则发送指令
								sendPublishStartRecordCommand(courseSchedule, startTime);
							} else {
								courseScheduletime = new CourseScheduleTime();
								courseScheduletime.setSectionOfDay(second);
								courseScheduletime.setStartTimeStr(startTime);
								listCourseScheduletime = courseScheduleTimeService.findSelective(courseScheduletime);
								if(null != listCourseScheduletime && listCourseScheduletime.size() == 1) {    //存在1条记录则发送指令
									sendPublishStartRecordCommand(courseSchedule, startTime);
								}
							}						
						}
					} else {
LOGGER.debug("-------------------------------->>>>课程ID为【"+courseSchedule.getId()+"】的课程表【course_schedule】的节次(小节)字段【section_of_day】数据不合法！");
						continue;
					}
				} else {
LOGGER.debug("-------------------------------->>>>课程ID为【"+courseSchedule.getId()+"】的课程表【course_schedule】的节次(小节)字段【section_of_day】数据为空！");
					continue;
				}
				
				//20190307-zjc modified；定时任务只管向流媒体服务器发送指令，不做自动发布课程的操作，将此操作移至流媒体服务器回调Web接口的时机。
				// 将自动录制的视频资源更新为发布状态；并更新视频资源其他相关信息；
//				updateOffLineVideoResourcesInfo(classroom, courseSchedule, new Date());
			}
		}
		
	}
	
	/**
	 * @功能描述：发送开始录制指令
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月7日 下午4:05:49
	 * 
	 * @param courseSchedule
	 * @param startTime
	 */
	private void sendPublishStartRecordCommand(CourseSchedule courseSchedule, String startTime ) {		
		String ip = null;
		SysClassroom classroom = sysClassroomService.findByKey(courseSchedule.getClassroomId());
		if(null != classroom) {
			String classCode = classroom.getClassCode();
			String clientIp = classroom.getClientIp();					
			if (clientIp != null && !"".equals(clientIp)) {
				String[] clientIpArray = clientIp.split(":|：");
				ip = clientIpArray[0].trim();
				Integer port = Integer.parseInt(clientIpArray[1].trim());
				UdpSend.getInstance().sendStartData(ip, port);
LOGGER.debug("-------------------------------->>>>【"+startTime+" 定时开始任务】【教室:"+classroom.getClassName()+"[IP:port="+clientIp+"]】发送<开始>录制指令成功！");							
			} else {
				String bublishCtrlService = prop.getProperty("bublishCtrlService");
				String[] bublishCtrlServiceIpArray = bublishCtrlService.split(":|：");
				ip = bublishCtrlServiceIpArray[0].trim();
				Integer port = Integer.parseInt(bublishCtrlServiceIpArray[1].trim());
				UdpSend.getInstance().sendStartData(ip, port, classCode);
LOGGER.debug("-------------------------------->>>>【"+startTime+" 定时开始任务】【教室:"+classroom.getClassName()+"[IP:port="+bublishCtrlService+"]】发送<开始>录制指令成功！");						
			}
		}
	}
	
	/**
	 * 将自动录制的视频资源更新为发布状态；并更新视频资源其他相关信息
	 * 在每个教室发送开始录制指令5s后，再更改自动录制的视频资源信息。因为在开始录制时流媒体服务器可能还没有及时调用接口插入相关视频资源记录，等流媒体更新后再更新
	 * 更新视频资源名称为：教室名_专业名_课程名_上课班级名_上课教师名_时间戳，更新视频为发布状态等相关信息
	 * 
	 * @param classroom 教室信息
	 * @param courseSchedule 课程表信息
	 * @param accessDate 调用方法时的当前时间（以此时间作为依据，后延5s，判断这个时间段内流媒体是否回调创建资源记录接口，来作为更新资源信息的依据）
	 */
	@SuppressWarnings("unused")
	private synchronized void updateOffLineVideoResourcesInfo(SysClassroom classroom, CourseSchedule courseSchedule, Date accessDate) {
		try {
			//由于服务器间可能存在时间差，且流媒体服务器回调接口也不是实时的，为了避免流媒体服务器没有更新到数据，这里让该线程暂驻5s
			Thread.sleep(5000L);
			
			String createDateStr1 = DateUtil.getStringDate(accessDate, "yyyy-MM-dd HH:mm:ss");
			accessDate.setTime(accessDate.getTime() + 5000L);
			String createDateStr2 = DateUtil.getStringDate(accessDate, "yyyy-MM-dd HH:mm:ss");
			List<OffLineRecordVideo>  videoList = offLineRecordVideoService.getListByCidStime(classroom.getId(), createDateStr1, createDateStr2);
			if(null != videoList && !videoList.isEmpty()) {
				OffLineRecordVideo offLineRecordVideo = videoList.get(0);    //这里暂做粗暴处理；因为如果获取的记录数不在[0, 4]范围，或者4条记录的offLineVideoId不一致，说明在同一时间该教室触发了多条开始录制指令，此种情况暂认为不可能存在；
				if(null != offLineRecordVideo) {
					OffLineVideoResources  offLineVideoResources = offLineVideoResourcesService.findByKey(offLineRecordVideo.getOfflinevideoid());
					if(null != offLineVideoResources) {
						String recordCreateDateStr = DateUtil.getStringDate(offLineRecordVideo.getCreateDate(), "yyyyMMddHHmmss");
						//视频资源名称：教室名_专业名_课程名_上课班级名_上课教师名_时间戳
						String title = classroom.getClassName() + "_" + courseSchedule.getSpecialtyName() + "_" 
								+ courseSchedule.getSubjectName() + "_" + courseSchedule.getClassName() + "_"
								+ courseSchedule.getTeacherName() + "_" + recordCreateDateStr;
//System.out.println("----------------------------[resId="+offLineVideoResources.getId()+"]>>>>title=" + title);						
						offLineVideoResources.setTitle(title);
						offLineVideoResources.setGradeName(courseSchedule.getSpecialtyName());
						offLineVideoResources.setSubjectName(courseSchedule.getSubjectName());
						offLineVideoResources.setTeacherId(Constant.AUTO_PUBLISH_USERID);
						offLineVideoResources.setType(OffLineVideoResources.TYPE_B);
						offLineVideoResources.setReleaseState("Y");    //发布状态
						offLineVideoResourcesService.reUpdateByKeySelective(offLineVideoResources);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

