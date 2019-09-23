package com.zzrenfeng.zhsx.model;

import java.util.List;

/**
 * 课程时间扩展类
 * @author 田杰熠
 *
 */
public class LoClassTimeExtend {
	
	List<LoClassTime> loClassTimeList;

	public List<LoClassTime> getLoClassTimeList() {
		return loClassTimeList;
	}

	public void setLoClassTimeList(List<LoClassTime> loClassTimeList) {
		this.loClassTimeList = loClassTimeList;
	}

	@Override
	public String toString() {
		return "LoClassTimeExtend [loClassTimeList=" + loClassTimeList + "]";
	}
	
	
	
	
}
