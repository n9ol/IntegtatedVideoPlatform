package com.zzrenfeng.zhsx.service;


import java.util.List;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebComments;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.WebComments
 */
public interface WebCommentsService extends BaseService<WebComments> {

	/**
	 * 批量更改显示状态
	 * @param ids
	 * @param isShown
	 */
	void updateBatch(List<String> ids,String isShown);

	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteBatch(List<String> ids);
	

}
