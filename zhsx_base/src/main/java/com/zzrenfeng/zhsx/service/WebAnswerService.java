package com.zzrenfeng.zhsx.service;



import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebAnswer;
import com.zzrenfeng.zhsx.model.WebQuestion;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-05 10:15:08
 * @see com.zzrenfeng.zhsx.service.WebAnswer
 */
public interface WebAnswerService extends BaseService<WebAnswer> {

	/**
	 * 批量更改显示状态
	 * @param ids
	 * @param ifShow
	 */
	void updateBatch(List<String> ids,String ifShow);

	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteBatch(List<String> ids);

	/**
	 * 设为最佳答案
	 */
	public void updateIfBest(String id);


	/**
	 * 获得前台页面最新的解答
	 * 
	 */
	public List<WebAnswer> getNew(WebAnswer w);


	/**
	 * 获得前台已解答的问题
	 */

	Page<WebAnswer>  getResolve(WebAnswer w , int p, int pageSize);


	/**
	 * 通过问题id获得所有的答案
	 */
	Page<WebAnswer> getByQid(WebAnswer w ,int p,int pageSize);
	 /**
	  * 删除问题时批量删除答案
	  * @param hm
	  */
	public void deleteAnswer(List<String> ids);

	/**
	 * 回答更新用户经验值
	 * @param userId
	 * @param pubId
	 */
	void addUserExp(String userId,String pubId,String type);

	
	
}
