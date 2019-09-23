package com.zzrenfeng.zhsx.controller.Interface;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.service.SysClassroomService;

/**
 * 电子白板-接口
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/whiteboard")
public class WhiteboardController {

	@Resource
	private SysClassroomService sysClassroomService;

	@ResponseBody
	@RequestMapping("/getClassRoom")
	public List<Map<String, Object>> getClassRoom(@RequestParam String schoolId) {
		List<Map<String, Object>> findAllNameAndCode = sysClassroomService.findAllNameAndCode(schoolId);
		return findAllNameAndCode;
	}

}
