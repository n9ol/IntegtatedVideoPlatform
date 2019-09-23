package com.zzrenfeng.zhsx.controller.device.interfaces;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.WebDeviceManage;
import com.zzrenfeng.zhsx.model.WebDeviceRecord;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.WebDeviceManageService;
import com.zzrenfeng.zhsx.service.WebDeviceRecordService;
import com.zzrenfeng.zhsx.util.JsonUtils;
import com.zzrenfeng.zhsx.util.RandomUtils;

@Controller
@RequestMapping(value = "/GetAndWriteData")
public class GetAndWriteDataController {
	// 自定义属性编辑器
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// Date.class必须是与controler方法形参pojo属性一致的date类型，这里是java.util.Date
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

	@Resource
	private WebDeviceManageService ideviceManageService;
	@Resource
	private WebDeviceRecordService ideviceRecordService;
	@Resource
	private SysSchoolService iSchoolManageService;
	@Resource
	private SysClassroomService iClassManageService;
	@Resource
	private SysDictService sysDictService;
	
	@Resource
	private String platformLevel;//查询地区使用
	@Resource
	private String platformLevelId;//查询地区使用
	/**
	 * http://1.192.34.189:8080/rf/GetAndWriteData/getAndWritedata? data=[{
	 * "deviceCode":"","deviceType":"","deviceState":"","deviceIp":"",
	 * "deviceMac":"","deviceClientVersionNum":"","classid":"","classname":"",
	 * "deviceProvince":"",
	 * "deviceCity":"","deviceArea":"","schoolId":"","schoolName":"",
	 * "drStartTime":"" }]
	 * 
	 * produces 是将字符串转换成json格式的数据，用的是
	 * <spring.version>3.1.1.RELEASE</spring.version>
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getAndWritedata")
	public @ResponseBody Map<String, Object> getAndWritedata(
			@RequestParam(value = "data", required = false) String data, HttpServletRequest request) {
		/* 获取通过url传递过来的数据 */
		/*
		 * try { data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		System.out.println("I AM COME getAndWritedata().......");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		if (dataList == null || dataList.size() <= 0) {
			returnMap.put("errorCode", "003data");// data转换成json后的 数据为空
			return returnMap;
		}
		Map<String, Object> dataMap = dataList.get(0);
		if (dataMap == null) {
			returnMap.put("errorCode", "004data");// data转换成json后，获取内容不存在
			return returnMap;
		}

		TreeMap<String, Object> param = new TreeMap<String, Object>();
		/* 获取数据加入非空判断 */
		Object deviceCode = dataMap.get("deviceCode");
		Object deviceType = dataMap.get("deviceType");
		Object deviceState = dataMap.get("deviceState");
		Object deviceIp = dataMap.get("deviceIp");
		Object deviceMac = dataMap.get("deviceMac");
		Object deviceClientVersionNum = dataMap.get("deviceClientVersionNum");
		Object classid = dataMap.get("classid");
		Object classname = dataMap.get("classname");
		Object deviceProvince = dataMap.get("deviceProvince");
		Object deviceCity = dataMap.get("deviceCity");
		Object deviceArea = dataMap.get("deviceArea");
		Object schoolId = dataMap.get("schoolId");
		Object schoolName = dataMap.get("schoolName");
		/*
		 * 使用本地时间		
		 * Object drStartTime = dataMap.get("drStartTime");*/
		Object modify_id = dataMap.get("teacherName");

		Object flag = dataMap.get("flag");// 1 插入 2 更新
		/* 改动 */
		if ((null == deviceCode) || (null == deviceType) || (null == deviceState) || (null == deviceIp)
				|| (null == deviceMac) || (null == deviceClientVersionNum) || (null == classid) || (null == classname)
				|| (null == deviceProvince) || (null == deviceCity) || (null == deviceArea) || (null == schoolId)
				|| (null == schoolName) || (null == flag) || (null == modify_id)) {//|| (null == drStartTime)
			returnMap.put("errorCode", "005data");// data中的数据项有为空的
			return returnMap;
		}
		param.put("deviceCode", deviceCode);
		param.put("deviceType", deviceType);
		param.put("deviceState", deviceState);
		param.put("deviceIp", deviceIp);
		param.put("deviceMac", deviceMac);//需要这个字段
		param.put("deviceClientVersionNum", deviceClientVersionNum);
		param.put("classid", classid);// id
		param.put("classname", classname);// name
		param.put("deviceProvince", deviceProvince);// id
		param.put("deviceCity", deviceCity);// id
		param.put("deviceArea", deviceArea);// id
		param.put("schoolId", schoolId);// id
		param.put("schoolName", schoolName);// name
		Date drStartTime = new Date();
		param.put("drStartTime", drStartTime);
		param.put("drEndTime", drStartTime);// 初始化时，给定默认时间    
		param.put("modify_id", modify_id);

