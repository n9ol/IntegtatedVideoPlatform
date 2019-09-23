package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebQuestion;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-02 14:33:25
 * @see com.zzrenfeng.zhsx.service.WebQuestion
 */
public interface WebQuestionService extends BaseService<WebQuestion> {


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
	 * 获得前台页面最新的解答
	 * 
	 */
	public List<WebQuestion> getNew(WebQuestion w);
	/**
	 * 获得前台页面未解答问题
	 * 
	 */
	Page<WebQuestion> getNotAnswer(WebQuestion w,int p,int pageSize);
	
	/**
	 * 精选问题集
	 */
	public List<WebQuestion> getBest(WebQuestion w);
	
	/**
	 * 最新提问
	 */
	public Page<WebQuestion> findNewQue(WebQuestion que,int p,int pageSize);

}
