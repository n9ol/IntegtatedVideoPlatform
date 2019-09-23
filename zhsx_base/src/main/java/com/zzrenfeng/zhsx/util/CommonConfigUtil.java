package com.zzrenfeng.zhsx.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 获取配置信息
 * 
 * @author Administrator
 *
 */
public class CommonConfigUtil {

	private CommonConfigUtil() {
	}

	public static final Logger LOGGER = Logger.getLogger(CommonConfigUtil.class);

	private static class CommonConfigUtilHolder {

		private static final CommonConfigUtil INSTANCE = new CommonConfigUtil();

	}

	public static final CommonConfigUtil getInstance() {
		return CommonConfigUtilHolder.INSTANCE;

	}

	public static String getConf(String confName) {
		String conf = null;
		Properties props = new Properties();
		InputStream in;
		in = CommonConfigUtil.class.getResourceAsStream("/commonConfig.properties");
		try {
			props.load(in);
		} catch (Exception e) {
			LOGGER.info("加载【commonConfig.properties】文件出错!!");
		}

		conf = props.get(confName) == null ? null : props.get(confName).toString();
		return conf;

	}

	public static String getConf(String confileName, String confName) {
		String conf = null;
		Properties props = new Properties();
		InputStream in;
		in = CommonConfigUtil.class.getResourceAsStream("/" + confileName);
		try {
			props.load(in);
		} catch (Exception e) {
			LOGGER.info("加载【" + confileName + "】文件出错!!");
		}

		conf = props.get(confName) == null ? null : props.get(confName).toString();
		return conf;

	}

}
