package com.zzrenfeng.zhsx.controller.androidios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;

/**
 * 移动端接口- 基础信息
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/androidios")
public class AndroidiosController extends BaseController {

	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private String fileWebPath;

	/**
	 * 静态文件路径
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/staticFilePath")
	public AndroidiosModel staticFilePath() {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(fileWebPath);
		return androidiosModel;
	}

	/**
	 * 获得学校信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSchoolInfo")
	public AndroidiosModel iosgetSchoolInfo(SysSchool school) {
		List<SysSchool> schoolList = sysSchoolService.findSelective(school);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(schoolList);
		return androidiosModel;
	}

	/**
	 * 获得教室,通过 _schoolid
	 * 
	 * @param schoolid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getClassesInfo")
	public AndroidiosModel getClassesInfo(@RequestParam String schoolid) {
		SysClassroom classRoom = new SysClassroom();
		classRoom.setSchoolId(schoolid);
		classRoom.setBak("Y");
		List<SysClassroom> classRoomList = sysClassroomService.findSelective(classRoom);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(classRoomList);
		return androidiosModel;
	}

	/**
	 * 获得年级
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGradeslist")
	public AndroidiosModel geiunGradeslist() {
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(sysDicts);
		return androidiosModel;
	}

	/**
	 * 根据年级ID获得科目
	 * 
	 * @param gradeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSubjectslist")
	public AndroidiosModel geiunSubjectslist(String gradeId) {
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

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(subjects);
		return androidiosModel;
	}

}
