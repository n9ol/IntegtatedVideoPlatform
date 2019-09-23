package com.zzrenfeng.zhsx.service.impl.eclassbrand.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.mapper.eclassbrand.sys.ESysPermissionMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysPermission;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysPermissionService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:06
 * @see com.zzrenfeng.ESysPermission.model.sys.com.zzrenfeng.classbrand.model.sys.SysPermission
 */

@Service
public class ESysPermissionServiceImpl implements ESysPermissionService {

	@Resource
	private ESysPermissionMapper esysPermissionMapper;

	@Resource
	private Environment env;

	@Override
	public List<ESysPermission> getPermissionsByRoldId(String roldId) {
		return esysPermissionMapper.getPermissionsByRoldId(roldId);
	}

	@Override
	public List<ESysPermission> getPermissionsByUserId(String userId) {
		return esysPermissionMapper.getPermissionsByUserId(userId);
	}

	@Override
	public void insterLevelOneMenu(String name, int available) {
		ESysPermission sysPermission = new ESysPermission();
		sysPermission.setAvailable(available);
		sysPermission.setName(name);
		sysPermission.setResourceType(ESysPermission.RESOURCE_TYPE_MENU);
		sysPermission.setParentId("0");
		sysPermission.setParentIds("0/");
		insert(sysPermission);
	}

	// @Override
	// public Map<String, Object> findEachLevelPermissions(List<SysPermission>
	// sysPermissions) {
	// Map<String, Object> hm = new HashMap<String, Object>();
	//
	// List<SysPermission> LevelOneMenu =
	// sysPermissions.stream().filter(isLevelOneMenu).collect(Collectors.toList());
	// List<SysPermission> LevelTwoMenu =
	// sysPermissions.stream().filter(isLevelTwoMenu).collect(Collectors.toList());
	// List<SysPermission> button =
	// sysPermissions.stream().filter(isButton).collect(Collectors.toList());
	//
	// hm.put("LevelOneMenu", LevelOneMenu);
	// hm.put("LevelTwoMenu", LevelTwoMenu);
	// hm.put("button", button);
	//
	// return hm;
	// }

	@Override
	public List<ESysPermission> findAllAvailable(String roleId) {
		return esysPermissionMapper.findAllAvailable(roleId);
	}

	@Override
	public ESysPermission insert(ESysPermission t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysPermission updateByKey(ESysPermission t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByKeySelective(ESysPermission t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysPermission findByKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysPermission> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysPermission> findSelective(ESysPermission t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<ESysPermission> findPageSelective(ESysPermission t, int p, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findEachLevelPermissions(List<ESysPermission> sysPermissions) {
		// TODO Auto-generated method stub
		return null;
	}

	// /**
	// * 是否为一级菜单
	// */
	// Predicate<SysPermission> isLevelOneMenu = (permission) -> {
	// Boolean levelOne = permission.getParentId().equals("0");
	// Boolean menu = permission.getResourceType().equals(resourceTypeMenu);
	// return levelOne && menu;
	// };
	//
	// /**
	// * 是否为二级菜单
	// */
	// Predicate<SysPermission> isLevelTwoMenu = (permission) -> {
	// Boolean levelTwo = !(permission.getParentId().equals("0"));
	// Boolean menu = permission.getResourceType().equals(resourceTypeMenu);
	// return levelTwo && menu;
	// };
	//
	// /**
	// * 是否为按钮
	// */
	// Predicate<SysPermission> isButton = (permission) -> {
	// return permission.getResourceType().equals(resourceTypeButton);
	// };

}
