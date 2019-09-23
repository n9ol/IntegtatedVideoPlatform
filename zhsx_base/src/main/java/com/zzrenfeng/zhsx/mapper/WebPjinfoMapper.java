package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebPjinfo;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-23 09:27:09
 * @see com.zzrenfeng.zhsx.service.WebPjinfo
 */

public interface WebPjinfoMapper extends BaseMapper<WebPjinfo> {

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
	 * @param webpj
	 */
	void insertorupdata(WebPjinfo webpj);

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
	List<WebPjinfo> listWebPjinfo(String bak2);
}
