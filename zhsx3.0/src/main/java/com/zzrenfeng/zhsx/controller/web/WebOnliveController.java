package com.zzrenfeng.zhsx.controller.web;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.controller.base.BaseController;
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
import com.zzrenfeng.zhsx.util.Utils;

/**
 * 与流媒体服务器互动接口
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2014-11-25 16:11:30
 * @see com.zzrenfeng.controller.WebOnlive
 */
@Controller
@RequestMapping(value = "/webonlive")
public class WebOnliveController extends BaseController {

	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;
	@Resource
	private OffLineRecordVideoService offLineRecordVideoService;
	@Resource
	private CourseScheduleService courseScheduleService;
	@Resource
	private CourseScheduleTimeService courseScheduleTimeService;
	@Resource
	private Environment environment;
	
	private Object object = new Object();

	/**
	 * 不允许下载
	 */
	private final String IS_ALLOW_DOWNLOAD_N = "N";

	/**
	 * 发布状态 - 未发布
	 */
	private final String RELEASE_STATE_N = "N";
	/**
	 * 发布状态 - 发布
	 */
	private final String RELEASE_STATE_Y = "Y";

	/**
	 * 审核状态 - 已审核
	 */
	private final String IS_SHOW_Y = "Y";

	/**
	 * 录制视频类型 - 在线评估
	 */
	private final String RECORD_VIDEO_TYPE_A = "A";

	/**
	 * 是否是自动录制的视频
	 */
	private final String IS_RECORD_Y = "Y";
	
	
	/**
	 * @功能描述：插入离线视频资源记录，并根据配置判断是否自动将资源发布到前台
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月8日 上午11:10:42
	 * 
	 * @param offLineVideoResourcesId
	 * @param sysClassroom
	 * @param date
	 * @return
	 */
	private OffLineVideoResources insterOffLineVideoResources(String offLineVideoResourcesId, SysClassroom sysClassroom,
			Date date) {
		OffLineVideoResources offLineVideoResources = null;
		String isAutoPublish = environment.getProperty("is.automatic.publish");    //是否自动发布		
		if("Y".equalsIgnoreCase(isAutoPublish)) {    //自动发布视频资源到前台“课堂回放”栏目中
			CourseSchedule courseSchedule = getCourseScheduleByCroomAndRtime(sysClassroom, date);
			if(null != courseSchedule) {
				offLineVideoResources = new OffLineVideoResources();
				
				String recordCreateDateStr = DateUtil.getStringDate(date, "yyyyMMddHHmmss");
				//视频资源名称：教室名_专业名_课程名_上课班级名_上课教师名_时间戳
				String title = sysClassroom.getClassName() + "_" + courseSchedule.getSpecialtyName() + "_" 
						+ courseSchedule.getSubjectName() + "_" + courseSchedule.getClassName() + "_"
						+ courseSchedule.getTeacherName() + "_" + recordCreateDateStr;
				
				offLineVideoResources.setId(offLineVideoResourcesId);
				offLineVideoResources.setTitle(title);
				offLineVideoResources.setSchoolId(sysClassroom.getSchoolId());
				offLineVideoResources.setUploadName(IS_ALLOW_DOWNLOAD_N);
				offLineVideoResources.setGradeName(courseSchedule.getSpecialtyName());
				offLineVideoResources.setSubjectName(courseSchedule.getSubjectName());
				offLineVideoResources.setTeacherId(Constant.AUTO_PUBLISH_USERID);
				offLineVideoResources.setType(OffLineVideoResources.TYPE_B);
				offLineVideoResources.setReleaseState(RELEASE_STATE_Y);    //发布状态
				offLineVideoResources.setIsShow(IS_SHOW_Y);
				offLineVideoResources.setTranscodingState("O");
				offLineVideoResources.setProgress(100);
				offLineVideoResources.setIsRecord(IS_RECORD_Y);
				offLineVideoResources.setCreateTime(date);
			}
		} else {		
			offLineVideoResources = new OffLineVideoResources();
			offLineVideoResources.setId(offLineVideoResourcesId);
			offLineVideoResources.setTitle(sysClassroom.getClassName() + offLineVideoResourcesId);
			offLineVideoResources.setSchoolId(sysClassroom.getSchoolId());
			offLineVideoResources.setUploadName(IS_ALLOW_DOWNLOAD_N);
			offLineVideoResources.setReleaseState(RELEASE_STATE_N);
			offLineVideoResources.setIsShow(IS_SHOW_Y);
			offLineVideoResources.setTranscodingState("O");
			offLineVideoResources.setProgress(100);
			offLineVideoResources.setIsRecord(IS_RECORD_Y);
			offLineVideoResources.setCreateTime(date);
		}
		offLineVideoResourcesService.appendOffLineVideoResources(offLineVideoResources);		
		return offLineVideoResources;
	}

