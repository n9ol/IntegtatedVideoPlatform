package com.zzrenfeng.zhsx.util;

import java.util.UUID;

/**
 * 功能：生成UUID
 * author: zhoujincheng
 * create: 2016/4/21 8:22
 */
public class UUIDUtils {

	/**
     * 获得一个UUID
     * <p>
     * return String UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * @功能描述：获取一串32位小写字母UUID
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月20日 下午3:25:29
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     */
    public static String getLowerUUID() {
    	return getUUID().toLowerCase();
    }
    /**
     * @功能描述：获取一串32位大写字母UUID
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月20日 下午3:27:07
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     */
    public static String getUpperUUID() {
    	return getUUID().toUpperCase();
    }

    //获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String... args){
    	
    	System.out.println(UUID.randomUUID().toString());
    	
    }

}
