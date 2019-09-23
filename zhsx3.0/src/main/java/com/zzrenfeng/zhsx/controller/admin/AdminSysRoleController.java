package com.zzrenfeng.zhsx.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysPermission;
import com.zzrenfeng.zhsx.model.SysRole;
import com.zzrenfeng.zhsx.model.SysRolePermission;
import com.zzrenfeng.zhsx.service.SysPermissionService;
import com.zzrenfeng.zhsx.service.SysRolePermissionService;
import com.zzrenfeng.zhsx.service.SysRoleService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:49
 * @see com.zzrenfeng.zhsx.controller.SysRole
 */
@Controller
@RequestMapping(value = "/adminSysrole")
public class AdminSysRoleController extends BaseController {

	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysPermissionService sysPermissionService;

	@Resource
	private SysRolePermissionService sysRolePermissionService;

	/**
	 * 进入角色管理页面
	 * 
	 * @param model
	 * @param p
	 * @param sysRole
	 * @return
	 */
	@RequestMapping("/sysrole")
	public String sysrole(Model model, Integer p, SysRole sysRole) {
		if (p == null) {
			p = 1;
		}
		Page<SysRole> pageInfo = sysRoleService.findPageSelective(sysRole, p, 10);
		List<SysRole> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		int pages = pageInfo.getPages();
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("pages", pages);// 总页数
		model.addAttribute("lists", lists);
		model.addAttribute("menuType", sysRole.getRoleType());
		return "/admin/sysrole/sysrole";
	}

	/**
	 * 更新角色信息
	 * 
	 * @param response
	 * @param sysRole
	 */
	@RequestMapping("/updateSysRole")
	public void updateSysRole(HttpServletResponse response, SysRole sysRole) {
		try {
			sysRoleService.updateByKeySelective(sysRole);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 添加权限角色页面
	 * 
	 * @return
	 */
	@RequestMapping("/addSysRole")
	public String addSysRole(String roleType, Model model) {
		model.addAttribute("roleType", roleType);
		return "/admin/sysrole/addSysRole";
	}

	/**
	 * 添加权限角色管理
	 * 
	 * @param response
	 * @param sysRole
	 */
	@RequestMapping("/insterSysrole")
	public void insterSysrole(HttpServletResponse response, SysRole sysRole) {
		try {
			sysRoleService.insert(sysRole);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 编辑权限角色页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editSysRole")
	public String editSysRole(Model model, @RequestParam String id) {
		SysRole sysRole = sysRoleService.findByKey(id);
		model.addAttribute("sysRole", sysRole);
		return "/admin/sysrole/editSysRole";
	}

	/**
	 * 删除角色
	 */
	@RequestMapping("/delSysRole")
	public void delSysRole(HttpServletResponse response, @RequestParam String id) {
		try {
			sysRoleService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 角色授权页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/accreditTree")
	public String accreditTree(Model model, @RequestParam String id, @RequestParam String menuType) {
		SysPermission sysPermission = new SysPermission();
		sysPermission.setAvailable(true);
		List<SysPermission> sysPermissions = sysPermissionService.findSelective(sysPermission);
		model.addAttribute("sysPermissions", sysPermissions);
		List<SysPermission> firstSysPermissions = new ArrayList<>();
		for (SysPermission sysPermission2 : sysPermissions) {
			boolean isBase = "menu".equals(sysPermission2.getResourceType()) && "0".equals(sysPermission2.getParentId())
					&& menuType.equals(sysPermission2.getUrl());
			if (isBase) {
				firstSysPermissions.add(sysPermission2);
			}
		}
		model.addAttribute("firstSysPermissions", firstSysPermissions);

		SysRolePermission sysRolePermission = new SysRolePermission();
		sysRolePermission.setRoleId(id);
		List<SysRolePermission> sysRolePermissions = sysRolePermissionService.findSelective(sysRolePermission);
		model.addAttribute("sysRolePermissions", sysRolePermissions);

		model.addAttribute("id", id);
		return "/admin/sysrole/accreditTree";
	}

	/**
	 * 更改角色权限
	 * 
	 * @param response
	 * @param sysRole
	 */
	@RequestMapping("/updateRolePermission")
	public void updateRolePermission(HttpServletResponse response, String id, String[] permissionIds) {
		try {
			sysRoleService.updateRolePermission(id, permissionIds);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
