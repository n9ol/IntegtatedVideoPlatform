package com.zzrenfeng.zhsx.controller.Interface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.MessageUtils;

/**
 * 设备自动录制视频，转码 - 对接 接口
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/automaticRecording")
public class AutomaticRecordingController {

	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private UserService userService;
	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;
	@Resource
	private SysDictService sysDictService;

	/**
	 * 获得学校信息
	 * 
	 * @return
	 */
	@RequestMapping("/getSchool")
	public @ResponseBody List<SysSchool> getSchool() {
		SysSchool school = new SysSchool();
		List<SysSchool> schoolList = sysSchoolService.findIdAndSchoolNameSelective(school);
		return schoolList;
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
	 * 获得年级
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGradeslist")
	public List<SysDict> getGradeslist() {
		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		return sysDicts;
	}

	/**
	 * 根据年级ID获得科目
	 * 
	 * @param gradeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSubjectslist")
	public List<SysDict> getSubjectslist(String gradeId) {
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("S");
		sysDict.setPid(gradeId);
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);

		// 科目去重复
		List<SysDict> subjects = new ArrayList<SysDict>();
		String tem = "1";
		for (SysDict o : sysDicts) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		return subjects;
	}

	/**
	 * 获得视频类型
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVideoType")
	public Map<String, Object> getVideoType() {
		Map<String, Object> hm = new HashMap<>();
		hm.put(OffLineVideoResources.TYPE_B, MessageUtils.VIDEOTYPE_B);
		hm.put(OffLineVideoResources.TYPE_P, MessageUtils.VIDEOTYPE_P);
		hm.put(OffLineVideoResources.TYPE_S, MessageUtils.VIDEOTYPE_S);
		hm.put(OffLineVideoResources.TYPE_H, MessageUtils.VIDEOTYPE_H);
		return hm;
	}

	/**
	 * 通过 文件前5M的MD5值,原文件视频大小,上传人验证文件是否已存在
	 * 
	 * @param bak1
	 * @param userId
	 * @param bak2
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/verifyFileIsExist")
	public Map<String, Object> verifyFileIsExist(String bak1, String userId, String bak2) {
		Map<String, Object> hm = new HashMap<>();
		OffLineVideoResources videoRes = new OffLineVideoResources();
		videoRes.setUserId(userId);
		videoRes.setBak1(bak1);
		videoRes.setBak2(bak2);
		List<OffLineVideoResources> videoResList = offLineVideoResourcesService.findSelective(videoRes);
		if (videoResList != null && videoResList.size() > 0) {
			hm.put("r", 0);
			hm.put("rs", "已上传过该文件,不可重复上传");
		} else {
			hm.put("r", 1);
			hm.put("rs", "可以上传");
		}
		return hm;
	}

	/**
	 * 添加自动录制视频信息到数据库
	 * 
	 * @param offLineVideoResources
	 */
	@ResponseBody
	@RequestMapping("/insterVideoResour")
	public Map<String, Object> insterVideoResour(HttpServletRequest request,
			OffLineVideoResources offLineVideoResources) {
		Map<String, Object> hm = new HashMap<>();

		try {
			offLineVideoResourcesService.append(request, offLineVideoResources);
			hm.put("r", 1);
			hm.put("rs", MessageUtils.SUCCESS);
		} catch (Exception e) {
			hm.put("r", 0);
			hm.put("rs", MessageUtils.FAilURE);
		}
		return hm;
	}

}
