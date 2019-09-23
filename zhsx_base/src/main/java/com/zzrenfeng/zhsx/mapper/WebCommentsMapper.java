package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebComments;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.WebComments
 */

public interface WebCommentsMapper extends BaseMapper<WebComments>{


	/**
	 * 批量更改显示状态
	 * @param ids
	 * @param isShown
	 */
	void updateBatch(Map<String, Object> hm);

	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteBatch(List<String> ids);



}