	private void insterOffLineRecordVideo(String streamtype, String videopatch,
			OffLineVideoResources offLineVideoResources, SysClassroom sysClassroom, Date date) {
		OffLineRecordVideo offLineRecordVideo = new OffLineRecordVideo();
		offLineRecordVideo.setOfflinevideoid(offLineVideoResources.getId());
		offLineRecordVideo.setClassroomid(sysClassroom.getId());
		offLineRecordVideo.setVideotype(RECORD_VIDEO_TYPE_A);
		offLineRecordVideo.setStreamtype(streamtype);
		offLineRecordVideo.setVideopatch(videopatch);
		offLineRecordVideo.setCreateDate(date);
		offLineRecordVideo.setModifyDate(date);
		offLineRecordVideoService.tInsert(offLineRecordVideo);
	}

	/**
	 * 获取当前录制视频主键id(该主键id是伪id因为接受服务器发送信息之间存在时间差)
	 * 
	 * @param ipAdrress
	 * @param classCode
	 * @param date
	 * @return
	 */
	private String getOffLineVideoResourcesId(String ipAdrress, String classCode, String videoPath) {
		int indexOf2 = videoPath.indexOf("_");
		int indexOf3 = videoPath.indexOf(".flv");
		String dateString = videoPath.substring(indexOf2 + 1, indexOf3);
		String replaceAll = ipAdrress.replace(".", "");
		String offLineVideoResourcesId = replaceAll + classCode + dateString;
		return offLineVideoResourcesId;
	}

