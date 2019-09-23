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
		webDeviceRecord.setDeviceCode(param.get("deviceCode").toString());
		webDeviceRecord.setDeviceMac(param.get("deviceMac").toString());
		/*时间转换成Date之后，不用以下时间的格式
		 * try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			webDeviceRecord.setDrStartTime(sdf.parse(param.get("drStartTime").toString()));
			webDeviceRecord.setDrEndTime(sdf.parse(param.get("drEndTime").toString()));
			webDeviceRecord.setDrUsingLong((long)0);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		Date drStartTime = (Date)param.get("drStartTime");
		Date drEndTime = (Date) param.get("drStartTime");
		webDeviceRecord.setDrStartTime(drStartTime);
		webDeviceRecord.setDrEndTime(drEndTime);
		webDeviceRecord.setDrUsingLong((long)0);
		webDeviceRecordMapper.insert(webDeviceRecord);
		String dr_id = webDeviceRecord.getId();
		return dr_id;
	}

	public Map<String, Object> inSomeMap(TreeMap<String, Object> param) {
		/*回执状态*/
		Map<String,Object> returnMap = new HashMap<String,Object>();
		/*将设备信息数据写入到数据库*/
		WebDeviceManage dm = new WebDeviceManage();
		dm.setDeviceCode(param.get("deviceCode").toString());
		String deviceArea= param.get("deviceArea").toString();
		dm.setDeviceArea(deviceArea);
		String deviceCity= param.get("deviceCity").toString();
		dm.setDeviceCity(deviceCity);
		dm.setDeviceClientVersionNum(param.get("deviceClientVersionNum").toString());
		dm.setDeviceIp(param.get("deviceIp").toString());
		String deviceMac = param.get("deviceMac").toString();
		dm.setDeviceMac(deviceMac);
		String province= param.get("deviceProvince").toString();
		dm.setDeviceProvince(province);
		dm.setDeviceState(param.get("deviceState").toString());
		dm.setDeviceType(param.get("deviceType").toString());
//		String schoolName = param.get("schoolName").toString();
//		dm.setSchool_name(schoolName);
		dm.setIsvalid(WebDeviceManage.DEVICE_ISVALID_YES);
		String schoolId = param.get("schoolId").toString();//*改动
		dm.setSchoolId(schoolId);
		dm.setCreateTime(new Date());
		dm.setModifyTime(new Date());
		dm.setModifyId(param.get("modify_id").toString());
		
		/* 2018-08-10 David
		 * 插入之前判读  改mac地址信息是否存在
		 * （1）不存在，则插入
		 * （2）存在，则更新之前的状态，即将isvalid设定为禁用
		 * */
		WebDeviceManage dmT = new WebDeviceManage();
		dmT.setDeviceMac(deviceMac);
		List<WebDeviceManage> dmList = webDeviceManageMapper.findSelective(dmT);
		if ((dmList != null) && (dmList.size() > 0)) {// 说明已经存在
			dmT = dmList.get(0);
			dmT.setIsvalid(WebDeviceManage.DEVICE_ISVALID_NO);
			webDeviceManageMapper.updateByPrimaryKeySelective(dmT);
		}
		
		webDeviceManageMapper.insert(dm);
		String deviceId = dm.getId();
		
		String drId = inDeviceRecord(param);
		
		/*向班级设备中插入数据*/
		WebClassDevice classDevice = new WebClassDevice();
		//String classname= param.get("classname").toString();
		//classDevice.setClassName(classname);
		classDevice.setDeviceId(deviceId);//插入后的结果
		//classDevice.setDeviceCode(param.get("deviceCode").toString());
		String classid = param.get("classid").toString();
		classDevice.setClassId(classid);//*改动
		
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
		dm.setDeviceCode(deviceCode);//不变
		String deviceArea= param.get("deviceArea").toString();//变
		dm.setDeviceArea(deviceArea);
		String deviceCity= param.get("deviceCity").toString();//变
		dm.setDeviceCity(deviceCity);
		dm.setDeviceClientVersionNum(param.get("deviceClientVersionNum").toString());
		dm.setDeviceIp(param.get("deviceIp").toString());
		dm.setDeviceMac(param.get("deviceMac").toString());
		String province= param.get("deviceProvince").toString();//变
		dm.setDeviceProvince(province);
		dm.setDeviceState(param.get("deviceState").toString());
		dm.setDeviceType(param.get("deviceType").toString());
		//String schoolName = param.get("schoolName").toString();//变
		//dm.setSchoolName(schoolName);
		dm.setIsvalid("0");
		String schoolId = param.get("schoolId").toString();//变
		dm.setSchoolId(schoolId);
//		dm.setCreate_time(new Date());
//		dm.setModify_time(new Date());
		//获取设备id
		WebDeviceManage tempDM = new WebDeviceManage();
		tempDM.setDeviceCode(deviceCode);
		List<WebDeviceManage> tempWDMList = webDeviceManageMapper.findSelective(tempDM);
		dm.setId(tempWDMList.get(0).getId());
		
		//需要记录id
		webDeviceManageMapper.updateByPrimaryKeySelective(dm);
		String deviceId = dm.getId();
		
		String drId = inDeviceRecord(param);
		
		/*向班级设备中插入数据*/
		WebClassDevice classDevice = new WebClassDevice();
		//String classname= param.get("classname").toString();
		//classDevice.setClassName(classname);//变
		classDevice.setId(deviceId);//插入后的结果
		//classDevice.setDeviceCode(param.get("deviceCode").toString());//不变
		String classid = param.get("classid").toString();
		classDevice.setClassId(classid);//变
		//获取id
		WebClassDevice tempCD = new WebClassDevice();
		//tempCD.setDeviceCode(deviceCode);  TODO
		tempCD.setDeviceId(deviceId);//有设备编号修改成为设备id
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
				//WebDeviceManage webDeviceManage = webDeviceManageMapper.selectByPrimaryKey(idsArr[i]);
				
				WebClassDevice webClassDevice = new WebClassDevice(); 
				webClassDevice.setId(idsArr[i]);
				//webClassDevice.setDeviceCode(webDeviceManage.getDeviceCode());
				webClassDeviceMapper.deleteBySelective(webClassDevice);
				String id = idsArr[i];
				webDeviceManageMapper.deleteByPrimaryKey(id);
			}
		}
		
		
	}

	@Override
	public Integer findDeviceStateByClassId(String classId) {
		return webDeviceManageMapper.findDeviceStateByClassId(classId);
	}

	@Override
	public List<Map<String, Object>> getDeviceByClassId(String classId) {
		// TODO
		return webDeviceManageMapper.getDeviceByClassId(classId);
	}
	
}
