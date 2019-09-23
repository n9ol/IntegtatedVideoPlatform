package com.zzrenfeng.zhsx.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-08 11:23:59
 * @see com.zzrenfeng.zhsx.service.OffLineVideoResources
 */
public interface OffLineVideoResourcesService extends BaseService<OffLineVideoResources> {

	/**
	 * 远程更新数据库接口
	 * 
	 * @param videoResources
	 */
	void renewalData(OffLineVideoResources videoResources);

	/**
	 * 批量删除课件资源
	 * 
	 * @param ids
	 */
	void batchDelVideoRes(List<String> ids);

	/**
	 * 发布离线视频更新用户经验值
	 * 
	 * @param userId
	 * @param pubId
	 */
	void updateUserExp(String userId, String pubId);

	/**
	 * 更新浏览量
	 */
	void updatePageView(String id);

	/**
	 * 设备自动录制视频，转码 - 添加数据库信息
	 * 
	 * @param offLineVideoResources
	 */
	void append(HttpServletRequest request, OffLineVideoResources offLineVideoResources) throws InterruptedException;

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

}