	/**
	 * 更改教室直播发布状态 TODO 在外网映射,到内网的情况下怎么处理
	 * 
	 * @param code
	 * @param state
	 */
	@RequestMapping("/changeState")
	public void changeState(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "code") String classCode, @RequestParam(value = "state") String onlineState) {
		String ipAdrress = Utils.getIpAdrress(request);
		System.out.println("changeState=" + ipAdrress + "---" + classCode + "---" + onlineState);
		sysClassroomService.tUpdateOnlineState(classCode, ipAdrress, onlineState);
	}

	/**
	 * 保存 录制评估视频流路径
	 * 
	 * @param response
	 * @param streamtype
	 *            流类型, 1、2、3、a 分别代表 教师、学生、电子白板、音频
	 * @param classCode
	 *            教室编号
	 * @param videoPath
	 *            文件路径(录制文件名)
	 * @throws ParseException
	 */
	@RequestMapping("/streamrecording")
	public synchronized void streamRecording(HttpServletResponse response, HttpServletRequest request,
			@RequestParam String streamtype, @RequestParam(value = "code") String classCode,
			@RequestParam(value = "filename") String videoPath) throws ParseException {
		Date date = new Date();
		String ipAdrress = Utils.getIpAdrress(request);
		SysClassroom sysClassroom = sysClassroomService.getSysClassroomByClassCode(classCode);
		if (sysClassroom == null) {
			System.out.println("isExist: The classroom was not found");
			return;
		}
		String offLineVideoResourcesId = getOffLineVideoResourcesId(ipAdrress, classCode, videoPath);
		List<String> listIds = offLineVideoResourcesService.listIds(offLineVideoResourcesId);

		OffLineVideoResources offLineVideoResources = offLineVideoResourcesService
				.getOffLineVideoResourcesByIds(listIds);
		if (offLineVideoResources == null) {
			synchronized (object) {
				offLineVideoResources = offLineVideoResourcesService.getOffLineVideoResourcesByIds(listIds);
				if (offLineVideoResources == null) {
					offLineVideoResources = insterOffLineVideoResources(offLineVideoResourcesId, sysClassroom, date);
				}
			}
		}
		insterOffLineRecordVideo(streamtype, videoPath, offLineVideoResources, sysClassroom, date);
	}
	
	/**
	 * @功能描述：通过教室信息和录制记录创建时间获取所录制课程在课程表中的对象信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月8日 上午9:29:43
	 * 
	 * @param classroom 教室信息
	 * @param recordCreateTime 录制记录创建时间
	 * @return
	 */
	private CourseSchedule getCourseScheduleByCroomAndRtime(SysClassroom classroom, Date recordCreateTime) {
		if(null != classroom) {
			CourseSchedule csTemp = new CourseSchedule();
			csTemp.setClassroomId(classroom.getId());
			csTemp.setDayOfWeek(DateUtil.getIntWeekDay());
			String timeStr = DateUtil.getStringDate(recordCreateTime, "HH:mm:ss");
			csTemp.setStartTimeStr(timeStr);
			List<CourseSchedule> onliveList = courseScheduleService.listCourseSchedule(csTemp);
			if(null != onliveList && !onliveList.isEmpty()) {
				for (CourseSchedule courseSchedule : onliveList) {
					//判断当前开始时间是大节的开始时间（也是该大节的第1小节的开始时间），还是该大节中第2小节的开始时间
					String sections = courseSchedule.getSectionOfDay();
					CourseScheduleTime courseScheduleTime = null;
					CourseScheduleTime courseScheduleTimeTemp = null;
					if(null != sections && !"".equals(sections)) {
						String[] sectionsArray = sections.split(",|，");
						if(sectionsArray.length == 2) {
							if(sectionsArray[0].equals(sectionsArray[1])) {    //两者相等说明该节为小节课
								int section = Integer.valueOf(sectionsArray[0]);
								//根据该节次和开始时间到“上课节次时间表”【course_schedule_time】中查询是否存在记录，存在则直接返回该对象
								courseScheduleTimeTemp = new CourseScheduleTime();
								courseScheduleTimeTemp.setSectionOfDay(section);
								courseScheduleTimeTemp.setStartTimeStr(timeStr);
								courseScheduleTime = courseScheduleTimeService.getCourseScheduleTimeByConditions(courseScheduleTimeTemp);
								if(null != courseScheduleTime) {    //存在1条记录则返回该课程表对象
									return courseSchedule;
								}
							} else {    //两者不等说明该节为大节课
								int first = Integer.valueOf(sectionsArray[0]);
								int second = Integer.valueOf(sectionsArray[1]);
								courseScheduleTimeTemp = new CourseScheduleTime();
								courseScheduleTimeTemp.setSectionOfDay(first);
								courseScheduleTimeTemp.setStartTimeStr(timeStr);
								courseScheduleTime = courseScheduleTimeService.getCourseScheduleTimeByConditions(courseScheduleTimeTemp);
								if(null != courseScheduleTime) {
									return courseSchedule;
								} else {
									courseScheduleTimeTemp = new CourseScheduleTime();
									courseScheduleTimeTemp.setSectionOfDay(second);
									courseScheduleTimeTemp.setStartTimeStr(timeStr);
									courseScheduleTime = courseScheduleTimeService.getCourseScheduleTimeByConditions(courseScheduleTimeTemp);
									if(null != courseScheduleTime) {
										return courseSchedule;
									}
								}						
							}
						} else {
							System.out.println("====>>>> ERROR <<<<====:课程ID为【"+courseSchedule.getId()+"】的课程表【course_schedule】的节次(小节)字段【section_of_day】数据不合法！");
						}
					} else {
						System.out.println("====>>>> ERROR <<<<====:课程ID为【"+courseSchedule.getId()+"】的课程表【course_schedule】的节次(小节)字段【section_of_day】数据为空！");
					}
				}
			}
		}
		return null;
	}

}
