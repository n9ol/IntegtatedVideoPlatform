package com.zzrenfeng.zhsx.task;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleTime;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleTimeService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.SpringUtil;
import com.zzrenfeng.zhsx.util.UdpSend;
/**
 * 根据课程表安排定时发送结束录制指令任务
 * 
 * @author zhoujincheng
 * @date 20190304 15:38
 * @version 1.0.0
 */
public class SendEndRecordCourseQuartzJob extends QuartzJobBean {
	private final Logger LOGGER = LoggerFactory.getLogger(SendEndRecordCourseQuartzJob.class);
	
	//读取properties配置文件
    Properties prop = null;
    public SendEndRecordCourseQuartzJob() {
    	prop = new Properties();
    	try {
			prop.load(SendEndRecordCourseQuartzJob.class.getResourceAsStream("/commonConfig.properties"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	private CourseScheduleService courseScheduleService = SpringUtil.getBean("courseScheduleService");
	private CourseScheduleTimeService courseScheduleTimeService = SpringUtil.getBean("courseScheduleTimeService");
	private SysClassroomService sysClassroomService = SpringUtil.getBean("sysClassroomService");
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String endTime = String.valueOf(context.getMergedJobDataMap().get("endTime"));    //获取结束执行时间参数
		LOGGER.debug("==============>>>> 【结束录制任务-" + endTime + "】执行UDP指令向流媒体服务器发送Public Stop Record命令【开始】 <<<<==============");
		getTodayEndCourseListByEndTimeAndSendStopRecord(endTime);
		
		LOGGER.debug("==============>>>> 【结束录制任务-" + endTime + "】执行UDP指令向流媒体服务器发送Public Stop Record命令【结束】 <<<<==============");
	}
	
	/**
	 * 通过结束时间获取当天结束的课程列表并给流媒体服务器发送结束录制指令
	 * @param endTime
	 * @return
	 */
	private void getTodayEndCourseListByEndTimeAndSendStopRecord(String endTime) {
		CourseSchedule csTemp = new CourseSchedule();
		csTemp.setDayOfWeek(DateUtil.getIntWeekDay());
		csTemp.setEndTimeStr(endTime);
//		csTemp.setTimeSorting(Constant.COURSE_SCHEDULE_TIMESORTING_Y);
		List<CourseSchedule> offliveList = courseScheduleService.listCourseSchedule(csTemp);
		if(null != offliveList && !offliveList.isEmpty()) {
			for (CourseSchedule courseSchedule : offliveList) {
				//判断当前结束时间是大节的结束时间（也是该大节的第2小节的结束时间），还是该大节中第1小节的结束时间
				String sections = courseSchedule.getSectionOfDay();
				List<CourseScheduleTime> listCourseScheduletime = null;
				if(null != sections && !"".equals(sections)) {
					String[] sectionsArray = sections.split(",|，");
					if(sectionsArray.length == 2) {
						CourseScheduleTime courseScheduletime = null;
						if(sectionsArray[0].equals(sectionsArray[1])) {    //两者相等说明该节为小节课
							int section = Integer.valueOf(sectionsArray[0]);
							//根据该节次和开始时间到“上课节次时间表”【course_schedule_time】中查询是否存在记录，存在则发送结束录制指令
							courseScheduletime = new CourseScheduleTime();
							courseScheduletime.setSectionOfDay(section);
							courseScheduletime.setEndTimeStr(endTime);
							listCourseScheduletime = courseScheduleTimeService.findSelective(courseScheduletime);
							if(null != listCourseScheduletime && listCourseScheduletime.size() == 1) {    //存在1条记录则发送指令
								sendPublishStopRecordCommand(courseSchedule, endTime);
							}
						} else {    //两者不等说明该节为大节课
							int first = Integer.valueOf(sectionsArray[0]);
							int second = Integer.valueOf(sectionsArray[1]);
							courseScheduletime = new CourseScheduleTime();
							courseScheduletime.setSectionOfDay(first);
							courseScheduletime.setEndTimeStr(endTime);
							listCourseScheduletime = courseScheduleTimeService.findSelective(courseScheduletime);
							if(null != listCourseScheduletime && listCourseScheduletime.size() == 1) {    //存在1条记录则发送指令
								sendPublishStopRecordCommand(courseSchedule, endTime);
							} else {
								courseScheduletime = new CourseScheduleTime();
								courseScheduletime.setSectionOfDay(second);
								courseScheduletime.setEndTimeStr(endTime);
								listCourseScheduletime = courseScheduleTimeService.findSelective(courseScheduletime);
								if(null != listCourseScheduletime && listCourseScheduletime.size() == 1) {    //存在1条记录则发送指令
									sendPublishStopRecordCommand(courseSchedule, endTime);
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
			}
		}		
	}
	
	/**
	 * @功能描述：发送结束录制指令
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月7日 下午4:40:10
	 * 
	 * @param courseSchedule
	 * @param endTime
	 */
	private void sendPublishStopRecordCommand(CourseSchedule courseSchedule, String endTime) {
		String ip = null;
		SysClassroom classroom = sysClassroomService.findByKey(courseSchedule.getClassroomId());
		if(null != classroom) {
			String classCode = classroom.getClassCode();
			String clientIp = classroom.getClientIp();
			if (clientIp != null && !"".equals(clientIp)) {
				String[] clientIpArray = clientIp.split(":|：");
				ip = clientIpArray[0].trim();
				Integer port = Integer.parseInt(clientIpArray[1].trim());
				UdpSend.getInstance().sendStopData(ip, port);
LOGGER.debug("-------------------------------->>>>【"+endTime+" 定时结束任务】【教室:"+classroom.getClassName()+"[IP:port="+clientIp+"]】发送<结束>录制指令成功！");								
			} else {
				String bublishCtrlService = prop.getProperty("bublishCtrlService");
				String[] bublishCtrlServiceIpArray = bublishCtrlService.split(":|：");
				ip = bublishCtrlServiceIpArray[0].trim();
				Integer port = Integer.parseInt(bublishCtrlServiceIpArray[1].trim());
				UdpSend.getInstance().sendStopData(ip, port, classCode);
LOGGER.debug("-------------------------------->>>>【"+endTime+" 定时结束任务】【教室:"+classroom.getClassName()+"[IP:port="+bublishCtrlService+"]】发送<结束>录制指令成功！");						
			}				
		}
	}
	

}

