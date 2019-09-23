package com.zzrenfeng.zhsx.controller.Interface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.StudentBehaviorRecord;
import com.zzrenfeng.zhsx.model.StudentBehaviorRecordData;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.StudentBehaviorRecordDataService;
import com.zzrenfeng.zhsx.service.StudentBehaviorRecordService;
import com.zzrenfeng.zhsx.util.JsonUtils;

/**
 * 学生行为分析操作类
 * @author David
 *
 */
@Controller
@RequestMapping("/studentBehaviorAnalysis")
public class StudentBehaviorAnalysisController {

	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private StudentBehaviorRecordService studentBehaviorRecordService;
	@Resource
	private StudentBehaviorRecordDataService studentBehaviorRecordDataService;
	
	/**
	 * 省、市、县、
	 * 1.学校
	 * 2.教室编号
	 * @param request
	 * @param response
	 * @param classId 教室id
	 * @param schoolId 学校id
	 * @return
	 * 请求的url
	 * http://192.168.0.160:8080/zhsx/studentBehaviorAnalysis/getLoScheduleBySC?schoolId=628e9900-952d-11e8-b9d6-001c25d63ebd&classId=713013b4-95fd-11e8-b9d6-001c25d63ebd
	 * 成功时的情况
	 	{
		    "errorCode": "0",
		    "scheduleId": "e39adc26-7ba9-11e9-9d07-001c25d63ebd"
		}
	 * 
	 */
	@ResponseBody
	@RequestMapping("/getLoScheduleBySC")
	public Map<String, Object> getLoScheduleBySC(HttpServletRequest request, HttpServletResponse response,String schoolId, String classId) {
		Map<String, Object> returnMap =  new HashMap<String, Object>();
		
		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setSchoolId(schoolId);
		loSchedule.setClassId(classId);
		List<LoSchedule> loScheduleList = loScheduleService.getLoScheduleBySC(loSchedule);
		if (loScheduleList.size()>0) {
			returnMap.put("scheduleId", loScheduleList.get(0).getId());
			returnMap.put("errorCode", "0");// 数据为空
		} else {
			returnMap.put("errorCode", "1false");// 数据为空
		}
		
		return returnMap;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param data  封装的json数据
	   {
	    "schoolId": "628e9900-952d-11e8-b9d6-001c25d63ebd",
	    "classId": "713013b4-95fd-11e8-b9d6-001c25d63ebd",
	    "scheduleId": "e39adc26-7ba9-11e9-9d07-001c25d63ebd",
	    "data": [
	        {   
	        	"createDate": "2019-05-2 09:13:56",
	            "dataT":[
	            	{
	            	 "serialNumber":"1",
	            	 "behavior":"LB"
	            	},
			        {
			         "serialNumber":"2",
	            	 "behavior":"LH"
	            	},
	            	{
			         "serialNumber":"3",
	            	 "behavior":"OT"
	            	},
			         ......
			        {
			         "serialNumber":"30",
	            	 "behavior":"OT"
	            	}
	
	            ] 
	        },
	        {
	            "createDate": "2019-05-22 09:13:56",
	            "dataT":[
	            	{
	            	 "serialNumber":"1",
	            	 "behavior":"LB"
	            	},
			        {
			         "serialNumber":"2",
	            	 "behavior":"LH"
	            	},
	            	{
			         "serialNumber":"3",
	            	 "behavior":"OT"
	            	},
			         ......
			        {
			         "serialNumber":"30",
	            	 "behavior":"OT"
	            	}
	
	            ] 
	        },
	        {
	            "createDate": "2019-05-22 09:13:56",
	            "dataT":[
	            	{
	            	 "serialNumber":"1",
	            	 "behavior":"LB"
	            	},
			        {
			         "serialNumber":"2",
	            	 "behavior":"LH"
	            	},
	            	{
			         "serialNumber":"3",
	            	 "behavior":"OT"
	            	},
			         ......
			        {
			         "serialNumber":"30",
	            	 "behavior":"OT"
	            	}
	
	            ] 
	        },
	        ......
	        {
	            "createDate": "2019-05-22 09:13:56",
	            "dataT":[
	            	{
	            	 "serialNumber":"1",
	            	 "behavior":"LB"
	            	},
			        {
			         "serialNumber":"2",
	            	 "behavior":"LH"
	            	},
	            	{
			         "serialNumber":"3",
	            	 "behavior":"OT"
	            	},
			         ......
			        {
			         "serialNumber":"30",
	            	 "behavior":"OT"
	            	}
	
	            ] 
	        }
	    ]
	}
	 * 
	 * @RequestParam(value = "data", required = false)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/inStudentBehavior")
	public Map<String, Object> inStudentBehavior(@RequestParam(value = "data")String data, HttpServletRequest request) {
		System.out.println(new Date());
		/**
		 
		 String schoolId
		 String classId
		 String scheduleId
		 */
		Map<String, Object> returnMap =  new HashMap<String, Object>();
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

		/* 获取数据加入非空判断 */
		Object schoolId = dataMap.get("schoolId");
		Object classId = dataMap.get("classId");
		Object scheduleId = dataMap.get("scheduleId");
		Object data1 = dataMap.get("data");//是  List<Map>
		/* 改动 */
		if ((null == schoolId) || (null == classId) || (null == scheduleId) || (null == data1)) {//|| (null == drStartTime)
			returnMap.put("errorCode", "005data");// data中的数据项有为空的
			return returnMap;
		}
		StudentBehaviorRecord sbr = new StudentBehaviorRecord();
		sbr.setSchoolId(schoolId.toString());
		sbr.setClassId(classId.toString());
		sbr.setScheduleId(scheduleId.toString());
		sbr.setCreateDate(new Date());
		studentBehaviorRecordService.inStudentBehaviorRecord(sbr);//TODO
		String sbrId = sbr.getId();//主表的id
		try {
			List<Map<String, Object>> dataTemp = (List<Map<String,Object>>)data1;
			for (int i = 0; i < dataTemp.size(); i++) {
				Map<String, Object> dateMap = dataTemp.get(i);
				Object createDate = dateMap.get("createDate");
				Object dataT = dateMap.get("dataT");
				if((null == createDate) || (null == dataT) ){//判断是否为空
					break;
				}
				
				List<Map<String, Object>> dataTTemp = (List<Map<String,Object>>)dataT;
				for (int j = 0; j < dataTTemp.size(); j++) {
					Map<String, Object> dataTTemps = dataTTemp.get(j);
					Object serialNumber = dataTTemps.get("serialNumber");
					Object behavior = dataTTemps.get("behavior");
					if((null == serialNumber) || (null == behavior) ){//判断是否为空
						break;
					}
					
					StudentBehaviorRecordData sbrd = new StudentBehaviorRecordData();
					sbrd.setSbrId(sbrId);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date createDateT = sdf.parse(createDate.toString());
					sbrd.setCreateTime(createDateT);
					
					sbrd.setNum(Integer.parseInt(serialNumber.toString()));//TODO
					sbrd.setBehavior(behavior.toString());
					studentBehaviorRecordDataService.inStudentBehaviorRecordData(sbrd);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println(new Date());
		
		returnMap.put("errorCode", "0");// 插入数据成功	
		return returnMap;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


