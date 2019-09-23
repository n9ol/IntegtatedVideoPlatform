package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebPjinfo;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-22 18:27:41
 * @see com.zzrenfeng.zhsx.service.WebPjinfo
 */
public interface WebPjinfoService extends BaseService<WebPjinfo> {

	/**
	 * 获得评估平均分展示图数据
	 * 
	 * @param pgId
	 * @return
	 */
	List<WebPjinfo> getPgAverageDraw(String pgId);

	/**
	 * 添加或更新数据(课前评估得分)
	 * 
	 * @param userId
	 * @param pgId
	 * @param pgPjInfoId
	 * @param score
	 */
	void insertorupdata(String userId, String pgId, String pgPjInfoId, String score);

	/**
	 * 添加初始化评估项信息
	 * 
	 * @param webPjinfo
	 */
	void insertInitializeWebPjinfo(WebPjinfo webPjinfo);

	/**
	 * 通过 webPjId 获得 WebPjinfo
	 * 
	 * @param webPjId
	 * @return
	 */
	List<WebPjinfo> listWebPjinfo(String webPjId);

	/**
	 * 获取评估项-(无插入后获取)
	 * 
	 * @param currUserId
	 * @param pgId
	 * @param onOff
	 * @param pgType
	 * @param webPjId
	 * @return
	 */
	List<WebPjinfo> listWebPjinfo(String currUserId, String pgId, String onOff, String pgType, String webPjId);
}
