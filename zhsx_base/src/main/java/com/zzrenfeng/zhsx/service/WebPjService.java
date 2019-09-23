package com.zzrenfeng.zhsx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebPj;
import com.zzrenfeng.zhsx.model.WebPjInfoExt;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-22 18:28:32
 * @see com.zzrenfeng.zhsx.service.WebPj
 */
public interface WebPjService extends BaseService<WebPj> {

	/**
	 * 批量添加当前评估信息
	 * 
	 * @param userId
	 *            用户id
	 * @param pgId
	 *            评估对象id
	 * @param onOff
	 *            评估类型(ON 在线 ，OFF 离线)
	 */
	void insterPgMessage(String userId, String pgId, String onOff, String type);

	/**
	 * 计算得分
	 * 
	 * @param webPjInfoExt
	 * @return
	 */
	double calculateTotal(WebPjInfoExt webPjInfoExt);

	/**
	 * 获得评估总分平均分
	 * 
	 * @param pgId
	 * @return
	 */
	Map<String, Object> getPgAverageDraw(String pgId);

	/**
	 * 根据用户查询评估记录
	 * 
	 * @param userId
	 * @param p
	 * @param pageSize
	 * @return
	 */
	Page<Map<String, String>> findPjRecord(String userId, int p, int pageSize);

	HashMap<String, Integer> getStaffCountContributionEvaluation(Map<String, Object> paramMap);

	/**
	 * 排行榜统计信息（教研员） NO1（课前备课） 该功能包含3张图： 1.本教研员所评 -- 最近一周 - 不同活跃度分析 / 教师
	 * 获取评论的所有老师
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Map<String, String>> getStaffCommonentAllTeacher(Map<String, Object> paramMap);

	/**
	 * 学生回答问题次数 ，学生回答问题时长
	 * 
	 * @param string
	 * @return
	 */
	Map<String, Object> getStaffCommonentTeacherCountsAndTimelength(String string);

	List<Map<String, Object>> getStaffCommonentAVGAndEXP(Map<String, Object> paramMap);

	Map<String, Double> getAVGBySchool(Map<String, Object> paramMap);

	List<Map<String, Object>> getStaffCommentSchool(Map<String, Object> paramMap);

	List<Map<String, Object>> getStaffCommonentAVGAndEXPMiddle(Map<String, Object> paramMap);

	Integer getStaffCommentAVGInCourse(Map<String, Object> paramMap);

	Map<String, Object> getStaffCommentAllTeacherScoreByCurrentWeek(Map<String, Object> paramMap);

	/**
	 * 某个学校周几的三项（前、中、后）平均分数
	 * 
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> getAVGAllBySchool(Map<String, Object> paramMap);

	/**
	 * 1.查询该教研员评论的老师 2.该老师在前3个月等级上升幅度（上升幅度大的老师）
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getStaffCommonentTopTenTeacherByBigRise(Map<String, Object> paramMap);

	/**
	 * 获得评论结果-总评语不为空
	 * 
	 * @return
	 */
	List<WebPj> findSelectiveAndallResultNotNull(WebPj webPj);

	HashMap<String, Integer> getLeaderCountContributionEvaluation(Map<String, Object> paramMap);

	/**
	 * 获取区域所有老师的课前备课评估平均分以及EXP
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getLeaderCommonentAVGAndEXP(Map<String, Object> paramMap);

	/**
	 * 获取区域所有老师的课中评估平均分以及EXP
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getLeaderCommonentAVGAndEXPMiddle(Map<String, Object> paramMap);

	/**
	 * 获取当前用户的总评和一级评估项
	 * 
	 * @param currUserId
	 *            当前用户
	 * @param pgId
	 *            评估对象
	 * @param onOff
	 *            在线离线
	 * @param Bak1
	 *            课前，课中
	 * @return
	 */
	Map<String, Object> getPjInfo(String currUserId, String pgId, String onOff, String pgType);

	/**
	 * 删除用户的评估记录
	 * 
	 * @param currUserId
	 * @param pgId
	 */
	void deleteWebPg(String currUserId, String pgId);

}
