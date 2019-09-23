package com.zzrenfeng.zhsx.controller.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.PgPjinfo;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.PgPjinfoService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.FilesUploadUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;

/**
 * 公共controller类
 * 
 * @author 田杰熠
 *
 */
@Controller
public class UtilController {

	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private UserService userService;
	@Resource
	private PgPjinfoService pgPjinfoService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private LoFscheduleService loFscheduleService;

	/**
	 * 文件上传公共类
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> uploadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> hm = new HashMap<String, Object>();
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload"); // 上传到服务器的路径
		String filePath = FilesUploadUtil.fileUpload(file, uploadPath);
		if (filePath == null) {
			hm.put("path", "");
		} else {
			hm.put("code", 0);
			hm.put("msg", MessageUtils.SUCCESS);
			Map<String, Object> hm2 = new HashMap<String, Object>();
			String path = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
					+ request.getRequestURI().split("/")[1] + "/upload/" + filePath;
			hm2.put("src", path);
//			hm2.put("title", file.getOriginalFilename());
			hm.put("path", filePath);
			hm.put("data", hm2);
		}
		return hm;
	}

	/**
	 * 通过学校id获得教室信息
	 * 
	 * @param schoolId
	 */
	@RequestMapping("/getClassRoomBySchoolId")
	public @ResponseBody List<SysClassroom> getClassRoomBySchoolId(String schoolId) {
		SysClassroom classroom = new SysClassroom();
		classroom.setSchoolId(schoolId);
		classroom.setBak("Y");
		List<SysClassroom> classroomList = sysClassroomService.findSelective(classroom);
		return classroomList;
	}

	/**
	 * 通过学校id获得教师信息
	 * 
	 * @param schoolId
	 */
	@RequestMapping("/getUserBySchoolId")
	public @ResponseBody List<User> getUserBySchoolId(String schoolId) {
		List<User> userlList = userService.findTeacherBySchoolId(schoolId);
		return userlList;
	}

	/**
	 * 通过类型获得评估项
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/getPjInfoByType")
	public @ResponseBody List<PgPjinfo> getPjInfoByType(String type) {
		PgPjinfo pgPjinfo = new PgPjinfo();
		pgPjinfo.setType(type);
		List<PgPjinfo> pgPjinfos = pgPjinfoService.findSelective(pgPjinfo);
		return pgPjinfos;
	}

	/**
	 * 通过区县id获得学校
	 * 
	 * @param countyId
	 * @return
	 */
	@RequestMapping("/getSchoolByCountyId")
	public @ResponseBody List<SysSchool> getSchoolByCountyId(String countyId) {
		SysSchool schoool = new SysSchool();
		schoool.setCountyId(countyId);
		List<SysSchool> schoolList = sysSchoolService.findSelective(schoool);
		return schoolList;
	}

	/**
	 * 通过直播id获得附讲信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getLoFscheduleByScheduleId")
	public @ResponseBody List<LoFschedule> getLoFscheduleByScheduleId(String id) {
		LoFschedule lof = new LoFschedule();
		lof.setZId(id);
		List<LoFschedule> lofList = loFscheduleService.findSelective(lof);
		return lofList;
	}

}
