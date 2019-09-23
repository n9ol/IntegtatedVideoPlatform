package com.zzrenfeng.zhsx.service;

import java.text.ParseException;
import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.CourResource;


/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-27 17:47:36
 * @see com.zzrenfeng.zhsx.service.CourResource
 */
public interface CourResourceService extends BaseService<CourResource> {


	/**
	 * 批量删除资源
	 * @param ids
	 */
	void batchDelCourRes(List<String> ids);
	
	/**
	 * 批量更新课件资源 数据库基本信息
	 * @param courResource
	 * @param edit_id
	 */
	void batchUpdataCourRes(CourResource courResource,String[] edit_id);
	
	/**
	 * 上传课件更新用户经验值
	 * @param userId
	 * @param pubId
	 */
	void addUserExp(String userId,String pubId) throws ParseException;
	
}
