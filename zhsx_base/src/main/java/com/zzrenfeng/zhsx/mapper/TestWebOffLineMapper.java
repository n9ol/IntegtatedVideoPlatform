package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebOffLine;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-09-09 15:22:25
 * @see com.zzrenfeng.zhsx.service.TestWebOffLine
 */

public interface TestWebOffLineMapper extends BaseMapper<TestWebOffLine>{



/**
 * 按照科目分类参数（schoolId gradeId）
 */
public List<TestWebOffLine> getAllBysubj(TestWebOffLine ol);
/**
 * 根据学科 统计班级平均得分
 * @param ol
 * @return
 */
public TestWebOffLine getClassScore(TestWebOffLine ol);
/**
 * 统计各年级的的平均分
 */
public List<TestWebOffLine> getAvgByGrade(String schoolId);
/**
 * 统计各学校的平均分 合格占比
 */
public List<TestWebOffLine> getAvgBySchool();
/**
 * 统计每个班级本年综合平均成绩
 */
public List<TestWebOffLine> getByMonth(TestWebOffLine ol);
/**
 * 批量删除
 */
public void deleteBatch(Map<String, Object> hm);
/**
 * 根据年级 学校主键查询该年级的所有班级（去重）
 * 
 */
public List<TestWebOffLine> findClassRoom(TestWebOffLine ol);
}

