package com.zzrenfeng.zhsx.controller.eclassbrand;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysPermission;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUser;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysPermissionService;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysUserService;

@Validated
@Controller
public class EClassbrandSysController extends BaseController {

	@Resource
	private ESysUserService sysUserService;
	@Resource
	private ESysPermissionService eSysPermissionService;

	/**
	 * 获取对象权限菜单 class_brand数据库
	 * 
	 * @param model
	 * @param menuType
	 * @return
	 */
	@RequestMapping("/listEclassBrandSysPermissionsMenu")
	public String listEclassBrandSysPermissionsMenu(Model model, @RequestParam String menuType) {
		model.addAttribute("menuType", menuType);
		ESysUser sysUser = sysUserService.findByUserCode(getUserCode());
		if (sysUser == null) {
			return "/admin/t/adminLeft";
		}
		List<ESysPermission> sysPermissions = eSysPermissionService.getPermissionsByUserId(sysUser.getId());
		List<ESysPermission> firstSysPermissions = new ArrayList<>();
		for (ESysPermission sysPermission : sysPermissions) {
			boolean isBase = "menu".equals(sysPermission.getResourceType()) && "0".equals(sysPermission.getParentId())
					&& menuType.equals(sysPermission.getUrl());
			if (isBase) {
				firstSysPermissions.add(sysPermission);
			}
		}
		model.addAttribute("firstSysPermissions", firstSysPermissions);
		model.addAttribute("sysPermissions", sysPermissions);
		return "/admin/t/adminLeft";
	}
}
