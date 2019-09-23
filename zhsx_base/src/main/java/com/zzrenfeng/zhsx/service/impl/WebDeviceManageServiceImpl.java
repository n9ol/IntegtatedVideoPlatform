package com.zzrenfeng.zhsx.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.WebClassDevice;
import com.zzrenfeng.zhsx.model.WebDeviceManage;
import com.zzrenfeng.zhsx.model.WebDeviceRecord;
import com.zzrenfeng.zhsx.service.WebDeviceManageService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.WebClassDeviceMapper;
import com.zzrenfeng.zhsx.mapper.WebDeviceManageMapper;
import com.zzrenfeng.zhsx.mapper.WebDeviceRecordMapper;

/**
 * TODO 设备管理
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 14:24:22
 * @see com.zzrenfeng.zhsx.service.impl.WebDeviceManage
 */

@Service
public class WebDeviceManageServiceImpl extends BaseServiceImpl<BaseMapper<WebDeviceManage>, WebDeviceManage> implements WebDeviceManageService {

	
	@Resource
	private WebDeviceManageMapper webDeviceManageMapper;
	@Resource
	private WebDeviceRecordMapper webDeviceRecordMapper;
	@Resource
	private WebClassDeviceMapper webClassDeviceMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebDeviceManage> webDeviceManageMapper) {
		super.setBaseMapper(webDeviceManageMapper);
	}
	
	/**
	 * 获取学校的总的统计
	 */
	public Map<String, Object> getTotalSchool(Map<String, Object> paramMap) {
		return webDeviceManageMapper.getTotalSchool(paramMap);
	}

	/**
	 * 获取学校的详细信息统计
	 */
	public List<Map<String, Object>> getdetailSchool(List<SysSchool> schoolList, Map<String, Object> paramMap2) {
		List<Map<String, Object>> detailSchoolListMap = new ArrayList<Map<String, Object>>();
		
		if(schoolList != null && schoolList.size()>0){
			for (int i = 0; i < schoolList.size(); i++) {
				String schoolName= null;
				Integer schoolTotaldevices=null;
				Integer schoolUsedevices=null;
				Double schoolUserate=null;
				Double totaluselength=null;
				Double averageuselength=null;
				Map<String, Object> params= new HashMap<String, Object>();
				params.put("province", paramMap2.get("province"));
				params.put("city", paramMap2.get("city"));
				params.put("area", paramMap2.get("area"));
				params.put("startTime", paramMap2.get("startTime"));
				params.put("endTime", paramMap2.get("endTime"));
				params.put("school_id", schoolList.get(i).getId());
				
				params.put("schoolName", schoolName);
				params.put("schoolTotaldevices", schoolTotaldevices);
				params.put("schoolUsedevices", schoolUsedevices);
				params.put("schoolUserate", schoolUserate);
				params.put("totaluselength", totaluselength);
				params.put("averageuselength", averageuselength);
				Map<String, Object> paramTemp = webDeviceManageMapper.getdetailSchool(params);
				
				detailSchoolListMap.add(paramTemp);
			}
		}
		
		return detailSchoolListMap;
	}

	public List<Map<String, Object>> getdetailClass(List<SysClassroom> sysClassroomList, Map<String, Object> paramMap) {
		List<Map<String, Object>> detailClassListMap = new ArrayList<Map<String, Object>>();
		if(sysClassroomList != null && sysClassroomList.size()>0){
			for (int i = 0; i < sysClassroomList.size(); i++) {
				String className = null;
				Integer classTotaldevices=null;
				Integer classUsedevices=null;
				Double classUserate=null;
				Double totaluselength=null;
				Double averageuselength=null;
				Map<String, Object> params= new HashMap<String, Object>();
				params.put("device_province", paramMap.get("province"));
				params.put("device_city", paramMap.get("city"));
				params.put("device_area", paramMap.get("area"));
				Object schoolid = paramMap.get("schoolid");
				if(schoolid!=null){
					params.put("school_id", schoolid.toString());//.toString()
				}else{
					params.put("school_id",null);//.toString()
				}
				params.put("dr_start_time", paramMap.get("startTime"));
				params.put("dr_end_time", paramMap.get("endTime"));
				params.put("class_id",sysClassroomList.get(i).getId());
				
				params.put("className",className);
 				params.put("classTotaldevices", classTotaldevices);
				params.put("classUsedevices", classUsedevices);
				params.put("classUserate", classUserate);
				params.put("totaluselength", totaluselength);
				params.put("averageuselength", averageuselength);
				
				Map<String, Object> paramTemp = webDeviceManageMapper.getDetailClass(params);
				detailClassListMap.add(paramTemp);
			}
		}
		return detailClassListMap;
	}

	/**
	 * C++端的程序“刷新”后，获取设备的详细信息
	 */
	public Map<String, Object> selectDeviceCalssInfo(String string) {
		return webDeviceManageMapper.selectDeviceCalssInfo(string);
	}
	
	public String inDeviceRecord(TreeMap<String, Object> param) {
		WebDeviceRecord webDeviceRecord = new WebDeviceRecord();
		webDeviceRecord.setDevice_code(param.get("deviceCode").toString());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			webDeviceRecord.setDr_start_time(sdf.parse(param.get("drStartTime").toString()));
			webDeviceRecord.setDr_end_time(sdf.parse(param.get("drEndTime").toString()));
			webDeviceRecord.setDr_using_long((long)0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		webDeviceRecordMapper.insert(webDeviceRecord);
		String dr_id = webDeviceRecord.getDr_id();
		return dr_id;
	}

	public Map<String, Object> inSomeMap(TreeMap<String, Object> param) {
		/*回执状态*/
		Map<String,Object> returnMap = new HashMap<String,Object>();
		/*将设备信息数据写入到数据库*/
		WebDeviceManage dm = new WebDeviceManage();
		dm.setDevice_code(param.get("deviceCode").toString());
		String deviceArea= param.get("deviceArea").toString();
		dm.setDevice_area(deviceArea);
		String deviceCity= param.get("deviceCity").toString();
		dm.setDevice_city(deviceCity);
		dm.setDevice_client_version_num(param.get("deviceClientVersionNum").toString());
		dm.setDevice_ip(param.get("deviceIp").toString());
		dm.setDevice_mac(param.get("deviceMac").toString());
		String province= param.get("deviceProvince").toString();
		dm.setDevice_province(province);
		dm.setDevice_state(param.get("deviceState").toString());
		dm.setDevice_type(param.get("deviceType").toString());
		String schoolName = param.get("schoolName").toString();
		dm.setSchool_name(schoolName);
		dm.setDevice_isvalid("0");
		String schoolId = param.get("schoolId").toString();//*改动
		dm.setSchool_id(schoolId);
		dm.setCreate_time(new Date());
		dm.setModify_time(new Date());
		dm.setModify_id(param.get("modify_id").toString());
		webDeviceManageMapper.insert(dm);
		String deviceId = dm.getDevice_id();
		
		String drId = inDeviceRecord(param);
		
		/*向班级设备中插入数据*/
		WebClassDevice classDevice = new WebClassDevice();
		String classname= param.get("classname").toString();
		classDevice.setClassname(classname);
		classDevice.setDevice_id(deviceId);//插入后的结果
		classDevice.setDevice_code(param.get("deviceCode").toString());
		String classid = param.get("classid").toString();
		classDevice.setClassid(classid);//*改动
		
		webClassDeviceMapper.insert(classDevice);

		String id= classDevice.getId();
		
		if((deviceId!=null) && (!"".equalsIgnoreCase(deviceId)) && (drId!=null) && (!"".equalsIgnoreCase(drId)) && (id!=null) && (!"".equalsIgnoreCase(id))){//全部插入成功
			returnMap.put("deviceId", deviceId);
			returnMap.put("drId", drId);
			returnMap.put("id", id);
			returnMap.put("errorCode", "0success");
			return returnMap;
		}else{//插入失败
			returnMap.put("errorCode", "1false");
			return returnMap;
		}
		
	}
	/**
	 * 省 、市、县、学校、教室、负责老师
	 * 
	 */
	public Map<String, Object> upSomeMap(TreeMap<String, Object> param) {
		/*回执状态*/
		Map<String,Object> returnMap = new HashMap<String,Object>();
		/*将设备信息数据写入到数据库*/
		WebDeviceManage dm = new WebDeviceManage();
		String deviceCode=param.get("deviceCode").toString();
		dm.setDevice_code(deviceCode);//不变
		String deviceArea= param.get("deviceArea").toString();//变
		dm.setDevice_area(deviceArea);
		String deviceCity= param.get("deviceCity").toString();//变
		dm.setDevice_city(deviceCity);
		dm.setDevice_client_version_num(param.get("deviceClientVersionNum").toString());
		dm.setDevice_ip(param.get("deviceIp").toString());
		dm.setDevice_mac(param.get("deviceMac").toString());
		String province= param.get("deviceProvince").toString();//变
		dm.setDevice_province(province);
		dm.setDevice_state(param.get("deviceState").toString());
		dm.setDevice_type(param.get("deviceType").toString());
		String schoolName = param.get("schoolName").toString();//变
		dm.setSchool_name(schoolName);
		dm.setDevice_isvalid("0");
		String schoolId = param.get("schoolId").toString();//变
		dm.setSchool_id(schoolId);
//		dm.setCreate_time(new Date());
//		dm.setModify_time(new Date());
		//获取设备id
		WebDeviceManage tempDM = new WebDeviceManage();
		tempDM.setDevice_code(deviceCode);
		List<WebDeviceManage> tempWDMList = webDeviceManageMapper.findSelective(tempDM);
		dm.setDevice_id(tempWDMList.get(0).getDevice_id());
		
		//需要记录id
		webDeviceManageMapper.updateByPrimaryKeySelective(dm);
		String deviceId = dm.getDevice_id();
		
		String drId = inDeviceRecord(param);
		
		/*向班级设备中插入数据*/
		WebClassDevice classDevice = new WebClassDevice();
		String classname= param.get("classname").toString();
		classDevice.setClassname(classname);//变
		classDevice.setDevice_id(deviceId);//插入后的结果
		classDevice.setDevice_code(param.get("deviceCode").toString());//不变
		String classid = param.get("classid").toString();
		classDevice.setClassid(classid);//变
		//获取id
		WebClassDevice tempCD = new WebClassDevice();
		tempCD.setDevice_code(deviceCode);
		List<WebClassDevice> tempCDList = webClassDeviceMapper.findSelective(tempCD);
		classDevice.setId(tempCDList.get(0).getId());
		//需要记录id
		webClassDeviceMapper.updateByPrimaryKeySelective(classDevice);//仍然插入使用时间
		String id= classDevice.getId();
		
		if((deviceId!=null) && (!"".equalsIgnoreCase(deviceId)) && (drId!=null) && (!"".equalsIgnoreCase(drId)) && (id!=null) && (!"".equalsIgnoreCase(id))){//全部插入成功
			returnMap.put("deviceId", deviceId);
			returnMap.put("drId", drId);
			returnMap.put("id", id);
			returnMap.put("errorCode", "0success");
			return returnMap;
		}else{//更新失败
			returnMap.put("errorCode", "1false");
			return returnMap;
		}
	}

	/**
	 * 调用在线的方法
	 */
	public List<String> selectAllDeviceCodeOnline() {
		return webDeviceManageMapper.selectAllDeviceCodeOnline();
	}

	/**
	 * 更新设备信息，为了绕过事务控制
	 * @param dm
	 */
	public void upByKeySelective(WebDeviceManage dm) {
		webDeviceManageMapper.updateByPrimaryKeySelective(dm);
	}

	/**
	 * 查询设备首页的相关数据统计
	 */
	public Map<String, Object> findTotal() {
		return webDeviceManageMapper.findTotal();
	}

	public List<Map<String,Object>> onlineDeviceEveryday(Map<String, Object> paramMap) {
		return webDeviceManageMapper.onlineDeviceEveryday(paramMap);
	}

	public Map<String, Object> totalOnlineDeviceCount(
			Map<String, Object> paramMap) {
		return webDeviceManageMapper.totalOnlineDeviceCount(paramMap);
	}

	public List<Map<String, Object>> everyDayAccessCount(
			Map<String, Object> paramMap) {
		return webDeviceManageMapper.everyDayAccessCount(paramMap);
	}

	public List<Map<String, Object>> repairDeviceCount(
			Map<String, Object> paramMap) {
		return webDeviceManageMapper.repairDeviceCount(paramMap);
	}
	/**
	 * 计算设备的使用率
	 * Map<String, Object> paramMap
	 */
	public List<Map<String, Object>> usageRateRank(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> mapList = webDeviceManageMapper.usageRateRank(paramMap);
		for (int i = 0; i < mapList.size(); i++) {
			Map<String, Object> tempMap = mapList.get(i);
			Object countDeviceCode = tempMap.get("countDeviceCode");
			Object usageRate = tempMap.get("usageRate");
			if(countDeviceCode != null){
				Integer tCountDeviceCode = Integer.parseInt(countDeviceCode.toString());
				Double tUsageRate= Double.parseDouble(usageRate.toString());
				double tur = tUsageRate/tCountDeviceCode;
				
				tempMap.remove("usageRate");//移除之前的
				tempMap.put("usageRate", tur);//保存现在的
			}else{
				usageRate=0;
				tempMap.remove("usageRate");//移除之前的
				tempMap.put("usageRate", usageRate);//保存现在的
			}
		}
		
		return mapList;
	}

	public void deleteBatchByKeys(String ids) {
		if(ids!=null){
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				WebDeviceManage webDeviceManage = webDeviceManageMapper.selectByPrimaryKey(idsArr[i]);
				
				WebClassDevice webClassDevice = new WebClassDevice(); 
				webClassDevice.setDevice_id(idsArr[i]);
				webClassDevice.setDevice_code(webDeviceManage.getDevice_code());
				webClassDeviceMapper.deleteBySelective(webClassDevice);
				
				webDeviceManageMapper.deleteByPrimaryKey(idsArr[i]);
			}
		}
		
		
	}

	@Override
	public Integer findDeviceStateByClassId(String classId) {
		return webDeviceManageMapper.findDeviceStateByClassId(classId);
	}
	
}
