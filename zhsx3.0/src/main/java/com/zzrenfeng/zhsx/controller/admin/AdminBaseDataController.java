package com.zzrenfeng.zhsx.controller.admin;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.ValidateGroup1;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.base.BaseTeachingBuilding;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.base.BaseTeachingBuildingService;
import com.zzrenfeng.zhsx.service.eclassbrand.classroom.ClassroomProfileService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.service.eclassbrand.social.SocialExam2Service;
import com.zzrenfeng.zhsx.service.eclassbrand.social.SocialExamService;
import com.zzrenfeng.zhsx.util.DownloadUtils;
import com.zzrenfeng.zhsx.util.ExcelUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.ValidationUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 基本数据信息管理
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/adminBaseData")
public class AdminBaseDataController extends BaseController {

	@Resource
	private SysDictService sysDictService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private UserService userService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private LoFscheduleService loFscheduleService;
	@Resource
	private String fileWebPath;
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;
	@Resource
	private Environment environment;
	@Resource
	private BaseTeachingBuildingService baseTeachingBuildingService;
	@Resource
	private CourseScheduleService courseScheduleService;
	@Resource
	private SocialExamService socialExamService;
	@Resource
	private SocialExam2Service socialExam2Service;
	@Resource
	private ClassroomProfileService classroomProfileService;

	/**
	 * 进入区域管理页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/area")
	public String area(HttpServletRequest request, HttpServletResponse response, Model model) {
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("P");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);
		int st = 0;
		String state = request.getParameter("state");
		if (state != null && !state.equals("")) {
			st = Integer.valueOf(state);
		}
		model.addAttribute("state", st);
		return "/admin/base/area";
	}

	/**
	 * 根据省级ID获得市
	 * 
	 * @param request
	 * @param response
	 * @param provinceId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCity")
	public List<SysDict> getCity(HttpServletRequest request, HttpServletResponse response, String provinceId) {
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("C");
		sysDict.setPid(provinceId);

		// 如果登录时走下面代码
		if (isLogined()) {
			String bak1 = getUserBak1();
			String bak2 = getUserBak2();
			if (bak1.equals(User.bak1_city) || bak1.equals(User.bak1_county) || bak1.equals(User.bak1_schoool)) {
				SysDict sysDict2 = sysDictService.findByKey(bak2);
				if (sysDict2 != null) {
					sysDict.setId(sysDict2.getPid());
				}
			}
		}
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		return sysDicts;
	}

	/**
	 * 根据市级ID获得区县
	 * 
	 * @param request
	 * @param response
	 * @param cityId
	 */
	@RequestMapping("/getCounty")
	public @ResponseBody List<SysDict> getCounty(HttpServletRequest request, HttpServletResponse response,
			String cityId) {
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("A");
		sysDict.setPid(cityId);

		/**
		 * 此处算法逻辑有问题. 1、当用户为区县级或者校级管理员时 可能会因 bak2(用户所在区县id)，与
		 * cityId(市级id)不匹配而导致奇怪的错误 如 : cityId 为许昌市id 而 bak2 为 金水区id 2、
		 * 当用户为区县级或者校级管理员时, 是不需要选择 区县信息的
		 * 
		 * 
		 */
		// 如果登录时走下面代码
		// SysDict sysDict2 = null;
		// if (isLogined()) {
		// String bak1 = getUserBak1();
		// String bak2 = getUserBak2();
		// if (bak1.equals(User.bak1_county) || bak1.equals(User.bak1_schoool))
		// {
		// sysDict2 = sysDictService.findByKey(bak2);
		// }
		// }

		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		return sysDicts;
	}

	/**
	 * 获得学校
	 * 
	 * @param request
	 * @param response
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @return
	 */
	@RequestMapping("/getSchool")
	public @ResponseBody List<SysSchool> getSchool(HttpServletRequest request, HttpServletResponse response,
			String provinceId, String cityId, String areaId) {
		SysSchool sysSchool = new SysSchool();
		sysSchool.setProvinceId(provinceId);
		sysSchool.setCityId(cityId);
		sysSchool.setCountyId(areaId);
		List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
		return schoolList;
	}

	/**
	 * 进入添加地区页面
	 * 
	 * @param model
	 * @param keyName
	 * @return
	 */
	@RequestMapping("/addArea")
	public String addArea(Model model, String keyName, String pid) {
		model.addAttribute("keyName", keyName);
		model.addAttribute("pid", pid);
		return "/admin/base/addArea";
	}

