package com.zzrenfeng.zhsx.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
//		removeDuplicate4Set();
//		splitString();
		
	}
	
	/**
	 * @功能描述：拆分字符串为单个字符
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月7日 下午2:54:33
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static void splitString() {
		String str = "a,b，c,a";
		String[] strs = str.split(",|，");
		System.out.println("--------------------->>>>长度：" + strs.length);
		System.out.println("--------------------->>>>1和4是否一样：" + strs[0].equals(strs[3]));
		for(String s : strs) {
			System.out.println("--------------------->>>>"+ s);
		}
		
		System.out.println("-----------------------------------------");
		
		String str1 = "2";
		String[] str1s = str1.split(",|，");
		System.out.println("------------------------->>>>str1s[0]=" + Integer.valueOf(str1s[0]));
//		System.out.println("------------------------->>>>strs.length=" + str1s.length);
	}
	
	/**
	 * @功能描述：集合去重
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月7日 下午2:47:11
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static void removeDuplicate4Set() {
		List<Object> list = new ArrayList<Object>();
		list.add(null);
		list.add(1);
		list.add(2);
		list.add(1);
		list.add(3);
		list.add(null);
		list.add("");
		list.add("");
		list.add("a");
		list.add("b");
		list.add("a");
		list.add("c");
		for (Object object : list) {
			System.out.println("=================[去重前的List]>>>>" + String.valueOf(object));
		}
		System.out.println("------------------------------------------------------");
		Set<Object> set = new LinkedHashSet<Object>();
		set.addAll(list);
		list.clear();
		list.addAll(set);
		for (Object object : list) {
			System.out.println("=================[去重后的List]>>>>" + String.valueOf(object));
		}
		System.out.println("------------------------------------------------------");
		for (Object object : set) {
			System.out.println("=================[Set]>>>>" + String.valueOf(object));
		}
	}
	
	
}
