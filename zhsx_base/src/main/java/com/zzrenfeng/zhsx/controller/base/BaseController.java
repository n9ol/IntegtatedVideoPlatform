package com.zzrenfeng.zhsx.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.zzrenfeng.zhsx.model.ShiroUser;

/**
 * 公共Controller类
 * 
 * @author 田杰熠
 *
 */
@Controller
@PropertySource({ "classpath:commonConfig.properties" })
public abstract class BaseController {

	@Resource
	private String fileWebPath;

	@Resource
	private Environment env;

	@ModelAttribute("principal")
	public ShiroUser getShiroUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	@ModelAttribute("fileWebPath")
	public String getFileWebPath() {
		return fileWebPath;
	}

	@ModelAttribute("androidAPKWebPath")
	public String androidAPKWebPath(HttpServletRequest request) {
		String path = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
				+ request.getRequestURI().split("/")[1];
		String androidAPKWebPath = path + "/DownloadApk?filename=" + env.getProperty("androidAPK");
		return androidAPKWebPath;
	}

	@ModelAttribute("iosDownloadPath")
	public String iosDownloadPath() {
		String iosDownloadPath = env.getProperty("ios.download.path");
		return iosDownloadPath;
	}

	@ModelAttribute("EClassBrandPath")
	public String EClassBrandPath() {
		String iosDownloadPath = env.getProperty("EClassBrand.path");
		return iosDownloadPath;
	}

	public boolean isLogined() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	public String getUserId() {
		return getShiroUser().getId();
	}

	public String getUserCode() {
		return getShiroUser().getUserCode();
	}

	public String getUserType() {
		return getShiroUser().getUserType();
	}

	public String getUserSchoolId() {
		return getShiroUser().getSchoolId();
	}

	public String getUserPhone() {
		return getShiroUser().getPhone();
	}

	public String getUserQQ() {
		return getShiroUser().getqQ();
	}

	public String getUserEmail() {
		return getShiroUser().getEmail();
	}

	public String getUserFilePath() {
		return getShiroUser().getFilePath();
	}

	public String getUserPhoto() {
		return getShiroUser().getPhoto();
	}

	public String getUserBak() {
		return getShiroUser().getBak();
	}

	public String getUserBak1() {
		return getShiroUser().getBak1();
	}

	public String getUserBak2() {
		return getShiroUser().getBak2();
	}

	public BaseController() {
		super();
	}

}