	/**
	 * 添加地区信息到数据库
	 * 
	 * @param response
	 * @param sysDict
	 */
	@RequestMapping("/insertArea")
	public void insterArea(HttpServletResponse response, @Validated SysDict sysDict) {
		Date date = new Date();
		sysDict.setCreateTime(date);
		sysDict.setModiyTime(date);
		int res = sysDictService.insert(sysDict);
		if (res > 0) {
			WriterUtils.toText(response, MessageUtils.SUCCESS);
		} else {
			WriterUtils.toText(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 进入编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("/editArea")
	public String editArea(Model model, String id) {
		SysDict sysDict = sysDictService.findByKey(id);
		model.addAttribute("sysDict", sysDict);
		return "/admin/base/editArea";
	}

	/**
	 * 修改地区信息
	 * 
	 * @param response
	 */
	@RequestMapping("/updateArea")
	public void up(HttpServletResponse response, @Validated SysDict sysDict) {
		sysDict.setModiyTime(new Date());
		int res = sysDictService.updateByKeySelective(sysDict);
		if (res > 0) {
			WriterUtils.toText(response, MessageUtils.SUCCESS);
		} else {
			WriterUtils.toText(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 删除地区信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delArea")
	public void delarea(HttpServletRequest request, HttpServletResponse response, String id, String keyName) {
		if (keyName.equals("A")) {
			try {
				sysDictService.deleteByKey(id);
				WriterUtils.toText(response, MessageUtils.SUCCESS);
			} catch (Exception e) {
				WriterUtils.toText(response, MessageUtils.FAilURE);
			}
		}
		if (keyName.equals("C")) {
			try {
				sysDictService.deleteLevel2Data(id);
				WriterUtils.toText(response, MessageUtils.SUCCESS);
			} catch (Exception e) {
				WriterUtils.toText(response, MessageUtils.FAilURE);
			}
		}
		if (keyName.equals("P")) {
			try {
				sysDictService.deleteLevel3Data(id);
				WriterUtils.toText(response, MessageUtils.SUCCESS);
			} catch (Exception e) {
				WriterUtils.toText(response, MessageUtils.FAilURE);
			}
		}
	}

	/**
	 * 进入年级科目页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/gradeSubjects")
	public String gradeSubjects(HttpServletRequest request, HttpServletResponse response, Model model) {
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);
		int st = 0;
		String state = request.getParameter("state");
		if (state != null && !state.equals("")) {
			st = Integer.valueOf(state);
		}
		model.addAttribute("state", st);
		return "/admin/base/gradeSubjects";
	}

	/**
	 * 根据年级ID获得科目
	 * 
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/getSubjects")
	public @ResponseBody List<SysDict> getSubjects(String gradeId) {
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
	 * 进入添加年级科目页面
	 * 
	 * @param model
	 * @param keyName
	 * @param pid
	 * @return
	 */
	@RequestMapping("/addGradeSubject")
	public String addGrade(Model model, String keyName, String pid) {
		model.addAttribute("keyName", keyName);
		model.addAttribute("pid", pid);
		return "/admin/base/addGradeSubject";
	}

	/**
	 * 添加年级科目到数据库
	 * 
	 * @param response
	 * @param sysDict
	 */
	@RequestMapping("/insertGradeSubject")
	public void insertGradeSubject(HttpServletResponse response, @Validated SysDict sysDict) {
		Date date = new Date();
		sysDict.setCreateTime(date);
		sysDict.setModiyTime(date);
		int res = sysDictService.insert(sysDict);
		if (res > 0) {
			WriterUtils.toText(response, MessageUtils.SUCCESS);
		} else {
			WriterUtils.toText(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 进入编辑年级科目页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editGradeSubject")
	public String editGradeSubject(Model model, String id) {
		SysDict sysDict = sysDictService.findByKey(id);
		model.addAttribute("sysDict", sysDict);
		return "/admin/base/editGradeSubject";
	}

	/**
	 * 修改年级科目、版本
	 * 
	 * @param response
	 * @param sysDict
	 */
	@RequestMapping("/updateGradeSubject")
	public void updateGradeSubject(HttpServletResponse response, @Validated SysDict sysDict) {
		sysDict.setModiyTime(new Date());
		int res = sysDictService.updateByKeySelective(sysDict);
		if (res > 0) {
			WriterUtils.toText(response, MessageUtils.SUCCESS);
		} else {
			WriterUtils.toText(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 删除年级科目
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param keyName
	 */
	@RequestMapping("/delGradeSubject")
	public void delGradeSubject(HttpServletRequest request, HttpServletResponse response, String id, String keyName) {
		if (keyName.equals("G")) {
			try {
				sysDictService.deleteLevel2Data(id);
				WriterUtils.toText(response, MessageUtils.SUCCESS);
			} catch (Exception e) {
				WriterUtils.toText(response, MessageUtils.FAilURE);
			}
		}
		if (keyName.equals("S")) {
			try {
				sysDictService.deleteByKey(id);
				WriterUtils.toText(response, MessageUtils.SUCCESS);
			} catch (Exception e) {
				WriterUtils.toText(response, MessageUtils.FAilURE);
			}
		}
	}

	/**
	 * 进入版本管理页面
	 * 
	 * @param request
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping("/version")
	public String version(HttpServletRequest request, Model model, Integer p) {
		if (p == null) {
			p = 1;
		}
		SysDict version = new SysDict();
		version.setKeyname(SysDict.KEYNAME_VERSION);
		Page<SysDict> pageInfo = sysDictService.findPageSelective(version, p, 12);
		List<SysDict> lists = pageInfo.getResult();
		int pages = pageInfo.getPages();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		return "/admin/base/version";
	}

	/**
	 * 进入添加版本页面
	 * 
	 * @return
	 */
	@RequestMapping("/addVersion")
	public String addVersion() {
		return "/admin/base/addVersion";
	}

	/**
	 * 添加版本信息到数据库
	 * 
	 * @param response
	 */
	@RequestMapping("/insertVersion")
	public void insertVersion(HttpServletResponse response, @Validated SysDict sysDict) {
		Date date = new Date();
		sysDict.setCreateTime(date);
		sysDict.setModiyTime(date);
		sysDict.setKeyname(SysDict.KEYNAME_VERSION);
		try {
			sysDictService.insert(sysDict);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 删除版本信息
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delVersion")
	public void delVersion(HttpServletResponse response, String id) {
		try {
			sysDictService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入教材版本编辑页面
	 * 
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/editVersion")
	public String editVersion(Model model, String id, String value, Integer sort) {
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("sort", sort);
		return "/admin/base/editVersion";
	}

	/**
	 * 批量删除版本信息
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("/batchDelVersion")
	public void batchDelVersion(HttpServletResponse response, String[] del_id) {
		List<String> ids = Arrays.asList(del_id);
		try {
			sysDictService.batchDelVersion(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入学校管理页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("sysSchool")
	public String sysSchool(HttpServletRequest request, Model model, SysSchool sysSchool, Integer p) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("search", sysSchool.getSearch());

		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			sysSchool.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (ids != null && ids.size() > 0)
				sysSchool.setIds(ids);
		}
		Page<SysSchool> pageInfo = sysSchoolService.findPageSelective(sysSchool, p, 12);

		List<SysSchool> lists = pageInfo.getResult();
		int pages = pageInfo.getPages();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);

		// 是否为校级平台
		String schoolLevel = environment.getProperty("school.level");
		model.addAttribute("schoolLevel", schoolLevel);

		return "/admin/base/sysSchool";
	}

	/**
	 * 进入添加学校页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/addSchool")
	public String addSchool(Model model) {
		SysDict sysDict = new SysDict();
		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			SysDict sysDict2 = sysDictService.findByKey(bak2);
			if (sysDict2 != null) {
				SysDict sysDict3 = sysDictService.findByKey(sysDict2.getPid());
				if (sysDict3 != null) {
					sysDict.setId(sysDict3.getPid());
				}
			}
		}

		// 获取平台对应地区
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {
			sysDict.setKeyname("P");
		} else if (platformLevel.equals("P")) {
			sysDict.setKeyname("C");
			sysDict.setPid(platformLevelId);
			model.addAttribute("provinceId", platformLevelId);
		} else if (platformLevel.equals("C")) {
			sysDict.setKeyname("A");
			sysDict.setPid(platformLevelId);
			SysDict sysDict2 = sysDictService.findByKey(platformLevelId);
			model.addAttribute("provinceId", sysDict2.getPid());
			model.addAttribute("cityId", platformLevelId);
		} else if (platformLevel.equals("A")) {
			sysDict.setKeyname("T");
			sysDict.setPid(platformLevelId);
			SysDict sysDict2 = sysDictService.findByKey(platformLevelId);
			SysDict sysDict3 = sysDictService.findByKey(sysDict2.getPid());
			model.addAttribute("provinceId", sysDict3.getPid());
			model.addAttribute("cityId", sysDict2.getPid());
			model.addAttribute("countyId", platformLevelId);
		}
		model.addAttribute("platformLevel", platformLevel);

		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);

		return "/admin/base/addSchool";
	}

	/**
	 * 添加新的学校到数据库
	 * 
	 * @param response
	 * @param sysSchool
	 */
	@RequestMapping("/insertSchool")
	public void insertSchool(HttpServletResponse response, @Validated SysSchool sysSchool) {
		Date date = new Date();
		sysSchool.setCreateTime(date);
		sysSchool.setModiyTime(date);
		sysSchool.setIsaf("N");
		try {
			sysSchoolService.insert(sysSchool);
			WriterUtils.toText(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toText(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 删除学校
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delSchool")
	public void delSchool(HttpServletResponse response, String id) {
		SysClassroom sysClassroom = new SysClassroom();
		sysClassroom.setSchoolId(id);
		List<SysClassroom> sList = sysClassroomService.findSelective(sysClassroom);
		if (sList != null && sList.size() > 0) {
			WriterUtils.toText(response, "该学校有教室存在,不可删除!");
		} else {
			try {
				sysSchoolService.deleteByKey(id);
				WriterUtils.toText(response, MessageUtils.SUCCESS);
			} catch (Exception e) {
				WriterUtils.toText(response, MessageUtils.FAilURE);
			}
		}
	}

	/**
	 * 进入编辑学校页面
	 * 
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editSchool")
	public String editSchool(HttpServletResponse response, Model model, String id) {
		SysSchool sysSchool = sysSchoolService.findByKey(id);
		model.addAttribute("sysSchool", sysSchool);

		String bak1 = getUserBak1();
		String bak2 = getUserBak2();

		// 获得省信息
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("P");
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			SysDict sysDict2 = sysDictService.findByKey(bak2);
			if (sysDict2 != null) {
				SysDict sysDict3 = sysDictService.findByKey(sysDict2.getPid());
				if (sysDict3 != null) {
					sysDict.setId(sysDict3.getPid());
				}

			}
		}
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);

		// 获得市级信息
		sysDict.setId(null);
		sysDict.setKeyname("C");
		sysDict.setPid(sysSchool.getProvinceId());
		if (bak1.equals(User.bak1_city) || bak1.equals(User.bak1_county) || bak1.equals(User.bak1_schoool)) {
			SysDict sysDict3 = sysDictService.findByKey(bak2);
			if (sysDict3 != null) {
				sysDict.setId(sysDict3.getPid());
			}
		}
		List<SysDict> citys = sysDictService.findSelective(sysDict);
		model.addAttribute("citys", citys);

		// 获得区县
		sysDict.setId(null);
		sysDict.setKeyname("A");
		sysDict.setPid(sysSchool.getCityId());
		if (bak1.equals(User.bak1_county) || bak1.equals(User.bak1_schoool)) {
			sysDict.setId(bak2);
		}
		List<SysDict> countys = sysDictService.findSelective(sysDict);
		model.addAttribute("countys", countys);

		model.addAttribute("platformLevel", platformLevel);
		model.addAttribute("platformLevelId", platformLevelId);

		return "/admin/base/editSchool";
	}

	/**
	 * 更新学校数据库信息
	 * 
	 * @param response
	 * @param syscSchool
	 */
	@RequestMapping("/updateSchool")
	public void updateSchool(HttpServletResponse response, @Validated SysSchool syscSchool) {
		syscSchool.setModiyTime(new Date());
		try {
			sysSchoolService.updateByKeySelective(syscSchool);
			WriterUtils.toText(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toText(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("/batchDelSchool")
	public void batchDelSchool(HttpServletResponse response, String[] del_id) {
		SysClassroom sysClassroom = new SysClassroom();
		List<String> ids = new ArrayList<String>();
		int j = 0;
		for (int i = 0; i < del_id.length; i++) {
			String id = del_id[i];
			sysClassroom.setSchoolId(id);
			List<SysClassroom> sList = sysClassroomService.findSelective(sysClassroom);
			if (sList != null && sList.size() > 0)
				j++;
			else
				ids.add(id);
		}
		if (ids != null && ids.size() > 0) {
			try {
				sysSchoolService.delBatchSchool(ids);
				if (j > 0) {
					WriterUtils.toText(response, j + "所学校有教室存在,不可删除");
				} else {
					WriterUtils.toText(response, MessageUtils.SUCCESS);
				}
			} catch (Exception e) {
				WriterUtils.toText(response, MessageUtils.FAilURE);
				e.printStackTrace();
			}
		} else {
			WriterUtils.toText(response, "所选学校有教室存在,不可删除");
		}
	}

	/**
	 * 下载批量添加模板
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/downloadTem")
	public void downloadSchoolTem(HttpServletRequest request, HttpServletResponse response, String filename)
			throws UnsupportedEncodingException {
		String filepath = fileWebPath + "/template/" + filename;
		DownloadUtils.downloadInternet(response, request, filepath, filename);
	}

	/**
	 * 进入批量添加学校页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/batchAddSchool")
	public String batchAddSchool(Model model) {
		SysDict sysDict = new SysDict();

		// 区域管理员筛选
		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			SysDict sysDict2 = sysDictService.findByKey(bak2);
			if (sysDict2 != null) {
				SysDict sysDict3 = sysDictService.findByKey(sysDict2.getPid());
				if (sysDict3 != null) {
					sysDict.setId(sysDict3.getPid());
				}

			}
		}

		// 平台级别筛选
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {
			sysDict.setKeyname("P");
		} else if (platformLevel.equals("P")) {
			sysDict.setKeyname("C");
			sysDict.setPid(platformLevelId);
			model.addAttribute("provinceId", platformLevelId);
		} else if (platformLevel.equals("C")) {
			sysDict.setKeyname("A");
			sysDict.setPid(platformLevelId);
			SysDict sysDict2 = sysDictService.findByKey(platformLevelId);
			model.addAttribute("provinceId", sysDict2.getPid());
			model.addAttribute("cityId", platformLevelId);
		} else if (platformLevel.equals("A")) {
			sysDict.setKeyname("T");
			sysDict.setPid(platformLevelId);
			SysDict sysDict2 = sysDictService.findByKey(platformLevelId);
			SysDict sysDict3 = sysDictService.findByKey(sysDict2.getPid());
			model.addAttribute("provinceId", sysDict3.getPid());
			model.addAttribute("cityId", sysDict2.getPid());
			model.addAttribute("countyId", platformLevelId);
		}
		model.addAttribute("platformLevel", platformLevel);

		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);

		return "/admin/base/batchAddSchool";
	}

	/**
	 * 批量添加学校到数据库
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 * @param school
	 */
	@RequestMapping("/batchInsertSchool")
	public void batchInsertSchool(HttpServletRequest request, HttpServletResponse response, String filePath,
			@Validated(value = { ValidateGroup1.class }) SysSchool school) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		filePath = uploadPath + filePath;
		Map<String, Object> hm = null;
		try {
			hm = ExcelUtil.getDataFromExcel(filePath);
		} catch (Exception e1) {
			WriterUtils.toHtml(response, "execl导入失败");
		}
		if (hm == null) {
			WriterUtils.toHtml(response, "execl导入失败");
			return;
		}
		Sheet sheet = (Sheet) hm.get("sheet");
		Row rowHead = (Row) hm.get("rowHead");
		Integer totalRowNum = (Integer) hm.get("totalRowNum");
		// 判断表头是否正确
		if (rowHead.getPhysicalNumberOfCells() != 2) {
			WriterUtils.toHtml(response, "表头的数量不对,请下载导入模板");
			return;
		} else {
			String provinceId = school.getProvinceId();
			String cityId = school.getCityId();
			String countyId = school.getCountyId();
			Date date = new Date();
			String schoolName = null;
			String address = null;
			String j = "";
			List<SysSchool> sList = new ArrayList<SysSchool>();
			for (int i = 1; i <= totalRowNum; i++) {
				SysSchool sysSchool = new SysSchool();
				sysSchool.setProvinceId(provinceId);
				sysSchool.setCityId(cityId);
				sysSchool.setCountyId(countyId);
				sysSchool.setCreateTime(date);
				sysSchool.setModiyTime(date);
				sysSchool.setIsaf("N");

				// 获得第i行对象
				Row row = sheet.getRow(i);

				// 获得获得第i行第0列的 String类型对象
				Cell cell = row.getCell((short) 0);
				schoolName = ExcelUtil.formatCell(cell);
				if (schoolName != null && !schoolName.equals("")) {
					sysSchool.setSchoolName(schoolName);
				} else {
					j += (i + 1) + ",";
					continue;
				}

				boolean res = validationSchool(response, schoolName);
				if (!res) {
					j += (i + 1) + ",";
					continue;
				}

				cell = row.getCell((short) 1);
				address = ExcelUtil.formatCell(cell);
				if (address != null && !address.equals("")) {
					sysSchool.setAddress(address);
				} else {
					j += (i + 1) + ",";
					continue;
				}
				sList.add(sysSchool);
			}
			try {
				if (sList != null && sList.size() > 0) {
					sysSchoolService.insertBatch(sList);
					if (j.equals(""))
						WriterUtils.toHtml(response, MessageUtils.SUCCESS);
					else
						WriterUtils.toHtml(response, "第" + j + "行的学校名称为空或已存在,未能添加！");
				} else {
					WriterUtils.toHtml(response, MessageUtils.FAilURE + ",可能原因所有学校名称都为空或已存在！");
				}
				File f = new File(filePath);
				if (f.exists())
					f.delete();
			} catch (Exception e) {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 进入教室管理页面
	 * 
	 * @param request
	 * @param model
	 * @param classroom
	 * @param p
	 * @return
	 */
	@RequestMapping("/classRoom")
	public String classRoom(HttpServletRequest request, Model model, SysClassroom classroom, Integer p) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);
		model.addAttribute("search", classroom.getSearch());

		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			classroom.setAuthority(bak1);
			List<String> schoolIds = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (schoolIds != null && schoolIds.size() > 0)
				classroom.setSchoolIds(schoolIds);
		}

		Page<SysClassroom> pageInfo = sysClassroomService.findPageSelective(classroom, p, 12);
		int pages = pageInfo.getPages(); // 总页数
		List<SysClassroom> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);

		// 获取是否为校级平台
		String schoollevel = environment.getProperty("school.level");
		model.addAttribute("schoollevel", schoollevel);
		return "/admin/base/classRoom";
	}

	/**
	 * 进入添加教室页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/addclassRoom")
	public String addclassRoom(Model model) {

		// 获取学校
		SysSchool school = new SysSchool();
		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			school.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (ids != null && ids.size() > 0)
				school.setIds(ids);
		}
		List<SysSchool> schools = sysSchoolService.findSelective(school);
		model.addAttribute("schools", schools);

		// 获取教学楼
		List<BaseTeachingBuilding> listBuilding = baseTeachingBuildingService.findAll();
		model.addAttribute("listBuilding", listBuilding);

		// 获取是否为校级平台
		String schoollevel = environment.getProperty("school.level");
		String schoolId = environment.getProperty("school.id");
		model.addAttribute("schoollevel", schoollevel);
		model.addAttribute("schoolId", schoolId);
		return "/admin/base/addclassRoom";
	}

	/**
	 * 验证统一服务器下教室编号是否唯一
	 * 
	 * @param classCode
	 * @return
	 */
	@RequestMapping("/checkClassCode")
	public @ResponseBody boolean checkClassCode(String classCode, String serviceIp) {
		boolean res = true;
		SysClassroom classroom = new SysClassroom();
		classroom.setClassCode(classCode);
		classroom.setServiceIp(serviceIp);
		List<SysClassroom> sList = sysClassroomService.findSelective(classroom);
		if (sList != null && sList.size() > 0)
			res = false;
		return res;
	}

	/**
	 * 添加教室信息到数据库
	 * 
	 * @param response
	 * @param classroom
	 */
	@RequestMapping("/insertClassRoom")
	public void insertClassRoom(HttpServletResponse response, @Validated SysClassroom classroom) {
		try {
			classroom.setBak("Y");
			sysClassroomService.insert(classroom);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 删除教室
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delClassRoom")
	public void delClassRoom(HttpServletResponse response, String id) {
		try {
			sysClassroomService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入编辑页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editClassRoom")
	public String editClassRoom(Model model, String id) {
		SysClassroom classroom = sysClassroomService.findByKey(id);
		model.addAttribute("classroom", classroom);

		// 获得学校
		SysSchool school = new SysSchool();
		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			school.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (ids != null && ids.size() > 0)
				school.setIds(ids);
		}
		List<SysSchool> schools = sysSchoolService.findSelective(school);
		model.addAttribute("schools", schools);

		// 获取教学楼
		List<BaseTeachingBuilding> listBuilding = baseTeachingBuildingService.findAll();
		model.addAttribute("listBuilding", listBuilding);

		// 获取是否为校级平台
		String schoollevel = environment.getProperty("school.level");
		String schoolId = environment.getProperty("school.id");
		model.addAttribute("schoollevel", schoollevel);
		model.addAttribute("schoolId", schoolId);
		return "/admin/base/editClassRoom";
	}

	/**
	 * 更新数据库教室信息
	 * 
	 * @param response
	 * @param sysClassroom
	 */
	@RequestMapping("/updateClassRoom")
	public void updateClassRoom(HttpServletResponse response, @Validated SysClassroom sysClassroom) {
		try {
			sysClassroomService.updateByKeySelective(sysClassroom);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
		// 同步修改上课课表教室名称
		String classroomId = sysClassroom.getId();
		String classroomName = sysClassroom.getClassName();
		courseScheduleService.updateClassroomNameByClassroomId(classroomId, classroomName);
		socialExamService.updateClassroomName(classroomId, classroomName);
		classroomProfileService.updateClassroomName(classroomId, classroomName);
		socialExam2Service.updateClassroomName(classroomId, classroomName);
	}

	/**
	 * 批量删除教室
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("/batchDelClassRoom")
	public void batchDelClassRoom(HttpServletResponse response, String[] del_id) {
		List<String> ids = Arrays.asList(del_id);
		try {
			sysClassroomService.delbatchClassRoom(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 批量修改教室状态
	 * 
	 * @param response
	 * @param del_id
	 * @param bak
	 */
	@RequestMapping("/batchUpdateState")
	public void batchUpdateState(HttpServletResponse response, String[] del_id, String bak) {
		List<String> ids = Arrays.asList(del_id);
		try {
			sysClassroomService.batchUpdateState(ids, bak);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入批量添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/batchAddClassRoom")
	public String batchAddClassRoom(Model model) {
		return "/admin/base/batchAddClassRoom";
	}

	/**
	 * 批量添加教室到数据库
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 * @param classroom
	 */
	@RequestMapping("/batchInsertClassRoom")
	public void batchInsertClassRoom(HttpServletRequest request, HttpServletResponse response, String filePath) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		filePath = uploadPath + filePath;
		Map<String, Object> hm = null;
		try {
			hm = ExcelUtil.getDataFromExcel(filePath);
		} catch (Exception e1) {
			WriterUtils.toHtml(response, "execl导入失败");
		}
		if (hm == null) {
			WriterUtils.toHtml(response, "execl导入失败");
			return;
		}
		Sheet sheet = (Sheet) hm.get("sheet");
		Row rowHead = (Row) hm.get("rowHead");
		Integer totalRowNum = (Integer) hm.get("totalRowNum");
		// 判断表头是否正确
		if (rowHead.getPhysicalNumberOfCells() != 11) {
			WriterUtils.toHtml(response, "表头的数量不对,请下载导入模板");
		} else {
			String className = "";
			String classCode = "";
			String serviceIp = "";
			String webPort = "";
			String roomId = "";
			String uid = "";
			String clientIp = "";
			String schoolName = "";
			String teachingBuildingName = "";
			String bak2 = "";
			String studentUrl = "";

			List<SysClassroom> sList = new ArrayList<SysClassroom>();
			StringBuilder errorMessage = new StringBuilder();
			StringBuilder classCodes = new StringBuilder();
			StringBuilder ipClassCodes = new StringBuilder();
			SysClassroom classroom = null;
			SysSchool school = null;
			BaseTeachingBuilding build = null;
			for (int i = 1; i <= totalRowNum; i++) {
				classroom = new SysClassroom();
				classroom.setBak("Y");
				// 获得第i行对象
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}

				// 获得获得第i行第0列的 String类型对象
				Cell cell = row.getCell((short) 0);
				className = ExcelUtil.formatCell(cell);
				if (className == null || className.equals("")) {
					errorMessage.append("第" + (i + 1) + "行的教室名称不能为空<br>");
					continue;
				}
				classroom.setClassName(className);

				cell = row.getCell((short) 1);
				classCode = ExcelUtil.formatCell(cell);
				if (classCode == null || classCode.equals("")) {
					errorMessage.append("第" + (i + 1) + "行的教室编号不能为空<br>");
					continue;
				}
				if (!ValidationUtils.isInteger(classCode)) {
					errorMessage.append("第" + (i + 1) + "行的教室编号必须为数字<br>");
					continue;
				}
				classCodes.append(classCode + ",");
				classroom.setClassCode(classCode);

				cell = row.getCell((short) 2);
				serviceIp = ExcelUtil.formatCell(cell);
				if (serviceIp != null && !serviceIp.equals("")) {
					classroom.setServiceIp(serviceIp);
				} else {
					errorMessage.append("第" + (i + 1) + "行的直播服务器ip:port不能为空<br>");
					continue;
				}
				if (ipClassCodes.indexOf(serviceIp + classCode) != -1) {
					errorMessage.append("第" + (i + 1) + "行" + serviceIp + "直播服务器下的" + classCode + "教室编号与其他行重复<br>");
					continue;
				}
				ipClassCodes.append(serviceIp + classCode + ",");

				if (!checkClassCode(classCode, serviceIp)) {
					errorMessage.append("第" + (i + 1) + "行" + serviceIp + "直播服务器下的" + classCode + "教室编号已存在<br>");
					continue;
				}

				cell = row.getCell((short) 3);
				webPort = ExcelUtil.formatCell(cell);
				if (webPort != null && !webPort.equals("")) {
					classroom.setWebPort(webPort);
				} else {
					errorMessage.append("第" + (i + 1) + "行的web端口不能为空<br>");
					continue;
				}

				cell = row.getCell((short) 4);
				roomId = ExcelUtil.formatCell(cell);
				if (roomId != null && !roomId.equals("")) {
					classroom.setRoomId(roomId);
				} else {
					errorMessage.append("第" + (i + 1) + "行的教学编号不能为空<br>");
					continue;
				}

				cell = row.getCell((short) 5);
				uid = ExcelUtil.formatCell(cell);
				if (uid != null && !uid.equals("")) {
					classroom.setUid(uid);
				} else {
					errorMessage.append("第" + (i + 1) + "行的用户编号不能为空<br>");
					continue;
				}

				cell = row.getCell((short) 6);
				clientIp = ExcelUtil.formatCell(cell);
				classroom.setClientIp(clientIp);

				cell = row.getCell((short) 7);
				schoolName = ExcelUtil.formatCell(cell);
				if (schoolName == null || schoolName.equals("")) {
					errorMessage.append("第" + (i + 1) + "行的学校名称不能为空<br>");
					continue;
				}

				school = new SysSchool();
				school.setSchoolName(schoolName);
				List<SysSchool> schools = sysSchoolService.findSelective(school);
				if (schools == null || schools.size() <= 0) {
					errorMessage.append("第" + (i + 1) + "行的学校不存在<br>");
					continue;
				}
				classroom.setSchoolId(schools.get(0).getId());

				cell = row.getCell((short) 8);
				teachingBuildingName = ExcelUtil.formatCell(cell);
				if (teachingBuildingName == null || teachingBuildingName.equals("")) {
					errorMessage.append("第" + (i + 1) + "行的教学楼名称不能为空<br>");
					continue;
				}
				build = new BaseTeachingBuilding();
				build.setBuildName(teachingBuildingName);
				List<BaseTeachingBuilding> listBuild = baseTeachingBuildingService.findSelective(build);
				if (listBuild == null || listBuild.size() <= 0) {
					errorMessage.append("第" + (i + 1) + "行的教学楼不存在<br>");
					continue;
				}
				classroom.setTeachingBuildingId(listBuild.get(0).getId());
				classroom.setTeachingBuildingName(teachingBuildingName);

				cell = row.getCell((short) 9);
				bak2 = ExcelUtil.formatCell(cell);
				classroom.setBak2(bak2);

				cell = row.getCell((short) 10);
				studentUrl = ExcelUtil.formatCell(cell);
				classroom.setStudentUrl(studentUrl);

				sList.add(classroom);
			}
			try {
				if (sList == null || sList.size() <= 0) {
					WriterUtils.toHtml(response, MessageUtils.FAilURE + ",可能原因所有行的数据都不符合条件！");
					return;
				}

				sysClassroomService.insertBatch(sList);
				String errorMessageString = errorMessage.toString();
				if (errorMessageString == null || "".equals(errorMessageString)) {
					WriterUtils.toHtml(response, MessageUtils.SUCCESS);
				} else {
					WriterUtils.toHtml(response, errorMessageString);
				}
			} catch (Exception e) {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
				e.printStackTrace();
			} finally {
				File f = new File(filePath);
				if (f.exists())
					f.delete();
			}

		}
	}

	/**
	 * 验证地区(sysDict)是否已存在
	 */
	@RequestMapping("/validationArea")
	public void validationArea(HttpServletResponse response, String value, String keyName, String pid) {
		SysDict sysDict = new SysDict();
		sysDict.setKeyname(keyName);
		sysDict.setPid(pid);
		sysDict.setValue(value);
		List<SysDict> s = sysDictService.findSelective(sysDict);
		if (s != null && s.size() > 0)
			WriterUtils.toHtml(response, "S");
		else
			WriterUtils.toHtml(response, "F");
	}

	/**
	 * 验证学校是否已存在
	 * 
	 * @param response
	 * @param schoolName
	 */
	@RequestMapping("/validationSchool")
	public @ResponseBody boolean validationSchool(HttpServletResponse response, String schoolName) {
		boolean res = true;
		SysSchool school = new SysSchool();
		school.setSchoolName(schoolName);
		List<SysSchool> schools = sysSchoolService.findSelective(school);
		if (schools != null && schools.size() > 0)
			res = false;
		return res;
	}

	/**
	 * 验证ip、会议编号、用户编号
	 * 
	 * @param response
	 * @param serviceIp
	 * @param roomid
	 * @param uid
	 * @return
	 */
	@RequestMapping("/checkIpRoomUid")
	public @ResponseBody boolean checkIpRoomUid(HttpServletResponse response, String serviceIp, String roomid,
			String uid) {
		boolean res = true;
		SysClassroom classroom = new SysClassroom();
		classroom.setServiceIp(serviceIp);
		classroom.setRoomId(roomid);
		classroom.setUid(uid);
		List<SysClassroom> classrooms = sysClassroomService.findSelective(classroom);
		if (classrooms != null && classrooms.size() > 0)
			res = false;
		return res;
	}

	@RequestMapping("/updateisaf")
	public void updateisaf(HttpServletResponse response, SysSchool school) {
		try {
			sysSchoolService.updateByKeySelective(school);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}
}