		int f = Integer.parseInt(flag.toString());
		if (f == 1) {// 1 插入  启动之前已经绑定配置好的设备走的路径20180712
			/*
			 * 事务性操作放到service层 
			 * 一.首次操作 （添加下面信息） 1.设备信息 2.设备记录 3.班级设备 
			 * 二.之后操作  1.设备记录 2.更新设备的状态（将离线修改为在线）
			 */
			WebDeviceManage dm = new WebDeviceManage();
			dm.setDeviceCode(deviceCode.toString());
			List<WebDeviceManage> deviceList = ideviceManageService.findSelective(dm);
			if ((deviceList != null) && (deviceList.size()) > 0) {// 首次之后的
				String drId = ideviceManageService.inDeviceRecord(param);// 向设备记录表中插入数据
				// 更新设备的状态 将离线状态改为在线状态
				String deviceId = deviceList.get(0).getId();
				dm.setId(deviceId);
				dm.setDeviceState(WebDeviceManage.DEVICE_ONLINE_STATE);
				ideviceManageService.upByKeySelective(dm);

				returnMap.put("deviceId", deviceId);
				returnMap.put("id", "");
				returnMap.put("drId", drId);
				returnMap.put("errorCode", "0success");//
			} else {//首次绑定配置好的内容走的路径20180712
				/*
				 * 为了保证设备编号的唯一性，特做此操作：
				 * 1.传递过来的mac删除之前存在的相同的mac的记录（设备信息表中数据）
				 * 同时删除设备记录 表中数据和班级设备表中数据 
				 * 但是这样不准确，之前的记录都使用不了。 《故不使用这种方法。》
				 * 
				 * 2.解决同一台设备即同一个mac地址有不同的编号的问题：
				 * 分析：（1）.新建一条记录：  保证和原来的编号一致，获取之前设备信息的数据将数据更新到新的记录当中，同样操作设备记录表 ，班级设备表等《也不使用这种方法》
				 * 
				 * 
				 * 最终解决方法：
				 * 生成一条新的记录，重新生成一个设备编号，并且对这条记录
				 * */
				
				
				
				// 插入数据 不登录也可以使用
				returnMap = ideviceManageService.inSomeMap(param);
				
				
				
				
			}
		} else {// 2 更新 重新绑定时操作的内容
			/*
			 * 更新只有一种情况: 1.根据devicecode 修改设备信息 2.班级设备
			 * 
			 * 
			 * 
			 * 目前准备不修改之前的设备记录，重新生成一个设备记录，设备的code也重新生成
			 */
			returnMap = ideviceManageService.upSomeMap(param);

		}

