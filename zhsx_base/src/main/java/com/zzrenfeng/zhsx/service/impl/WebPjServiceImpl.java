package com.zzrenfeng.zhsx.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebPjMapper;
import com.zzrenfeng.zhsx.model.WebPj;
import com.zzrenfeng.zhsx.model.WebPjInfoExt;
import com.zzrenfeng.zhsx.service.WebPjService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-22 18:28:32
 * @see com.zzrenfeng.zhsx.service.impl.WebPj
 */

@Service
public class WebPjServiceImpl extends BaseServiceImpl<BaseMapper<WebPj>, WebPj> implements WebPjService {

	@Resource
	private WebPjMapper webPjMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<WebPj> webPjMapper) {
		super.setBaseMapper(webPjMapper);
	}

	@Override
	public double calculateTotal(WebPjInfoExt webPjInfoExt) {
		int weightSum = 0;
		List<Integer> weights = webPjInfoExt.getWeights();
		for (Integer integer : weights) {
			weightSum += integer;
		}
		double total = 0.0;
		List<Double> totals = webPjInfoExt.getTotals();
		for (int i = 0; i < totals.size(); i++) {
			total += new BigDecimal(totals.get(i)).multiply(
					new BigDecimal(weights.get(i)).divide(new BigDecimal(weightSum), 20, BigDecimal.ROUND_HALF_DOWN))
					.doubleValue();
		}
		return new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@Override
	public double calculateTotalNotWeight(WebPjInfoExt webPjInfoExt) {
		List<Double> totals = webPjInfoExt.getTotals();
		int size = totals.size();
		if (totals == null || size <= 0) {
			return 0;
		}

		double total = 0.0;
		for (Double double1 : totals) {
			total += double1;
		}
		double avgTotal = new BigDecimal(total).divide(new BigDecimal(size), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return avgTotal;
	}

	@Override
	public Map<String, Object> getPgAverageDraw(String pgId) {
		return webPjMapper.getPgAverageDraw(pgId);
	}

	@Override
	public Page<Map<String, String>> findPjRecord(String userId, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return webPjMapper.findPjRecord(userId);
	}

	/**
	 * 调用存储过程
	 */
	@Override
	public HashMap<String, Integer> getStaffCountContributionEvaluation(Map<String, Object> paramMap) {
		return webPjMapper.getStaffCountContributionEvaluation(paramMap);
	}

	/**
	 * 排行榜统计信息（教研员） NO1（课前备课） 该功能包含3张图： 1.本教研员所评 -- 最近一周 - 不同活跃度分析 / 教师
	 * 获取评论的所有老师
	 */
	@Override
	public List<Map<String, String>> getStaffCommonentAllTeacher(Map<String, Object> paramMap) {
		return webPjMapper.getStaffCommonentAllTeacher(paramMap);
	}

	/**
	 * 学生回答问题次数 ，学生回答问题时长
	 * 
	 * @param string
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getStaffCommonentTeacherCountsAndTimelength(String string) {
		return webPjMapper.getStaffCommonentTeacherCountsAndTimelength(string);
	}

	@Override
	public List<Map<String, Object>> getStaffCommonentAVGAndEXP(Map<String, Object> paramMap) {

		return webPjMapper.getStaffCommonentAVGAndEXP(paramMap);
	}

	@Override
	public Map<String, Double> getAVGBySchool(Map<String, Object> paramMap) {
		return webPjMapper.getAVGBySchool(paramMap);
	}

	@Override
	public List<Map<String, Object>> getStaffCommentSchool(Map<String, Object> paramMap) {
		return webPjMapper.getStaffCommentSchool(paramMap);
	}

	@Override
	public List<Map<String, Object>> getStaffCommonentAVGAndEXPMiddle(Map<String, Object> paramMap) {
		return webPjMapper.getStaffCommonentAVGAndEXPMiddle(paramMap);
	}

	@Override
	public Integer getStaffCommentAVGInCourse(Map<String, Object> paramMap) {
		return webPjMapper.getStaffCommentAVGInCourse(paramMap);
	}

	@Override
	public Map<String, Object> getStaffCommentAllTeacherScoreByCurrentWeek(Map<String, Object> paramMap) {
		return webPjMapper.getStaffCommentAllTeacherScoreByCurrentWeek(paramMap);
	}

	/**
	 * 某个学校周几的三项（前、中、后）平均分数
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Map<String, Object> getAVGAllBySchool(Map<String, Object> paramMap) {
		return webPjMapper.getAVGAllBySchool(paramMap);
	}

	/**
	 * 1.查询该教研员评论的老师 2.该老师在前3个月等级上升幅度（上升幅度大的老师）
	 */
	@Override
	public List<Map<String, Object>> getStaffCommonentTopTenTeacherByBigRise(Map<String, Object> paramMap) {
		return webPjMapper.getStaffCommonentTopTenTeacherByBigRise(paramMap);
	}

	@Override
	public List<WebPj> findSelectiveAndallResultNotNull(WebPj webPj) {
		return webPjMapper.findSelectiveAndallResultNotNull(webPj);
	}

	@Override
	public HashMap<String, Integer> getLeaderCountContributionEvaluation(Map<String, Object> paramMap) {
		return webPjMapper.getLeaderCountContributionEvaluation(paramMap);
	}

	@Override
	public List<Map<String, Object>> getLeaderCommonentAVGAndEXP(Map<String, Object> paramMap) {
		return webPjMapper.getLeaderCommonentAVGAndEXP(paramMap);
	}

	@Override
	public List<Map<String, Object>> getLeaderCommonentAVGAndEXPMiddle(Map<String, Object> paramMap) {
		return webPjMapper.getLeaderCommonentAVGAndEXPMiddle(paramMap);
	}

	@Override
	public WebPj getWebPj(String currUserId, String pgId, String onOff) {
		WebPj webPj = new WebPj();
		WebPj webPj1 = new WebPj(currUserId, pgId, onOff);
		webPj1.setAddTime(new Date());
		List<WebPj> webpjs = findSelective(webPj1);
		if (webpjs != null && webpjs.size() > 0) {
			webPj = webpjs.get(0);
		} else {
			webPjMapper.insterPgMessage(webPj1);
			webPj = webPjMapper.selectByPrimaryKey(webPj1.getId());
		}
		return webPj;
	}

	@Override
	public void deleteWebPg(String currUserId, String pgId) {
		WebPj webpj = new WebPj();
		webpj.setPgId(pgId);
		webpj.setUserId(currUserId);
		webPjMapper.deleteWebPg(webpj);
	}

	@Override
	public Page<WebPj> listWebPjResult(WebPj webPj, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return webPjMapper.listWebPjResult(webPj);
	}

	@Override
	public Page<WebPj> listPersonalWebPjRecord(String userId, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return webPjMapper.listPersonalWebPjRecord(userId);
	}

}
