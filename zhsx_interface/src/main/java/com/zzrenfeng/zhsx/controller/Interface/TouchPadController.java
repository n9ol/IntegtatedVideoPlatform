package com.zzrenfeng.zhsx.controller.Interface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.model.TouchPad;
import com.zzrenfeng.zhsx.service.TouchPadService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.JsonUtils;

/**
 * 触控板
 * @author David
 * 插入、删除、查询
 */
@Controller
@RequestMapping("/touchPad")
public class TouchPadController {

	// 自定义属性编辑器
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// Date.class必须是与controler方法形参pojo属性一致的date类型，这里是java.util.Date
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	@Resource
	private TouchPadService touchPadService;
	
	
	
	
	/**
	 * http://localhost:8080/zhsx/touchPad/inTouchPad?data=[{"class_code":"001","hand_written_board_code":"001","time_code":"001","data":"001"}]
	 * 
	 * produces 是将字符串转换成json格式的数据，用的是
	 * <spring.version>3.1.1.RELEASE</spring.version>
	 * 
	 * @param data 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/inTouchPad", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> inTouchPad(@RequestBody String data, HttpServletRequest request) {
		/* 获取通过url传递过来的数据 */
		/*
		 * try { data = new String(data.getBytes("ISO-8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		System.out.println("I AM COME inTouchPad().......");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.length()<=0) {
			returnMap.put("errorCode", "002data");// data的 数据为空
			return returnMap;
		}
		
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

		TouchPad touchPad = new TouchPad();
		/* 获取数据加入非空判断 */
		Object class_code = dataMap.get("class_code");
		Object hand_written_board_code = dataMap.get("hand_written_board_code");
		Object time_code = dataMap.get("time_code");
		Object fieldData = dataMap.get("data");
		
		/* 改动 */
		if ((null == class_code) || (null == hand_written_board_code) || (null == time_code) || (null == fieldData)) {
			returnMap.put("errorCode", "005data");// data中的数据项有为空的
			return returnMap;
		}
		
		touchPad.setClass_code(class_code.toString());
		touchPad.setHand_written_board_code(hand_written_board_code.toString());
		touchPad.setTime_code(time_code.toString());
		touchPad.setData(fieldData.toString());
		touchPad.setUpdate_date(new Date());
		touchPad.setCreate_date(new Date());
		
