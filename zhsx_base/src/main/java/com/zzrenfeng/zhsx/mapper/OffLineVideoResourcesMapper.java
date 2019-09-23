package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-08 11:23:59
 * @see com.zzrenfeng.zhsx.service.OffLineVideoResources
 */

public interface OffLineVideoResourcesMapper extends BaseMapper<OffLineVideoResources> {

	/**
	 * 批量删除课件资源
	 * 
	 * @param ids
	 */
	void batchDelVideoRes(List<String> ids);

	/**
	 * 更新浏览量
	 */
	void updatePageView(String id);

	/**
	 * 获得视频播放路径
	 * 
	 * @param id
	 * @return
	 */
	String findVideoPathById(String id);

	/**
	 * 流媒体服务器自动录制 - 添加数据库信息
	 * 
	 * @param offLineVideoResources
	 */
	int appendOffLineVideoResources(OffLineVideoResources offLineVideoResources);

	List<OffLineVideoResources> listOffLineVideoResourcesByIds(List<String> ids);
}
