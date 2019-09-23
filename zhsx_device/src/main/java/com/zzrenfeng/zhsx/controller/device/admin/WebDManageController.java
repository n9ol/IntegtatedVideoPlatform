package com.zzrenfeng.zhsx.controller.device.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.WebDeviceManage;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebDeviceManageService;
import com.zzrenfeng.zhsx.service.WebDeviceRecordService;

/**
 * 设备管理
 * 
 * @copyright {@link zzrenfeng.com}
 * @author David
 * @version 2017-08-08 14:24:22
 * @see com.zzrenfeng.zhsx.controller.WebDeviceManage
 */
@Controller
@RequestMapping(value = "/webdevicemanage/admin/")
public class WebDManageController extends BaseController {

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
		return "/admin/device/device_list";
	}

	@RequestMapping("/listDMData")
	public String listDMData(HttpServletRequest request, Model model, Integer p) {

		return "/admin/device/device_data";
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
				return "/admin/device/device_view";
			} else {
				return "/admin/device/device_edit";
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
	@ResponseBody
	@RequestMapping(value = "/deviceupdateDM")
	public Map<String, Object> deviceupdateDM(WebDeviceManage dm, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// String state = request.getParameter("state");

		int state = webDeviceManageService.updateByKeySelective(dm);
		if (state != 0) {// 成功
			resultMap.put("errorCode", 0);
		} else {// 失败
			resultMap.put("errorCode", 1);
		}
		return resultMap;
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

	/**
	 * 删除设备信息 同时删除WebClassDevice中对应的数据
	 * 
	 * @param dm
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deviceDeleteDM")
	public String deviceDeleteDM(String ids, HttpServletRequest request, HttpServletResponse response, Model model) {

		webDeviceManageService.deleteBatchByKeys(ids);

		return "redirect:/webdevicemanage/admin/listDMBycontion";
	}
}