		touchPadService.inTouchPad(touchPad);//躲过shiro的验证
		returnMap.put("errorCode", "0success");//成功
		return returnMap;
	}
	
	/**
	 * http://localhost:8080/zhsx/touchPad/deTouchPad?data=[{"id":"b089eeba-7e72-11e8-8b31-001c25d63ebd"}]
	 * @param data
	 * 
	 * get请求时用：
	 * @RequestParam(value = "data", required = false) String data, HttpServletRequest request
	 * post请求时用：
	 * @RequestBody(required = false) String data
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deTouchPad", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> deTouchPad(
			@RequestBody String data, HttpServletRequest request) {
		/* 获取通过url传递过来的数据 */
		System.out.println("I AM COME deTouchPad().......");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.length()<=0) {
			returnMap.put("errorCode", "002data");// data的 数据为空
			return returnMap;
		}
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
		Object id = dataMap.get("id");
		/* 改动 */
		if (null == id) {
			returnMap.put("errorCode", "005data");// data中的数据项有为空的
			return returnMap;
		}
		
		touchPadService.deByKey(id.toString());
		returnMap.put("errorCode", "0success");//成功
		return returnMap;
	}
	/**
	 * http://localhost:8080/zhsx/touchPad/selTouchPad?data=[{"class_code":"002","hand_written_board_code":"002","time_code":"002","data":"002"}]
	 * @param data 选择的查询条件,       
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selTouchPad", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTouchPad(
			@RequestBody String data, HttpServletRequest request) {
		/* 获取通过url传递过来的数据 */
		System.out.println("I AM COME selTouchPad().......");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.length()<=0) {
			returnMap.put("errorCode", "002data");// data的 数据为空
			return returnMap;
		}
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

		TouchPad touchPad = new TouchPad();
		/* 获取数据加入非空判断 */
		Object class_code = dataMap.get("class_code");
		Object hand_written_board_code = dataMap.get("hand_written_board_code");
		Object time_code = dataMap.get("time_code");
		Object fieldData = dataMap.get("data");
		Object update_date = dataMap.get("update_date");
		
		/* 改动 */
		if ((null != class_code) && (!class_code.toString().equals("")) ) {
			touchPad.setClass_code(class_code.toString());
		}
		if ((null != hand_written_board_code) && (!hand_written_board_code.toString().equals("")) ) {
			touchPad.setHand_written_board_code(hand_written_board_code.toString());
		}
		if ((null != time_code) && (!time_code.toString().equals("")) ) {
			touchPad.setTime_code(time_code.toString());
		}
		if ((null != fieldData) && (!fieldData.toString().equals("")) ) {
			touchPad.setData(fieldData.toString());
		}
		if ((null != update_date) && (!update_date.toString().equals("")) ) {
			//update_date
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.simpleLong);
				Date date = sdf.parse(update_date.toString());
				touchPad.setUpdate_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		List<TouchPad> touchPadList = touchPadService.findTouchPad(touchPad);
		
		returnMap.put("errorCode", "0success");//成功
		returnMap.put("touchPadList", touchPadList);//根据条件查询相关的数据
		return returnMap;
	}
	/**
	 * http://localhost:8080/zhsx/touchPad/selTouchPad?data=[{"class_code":"002"}]
	 * @param data 选择的查询条件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selHandWrittenBoardCodeByClassCode", method=RequestMethod.POST)
	public @ResponseBody List<String> selHandWrittenBoardCodeByClassCode(
			@RequestBody String data, HttpServletRequest request) {
		
		List<String> hwbcList = new ArrayList<String>();
		/* 获取通过url传递过来的数据 */
		System.out.println("I AM COME selHandWrittenBoardCodeByClassCode().......");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (data == null || data.length()<=0) {
			returnMap.put("errorCode", "002data");// data的 数据为空
			//return returnMap;
			hwbcList.add("002data");
			return hwbcList;
		}
		List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
		if (dataList == null || dataList.size() <= 0) {
			returnMap.put("errorCode", "003data");// data转换成json后的 数据为空
			//return returnMap;
			hwbcList.add("003data");
			return hwbcList;
		}
		Map<String, Object> dataMap = dataList.get(0);
		if (dataMap == null) {
			returnMap.put("errorCode", "004data");// data转换成json后，获取内容不存在
//			return returnMap;
			hwbcList.add("004data");
			return hwbcList;
		}

		TouchPad touchPad = new TouchPad();
		/* 获取数据加入非空判断 */
		Object class_code = dataMap.get("class_code");
		
		/* 改动 */
		if ((null != class_code) && (!class_code.toString().equals("")) ) {
			touchPad.setClass_code(class_code.toString());
		}
		
		
		//hwbcList.add("005data");
		hwbcList = touchPadService.findHandWrittenBoardCodeByClassCode(touchPad);
		
		//returnMap.put("errorCode", "0success");//成功
		//returnMap.put("", hwbcList);//根据条件查询相关的数据
		//return returnMap;
		return hwbcList;
	}
	
	  /**
	   * http://localhost:8080/zhsx/touchPad/selTouchPadByCalssAndHWB?data=[{"class_code":"002","hand_written_board_code":"002"}]
	   * @param 让修改为 只返回一条数据 data
	   * @param request
	   * @return
	   */
	  @RequestMapping(value = "/selTouchPadByCalssAndHWB", method=RequestMethod.POST)
	  public @ResponseBody  List<String> selTouchPadByCalssAndHWB(
	      @RequestBody String data, HttpServletRequest request) {
	    /* 获取通过url传递过来的数据 */
	    System.out.println("I AM COME selTouchPadByCalssAndHWB().......");
	    
	    List<String> hwbcList = new ArrayList<String>();
	    
	    //Map<String, Object> returnMap = new HashMap<String, Object>();
	    if (data == null || data.length()<=0) {
	      //returnMap.put("errorCode", "002data");// data的 数据为空
	      //return returnMap;
	      hwbcList.add("002data");
	      return hwbcList;
	    }
	    List<LinkedHashMap<String, Object>> dataList = JsonUtils.json2List(data);
	    if (dataList == null || dataList.size() <= 0) {
	      //returnMap.put("errorCode", "003data");// data转换成json后的 数据为空
	      //return returnMap;
	      hwbcList.add("003data");
	      return hwbcList;
	    }
	    Map<String, Object> dataMap = dataList.get(0);
	    if (dataMap == null) {
//	      returnMap.put("errorCode", "004data");// data转换成json后，获取内容不存在
//	      return returnMap;
	      hwbcList.add("004data");
	      return hwbcList;
	    }

	    TouchPad touchPad = new TouchPad();
	    /* 获取数据加入非空判断 */
	    Object class_code = dataMap.get("class_code");
	    Object hand_written_board_code = dataMap.get("hand_written_board_code");
	    
	    /* 改动 */
	    if ((null != class_code) && (!class_code.toString().equals("")) ) {
	      touchPad.setClass_code(class_code.toString());
	    }
	    if ((null != hand_written_board_code) && (!hand_written_board_code.toString().equals("")) ) {
	      touchPad.setHand_written_board_code(hand_written_board_code.toString());
	    }
	    
	    List<TouchPad> touchPadList = touchPadService.findTouchPad(touchPad);
	    if(touchPadList!=null && touchPadList.size()>0){
	    	data = touchPadList.get(0).getData();
	    }else{
	    	data="";
	    }
	    hwbcList.add(data);
	    //returnMap.put("errorCode", "0success");//成功
	    //returnMap.put("data", data);//根据条件查询相关的数据
	    //return returnMap;
	    return hwbcList;
	  }

	
	
	
}
