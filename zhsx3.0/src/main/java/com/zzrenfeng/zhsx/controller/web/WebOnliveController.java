package com.zzrenfeng.zhsx.controller.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.OffLineRecordVideo;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.service.OffLineRecordVideoService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
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

	/**
	 * 不允许下载
	 */
	private final String IS_ALLOW_DOWNLOAD_N = "N";

	/**
	 * 发布状态 - 未发布
	 */
	private final String RELEASE_STATE_N = "N";

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

	private OffLineVideoResources insterOffLineVideoResources(String offLineVideoResourcesId, SysClassroom sysClassroom,
			Date date) {
		OffLineVideoResources offLineVideoResources;
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
	private String getOffLineVideoResourcesId(String ipAdrress, String classCode, Date date) {
		String offLineVideoResourcesId = ipAdrress.replaceAll(":", "") + classCode
				+ DateUtil.getStringDate(date, "yyyyMMddHHmmss");
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
	 */
	@RequestMapping("/streamrecording")
	public void streamRecording(HttpServletResponse response, HttpServletRequest request,
			@RequestParam String streamtype, @RequestParam(value = "code") String classCode,
			@RequestParam(value = "filename") String videoPath) {
		Date date = new Date();
		String ipAdrress = Utils.getIpAdrress(request);
		System.out.println("streamrecording=" + streamtype + "---" + classCode + "---" + videoPath);
		System.out.println("serviceIp=" + ipAdrress);
		SysClassroom sysClassroom = sysClassroomService.getSysClassroom(classCode, ipAdrress);
		if (sysClassroom == null) {
			System.out.println("isExist: The classroom was not found");
			return;
		}
		String offLineVideoResourcesId = getOffLineVideoResourcesId(ipAdrress, classCode, date);
		OffLineVideoResources offLineVideoResources = offLineVideoResourcesService.findByKey(offLineVideoResourcesId);
		if (offLineVideoResources == null) {
			offLineVideoResources = insterOffLineVideoResources(offLineVideoResourcesId, sysClassroom, date);
		}
		insterOffLineRecordVideo(streamtype, videoPath, offLineVideoResources, sysClassroom, date);
	}

}
