package com.zzrenfeng.zhsx.service.usersynchronization;

/**
 * 
 * @Description 综合视讯用户与电子班牌用户同步操作的业务类
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年8月27日 上午9:34:54
 * @see com.zzrenfeng.zhsx.service.usersynchronization.UserSynchronizationService
 *
 */
public interface UserSynchronizationService {

	/**
	 * 修改用户密码(综合视讯电子班牌同步操作)
	 * 
	 * @param userCode
	 * @param newPassword
	 */
	void updatePasswordSynchronization(String userCode, String newPassword);

}
