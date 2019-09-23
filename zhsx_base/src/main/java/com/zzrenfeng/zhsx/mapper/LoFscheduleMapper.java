package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.LoFschedule;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-05-22 15:17:49
 * @see com.zzrenfeng.zhsx.service.LoFschedule
 */

public interface LoFscheduleMapper extends BaseMapper<LoFschedule>{


	/**
	 * 批量插入
	 * @param slist
	 */
	void insertBatch(List<LoFschedule> slist);
	
	/**
	 * 通过直播ID删除
	 * @param zId
	 * @return
	 */
	int deleteByzId(String zId);
	
	/**
	 * 批量删除
	 * @param zIdlist
	 */
	void deletebatch(List<String> zIdlist);
	

	

}

