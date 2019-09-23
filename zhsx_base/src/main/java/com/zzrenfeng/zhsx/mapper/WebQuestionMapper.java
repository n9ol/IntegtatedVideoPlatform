package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebQuestion;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-06-02 14:33:25
 * @see com.zzrenfeng.zhsx.service.WebQuestion
 */

public interface WebQuestionMapper extends BaseMapper<WebQuestion> {

	/**
	 * 批量修改状态
	 */
	public void updateBatch(Map<String, Object> hm);

	/**
	 * 批量删除
	 */
	public void deleteBatch(Map<String, Object> hm);

	/**
	 * 获得前台页面最新的解答
	 * 
	 */
	public List<WebQuestion> getNew(WebQuestion w);

	/**
	 * 获得前台页面未解答问题
	 * 
	 */
	Page<WebQuestion> getNotAnswer(WebQuestion w);

	/**
	 * 精选问题集
	 */
	public List<WebQuestion> getBest(WebQuestion w);

	/**
	 * 最新提问(包括我的提问)
	 */
	public Page<WebQuestion> findNewQue(WebQuestion que);
}
