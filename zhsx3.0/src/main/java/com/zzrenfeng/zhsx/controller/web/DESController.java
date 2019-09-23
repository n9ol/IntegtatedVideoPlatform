package com.zzrenfeng.zhsx.controller.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.util.DESUtils;

/**
 * 
 * @Description DES加密解密控制类
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年6月12日 下午3:46:36
 * @see com.zzrenfeng.zhsx.controller.web.DESController
 *
 */
@Controller
public class DESController {

	/**
	 * 获取数据库连接账号密码的密文文本
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCiphertext")
	public Map<String, String> getCiphertext(@RequestParam String username, @RequestParam String password) {
		String encryname = DESUtils.getEncryptString(username);
		String encrypassword = DESUtils.getEncryptString(password);

		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("encrypassword", encrypassword);
		hashMap.put("encryname", encryname);
		return hashMap;
	}

}