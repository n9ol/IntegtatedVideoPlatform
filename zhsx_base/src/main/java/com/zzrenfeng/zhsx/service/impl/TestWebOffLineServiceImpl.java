package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.TestWebOffLineMapper;
import com.zzrenfeng.zhsx.model.TestWebOffLine;
import com.zzrenfeng.zhsx.service.TestWebOffLineService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-09-09 15:22:25
 * @see com.zzrenfeng.zhsx.service.impl.TestWebOffLine
 */

@Service
public class TestWebOffLineServiceImpl extends BaseServiceImpl<BaseMapper<TestWebOffLine>, TestWebOffLine>
		implements TestWebOffLineService {

	@Resource
	private TestWebOffLineMapper testWebOffLineMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<TestWebOffLine> testWebOffLineMapper) {
		super.setBaseMapper(testWebOffLineMapper);
	}

	@Override
	public List<TestWebOffLine> getAllBysubj(TestWebOffLine ol) {
		List<TestWebOffLine> list = testWebOffLineMapper.getAllBysubj(ol);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public TestWebOffLine getClassScore(TestWebOffLine ol) {
		TestWebOffLine list = testWebOffLineMapper.getClassScore(ol);
		if (list != null) {
			return list;
		}
		return null;
	}

	@Override
	public List<TestWebOffLine> getAvgByGrade(String schoolId) {
		List<TestWebOffLine> list = testWebOffLineMapper.getAvgByGrade(schoolId);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<TestWebOffLine> getAvgBySchool() {
		List<TestWebOffLine> list = testWebOffLineMapper.getAvgBySchool();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<TestWebOffLine> getByMonth(TestWebOffLine ol) {
		List<TestWebOffLine> list = testWebOffLineMapper.getByMonth(ol);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public void deleteBatch(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		testWebOffLineMapper.deleteBatch(hm);
	}

	@Override
	public List<TestWebOffLine> findClassRoom(TestWebOffLine ol) {
		List<TestWebOffLine> list = testWebOffLineMapper.findClassRoom(ol);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
