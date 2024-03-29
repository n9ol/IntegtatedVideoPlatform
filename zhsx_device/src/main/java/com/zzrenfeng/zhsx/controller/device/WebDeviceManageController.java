package com.zzrenfeng.zhsx.controller.device;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.ExceptionMessage;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WebDeviceManage;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebDeviceManageService;
import com.zzrenfeng.zhsx.service.WebDeviceRecordService;
import com.zzrenfeng.zhsx.util.CommonConfigUtil;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * 设备管理
 * 
 * @copyright {@link zzrenfeng.com}
 * @author David
 * @version 2017-08-08 14:24:22
 * @see com.zzrenfeng.zhsx.controller.WebDeviceManage
 */
@Controller
@RequestMapping(value = "/webdevicemanage")
public class WebDeviceManageController extends BaseController {

	@Resource
	private WebDeviceManageService webDeviceManageService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private UserService userService;
	@Resource
	private WebDeviceRecordService webDeviceRecordService;
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model, Integer p) throws ExceptionMessage {
		// 学生角色没有查看权限
		if (getUserType().equals(User.userType_students)) {
			throw new ExceptionMessage("权限不足");
		}

		/* 总的统计 */
		Map<String, Object> totalMap = webDeviceManageService.findTotal();
		model.addAttribute("totalMap", totalMap);

		SysDict sysDicttemp = new SysDict();
		SysDict sysDicttemc = new SysDict();
		SysDict sysDicttema = new SysDict();

		/* 获取条件 */
		Map<String, Object> paramMap = new TreeMap<String, Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if (endTime == null) {
			endTime = DateUtil.getNextDay(0, "yyyy-MM-dd");// 获取当前系统前0天
		}
		if (startTime == null) {
			startTime = DateUtil.getNextDay(-90, "yyyy-MM-dd");// 获取当前系统前90天的时间
		}
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		model.addAttribute("startTimeAgu", startTime);
		model.addAttribute("endTimeAgu", endTime);

		model.addAttribute("platformLevel", platformLevel);// 平台级别
		/*
		 * TODO 地区信息备注： 1.国家级别 （1）初次查询显示所有的省、市、县。
		 * （2）当用户在前台页面筛选好条件，并点击“搜索”后；就再查询所有省份，并在前台选择用户搜索时选择的省份信息；
		 * 根据用户选择的省份来查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 * 根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 2.省级 （1）
		 * 初次查询显示指定的省下的市和该省下的所有县； （2） 当用户筛选好条件后，并点击“搜索”后；
		 * 查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 * 根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 3.市级 （1） 初次查询显示指定的市下的所有县；
		 * （2） 查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 4.县级 （都不显示） （1）
		 */
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {// 当是国家级别时
			String province = request.getParameter("province");
			if (province != null && (!StringUtils.isEmpty(province))) {
				paramMap.put("province", province);

				model.addAttribute("provinceAgu", province);
				sysDicttemc.setPid(province);
			}
			String city = request.getParameter("city");
			if (city != null && (!StringUtils.isEmpty(city))) {
				paramMap.put("city", city);

				model.addAttribute("cityAgu", city);
				sysDicttema.setPid(city);
			}
			String area = request.getParameter("area");
			if (area != null && (!StringUtils.isEmpty(area))) {
				paramMap.put("area", area);
				model.addAttribute("areaAgu", area);
			}

			/* 查询所有 省 、地区、 县 */
			sysDicttemp.setKeyname(SysDict.KEYNAME_PROVINCE);
			List<SysDict> provinceList = sysDictService.findSelective(sysDicttemp);
			model.addAttribute("provinceList", provinceList);

			sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemc);
			model.addAttribute("cityList", cityList);

			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findSelective(sysDicttema);
			model.addAttribute("areaList", areaList);

		} else if (platformLevel.equals("P")) {// platformLevelId 是省分的id
			String city = request.getParameter("city");
			if (city != null && (!StringUtils.isEmpty(city))) {
				paramMap.put("city", city);

				model.addAttribute("cityAgu", city);
				sysDicttema.setPid(city);
			}
			String area = request.getParameter("area");
			if (area != null && (!StringUtils.isEmpty(area))) {
				paramMap.put("area", area);
				model.addAttribute("areaAgu", area);
			}

			// 该省省份下面所有的市
			sysDicttemc.setPid(platformLevelId);
			sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemc);
			model.addAttribute("cityList", cityList);

			// 查询省份下面的所有县
			sysDicttema.setId(platformLevelId);// 此时仅仅是为了传递数据方便
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findAreaByProvince(sysDicttema);
			model.addAttribute("areaList", areaList);

		} else if (platformLevel.equals("C")) {
			String area = request.getParameter("area");
			if (area != null && (!StringUtils.isEmpty(area))) {
				paramMap.put("area", area);
				model.addAttribute("areaAgu", area);
			}

			sysDicttema.setPid(platformLevelId);
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findSelective(sysDicttema);
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("A")) {// 暂时不做处理

			model.addAttribute("platformLevelId", platformLevelId);
		}

		model.addAttribute("paramMap", paramMap);

		/* 每天设备上线数量 */
		List<Map<String, Object>> onlineDeviceEveryDayCount = webDeviceManageService.onlineDeviceEveryday(paramMap);
		Map<String, Object> resultMap1 = getDataMap(onlineDeviceEveryDayCount, startTime, endTime);
		model.addAttribute("resultMap1", resultMap1);

		// 在线设备数量(当前时刻)
		Map<String, Object> totalOnlineCountMap = webDeviceManageService.totalOnlineDeviceCount(paramMap);
		model.addAttribute("totalOnlineCountMap", totalOnlineCountMap);

		// 班班通使用率排行榜
		List<Map<String, Object>> usageRateRank = webDeviceManageService.usageRateRank(paramMap);
		model.addAttribute("usageRateRank", usageRateRank);

		// 设备维修数量变化曲线 --按月统计
		List<Map<String, Object>> repairDeviceCount = webDeviceManageService.repairDeviceCount(paramMap);
		Map<String, Object> resultMap3 = getDataMapByMonth(repairDeviceCount, startTime, endTime);
		model.addAttribute("resultMap3", resultMap3);

		// 每月接入班班通数量 -- 按月统计
		List<Map<String, Object>> everyDayAccessCount = webDeviceManageService.everyDayAccessCount(paramMap);
		Map<String, Object> resultMap2 = getDataMapByMonth(everyDayAccessCount, startTime, endTime);
		model.addAttribute("resultMap2", resultMap2);

		return "/web/device/index";
	}

	/**
	 * 转换类型<这个阶段时间每天>
	 * 
	 * @param onlineDeviceEveryDayCount
	 *            传入的List<Map<String,Object>>数据
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Map<String, Object> getDataMap(List<Map<String, Object>> onlineDeviceEveryDayCount, String startTime,
			String endTime) {
		Map<String, Object> resultMap = new TreeMap<String, Object>();
		String tempTime = "";
		try {
			int days = DateUtil.getdaysBetween(startTime, endTime);
			if (onlineDeviceEveryDayCount == null) {
				for (int i = days; i >= 0; i--) {
					tempTime = DateUtil.getNextDay(-i, "yyyy-MM-dd");
					resultMap.put(tempTime, 0);
				}
			} else {
				for (int i = days; i >= 0; i--) {
					tempTime = DateUtil.getNextDay(-i, "yyyy-MM-dd");

					boolean flag = true;
					// 循环完了只能比较一个日期值
					for (int j = 0; j < onlineDeviceEveryDayCount.size() && flag == true; j++) {
						Map<String, Object> tempM = onlineDeviceEveryDayCount.get(j);
						// 如果tempM的dates等于tempTime 则放到resultMap1中，否则是0;
						// tempM中有两个值 {dates=2017-09-09, counts=1}
						Object dates = tempM.get("dates");
						Object counts = tempM.get("counts");

						if (tempTime.equalsIgnoreCase(dates.toString())) {
							flag = false;
							resultMap.put(tempTime, counts);
							break;
						}

						// 两个字符串日期比较大小 是优化项目的查询
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date startDate = sdf.parse(tempTime);
						Date endDate = sdf.parse(dates.toString());
						Calendar start = Calendar.getInstance();
						Calendar end = Calendar.getInstance();
						start.setTime(startDate);
						end.setTime(endDate);
						/*
						 * start < end 返回-1 start = end 返回0 start > end 返回1
						 */
						int count = start.compareTo(end);
						if (count == -1) {
							break;
						}
					}
					// 与所有的值都比较过了，还没有相等的，就只能设定为0了
					if (flag == true) {
						resultMap.put(tempTime, 0);
					}
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * 转换类型<这个阶段时间每个月>
	 * 
	 * @param onlineDeviceEveryDayCount
	 *            传入的List<Map<String,Object>>数据
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Map<String, Object> getDataMapByMonth(List<Map<String, Object>> onlineDeviceEveryDayCount, String startTime,
			String endTime) {
		Map<String, Object> resultMap = new TreeMap<String, Object>();
		String tempTime = "";
		try {
			// 月份
			int months = DateUtil.getMonthsBetween(startTime, endTime);
			if (onlineDeviceEveryDayCount == null) {
				for (int i = months; i >= 0; i--) {
					tempTime = DateUtil.getNextMonth(-i, "yyyy-MM");
					resultMap.put(tempTime, 0);
				}
			} else {
				for (int i = months; i >= 0; i--) {
					tempTime = DateUtil.getNextMonth(-i, "yyyy-MM");

					boolean flag = true;
					// 循环完了只能比较一个日期值
					for (int j = 0; j < onlineDeviceEveryDayCount.size() && flag == true; j++) {
						Map<String, Object> tempM = onlineDeviceEveryDayCount.get(j);
						// 如果tempM的dates等于tempTime 则放到resultMap1中，否则是0;
						// tempM中有两个值 {dates=2017-09-09, counts=1}
						Object dates = tempM.get("dates");
						Object counts = tempM.get("counts");

						if (tempTime.equalsIgnoreCase(dates.toString())) {
							flag = false;
							resultMap.put(tempTime, counts);
							break;
						}

						// 两个字符串日期比较大小 是优化项目的查询
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
						Date startDate = sdf.parse(tempTime);
						Date endDate = sdf.parse(dates.toString());
						Calendar start = Calendar.getInstance();
						Calendar end = Calendar.getInstance();
						start.setTime(startDate);
						end.setTime(endDate);
						/*
						 * start < end 返回-1 start = end 返回0 start > end 返回1
						 */
						int count = start.compareTo(end);
						if (count == -1) {
							break;
						}
					}
					// 与所有的值都比较过了，还没有相等的，就只能设定为0了
					if (flag == true) {
						resultMap.put(tempTime, 0);
					}
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	@RequestMapping("/listDMBycontion")
	public String listDeviceManageBycontion(HttpServletRequest request, Model model, Integer p) {
		// 查询所有设备时首先执行
		checkOnlineDevice();

		if (p == null)
			p = 1;

		WebDeviceManage dm = new WebDeviceManage();
		SysDict sysDicttemp = new SysDict();
		SysDict sysDicttemc = new SysDict();
		SysDict sysDicttema = new SysDict();

		SysSchool sysSchool = new SysSchool();
		/* 首页功能***开始 ****/
		String schoolId = request.getParameter("schoolId");
		String deviceCode = request.getParameter("deviceCode");

		if ((schoolId != null) && (!StringUtils.isEmpty(schoolId))) {
			model.addAttribute("schoolIdAgu", schoolId);
			dm.setSchool_id(schoolId);
		} /*
			 * else{//因为schoolid=0 在数据库中不存在 model.addAttribute("schoolIdAgu",0);
			 * }
			 */
		if ((deviceCode != null) && (!StringUtils.isEmpty(deviceCode))) {
			model.addAttribute("deviceCodeAgu", deviceCode);
			dm.setDevice_code(deviceCode);
		}
		/* 首页功能***结束 ****/

		model.addAttribute("platformLevel", platformLevel);// 平台级别
		/*
		 * TODO 地区信息备注： 1.国家级别 （1）初次查询显示所有的省、市、县。
		 * （2）当用户在前台页面筛选好条件，并点击“搜索”后；就再查询所有省份，并在前台选择用户搜索时选择的省份信息；
		 * 根据用户选择的省份来查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 * 根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 2.省级 （1）
		 * 初次查询显示指定的省下的市和该省下的所有县； （2） 当用户筛选好条件后，并点击“搜索”后；
		 * 查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 * 根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 3.市级 （1） 初次查询显示指定的市下的所有县；
		 * （2） 查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 4.县级 （都不显示） （1）
		 */
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {// 当是国家级别时

			String province = request.getParameter("province");
			if ((province != null) && (!StringUtils.isEmpty(province))) {
				model.addAttribute("provinceAgu", province);
				dm.setDevice_province(province);
				sysDicttemc.setPid(province);
			}

			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				model.addAttribute("cityAgu", city);
				dm.setDevice_city(city);
				sysDicttema.setPid(city);
			}

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				dm.setDevice_area(area);

				sysSchool.setCountyId(area);
			}

			/* 查询所有 省 、地区、 县 */
			sysDicttemp.setKeyname(SysDict.KEYNAME_PROVINCE);
			List<SysDict> provinceList = sysDictService.findSelective(sysDicttemp);
			model.addAttribute("provinceList", provinceList);

			sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemc);
			model.addAttribute("cityList", cityList);

			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findSelective(sysDicttema);
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("P")) {// platformLevelId 是省分的id
			dm.setDevice_province(platformLevelId); // 查询班班通设备时使用
			sysSchool.setProvinceId(platformLevelId);// 查询省下面所有的学校

			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				model.addAttribute("cityAgu", city);
				dm.setDevice_city(city);
				sysDicttema.setPid(city);
			}

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				dm.setDevice_area(area);
				sysSchool.setCountyId(area);
			}

			// 该省省份下面所有的市
			sysDicttemc.setPid(platformLevelId);
			sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemc);
			model.addAttribute("cityList", cityList);

			// 查询省份下面的所有县
			sysDicttema.setId(platformLevelId);// 此时仅仅是为了传递数据方便
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findAreaByProvince(sysDicttema);
			model.addAttribute("areaList", areaList);

		} else if (platformLevel.equals("C")) {
			dm.setDevice_city(platformLevelId); // 查询班班通设备时使用
			sysSchool.setCityId(platformLevelId);// 查询市下面所有的学校

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				dm.setDevice_area(area);

				sysSchool.setCountyId(area);
			}

			sysDicttema.setPid(platformLevelId);
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findSelective(sysDicttema);
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("A")) {// 暂时不做处理
			dm.setDevice_area(platformLevelId); // 查询班班通设备时使用
			sysSchool.setCountyId(platformLevelId);// 查询区下面所有的学校

			model.addAttribute("platformLevelId", platformLevelId);
		}

		/* 查询学校信息 */
		List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
		model.addAttribute("schoolList", schoolList);

		/*
		 * ShiroUser user= getShiroUser(); model.addAttribute("user", user);
		 */

		Page<WebDeviceManage> pageInfo = webDeviceManageService.findPageSelective(dm, p, 10);// TODO
		int pages = pageInfo.getPages(); // 总页数
		List<WebDeviceManage> dmList = pageInfo.getResult();
		if (dmList != null && dmList.size() > 0) {
			for (int i = 0; i < dmList.size(); i++) {
				SysDict sysDicttempP = sysDictService.findByKey(dmList.get(i).getDevice_province());
				String pValue = "";
				if (sysDicttempP != null) {
					pValue = sysDicttempP.getValue();
				}

				SysDict sysDicttempC = sysDictService.findByKey(dmList.get(i).getDevice_city());
				String cValue = "";
				if (sysDicttempC != null) {
					cValue = sysDicttempC.getValue();
				}

				SysDict sysDicttempA = sysDictService.findByKey(dmList.get(i).getDevice_area());
				String aValue = "";
				if (sysDicttempA != null) {
					aValue = sysDicttempA.getValue();
				}

				String strTemp = pValue + cValue + aValue;
				dmList.get(i).setDevice_province(strTemp);
			}

		}
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("dmList", dmList);

		return "/web/device/device_list";
	}

	@RequestMapping("/listDMData")
	public String listDMData(HttpServletRequest request, Model model, Integer p) {

		return "/web/device/device_data";
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @param flag
	 *            1 查看; 2 编辑;
	 * @return
	 */
	@RequestMapping("findDMById")
	public String findDMById(HttpServletRequest request, Model model, String id, String flag) {
		if (flag == null || id == null) {
			return "/web/error";
		} else {
			WebDeviceManage webDeviceManage = webDeviceManageService.findByKey(id);
			model.addAttribute("dm", webDeviceManage);
			// 地区信息
			/* 查询所有 省 、地区、 县 */
			SysDict sysDicttemp = new SysDict();
			sysDicttemp.setKeyname(SysDict.KEYNAME_PROVINCE);
			List<SysDict> provinceList = sysDictService.findSelective(sysDicttemp);

			sysDicttemp.setKeyname(SysDict.KEYNAME_CITY);
			sysDicttemp.setPid(webDeviceManage.getDevice_province());
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemp);

			sysDicttemp.setKeyname(SysDict.KEYNAME_AREA);
			sysDicttemp.setPid(webDeviceManage.getDevice_city());
			List<SysDict> areaList = sysDictService.findSelective(sysDicttemp);

			model.addAttribute("provinceList", provinceList);
			model.addAttribute("cityList", cityList);
			model.addAttribute("areaList", areaList);

			SysSchool sysSchool = new SysSchool();// 根据地区来查询
			sysSchool.setProvinceId(webDeviceManage.getDevice_province());
			sysSchool.setCityId(webDeviceManage.getDevice_city());
			sysSchool.setCountyId(webDeviceManage.getDevice_area());
			List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
			model.addAttribute("schoolList", schoolList);

			if ("1".equalsIgnoreCase(flag)) {
				return "/web/device/device_view";
			} else {
				return "/web/device/device_edit";
			}
		}
	}

	/**
	 * 更新设备信息
	 * 
	 * @param dm
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deviceupdateDM")
	public String deviceupdateDM(WebDeviceManage dm, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// String state = request.getParameter("state");
		webDeviceManageService.updateByKeySelective(dm);
		return "redirect:/webdevicemanage/listDMBycontion";
	}

	// **************** 在线设备 ******************
	@RequestMapping("/listDMBycontionOnline")
	public String listDeviceManageBycontionOnline(HttpServletRequest request, Model model, Integer p) {
		// 查询所有设备时首先执行
		checkOnlineDevice();

		if (p == null)
			p = 1;
		WebDeviceManage dm = new WebDeviceManage();
		SysDict sysDicttemp = new SysDict();
		SysDict sysDicttemc = new SysDict();
		SysDict sysDicttema = new SysDict();

		SysSchool sysSchool = new SysSchool();
		/* 首页功能***开始 ****/
		String schoolId = request.getParameter("schoolId");
		String deviceCode = request.getParameter("deviceCode");

		if ((schoolId != null) && (!StringUtils.isEmpty(schoolId))) {
			model.addAttribute("schoolIdAgu", schoolId);
			dm.setSchool_id(schoolId);
		} /*
			 * else{//因为schoolid=0 在数据库中不存在 model.addAttribute("schoolIdAgu",0);
			 * }
			 */
		if ((deviceCode != null) && (!StringUtils.isEmpty(deviceCode))) {
			model.addAttribute("deviceCodeAgu", deviceCode);
			dm.setDevice_code(deviceCode);
		}
		/* 首页功能***结束 ****/

		model.addAttribute("platformLevel", platformLevel);// 平台级别
		/*
		 * 地区信息备注： 1.国家级别 （1）初次查询显示所有的省、市、县。
		 * （2）当用户在前台页面筛选好条件，并点击“搜索”后；就再查询所有省份，并在前台选择用户搜索时选择的省份信息；
		 * 根据用户选择的省份来查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 * 根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 2.省级 （1）
		 * 初次查询显示指定的省下的市和该省下的所有县； （2） 当用户筛选好条件后，并点击“搜索”后；
		 * 查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 * 根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 3.市级 （1） 初次查询显示指定的市下的所有县；
		 * （2） 查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息； 4.县级 （都不显示） （1）
		 */
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {// 当是国家级别时

			String province = request.getParameter("province");
			if ((province != null) && (!StringUtils.isEmpty(province))) {
				model.addAttribute("provinceAgu", province);
				dm.setDevice_province(province);
				sysDicttemc.setPid(province);
			}

			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				model.addAttribute("cityAgu", city);
				dm.setDevice_city(city);
				sysDicttema.setPid(city);
			}

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				dm.setDevice_area(area);

				sysSchool.setCountyId(area);
			}

			/* 查询所有 省 、地区、 县 */
			sysDicttemp.setKeyname(SysDict.KEYNAME_PROVINCE);
			List<SysDict> provinceList = sysDictService.findSelective(sysDicttemp);
			model.addAttribute("provinceList", provinceList);

			sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemc);
			model.addAttribute("cityList", cityList);

			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findSelective(sysDicttema);
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("P")) {// platformLevelId 是省分的id
			dm.setDevice_province(platformLevelId); // 查询班班通设备时使用
			sysSchool.setProvinceId(platformLevelId);// 查询省下面所有的学校

			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				model.addAttribute("cityAgu", city);
				dm.setDevice_city(city);
				sysDicttema.setPid(city);
			}

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				dm.setDevice_area(area);
				sysSchool.setCountyId(area);
			}

			// 该省省份下面所有的市
			sysDicttemc.setPid(platformLevelId);
			sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemc);
			model.addAttribute("cityList", cityList);

			// 查询省份下面的所有县
			sysDicttema.setId(platformLevelId);// 此时仅仅是为了传递数据方便
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findAreaByProvince(sysDicttema);
			model.addAttribute("areaList", areaList);

		} else if (platformLevel.equals("C")) {
			dm.setDevice_city(platformLevelId); // 查询班班通设备时使用
			sysSchool.setCityId(platformLevelId);// 查询市下面所有的学校

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				dm.setDevice_area(area);

				sysSchool.setCountyId(area);
			}

			sysDicttema.setPid(platformLevelId);
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findSelective(sysDicttema);
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("A")) {// 暂时不做处理
			dm.setDevice_area(platformLevelId); // 查询班班通设备时使用
			sysSchool.setCountyId(platformLevelId);// 查询区下面所有的学校

			model.addAttribute("platformLevelId", platformLevelId);
		}

		/* 查询学校信息 */
		List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
		model.addAttribute("schoolList", schoolList);
		/*
		 * ShiroUser user= getShiroUser(); model.addAttribute("user", user);
		 */
		dm.setDevice_state(WebDeviceManage.DEVICE_ONLINE_STATE);
		Page<WebDeviceManage> pageInfo = webDeviceManageService.findPageSelective(dm, p, 10);
		int pages = pageInfo.getPages(); // 总页数
		List<WebDeviceManage> dmList = pageInfo.getResult();
		if (dmList != null && dmList.size() > 0) {
			for (int i = 0; i < dmList.size(); i++) {
				SysDict sysDicttempP = sysDictService.findByKey(dmList.get(i).getDevice_province());
				String pValue = "";
				if (sysDicttempP != null) {
					pValue = sysDicttempP.getValue();
				}

				SysDict sysDicttempC = sysDictService.findByKey(dmList.get(i).getDevice_city());
				String cValue = "";
				if (sysDicttempC != null) {
					cValue = sysDicttempC.getValue();
				}

				SysDict sysDicttempA = sysDictService.findByKey(dmList.get(i).getDevice_area());
				String aValue = "";
				if (sysDicttempA != null) {
					aValue = sysDicttempA.getValue();
				}

				String strTemp = pValue + cValue + aValue;
				dmList.get(i).setDevice_province(strTemp);
			}

		}

		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("dmList", dmList);
		return "/web/device/device_listOnline";
	}

	@RequestMapping("/listDMDataOnline")
	public String listDMDataOnline(HttpServletRequest request, Model model, Integer p) {

		return "/web/device/device_dataOnline";
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @param flag
	 *            1 查看; 2 编辑;
	 * @return
	 */
	@RequestMapping("findDMByIdOnline")
	public String findDMByIdOnline(HttpServletRequest request, Model model, String id, String flag) {
		if (flag == null || id == null) {
			return "/web/error";
		} else {
			WebDeviceManage webDeviceManage = webDeviceManageService.findByKey(id);
			model.addAttribute("dm", webDeviceManage);
			// 地区信息
			/* 查询所有 省 、地区、 县 */
			SysDict sysDicttemp = new SysDict();
			sysDicttemp.setKeyname("P");
			List<SysDict> provinceList = sysDictService.findSelective(sysDicttemp);
			sysDicttemp.setKeyname("C");
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemp);
			sysDicttemp.setKeyname("A");
			List<SysDict> areaList = sysDictService.findSelective(sysDicttemp);

			model.addAttribute("provinceList", provinceList);
			model.addAttribute("cityList", cityList);
			model.addAttribute("areaList", areaList);

			SysSchool sysSchool = new SysSchool();
			List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
			model.addAttribute("schoolList", schoolList);

			if ("1".equalsIgnoreCase(flag)) {
				return "/web/device/device_viewOnline";
			} else {
				return "/web/device/device_editOnline";
			}
		}
	}

	/**
	 * 更新设备信息
	 * 
	 * @param dm
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deviceupdateDMOnline")
	public String deviceupdateDMOnline(WebDeviceManage dm, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// String state = request.getParameter("state");
		webDeviceManageService.updateByKeySelective(dm);
		return "redirect:/webdevicemanage/listDMBycontionOnline";
	}

	/**
	 * 无论是任何级别都会有默认值
	 * 
	 * @param province
	 * @param city
	 * @param area
	 * @return
	 */
	public Map<String, Object> getPCA(String province, String city, String area) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {// 当是国家级别时,没有地区id，所有会
			// 查询省、市、县 默认显示河南省，郑州市，金水区
			/*
			 * SysDict sysDict1 = new SysDict(); sysDict1.setValue("河南省");
			 * List<SysDict> sysDict1L = sysDictService.findSelective(sysDict1);
			 * province = sysDict1L.get(0).getId();
			 * 
			 * sysDict1.setValue("郑州市"); List<SysDict> sysDict2L =
			 * sysDictService.findSelective(sysDict1); city =
			 * sysDict2L.get(0).getId();
			 * 
			 * sysDict1.setValue("金水区"); List<SysDict> sysDict3L =
			 * sysDictService.findSelective(sysDict1); area =
			 * sysDict3L.get(0).getId(); province = "410000";//河南省 city =
			 * "410100";//郑州市 area = "410105";//金水区
			 */
			province = CommonConfigUtil.getConf("province");
			city = CommonConfigUtil.getConf("city");
			area = CommonConfigUtil.getConf("area");

		} else if (platformLevel.equals("P")) {
			province = platformLevelId;

			SysDict sysDict1 = new SysDict();
			sysDict1.setPid(province);
			List<SysDict> sysDict2L = sysDictService.findSelective(sysDict1);
			city = sysDict2L.get(0).getId();

			sysDict1.setPid(city);
			List<SysDict> sysDict3L = sysDictService.findSelective(sysDict1);
			area = sysDict3L.get(0).getId();
		} else if (platformLevel.equals("C")) {
			SysDict sysDict1 = new SysDict();

			city = platformLevelId;
			province = sysDictService.findByKey(city).getPid();

			sysDict1.setPid(city);
			List<SysDict> sysDict3L = sysDictService.findSelective(sysDict1);
			area = sysDict3L.get(0).getId();
		} else if (platformLevel.equals("A")) {
			area = platformLevelId;
			city = sysDictService.findByKey(area).getPid();
			province = sysDictService.findByKey(city).getPid();
		}
		reMap.put("province", province);
		reMap.put("city", city);
		reMap.put("area", area);

		return reMap;
	}

	/**
	 * 学校使用统计 （没有用到flag） 1.flag=1 从头部的列表过来的 2.flag=2 从地区筛选的搜索按钮来的
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/schoolUsingCount")
	public String schoolUsingCount(HttpServletRequest request, HttpServletResponse response, Model model, Integer p) {
		// 查询所有设备时首先执行
		checkOnlineDevice();
		if (p == null)// 分页，页码
			p = 1;

		model.addAttribute("platformLevel", platformLevel);// 平台级别

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Date startTimeTemp = new Date();
		Date endTimeTemp = new Date();
		try {
			/* startTime和endTime如果为空就使用默认值 startTime=当前时间-7天 endTime=当前时间 */
			if ((startTime == null) || (endTime == null) || (StringUtils.isEmpty(startTime))
					|| (StringUtils.isEmpty(endTime))) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, -7);
				startTimeTemp = c.getTime();
			} else {/* 将传递过来的数据格式化 */
				startTimeTemp = sdf.parse(startTime);
				endTimeTemp = sdf.parse(endTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 将date转换成string
		model.addAttribute("startTimeAgu", sdf.format(startTimeTemp));
		model.addAttribute("endTimeAgu", sdf.format(endTimeTemp));

		/*
		 * TODO 如果值为空，就给定默认值
		 * 省市县是必须要选择的；没有用到配置文件中的platform.level.id=410622，因为登录人员默认就是此地区的
		 */
		String province = request.getParameter("province");// id
		String city = request.getParameter("city");// id
		String area = request.getParameter("area");// id

		/* 地区如果为空就使用默认值 从登陆人中获得获得默认地区 如果没有数据就不显示 */
		if ((area == null) || (StringUtils.isEmpty(area))) {
			// area = CommonConfigUtil.getConf("areaId");
			area = getUserBak2();
			/*
			 * 加入判断，防止垃圾数据存在 默认从登录用中查找信息 如果登录人信息有误，则使用配置文件中的地区信息
			 */
			SysDict areaObect = sysDictService.findByKey(area);

			if (areaObect != null) {// 不为空
				city = areaObect.getPid();
				SysDict cityObect = sysDictService.findByKey(areaObect.getPid());
				if (cityObect != null) {
					province = cityObect.getPid();
				} else {
					Map<String, Object> tempMap = getPCA(province, city, area);
					province = tempMap.get("province").toString();
					city = tempMap.get("city").toString();
					area = tempMap.get("area").toString();
				}
			} else {// 为空 platformLevelId
				Map<String, Object> tempMap = getPCA(province, city, area);
				province = tempMap.get("province").toString();
				city = tempMap.get("city").toString();
				area = tempMap.get("area").toString();
			}

		}

		/*
		 * if ((city == null) || (StringUtils.isEmpty(city))) { // city =
		 * CommonConfigUtil.getConf("cityId"); area = getUserBak2(); SysDict
		 * areaObect = sysDictService.findByKey(area); city =
		 * areaObect.getPid(); }
		 * 
		 * if ((province == null) || (StringUtils.isEmpty(province))) { //
		 * province = CommonConfigUtil.getConf("provinceId"); area =
		 * getUserBak2(); SysDict areaObect = sysDictService.findByKey(area);
		 * 
		 * SysDict cityObect = sysDictService.findByKey(areaObect.getPid());
		 * province = cityObect.getPid(); }
		 */

		/* 为了查询方便 给之前查询的参数一个默认值 */
		model.addAttribute("provinceAgu", province);
		model.addAttribute("cityAgu", city);
		model.addAttribute("areaAgu", area);
		/* ************ 给定默认地区结束 ************* */

		/* 查询所有 省 、地区、 县 */
		SysDict sysDict = new SysDict();

		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {// 当是国家级别时,没有地区id，所有会

			sysDict.setKeyname(SysDict.KEYNAME_PROVINCE);
			List<SysDict> provinceList = sysDictService.findSelective(sysDict);
			model.addAttribute("provinceList", provinceList);

			List<SysDict> cityList = new ArrayList<SysDict>();
			if ((province != null) && (!StringUtils.isEmpty(province))) {
				sysDict.setKeyname(SysDict.KEYNAME_CITY);
				sysDict.setPid(province);
				cityList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("cityList", cityList);

			List<SysDict> areaList = new ArrayList<SysDict>();
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				sysDict.setKeyname(SysDict.KEYNAME_AREA);
				sysDict.setPid(city);
				areaList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("P")) {// platformLevelId 是省分的id
			province = platformLevelId;
			List<SysDict> cityList = new ArrayList<SysDict>();
			if ((province != null) && (!StringUtils.isEmpty(province))) {
				sysDict.setKeyname(SysDict.KEYNAME_CITY);
				sysDict.setPid(province);
				cityList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("cityList", cityList);

			List<SysDict> areaList = new ArrayList<SysDict>();
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				sysDict.setKeyname(SysDict.KEYNAME_AREA);
				sysDict.setPid(city);
				areaList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("areaList", areaList);

		} else if (platformLevel.equals("C")) {// platformLevelId 是市的id
			city = platformLevelId;

			List<SysDict> areaList = new ArrayList<SysDict>();
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				sysDict.setKeyname(SysDict.KEYNAME_AREA);
				sysDict.setPid(city);
				areaList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("A")) {// platformLevelId 是区县的id
			area = platformLevelId;

			model.addAttribute("platformLevelId", platformLevelId);
		}

		Integer totaldevices = null;
		Integer schooldevices = null;
		Double averageuserate = null;
		Double averageuselength = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("totaldevices", totaldevices);
		paramMap.put("schooldevices", schooldevices);
		paramMap.put("averageuserate", averageuserate);
		paramMap.put("averageuselength", averageuselength);

		paramMap.put("province", province);
		paramMap.put("city", city);
		paramMap.put("area", area);

		paramMap.put("startTime", startTimeTemp);
		paramMap.put("endTime", endTimeTemp);
		/* 总设备统计 */
		Map<String, Object> totalSchoolMap = webDeviceManageService.getTotalSchool(paramMap);
		model.addAttribute("totalSchoolMap", totalSchoolMap);

		Map<String, Object> paramMap2 = new HashMap<String, Object>();

		paramMap2.put("province", province);
		paramMap2.put("city", city);
		paramMap2.put("area", area);

		paramMap2.put("startTime", startTimeTemp);
		paramMap2.put("endTime", endTimeTemp);

		/* 查询所有的学校 */
		SysSchool sysSchool = new SysSchool();
		sysSchool.setProvinceId(province);
		sysSchool.setCityId(city);
		sysSchool.setCountyId(area);
		/* 方便统计其它 而查询 */
		List<SysSchool> otherSysSchoolList = sysSchoolService.findSelective(sysSchool);

		Page<SysSchool> pageInfo = sysSchoolService.findPageSelective(sysSchool, p, 10);
		// TODO 通过控制学校来控制分页
		// 前端的饼状图表显示是根据列表中的数据显示；列表中的数据是根据学校的多少而定；所以核心在学校的上面，使用学校分页可以有效的控制页面的展示；
		int pages = pageInfo.getPages(); // 总页数
		List<SysSchool> schoolList = pageInfo.getResult();
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("schoolList", schoolList);

		List<Map<String, Object>> detailSchoolListMap = webDeviceManageService.getdetailSchool(schoolList, paramMap2);
		model.addAttribute("detailSchoolListMap", detailSchoolListMap);
		if (pages > 1) {
			/* 各个学校设备统计情况 */
			int thisSchoolUsedevices = 0;
			for (Map<String, Object> map : detailSchoolListMap) {// 方便统计其它 而查询
				Object schoolUsedevices = map.get(2);
				if (schoolUsedevices != null && (!schoolUsedevices.toString().equals(0))) {
					thisSchoolUsedevices += Integer.parseInt(schoolUsedevices.toString());
				}
			}

			/* 所有的学校统计情况 */
			int totalSchoolUsedevices = 0;
			List<Map<String, Object>> otherListMap = webDeviceManageService.getdetailSchool(otherSysSchoolList,
					paramMap2);
			for (Map<String, Object> map : otherListMap) {// 方便统计其它 而查询
				Object schoolUsedevices = map.get(2);
				if (schoolUsedevices != null && (!schoolUsedevices.toString().equals(0))) {
					totalSchoolUsedevices += Integer.parseInt(schoolUsedevices.toString());
				}
			}
			model.addAttribute("otherDevice", totalSchoolUsedevices - thisSchoolUsedevices);
		} else {// 如果不存在
			model.addAttribute("otherDevice", "A");
		}

		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		return "/web/device/school_using_count";
	}

	/**
	 * 班级统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/classUsingCount")
	public String classUsingCount(HttpServletRequest request, HttpServletResponse response, Model model, Integer p) {
		// 查询所有设备时首先执行
		checkOnlineDevice();

		if (p == null)
			p = 1;

		model.addAttribute("platformLevel", platformLevel);// 平台级别

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		// String schoolName = request.getParameter("schoolName");
		Date startTimeTemp = new Date();
		Date endTimeTemp = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			/* startTime和endTime如果为空就使用默认值 startTime=当前时间-7天 endTime=当前时间 */
			if ((startTime == null) || (endTime == null) || (StringUtils.isEmpty(startTime))
					|| (StringUtils.isEmpty(endTime))) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, -7);
				startTimeTemp = c.getTime();
			} else {/* 将传递过来的数据格式化 */
				startTimeTemp = sdf.parse(startTime);
				endTimeTemp = sdf.parse(endTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 将date转换成string
		model.addAttribute("startTimeAgu", sdf.format(startTimeTemp));
		model.addAttribute("endTimeAgu", sdf.format(endTimeTemp));

		SysDict sysDict = new SysDict();

		/*
		 * TODO 如果值为空，就给定默认值
		 * 省市县是必须要选择的；没有用到配置文件中的platform.level.id=410622，因为登录人员默认就是此地区的
		 */
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String schoolid = request.getParameter("schoolid");

		/*
		 * 1.首次登陆时，地区和学校都为空（下面是只适合首次登陆） 2.地区如果为空就使用默认值 从登陆人中获得获得默认地区
		 * 3.当用户进到统计页面后，会对学校进行统计查询；正常情况下学校数据不为空 4.如果查询条件为空（学校为空）则无法查询
		 */
		if ((area == null) && (schoolid == null)) {
			// area = CommonConfigUtil.getConf("areaId");
			area = getUserBak2();
			/*
			 * 加入判断，防止垃圾数据存在 默认从登录用中查找信息 如果登录人信息有误，则使用配置文件中的地区信息
			 */
			SysDict areaObect = sysDictService.findByKey(area);

			if (areaObect != null) {// 不为空
				city = areaObect.getPid();
				SysDict cityObect = sysDictService.findByKey(areaObect.getPid());
				if (cityObect != null) {
					province = cityObect.getPid();

					schoolid = getUserSchoolId();// 只有用户信息对的情况下，才会有学校id
				} else {
					Map<String, Object> tempMap = getPCA(province, city, area);
					province = tempMap.get("province").toString();
					city = tempMap.get("city").toString();
					area = tempMap.get("area").toString();

				}
			} else {// 为空 platformLevelId
				Map<String, Object> tempMap = getPCA(province, city, area);
				province = tempMap.get("province").toString();
				city = tempMap.get("city").toString();
				area = tempMap.get("area").toString();
			}

		}

		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {// 当是国家级别时,没有地区id，所有会
			sysDict.setKeyname(SysDict.KEYNAME_PROVINCE);
			List<SysDict> provinceList = sysDictService.findSelective(sysDict);
			model.addAttribute("provinceList", provinceList);

			List<SysDict> cityList = new ArrayList<SysDict>();
			if ((province != null) && (!StringUtils.isEmpty(province))) {
				sysDict.setKeyname(SysDict.KEYNAME_CITY);
				sysDict.setPid(province);
				cityList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("cityList", cityList);

			List<SysDict> areaList = new ArrayList<SysDict>();
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				sysDict.setKeyname(SysDict.KEYNAME_AREA);
				sysDict.setPid(city);
				areaList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("P")) {// platformLevelId 是省分的id
			province = platformLevelId;

			List<SysDict> cityList = new ArrayList<SysDict>();
			if ((province != null) && (!StringUtils.isEmpty(province))) {
				sysDict.setKeyname(SysDict.KEYNAME_CITY);
				sysDict.setPid(province);
				cityList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("cityList", cityList);

			List<SysDict> areaList = new ArrayList<SysDict>();
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				sysDict.setKeyname(SysDict.KEYNAME_AREA);
				sysDict.setPid(city);
				areaList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("C")) {// platformLevelId 是市分的id
			city = platformLevelId;

			List<SysDict> areaList = new ArrayList<SysDict>();
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				sysDict.setKeyname(SysDict.KEYNAME_AREA);
				sysDict.setPid(city);
				areaList = sysDictService.findSelective(sysDict);
			}
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("A")) {// platformLevelId 是县分的id
			area = platformLevelId;
			model.addAttribute("platformLevelId", platformLevelId);
		}

		/* 根据地区查询使用设备的学校数 只用到参数省市县 */
		SysSchool sysSchool = new SysSchool();
		sysSchool.setProvinceId(province);
		sysSchool.setCityId(city);
		sysSchool.setCountyId(area);
		List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
		model.addAttribute("schoolList", schoolList);

		/*
		 * 首次从菜单栏进到页面中，schoolid是空的 当第一次处理，即if ((area == null) && (schoolid ==
		 * null)) 中schoolid还为空时，在这里进行第二次处理
		 */
		if ((schoolid == null) || (StringUtils.isEmpty(schoolid))) {// 为空时，设定一个默认值；首次进来时进到的页面
			if (schoolList != null && schoolList.size() > 0) {
				schoolid = schoolList.get(0).getId();
			}
		}

		/* 为了查询方便 给之前查询的参数一个默认值 */
		model.addAttribute("provinceAgu", province);
		model.addAttribute("cityAgu", city);
		model.addAttribute("areaAgu", area);
		model.addAttribute("schoolidAgu", schoolid);

		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("province", province);
		paramMap1.put("city", city);
		paramMap1.put("area", area);
		paramMap1.put("schoolid", schoolid);
		paramMap1.put("startTime", startTimeTemp);
		paramMap1.put("endTime", endTimeTemp);

		List<SysClassroom> otherSysClassroomList = new ArrayList<SysClassroom>();
		Page<SysClassroom> pageInfo = new Page<SysClassroom>();

		if (schoolid != null) {
			/* 根据学校获取班级情况 */
			SysClassroom sysClassroom = new SysClassroom();
			sysClassroom.setSchoolId(schoolid);
			/* 方便统计其它 而查询 */
			otherSysClassroomList = sysClassroomService.findSelective(sysClassroom);

			pageInfo = sysClassroomService.findPageSelective(sysClassroom, p, 10);
		}

		// TODO 通过控制班级来控制分页 ;前端页面显示，
		int pages = pageInfo.getPages(); // 总页数
		List<SysClassroom> sysClassroomList = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("sysClassroomList", sysClassroomList);

		List<Map<String, Object>> detailClassListMap = webDeviceManageService.getdetailClass(sysClassroomList,
				paramMap1);
		model.addAttribute("detailSchoolListMap", detailClassListMap);

		if (pages > 1) {
			/* 各个学校设备统计情况 */
			int thisClassroomUsedevices = 0;
			for (Map<String, Object> map : detailClassListMap) {// 方便统计其它 而查询
				Object classroomUsedevices = map.get(2);
				if (classroomUsedevices != null && (!classroomUsedevices.toString().equals(0))) {
					thisClassroomUsedevices += Integer.parseInt(classroomUsedevices.toString());
				}
			}

			/* 所有的学校统计情况 */
			int totalClassroomUsedevices = 0;
			List<Map<String, Object>> otherListMap = webDeviceManageService.getdetailClass(otherSysClassroomList,
					paramMap1);
			for (Map<String, Object> map : otherListMap) {// 方便统计其它 而查询
				Object classroomUsedevices = map.get(2);
				if (classroomUsedevices != null && (!classroomUsedevices.toString().equals(0))) {
					totalClassroomUsedevices += Integer.parseInt(classroomUsedevices.toString());
				}
			}
			model.addAttribute("otherDevice", totalClassroomUsedevices - thisClassroomUsedevices);
		} else {// 如果不存在
			model.addAttribute("otherDevice", "A");
		}

		return "/web/device/class_using_info";
	}

	/**
	 * 检查在线设备：将已经离线的设备但状态仍然为在线的设备的状态修改为离线
	 */
	public void checkOnlineDevice() {
		/*
		 * 调用在线的方法
		 */
		List<String> tempList = webDeviceManageService.selectAllDeviceCodeOnline();
		for (int i = 0; i < tempList.size(); i++) {
			String deviceCode = tempList.get(i);
			String endTime = webDeviceRecordService.selectEndTimeByDeviceCode(deviceCode);
			if (endTime != null && (!endTime.equalsIgnoreCase(""))) {
				// 将一些已经离线的设备但状态仍然为在线的设备的状态修改为离线
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date endTimes = format.parse(endTime);
					Date drEndNew = new Date();
					long timeTemp = drEndNew.getTime() - endTimes.getTime();
					if ((timeTemp / 1000) > 180) {// 已经断开3min
						// 更新设备的状态为离线 deviceCode
						WebDeviceManage webDeviceManage = new WebDeviceManage();
						webDeviceManage.setDevice_code(deviceCode);
						List<WebDeviceManage> dmList = webDeviceManageService.findSelective(webDeviceManage);
						webDeviceManage = dmList.get(0);
						webDeviceManage.setDevice_state(WebDeviceManage.DEVICE_UNLINE_STATE);
						webDeviceManageService.updateByKey(webDeviceManage);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
