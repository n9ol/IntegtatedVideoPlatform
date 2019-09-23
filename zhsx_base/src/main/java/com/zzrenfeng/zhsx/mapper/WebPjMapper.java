package com.zzrenfeng.zhsx.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebPj;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-23 09:27:38
 * @see com.zzrenfeng.zhsx.service.WebPj
 */

public interface WebPjMapper extends BaseMapper<WebPj> {

	/**
	 * 批量添加评估信息
	 */
	void insterPgMessage(WebPj webPj);

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
	 * @return
	 */
	Page<Map<String, String>> findPjRecord(String userId);

	/**
	 * 教师成长体系查询（课前中后评估平均分）
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> findGrowth(String userId);

	/**
	 * 教师排行榜-评课情况统计（教研员）
	 * 
	 * @param paramMap
	 * @return
	 */
	HashMap<String, Integer> getStaffCountContributionEvaluation(Map<String, Object> paramMap);

	/**
	 * 排行榜统计信息（教研员） NO1（课前备课） 该功能包含3张图： 1.本教研员所评 -- 最近一周 - 不同活跃度分析 / 教师
	 * 获取评论的所有老师
	 */
	List<Map<String, String>> getStaffCommonentAllTeacher(Map<String, Object> paramMap);

	/**
	 * 学生回答问题次数 ，学生回答问题时长
	 * 
	 * @param string
	 * @return Map<String, Object>
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

	List<Map<String, Object>> findDukeList(Map<String, Object> paramMap);

	HashMap<String, Integer> getLeaderCountContributionEvaluation(Map<String, Object> paramMap);

	List<Map<String, Object>> getLeaderCommonentAVGAndEXP(Map<String, Object> paramMap);

	List<Map<String, Object>> getLeaderCommonentAVGAndEXPMiddle(Map<String, Object> paramMap);

	/**
	 * 删除用户的评估记录
	 * 
	 * @param currUserId
	 * @param pgId
	 */
	void deleteWebPg(WebPj webPj);
}
