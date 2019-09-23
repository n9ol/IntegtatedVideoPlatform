package com.zzrenfeng.zhsx.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtils {

	public String getRandom() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 时间格式
		Date startTimeTemp = new Date();
		String code = sdf.format(startTimeTemp);
		// 随机数
		int MAX = 10000;
		int MIN = 0;
		Random rand = new Random();
		int randNumber = rand.nextInt(MAX - MIN + 1) + MIN;
		code += randNumber;
		return code;
	}

}
