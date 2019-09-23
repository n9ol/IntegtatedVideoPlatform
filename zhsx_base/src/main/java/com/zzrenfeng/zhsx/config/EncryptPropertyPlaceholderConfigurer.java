package com.zzrenfeng.zhsx.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.zzrenfeng.zhsx.util.DESUtils;

/**
 * 
 * @Description 自定义数据库连接账号密码加密配置类
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年6月12日 下午3:05:09
 * @see com.zzrenfeng.zhsx.config.EncryptPropertyPlaceholderConfigurer
 *
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// 如果在加密属性名单中发现该属性
		if (isEncryptProp(propertyName)) {
			String decryptValue = DESUtils.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	private boolean isEncryptProp(String propertyName) {
		for (String encryptName : encryptPropNames) {
			if (encryptName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}

}