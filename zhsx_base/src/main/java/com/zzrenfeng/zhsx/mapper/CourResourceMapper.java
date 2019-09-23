package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.CourResource;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-27 17:47:36
 * @see com.zzrenfeng.zhsx.service.CourResource
 */

public interface CourResourceMapper extends BaseMapper<CourResource>{


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
	void batchUpdataCourRes(Map<String, Object> hm);

}

