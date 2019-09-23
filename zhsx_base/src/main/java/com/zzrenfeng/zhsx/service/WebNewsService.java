package com.zzrenfeng.zhsx.service;



import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebNews;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-16 14:48:30
 * @see com.zzrenfeng.zhsx.service.WebNews
 */
public interface WebNewsService extends BaseService<WebNews> {



	/**
	 * 进入新闻中心
	 * 
	 */
	Page<WebNews> findAll(WebNews n ,int p,int pageSize);
	
	/**
	 * 新闻排行
	 */
	List<WebNews> getTop(WebNews n);

	
	/**
	 * 修改新闻访问量 
	 * @param id
	 * @param view
	 */
	void uNewsView(String id,int view);

	/**
	 * 远程更新数据库接口
	 * @param videoResources
	 */
	void renewalData(WebNews videoResources);

}
