package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebAnswer;
import com.zzrenfeng.zhsx.model.WebNews;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-17 10:36:53
 * @see com.zzrenfeng.zhsx.service.WebNews
 */

public interface WebNewsMapper extends BaseMapper<WebNews>{



	/**
	 * 进入新闻中心首页新闻
	 */
	Page<WebNews> findAll(WebNews n);
	/**
	 * 新闻排行
	 */
	List<WebNews> getTop(WebNews n);

	/**
	 * 远程更新数据库接口
	 * @param videoResources
	 */
	void renewalData(WebNews videoResources);
}

