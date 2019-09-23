package com.zzrenfeng.zhsx.base;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.service.SysDictService;

/**
 * 自定义异常处理器
 * 
 * @author 田杰熠
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionResolver {

	@Resource
	private SysDictService sysDictService;

	/**
	 * 全局异常捕捉处理
	 * 
	 * @param ex
	 * @return
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		String[] deciceArray = new String[] { "android", "iphone", "windows phone" };
		String requestHeader = request.getHeader("user-agent").toLowerCase();
		if (requestHeader != null) {
			for (int i = 0; i < deciceArray.length; i++) {
				if (requestHeader.indexOf(deciceArray[i]) > 0) {
					return androidios(request, e);
				}
			}
		}
		return resolveException(request, response, e);
	}

	/**
	 * 处理结果 - 返回到错误页面
	 * 
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Exception e) {

		String message = null;
		ExceptionMessage em = null;
		if (e instanceof ExceptionMessage) {
			em = (ExceptionMessage) e;
		} else {
			em = new ExceptionMessage("未知异常");
		}
		message = em.getMessage();
		String skinName = sysDictService.getSkinName();

		ModelAndView Mav = new ModelAndView();
		Mav.addObject("em", em);
		Mav.addObject("message", message);
		Mav.addObject("skinName", skinName);
		Mav.setViewName("error");
		return Mav;
	}

	/**
	 * 处理结果 - 以_json数据返回
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ResponseBody
	public AndroidiosModel androidios(HttpServletRequest request, Exception e) {
		Map<String, Object> map = new HashMap<>();

		String message = null;
		ExceptionMessage em = null;
		if (e instanceof ExceptionMessage) {
			em = (ExceptionMessage) e;
		} else {
			em = new ExceptionMessage("未知异常");
		}
		message = em.getMessage();

		map.put("msg", message);
		map.put("url", request.getRequestURL());

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(map);
		return androidiosModel;
	}

}
