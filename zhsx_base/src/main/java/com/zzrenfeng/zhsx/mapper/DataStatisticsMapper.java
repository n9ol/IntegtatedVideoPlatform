package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.DataStatistics;

/**
 * 数据统计
 * @author Administrator
 *
 */
public interface DataStatisticsMapper extends BaseMapper<DataStatistics> {

	
	List<Map<String, Object>> findTeacherContributionOfDay(String teacherId);
	
	List<Map<String, Object>> findTeacherContributionOfWeek(Map<String, Object> m);
	
	List<Map<String, Object>> findTeacherContributionOfMonth(Map<String, Object> m);
	
	List<Map<String, Object>> findTeacherContributionOfSemester(Map<String, Object> m);
	
	List<Map<String, Object>> scoreOfLastOnce(String teacherId);
	List<Map<String, Object>> scoreOfLastOnceKeqian(String pgId);
	
	List<Map<String, Object>> averageScoreOfThisWeek(Map<String, Object> m);
	
	
	List<Map<String, Object>> rankingAverageScoreOfKq(Map<String, Object> m);
	
	List<Map<String, Object>> rankingAverageScoreOfKz(Map<String, Object> m);
	
	List<Map<String, Object>> KzScoreFormTheSameSchoolTeacher(Map<String, Object> m);
	
	List<Map<String, Object>> lastPjTeacher(Map<String, Object> m);
	
	List<Map<String, Object>> lastZxPjTeacher(Map<String, Object> m);
	List<Map<String, Object>> lastZxPjTeacherInfo(Map<String, Object> m);
	List<Map<String, Object>> differentWaysOnScoring(Map<String, Object> m);
	
	List<Map<String, Object>> thisWeekZhScoreTeachers(Map<String, Object> m);
	List<Map<String, Object>> thisWeekZhScore(Map<String, Object> m);
	/**
	 * 查询调查问卷
	 * @param m
	 * @return
	 */
	List<Map<String, Object>> findQuestionnaire(Map<String, Object> m);
	/**
	 * 查询调查问卷题目
	 * @param m
	 * @return
	 */
	List<Map<String, Object>> findQuestionnaireQuestion(Map<String, Object> m);
	/**
	 * 查询调查问卷题目选项
	 * @param m
	 * @return
	 */
	List<Map<String, Object>> findQuestionnaireOption(Map<String, Object> m);
	
	
	/**
	 * 查询调查问卷统计
	 * @param m
	 * @return
	 */
	List<Map<String, Object>> findQuestionnaireSurvey(Map<String, Object> m);
	
	
}
