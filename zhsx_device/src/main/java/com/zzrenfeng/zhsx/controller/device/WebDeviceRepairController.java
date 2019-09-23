package com.zzrenfeng.zhsx.controller.device;

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

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.WebDeviceRepair;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.WebDeviceRepairService;

/**
 * 设备保修记录表
 * 
 * @copyright {@link zzrenfeng.com}
 * @author David
 * @version 2017-08-08 14:28:17
 * @see com.zzrenfeng.zhsx.controller.WebDeviceRepair
 */
@Controller
@RequestMapping(value = "/webdevicerepair")
public class WebDeviceRepairController extends BaseController {

	@Resource
	private WebDeviceRepairService webDeviceRepairService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listDRByContion")
	public String listDRByContion(HttpServletRequest request, HttpServletResponse response, Model model, Integer p) {
		if (p == null)
			p = 1;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		/* 查询所有 省 、地区、 县 */
		SysDict sysDicttemp = new SysDict();
		SysDict sysDicttemc = new SysDict();
		SysDict sysDicttema = new SysDict();

		SysSchool sysSchool = new SysSchool();

		String schoolId = request.getParameter("schoolId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if (schoolId != null && StringUtils.isNotEmpty(schoolId)) {
			model.addAttribute("schoolIdAgu", schoolId);
			paramMap.put("schoolId", schoolId);
		}
		if (startTime != null && StringUtils.isNotEmpty(startTime)) {
			model.addAttribute("startTimeAgu", startTime);
			paramMap.put("startTime", startTime);
		}
		if (endTime != null && StringUtils.isNotEmpty(endTime)) {
			model.addAttribute("endTimeAgu", endTime);
			paramMap.put("endTime", endTime);
		}

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
				paramMap.put("province", province);
				sysDicttemc.setPid(province);
			}

			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				model.addAttribute("cityAgu", city);
				paramMap.put("city", city);
				sysDicttema.setPid(city);
			}

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				paramMap.put("area", area);

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
			paramMap.put("province", platformLevelId);
			sysSchool.setProvinceId(platformLevelId);// 查询省下面所有的学校

			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				model.addAttribute("cityAgu", city);
				paramMap.put("city", city);
				sysDicttema.setPid(city);
			}

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				paramMap.put("area", area);
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
			paramMap.put("city", platformLevelId);
			sysSchool.setCityId(platformLevelId);// 查询市下面所有的学校

			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				model.addAttribute("areaAgu", area);
				paramMap.put("area", area);

				sysSchool.setCountyId(area);
			}

			sysDicttema.setPid(platformLevelId);
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findSelective(sysDicttema);
			model.addAttribute("areaList", areaList);
		} else if (platformLevel.equals("A")) {// 暂时不做处理
			paramMap.put("area", platformLevelId);
			sysSchool.setCountyId(platformLevelId);// 查询区下面所有的学校

			model.addAttribute("platformLevelId", platformLevelId);
		}

		List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
		model.addAttribute("schoolList", schoolList);

		paramMap.put("repair_isvalid", WebDeviceRepair.DEVICE_ISVALID_STATE);
		/* 查询数据 */
		Page<WebDeviceRepair> pageInfo = webDeviceRepairService.findPageByMapSelective(paramMap, p, 10);
		int pages = pageInfo.getPages(); // 总页数
		List<WebDeviceRepair> ldr = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("ldr", ldr);

		return "/web/device/em_fixed_info";
	}

	/**
	 * 到达添加、编辑、查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reachDR")
	public String reachDR(HttpServletRequest request, HttpServletResponse response, Model model) {
		String pca = getDicts();
		model.addAttribute("pca", pca);

		return "/web/device/online_fixed";
	}

	/**
	 * 需要登录 否则修改方法名字
	 * 
	 * @param request
	 * @param response
	 * @param dr
	 * @return
	 */
	@RequestMapping(value = "/insertDR")
	public String insertDR(HttpServletRequest request, HttpServletResponse response, WebDeviceRepair dr) {
		ShiroUser user = getShiroUser();
		dr.setCreate_id(user.getId());
		dr.setModify_id(user.getId());
		dr.setCreate_time(new Date());
		dr.setModify_time(new Date());
		dr.setRepair_time(new Date());

		String schoolId = user.getSchoolId();
		dr.setRepair_unit(schoolId);

		SysSchool sysSchool = sysSchoolService.findByKey(schoolId);
		dr.setRepair_unit_name(sysSchool.getSchoolName());

		dr.setRepair_isvalid(WebDeviceRepair.DEVICE_ISVALID_STATE);
		webDeviceRepairService.dataInsert(dr);
		return "redirect:/webdevicerepair/listDRByContion";
	}

	@RequestMapping(value = "/findDRById")
	public String findDRById(HttpServletRequest request, HttpServletResponse response, Model model) {
		String repair_id = request.getParameter("repair_id");
		WebDeviceRepair dr = webDeviceRepairService.findByKey(repair_id);
		model.addAttribute("dr", dr);

		String pca = getDicts();
		model.addAttribute("pca", pca);

		String flag = request.getParameter("flag");
		if ("1".equalsIgnoreCase(flag)) {
			return "/web/device/online_fixed_view";
		} else {// 不是1 就是2
			return "/web/device/online_fixed";
		}
	}

	/**
	 * 需要登录 否则修改方法名字
	 * 
	 * @param request
	 * @param response
	 * @param dr
	 * @return
	 */
	@RequestMapping(value = "/updateDR")
	public String updateDR(HttpServletRequest request, HttpServletResponse response, WebDeviceRepair dr) {
		webDeviceRepairService.dataUpdateByKeySelective(dr);
		return "redirect:/webdevicerepair/listDRByContion";
	}

	/**
	 * 获取地区信息
	 * 
	 * @return
	 */
	public String getDicts() {
		SysDict dicts = sysDictService.findByKey(platformLevelId);
		// 根据学校获取省市县的信息
		ShiroUser user = getShiroUser();
		String schoolId = user.getSchoolId();
		SysSchool sysSchool = sysSchoolService.findByKey(schoolId);
		String pca = "";
		if (sysSchool != null) {
			String province = sysSchool.getProvinceName();
			String city = sysSchool.getCityName();
			String country = sysSchool.getCountyName();
			pca = province + city + country;
		} else {
			// bak2 是县级信息
			String bak2 = user.getBak2();
			if (bak2 != null) {
				// 从配置文件中读去数据
				SysDict areaDict = sysDictService.findByKey(bak2);
				if (areaDict != null) {

					if (SysDict.KEYNAME_PROVINCE.equalsIgnoreCase(platformLevel)) {
						SysDict cityDict = sysDictService.findByKey(areaDict.getPid());

						if (cityDict != null) {
							SysDict provinceDict = sysDictService.findByKey(cityDict.getPid());
							if (provinceDict != null) {
								pca = provinceDict.getValue() + cityDict.getValue() + areaDict.getValue();
							} else {
								pca = dicts.getValue();
							}
						} else {
							pca = dicts.getValue();
						}

					} else if (SysDict.KEYNAME_CITY.equalsIgnoreCase(platformLevel)) {
						// 市区信息
						SysDict cityDict = sysDictService.findByKey(areaDict.getPid());
						if (cityDict != null) {
							pca = cityDict.getValue() + areaDict.getValue();
						} else {
							pca = dicts.getValue();
						}
					} else if (SysDict.KEYNAME_AREA.equalsIgnoreCase(platformLevel)) {
						pca = areaDict.getValue();
					}

				} else {
					pca = dicts.getValue();
				}
			} else {
				pca = dicts.getValue();

			}
		}

		return pca;
	}

}