		return returnMap;
	}

	/*
	 * @RequestMapping(value = "/updata") public @ResponseBody
	 * Map<String,Object>
	 * updata(@RequestParam(value="data",required=false)String
	 * data,HttpServletRequest request){
	 * System.out.println("我进到updata（）接口中了......."); Map<String,Object>
	 * returnMap = new HashMap<String,Object>();
	 * 
	 * return returnMap; }
	 */

	/**
	 * http://1.192.34.189:8080/rf/GetAndWriteData/getState? data=[{
	 * "deviceCode":"", "drEndTime":"" }] 业务逻辑：
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getState")
	public @ResponseBody Map<String, Object> getState(@RequestParam(value = "data") String data,
			HttpServletRequest request) {
		System.out.println("getState() START.......");
		/* 获取通过url传递过来的数据 */
		try {
			data = new String(data.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		Map<String, Object> dataMap = dataList.get(0);

		String drId = dataMap.get("drId").toString();
		/* 不在使用这个时间，使用本地的时间来计算
		 * String drEndTime = dataMap.get("drEndTime").toString();
		 * 
		 * */
		String deviceCode = dataMap.get("deviceCode").toString();
		// || (null == drEndTime) || ("".equals(drEndTime)) 
		if ((null == drId) || ("".equals(drId)) || (null == deviceCode)
				|| ("".equals(deviceCode))) {
			returnMap.put("errorCode", "005data");// data中的数据项有为空的
			return returnMap;
		}

		WebDeviceRecord deviceRecord = new WebDeviceRecord();
		/* 根据dr_id查询数据对象 */
		deviceRecord = ideviceRecordService.findByKey(drId);
		if (null == deviceRecord) {
			returnMap.put("errorCode", "006data");// 查询数据为空
			return returnMap;
		}

		Date drStart = deviceRecord.getDrStartTime();// 数据库中存储的旧值
		Date drEndOld = deviceRecord.getDrEndTime();// 数据库中存储的旧值
		Date drEndNew = new Date();
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//drEndNew = sdf.parse(drEndTime);  //不在使用这个时间，使用本地的时间来计算
		long timeTemp = drEndNew.getTime() - drEndOld.getTime();
		Boolean states = false;

		if ((timeTemp / 1000) > 30) {// 已经断开3min   改为30s
			returnMap.put("errorCode", "1false");

			// 更新设备的状态为离线 deviceCode
			WebDeviceManage webDeviceManage = new WebDeviceManage();
			webDeviceManage.setDeviceCode(deviceCode);
			List<WebDeviceManage> dmList = ideviceManageService.findSelective(webDeviceManage);
			webDeviceManage = dmList.get(0);
			webDeviceManage.setDeviceState(webDeviceManage.DEVICE_UNLINE_STATE);
			ideviceManageService.upByKeySelective(webDeviceManage);
		} else {// 未断开
			states = true;
			/* 将数据写入到数据库 */
			deviceRecord.setDrEndTime(drEndNew);
			deviceRecord.setDrUsingLong(drEndNew.getTime() - drStart.getTime());
			ideviceRecordService.upByKeySelective(deviceRecord);
			returnMap.put("errorCode", "0success");
		}


		return returnMap;
	}

	/**
	 * http://1.192.34.189:8080/rf/GetAndWriteData/getProvince
	 * 除了设备管理要使用，在学生行为分析也需要改接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProvince")
	public @ResponseBody Map<String, Object> getProvince(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		SysDict sysDicttemp = new SysDict();
		sysDicttemp.setKeyname(SysDict.KEYNAME_PROVINCE);
		List<SysDict> provinceList = new ArrayList<SysDict>();
		
		List<Map<String, Object>> provinceL = new ArrayList<Map<String, Object>>();
		
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {//当是国家级别时,查询所有
			provinceList = sysDictService.findSelective(sysDicttemp);
		}else if(platformLevel.equals("P")){//只查询省级
			sysDicttemp = sysDictService.findByKey(platformLevelId);
			provinceList.add(sysDicttemp);
		}else if(platformLevel.equals("C")){
			SysDict sysDicttemC = sysDictService.findByKey(platformLevelId);
			sysDicttemp = sysDictService.findByKey(sysDicttemC.getPid());
			provinceList.add(sysDicttemp);
		}else if(platformLevel.equals("A")){
			SysDict sysDicttemA = sysDictService.findByKey(platformLevelId);
			SysDict sysDicttemC = sysDictService.findByKey(sysDicttemA.getPid());
			sysDicttemp = sysDictService.findByKey(sysDicttemC.getPid());
			provinceList.add(sysDicttemp);
		}
		
		for (int i = 0; i < provinceList.size(); i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("provinceId", provinceList.get(i).getId());
			tempMap.put("provinceName", provinceList.get(i).getValue());
			provinceL.add(tempMap);
		}
		
		/**
		 * 直接返回对象 id和value
		 */
		returnMap.put("provinceL", provinceL);// 省或者市为空
		returnMap.put("errorCode", "0success");// 省或者市为空
		return returnMap;
	}

	/**
	 * http://1.192.34.189:8080/rf/GetAndWriteData/getCity? data=[{
	 * "province":"" //此处为 }]
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCity")
	public @ResponseBody Map<String, Object> getCity(@RequestParam(value = "data") String data,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.equals("")) {
			returnMap.put("errorCode", "1false");// 数据为空
			return returnMap;
		}
		/* 获取通过url传递过来的数据 */
		/*
		 * try { data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		Map<String, Object> dataMap = dataList.get(0);

		Object province = dataMap.get("province");
		if (province == null || province.toString().equalsIgnoreCase("")) {
			returnMap.put("errorCode", "2false");// 省份为空
			return returnMap;
		}

		SysDict sysDicttemc = new SysDict();
		sysDicttemc.setKeyname(SysDict.KEYNAME_CITY);
		sysDicttemc.setPid(province.toString());
		List<SysDict> cityList = new ArrayList<SysDict>();

		List<Map<String, Object>> cityL = new ArrayList<Map<String, Object>>();
		
		
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N") || platformLevel.equals("P")) {//当是国家级别时,查询所有
			cityList = sysDictService.findSelective(sysDicttemc);
		}else if(platformLevel.equals("C")){
			SysDict sysDicttemC = sysDictService.findByKey(platformLevelId);
			cityList.add(sysDicttemC);
		}else if(platformLevel.equals("A")){
			SysDict sysDicttemA = sysDictService.findByKey(platformLevelId);
			SysDict sysDicttemC = sysDictService.findByKey(sysDicttemA.getPid());
			cityList.add(sysDicttemC);
		}
		
		for (int i = 0; i < cityList.size(); i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("cityId", cityList.get(i).getId());
			tempMap.put("cityName", cityList.get(i).getValue());
			cityL.add(tempMap);
		}
		/**
		 * 直接返回对象 id和value
		 */
		returnMap.put("cityL", cityL);// 省份为空
		returnMap.put("errorCode", "0success");// 省份为空

		return returnMap;
	}

	@RequestMapping(value = "/getArea")
	public @ResponseBody Map<String, Object> getArea(@RequestParam(value = "data") String data,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.equals("")) {
			returnMap.put("errorCode", "1false");// 数据为空
			return returnMap;
		}
		/* 获取通过url传递过来的数据 */
		/*
		 * try { data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		Map<String, Object> dataMap = dataList.get(0);

		Object city = dataMap.get("city");
		if (city == null || city.toString().equalsIgnoreCase("")) {
			returnMap.put("errorCode", "2false");// 省或者市为空
			return returnMap;
		}

		SysDict sysDicttemc = new SysDict();
		sysDicttemc.setKeyname(SysDict.KEYNAME_AREA);
		sysDicttemc.setPid(city.toString());
		List<SysDict> areaList = new ArrayList<SysDict>();

		List<Map<String, Object>> areaL = new ArrayList<Map<String, Object>>();
		
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N") || platformLevel.equals("P") || platformLevel.equals("C")) {//当是国家级别时,查询所有
			areaList = sysDictService.findSelective(sysDicttemc);
		}else if(platformLevel.equals("A")){
			SysDict sysDicttemA = sysDictService.findByKey(platformLevelId);
			areaList.add(sysDicttemA);
		}
		
		for (int i = 0; i < areaList.size(); i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("areaId", areaList.get(i).getId());
			tempMap.put("areaName", areaList.get(i).getValue());
			areaL.add(tempMap);
		}
		/**
		 * 直接返回对象 id和value
		 */
		returnMap.put("areaL", areaL);// 省或者市为空
		returnMap.put("errorCode", "0success");// 省或者市为空

		return returnMap;
	}

	@RequestMapping(value = "/getSchool")
	public @ResponseBody Map<String, Object> getSchool(@RequestParam(value = "data") String data,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.equals("")) {
			returnMap.put("errorCode", "1false");// 数据为空
			return returnMap;
		}
		/* 获取通过url传递过来的数据 */
		/*
		 * try { data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		Map<String, Object> dataMap = dataList.get(0);
		/* 获取前端的所传递过来的参数 */
		Object province = dataMap.get("province");
		Object city = dataMap.get("city");
		Object area = dataMap.get("area");
		if (province == null || province.toString().equalsIgnoreCase("") || city == null
				|| city.toString().equalsIgnoreCase("") || area == null || area.toString().equalsIgnoreCase("")) {
			returnMap.put("errorCode", "2false");// 省或者市为空
			return returnMap;
		}
		/* 将参数设定到dm中 */
		SysSchool sysSchool = new SysSchool();
		sysSchool.setProvinceId(province.toString());
		sysSchool.setCityId(city.toString());
		sysSchool.setCountyId(area.toString());
		List<SysSchool> schoolList = iSchoolManageService.findSelective(sysSchool);

		List<Map<String, Object>> schoolL = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < schoolList.size(); i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("id", schoolList.get(i).getId());
			tempMap.put("schoolname", schoolList.get(i).getSchoolName());
			schoolL.add(tempMap);
		}
		returnMap.put("schoolL", schoolL);
		returnMap.put("errorCode", "0success");

		return returnMap;
	}

	/**
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getClass")
	public @ResponseBody Map<String, Object> getClass(@RequestParam(value = "data") String data,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.equals("")) {
			returnMap.put("errorCode", "1false");// 数据为空
			return returnMap;
		}
		/* 获取通过url传递过来的数据 */
		/*
		 * try { data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		Map<String, Object> dataMap = dataList.get(0);
		/* 获取前端的所传递过来的参数 */
		Object schoolid = dataMap.get("schoolid");
		if (schoolid == null || schoolid.toString().equalsIgnoreCase("")) {
			returnMap.put("errorCode", "2false");
			return returnMap;
		}

		SysClassroom sysClassroom = new SysClassroom();
		sysClassroom.setSchoolId(schoolid.toString());
		List<SysClassroom> classList = iClassManageService.findSelective(sysClassroom);

		List<Map<String, Object>> classL = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < classList.size(); i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("classid", classList.get(i).getId());
			tempMap.put("classname", classList.get(i).getClassName());
			classL.add(tempMap);
		}
		/**
		 * 直接返回对象 id和value
		 */
		returnMap.put("classL", classL);
		returnMap.put("errorCode", "0success");
		return returnMap;
	}
	
	/**
	 * 获取设备类型的接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDeviceType")
	public @ResponseBody Map<String, Object> getDeviceType(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		SysDict sysDicttemp = new SysDict();
		sysDicttemp.setKeyname(SysDict.KEYNAME_DEVICE_TYPE);
		List<SysDict> deviceTypeList = sysDictService.findSelective(sysDicttemp);
		
		returnMap.put("deviceTypeList",deviceTypeList);
		returnMap.put("errorCode", "0success");
		return returnMap;
	}
	
	
	/**
	 * http://1.192.34.189:8080/rf/GetAndWriteData/getDeviceCode
	 * 根据mac判断数据库表中是否已经存在mac地址了，返回3false；不存在3success
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDeviceCode")
	public @ResponseBody Map<String, Object> getDeviceCode(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		/*if (data == null || data.equals("")) {
			returnMap.put("errorCode", "1false");// 数据为空
			return returnMap;
		}
		// 获取通过url传递过来的数据 
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		Map<String, Object> dataMap = dataList.get(0);
		//获取前端的所传递过来的参数 
		Object mac = dataMap.get("mac");
		if (mac == null || mac.toString().equalsIgnoreCase("")) {
			returnMap.put("errorCode", "2false");
			return returnMap;
		}*/
		
		List<WebDeviceManage> dmList = new ArrayList<WebDeviceManage>();
		/*
		 * 根据传递的mac信息来判断数据库是否有相关的吗，mac地址信息（防止copy客户端软件）
		 * 这样做其实也可以保证设备编号唯一性 
		 
		WebDeviceManage dmT = new WebDeviceManage();
		dmT.setDeviceMac(mac.toString());
		dmList = ideviceManageService.findSelective(dmT);
		if ((dmList != null) && (dmList.size() > 0)) {// 说明已经存在
			returnMap.put("errorCode", "3false");
		} else {
			returnMap.put("errorCode", "3success");//mac不存在,可以使用
		}*/
		
		String deviceCode = "";
		int flag = 1;
		while (flag == 1) {
			RandomUtils edu = new RandomUtils();
			deviceCode = edu.getRandom();
			WebDeviceManage dm = new WebDeviceManage();
			dm.setDeviceCode(deviceCode);
			dmList = ideviceManageService.findSelective(dm);
			if ((dmList != null) && (dmList.size() > 0)) {// 说明已经存在
				flag = 1;
			} else {
				flag = 2;
			}
		}

		returnMap.put("deviceCode", deviceCode);
		returnMap.put("errorCode", "0success");
		return returnMap;
	}

	/**
	 * http://1.192.34.189:8080/rf/GetAndWriteData/getDeviceInfo? data=[{
	 * "deviceCode":"" }] 根据设备id获取信息 C++端的程序“刷新”后，获取设备的详细信息
	 */

	@RequestMapping(value = "/getDeviceInfo")
	public @ResponseBody Map<String, Object> getDeviceIInfo(@RequestParam(value = "data") String data,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.equals("")) {
			returnMap.put("errorCode", "1false");// 数据为空
			return returnMap;
		}
		/* 获取通过url传递过来的数据 */
		/*
		 * try { data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		Map<String, Object> dataMap = dataList.get(0);

		Object deviceId = dataMap.get("deviceId");
		if (deviceId == null || deviceId.toString().equalsIgnoreCase("")) {
			returnMap.put("errorCode", "2false");
			return returnMap;
		}

		Map<String, Object> deviceInfoMap = ideviceManageService.selectDeviceCalssInfo(deviceId.toString());

		returnMap.put("deviceInfoMap", deviceInfoMap);// 省份为空
		returnMap.put("errorCode", "0success");// 省份为空

		return returnMap;
	}

}
