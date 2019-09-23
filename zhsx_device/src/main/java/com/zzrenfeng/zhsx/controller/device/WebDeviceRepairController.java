package com.zzrenfeng.zhsx.controller.device;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.WebDeviceManage;
import com.zzrenfeng.zhsx.model.WebDeviceRepair;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.WebDeviceManageService;
import com.zzrenfeng.zhsx.service.WebDeviceRepairService;
import com.zzrenfeng.zhsx.util.Encodes;

/**
 * 设备保修记录表
 * 1.申请保修的必须是学校管理员,否则没有权限操作
 * 2.
 * 
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
	private SysClassroomService sysClassroomService;
	@Resource
	private WebDeviceManageService webDeviceManageService;
	
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
		
		model.addAttribute("platformLevel", platformLevel);//平台级别

		/*
		 * 地区信息备注：
		 * 1.国家级别
		 * 	（1）初次查询显示所有的省、市、县。
		 *  （2）当用户在前台页面筛选好条件，并点击“搜索”后；就再查询所有省份，并在前台选择用户搜索时选择的省份信息；
		 *  	根据用户选择的省份来查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 *  	根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息；
		 * 2.省级
		 * 	（1） 初次查询显示指定的省下的市和该省下的所有县；
		 *  （2） 当用户筛选好条件后，并点击“搜索”后；
		 *  	查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 *  	根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息；
		 * 3.市级
		 *  （1） 初次查询显示指定的市下的所有县；
		 *  （2） 查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息；
		 * 4.县级 （都不显示）
		 * 	（1）
		 * */
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {//当是国家级别时
			
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
		} else if (platformLevel.equals("P")) {//platformLevelId 是省分的id
			paramMap.put("province", platformLevelId);
			sysSchool.setProvinceId(platformLevelId);//查询省下面所有的学校
			
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
			//该省省份下面所有的市
			sysDicttemc.setPid(platformLevelId);
			sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
			List<SysDict> cityList = sysDictService.findSelective(sysDicttemc);
			model.addAttribute("cityList", cityList);
			
			//查询省份下面的所有县
			sysDicttema.setId(platformLevelId);//此时仅仅是为了传递数据方便
			sysDicttema.setKeyname(SysDict.KEYNAME_AREA);
			List<SysDict> areaList = sysDictService.findAreaByProvince(sysDicttema);
			model.addAttribute("areaList", areaList);
			
		} else if (platformLevel.equals("C")) {
			paramMap.put("city", platformLevelId);
			sysSchool.setCityId(platformLevelId);//查询市下面所有的学校
			
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
		} else if (platformLevel.equals("A")) {//暂时不做处理
			paramMap.put("area", platformLevelId);
			sysSchool.setCountyId(platformLevelId);//查询区下面所有的学校
			
			model.addAttribute("platformLevelId", platformLevelId);
		}
		
		List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
		model.addAttribute("schoolList", schoolList);
		
		
		paramMap.put("repair_isvalid", WebDeviceRepair.DEVICE_ISVALID_STATE);
		/* 查询数据  时间节点左右<=  >= */
		Page<WebDeviceRepair> pageInfo = webDeviceRepairService.findPageByMapSelective(paramMap, p, 10);
		int pages = pageInfo.getPages(); // 总页数
		List<WebDeviceRepair> ldr = pageInfo.getResult();
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
		//String pca = getDicts();
		//model.addAttribute("pca", pca);
		
		ShiroUser shiroUser = getShiroUser(); 
		String schoolId = shiroUser.getSchoolId();
		SysSchool school = sysSchoolService.findByKey(schoolId);
		model.addAttribute("school", school);
		SysClassroom sysClassroom = new SysClassroom();
		sysClassroom.setSchoolId(schoolId);
		List<SysClassroom> sysClassroomList = sysClassroomService.findSelective(sysClassroom);
		model.addAttribute("sysClassroomList", sysClassroomList);
		
		//根据学校查询所有的设备信息 默认显示一个学校的  不给默认显示的信息
		/*WebDeviceManage wdm = new WebDeviceManage();
		wdm.setSchoolId(schoolId);
		List<WebDeviceManage> wdmList = webDeviceManageService.findSelective(wdm);
		model.addAttribute("wdmList", wdmList);*/
		
		//查询所有的设备类型信息
		SysDict version = new SysDict();
		version.setKeyname(SysDict.KEYNAME_DEVICE_TYPE);
		Page<SysDict> pageInfo = sysDictService.findPageSelective(version, 1, 12);
		List<SysDict> typeList = pageInfo.getResult();
		model.addAttribute("typeList", typeList);
		
		
		
		
		return "/web/device/online_fixed";
	}
	/**
	 * 根据班级id查询设备信息    
	 * 需求变更： 班级和设备编号不用绑定在一起，故此此方法废弃
	 * @param request
	 * @param response
	 * @param classId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDeviceByClassId")
	public List<Map<String,Object>> getDeviceByClassId(HttpServletRequest request, HttpServletResponse response,String classId) {
		//根据学校查询所有的设备信息
		List<Map<String,Object>> wdmList = webDeviceManageService.getDeviceByClassId(classId);
		return wdmList;
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
/*		String webDeviceManageId = request.getParameter("webDeviceManageId");
		WebDeviceManage webDeviceManage = new WebDeviceManage();
		webDeviceManage.setId(webDeviceManageId);
		dr.setWebDeviceManage(webDeviceManage);*/
		
		ShiroUser user = getShiroUser();
		dr.setCreateId(user.getId());
		dr.setModifyId(user.getId());
		dr.setCreateTime(new Date());
		dr.setModifyTime(new Date());
		
		dr.setRepairTime(new Date());//申请报修的时间
		dr.setRepairState(WebDeviceRepair.DEVICE_REPAIR_STATE_NONE);//设备状态
		//授理状态 默认是0
		dr.setManaOverdueState(WebDeviceRepair.MANA_OVERDUE_STATE_NONE);
		//授理时间添加  受理人登录时的操作  dr.setManagerTime(new Date());
		
		//String schoolId = user.getSchoolId();
		//TODO dr.setSchoolId(schoolId);

		//SysSchool sysSchool = sysSchoolService.findByKey(schoolId);
		//dr.setRepair_unit_name(sysSchool.getSchoolName());  TODO 

		dr.setIsvalid(WebDeviceRepair.DEVICE_ISVALID_STATE);
		webDeviceRepairService.dataInsert(dr);
		return "redirect:/webdevicerepair/listDRByContion";
	}

	@RequestMapping(value = "/findDRById")
	public String findDRById(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("repair_id");
		WebDeviceRepair dr = webDeviceRepairService.findByKey(id);
		model.addAttribute("dr", dr);

		/*String pca = getDicts();
		model.addAttribute("pca", pca);*/

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
	 * @return
	 */
	public String getDicts(){
		SysDict dicts = sysDictService.findByKey(platformLevelId);
		// 根据学校获取省市县的信息
		ShiroUser user = getShiroUser();
		String schoolId = user.getSchoolId();
		SysSchool sysSchool = sysSchoolService.findByKey(schoolId);
		String pca = "";
		if(sysSchool != null){
			String province = sysSchool.getProvinceName();
			String city = sysSchool.getCityName();
			String country = sysSchool.getCountyName();
			pca = province + city + country;
		}else{
			//bak2  是县级信息
			String bak2= user.getBak2();
			if(bak2 != null){
				//从配置文件中读去数据
				SysDict areaDict = sysDictService.findByKey(bak2);
				if(areaDict!=null){
					
					if(SysDict.KEYNAME_PROVINCE.equalsIgnoreCase(platformLevel)){
						SysDict cityDict = sysDictService.findByKey(areaDict.getPid());
						
						if(cityDict != null ){
							SysDict provinceDict = sysDictService.findByKey(cityDict.getPid());
							if(provinceDict != null){
								pca = provinceDict.getValue()+cityDict.getValue()+areaDict.getValue();
							}else{
								pca = dicts.getValue();
							}
						}else{
							pca = dicts.getValue();
						}
						
					}else if(SysDict.KEYNAME_CITY.equalsIgnoreCase(platformLevel)){
						//市区信息
						SysDict cityDict = sysDictService.findByKey(areaDict.getPid());
						if(cityDict != null){
							pca = cityDict.getValue()+areaDict.getValue();
						}else{
							pca = dicts.getValue();
						}
					}else if(SysDict.KEYNAME_AREA.equalsIgnoreCase(platformLevel)){
						pca = areaDict.getValue();
					}
					
				}else{
					pca = dicts.getValue();
				}
			}else{
				pca = dicts.getValue();
				
			}
		}
		
		return pca;
	}
	
	
	//导出Excel
    @RequestMapping("exportRepair")
    @ResponseBody
    public String createExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		
		String schoolId = request.getParameter("schoolId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if (schoolId != null && StringUtils.isNotEmpty(schoolId)) {
			paramMap.put("schoolId", schoolId);
		}
		if (startTime != null && StringUtils.isNotEmpty(startTime)) {
			paramMap.put("startTime", startTime);
		}
		if (endTime != null && StringUtils.isNotEmpty(endTime)) {
			paramMap.put("endTime", endTime);
		}
		/*
		 * 地区信息备注：
		 * 1.国家级别
		 * 	（1）初次查询显示所有的省、市、县。
		 *  （2）当用户在前台页面筛选好条件，并点击“搜索”后；就再查询所有省份，并在前台选择用户搜索时选择的省份信息；
		 *  	根据用户选择的省份来查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 *  	根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息；
		 * 2.省级
		 * 	（1） 初次查询显示指定的省下的市和该省下的所有县；
		 *  （2） 当用户筛选好条件后，并点击“搜索”后；
		 *  	查询该省份下的所有市，并在前台选择上用户搜索时选择的市信息；
		 *  	根据用户选择的市份来查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息；
		 * 3.市级
		 *  （1） 初次查询显示指定的市下的所有县；
		 *  （2） 查询该市份下的所有县，并在前台选择上用户搜索时选择的县信息；
		 * 4.县级 （都不显示）
		 * 	（1）
		 * */
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {//当是国家级别时
			
			String province = request.getParameter("province");
			if ((province != null) && (!StringUtils.isEmpty(province))) {
				paramMap.put("province", province);
			}
			
			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				paramMap.put("city", city);
			}
			
			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				paramMap.put("area", area);
			}
		} else if (platformLevel.equals("P")) {//platformLevelId 是省分的id
			paramMap.put("province", platformLevelId);
			String city = request.getParameter("city");
			if ((city != null) && (!StringUtils.isEmpty(city))) {
				paramMap.put("city", city);
			}
			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				paramMap.put("area", area);
			}
		} else if (platformLevel.equals("C")) {
			paramMap.put("city", platformLevelId);
			String area = request.getParameter("area");
			if ((area != null) && (!StringUtils.isEmpty(area))) {
				paramMap.put("area", area);
			}
			
		} else if (platformLevel.equals("A")) {//暂时不做处理
			paramMap.put("area", platformLevelId);
		}
		
		paramMap.put("repair_isvalid", WebDeviceRepair.DEVICE_ISVALID_STATE);
		/* 查询数据 */
		List<WebDeviceRepair> ldr = webDeviceRepairService.selectDRByContions(paramMap);
    	
    	//ldr ; 获取查询结果的数据,reportlist为别的方法查询出来的数据，格式为List<Object[]>,其实这里不管reportlist是什么数据格式，这里只要对其进行封装就行了
    	//数据封装，这里的map之所以敢这样add是因为这里的add顺序和hql中的select字段顺序是一样的，总共就查询那么多字段
		List<Map<String,Object>> solist = new ArrayList<Map<String,Object>>();
		
		for(WebDeviceRepair tempDR:ldr){
			//每次循环都要重新new一个map，表示不同对象
			Map<String,Object> map = new HashMap<String,Object>();
			if(tempDR.getSchool() != null){
				map.put("schoolName", tempDR.getSchool().getSchoolName());//报修单位
			}else{
				map.put("schoolName", "");//报修单位
			}
			
			if(tempDR.getSysClass() != null ){
				map.put("className", tempDR.getSysClass().getClassName());//报修班级
			}else{
				map.put("className", "");//报修班级
			}
			
			if(tempDR.getUser() != null){
				map.put("nickName", tempDR.getUser().getNickName());//报修人
				map.put("phone", tempDR.getUser().getPhone());//报修电话
			}else{
				map.put("nickName", "");//报修人
				map.put("phone", "");//报修电话
			}
			
			if(tempDR.getSysDict() != null ){
				map.put("value", tempDR.getSysDict().getValue());//报修类型
			}else{
				map.put("value", "");//报修类型
			}
			
			if(tempDR.getManager() != null ){
				map.put("nickNameM", tempDR.getManager().getNickName());//授理人
				map.put("phoneM", tempDR.getManager().getPhone());//授理人电话
			}else{
				map.put("nickNameM", "");//授理人
				map.put("phoneM", "");//授理人电话
			}
			
			String manaOverdueState = tempDR.getManaOverdueState();
			if(manaOverdueState != null){
				if("0".equalsIgnoreCase(manaOverdueState)){
					map.put("manaOverdueState", "未授理 ");//授理状态
				}else if("1".equalsIgnoreCase(manaOverdueState)){
					map.put("manaOverdueState", "已授理");//授理状态
				}else if("2".equalsIgnoreCase(manaOverdueState)){
					map.put("manaOverdueState", "已经授理但逾期");//授理状态
				}
			}else{
				map.put("manaOverdueState", "");//授理状态
			}
			
			if(tempDR.getWebDeviceTechnician() != null ){
				map.put("technicianName", tempDR.getWebDeviceTechnician().getName());//维修人
				map.put("technicianPhone", tempDR.getWebDeviceTechnician().getPhone());//维修电话
			}else{
				map.put("technicianName", "");//维修人
				map.put("technicianPhone", "");//维修电话
			}
			
			solist.add(map);
		}
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("设备报修报表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        
        // 1.生成字体对象  
        HSSFFont font = wb.createFont();  
        font.setFontHeightInPoints((short) 12);  
        font.setFontName("新宋体");  
        
        // 2.生成样式对象，这里的设置居中样式和版本有关，我用的poi用HSSFCellStyle.ALIGN_CENTER会报错，所以用下面的
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置居中样式
        style.setFont(font); // 调用字体样式对象  
        style.setWrapText(true);  
       // style.setAlignment(HorizontalAlignment.CENTER);//设置居中样式   TODO
        
        // 3.单元格应用样式  
        cell.setCellStyle(style); 
        
        //设置单元格内容
        cell.setCellValue("设备报修报表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
       
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容及样式
        HSSFCell cell0=row2.createCell(0);
        cell0.setCellStyle(style);
        cell0.setCellValue("报修单位");
        
        HSSFCell cell1=row2.createCell(1);
        cell1.setCellStyle(style);
        cell1.setCellValue("报修班级");
        
        HSSFCell cell2=row2.createCell(2);
        cell2.setCellStyle(style);
        cell2.setCellValue("报修人");
        
        HSSFCell cell3=row2.createCell(3);
        cell3.setCellStyle(style);
        cell3.setCellValue("报修电话");
        
        HSSFCell cell4=row2.createCell(4);
        cell4.setCellStyle(style);
        cell4.setCellValue("报修类型");
        
        HSSFCell cell5=row2.createCell(5);
        cell5.setCellStyle(style);
        cell5.setCellValue("授理人");
        
        HSSFCell cell6=row2.createCell(6);
        cell6.setCellStyle(style);
        cell6.setCellValue("授理人电话");
        
        HSSFCell cell7=row2.createCell(7);
        cell7.setCellStyle(style);
        cell7.setCellValue("授理状态");
        
        HSSFCell cell8=row2.createCell(8);
        cell8.setCellStyle(style);
        cell8.setCellValue("维修人");
        
        HSSFCell cell9=row2.createCell(9);
        cell9.setCellStyle(style);
        cell9.setCellValue("维修电话");
        
        //单元格宽度自适应
        sheet.autoSizeColumn((short)3);
        sheet.autoSizeColumn((short)4);
        sheet.autoSizeColumn((short)5);
        sheet.autoSizeColumn((short)6);
        sheet.autoSizeColumn((short)7);
        sheet.autoSizeColumn((short)8);
        sheet.autoSizeColumn((short)9);
        //宽度自适应可自行选择自适应哪一行，这里写在前面的是适应第二行，写在后面的是适应第三行
        for (int i = 0; i < solist.size(); i++) {
            //单元格宽度自适应
        	sheet.autoSizeColumn((short)0);
        	sheet.autoSizeColumn((short)1);
        	sheet.autoSizeColumn((short)2);
        	//从sheet第三行开始填充数据
        	HSSFRow rowx=sheet.createRow(i+2);
        	Map<String,Object> map = solist.get(i);
        	
        	//这里的hospitalid,idnumber等都是前面定义的全局变量
        	String schoolName = (String) map.get("schoolName");//
        	HSSFCell cell00=rowx.createCell(0);
        	cell00.setCellStyle(style);
        	cell00.setCellValue(schoolName);
        	
        	String className = (String) map.get("className");
        	HSSFCell cell01=rowx.createCell(1);
        	cell01.setCellStyle(style);
        	cell01.setCellValue(className);
        	
        	String nickName = (String) map.get("nickName");
        	HSSFCell cell02=rowx.createCell(2);
        	cell02.setCellStyle(style);
        	cell02.setCellValue(nickName);
        	
        	String phone = (String) map.get("phone");
        	HSSFCell cell03=rowx.createCell(3);
        	cell03.setCellStyle(style);
        	cell03.setCellValue(phone);
        	
        	String value = (String) map.get("value");
        	HSSFCell cell04=rowx.createCell(4);
        	cell04.setCellStyle(style);
        	cell04.setCellValue(value);
        	
        	String nickNameM = (String) map.get("nickNameM");
        	HSSFCell cell05=rowx.createCell(5);
        	cell05.setCellStyle(style);
        	cell05.setCellValue(nickNameM);
        	
        	String phoneM = (String) map.get("phoneM");
        	HSSFCell cell06=rowx.createCell(6);
        	cell06.setCellStyle(style);
        	cell06.setCellValue(phoneM);
        	
        	String manaOverdueState = (String) map.get("manaOverdueState");
        	HSSFCell cell07=rowx.createCell(7);
        	cell07.setCellStyle(style);
        	cell07.setCellValue(manaOverdueState);
        	
        	String technicianName = (String) map.get("technicianName");
        	HSSFCell cell08=rowx.createCell(8);
        	cell08.setCellStyle(style);
        	cell08.setCellValue(technicianName);
        	
        	String technicianPhone = (String) map.get("technicianPhone");
        	HSSFCell cell09=rowx.createCell(9);
        	cell09.setCellStyle(style);
        	cell09.setCellValue(technicianPhone);

        }
        
        String fileName ="报修设备列表";
        if(request.getHeader( "USER-AGENT" ).toLowerCase().indexOf( "msie" ) >  0){//如果是IE
        	fileName = Encodes.urlEncode(fileName);
        }else{
        	fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }
        
        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setContentType("application/msexcel; charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");//文件名这里可以改
        wb.write(output);
        output.close();
        return null;
    }
}
