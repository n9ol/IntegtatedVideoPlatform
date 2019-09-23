package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.pattern.AnsiEscape;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebAnswer;
import com.zzrenfeng.zhsx.model.WebQuestion;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-05 10:15:08
 * @see com.zzrenfeng.zhsx.service.WebAnswer
 */

public interface WebAnswerMapper extends BaseMapper<WebAnswer>{


	/**
	 * 批量修改状态
	 * 
	 */
	public void updateBatch(Map<String, Object> hm);
	
	/**
	 * 批量删除
	 */
	public void deleteBatch(Map<String, Object> hm);

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

	Page<WebAnswer>  getResolve(WebAnswer w);

	
	/**
	 * 通过问题id获得所有的答案
	 */
	 Page<WebAnswer> getByQid(WebAnswer w);
	
	 /**
	  * 删除问题时批量删除答案
	  * @param hm
	  */
	public void deleteAnswer(Map<String, Object> hm);

	 
	
}

