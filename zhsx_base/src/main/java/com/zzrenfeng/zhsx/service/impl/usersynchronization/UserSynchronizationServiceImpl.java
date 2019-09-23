package com.zzrenfeng.zhsx.service.impl.usersynchronization;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUser;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysUserService;
import com.zzrenfeng.zhsx.service.usersynchronization.UserSynchronizationService;

@Service
public class UserSynchronizationServiceImpl implements UserSynchronizationService {

	@Resource
	private UserService userService;
	@Resource
	private ESysUserService eSysUserService;

	@Override
	public void updatePasswordSynchronization(String userCode, String newPassword) {
		userService.recomposeUserPassword(userCode, newPassword);
		ESysUser eSysUser = eSysUserService.findByUserCode(userCode);
		if (eSysUser != null) {
			eSysUserService.updateUserPassword(eSysUser.getId(), newPassword, userCode, eSysUser.getSalt());
		}
	}

}
