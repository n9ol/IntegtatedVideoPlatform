package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebPjinfo;


/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-22 18:27:41
 * @see com.zzrenfeng.zhsx.service.WebPjinfo
 */
public interface WebPjinfoService extends BaseService<WebPjinfo> {


	/**
	 * 获得评估平均分展示图数据
	 * @param pgId
	 * @return
	 */
	List<WebPjinfo> getPgAverageDraw(String pgId);

	/**
	 * 添加或更新数据(课前评估得分)
	 * @param userId
	 * @param pgId
	 * @param pgPjInfoId
	 * @param score
	 */
	void insertorupdata(String userId,String pgId,String pgPjInfoId,String score);

}
