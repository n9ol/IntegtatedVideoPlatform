package com.zzrenfeng.zhsx.service;


import java.util.List;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.LoFschedule;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-05-22 15:17:49
 * @see com.zzrenfeng.zhsx.service.LoFschedule
 */
public interface LoFscheduleService extends BaseService<LoFschedule> {

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




}
