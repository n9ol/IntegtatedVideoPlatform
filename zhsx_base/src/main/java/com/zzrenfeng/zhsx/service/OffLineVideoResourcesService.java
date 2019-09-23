package com.zzrenfeng.zhsx.service;

import java.text.ParseException;
import java.util.List;

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
	void append(OffLineVideoResources offLineVideoResources);

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

	/**
	 * 通过相差时间为1s的11个id集合查询录制的评估视频信息
	 * 
	 * @param ids
	 * @return
	 */
	OffLineVideoResources getOffLineVideoResourcesByIds(List<String> ids);

	/**
	 * 通过当前id获取11个相差1s的连续的id集合
	 * 
	 * @param id
	 * @return
	 */
	List<String> listIds(String id) throws ParseException;
	
	/**
	 * @功能描述：重写updateByKeySelective方法，避免日志拦截器拦截，导致调用者因与之返回类型不一致而报错
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月6日 下午1:52:18
	 * 
	 * @param offLineVideoResources
	 * @return
	 */
	int reUpdateByKeySelective(OffLineVideoResources offLineVideoResources);

}
