package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysSchoolMapper;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.SysSchool
 */

@Service
public class SysSchoolServiceImpl extends BaseServiceImpl<BaseMapper<SysSchool>, SysSchool> implements SysSchoolService {

	
	@Resource
	private SysSchoolMapper sysSchoolMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<SysSchool> sysSchoolMapper) {
		super.setBaseMapper(sysSchoolMapper);
	}

	@Override
	public int delBatchSchool(List<String> ids) {
		return sysSchoolMapper.delBatchSchool(ids);
	}

	@Override
	public void insertBatch(List<SysSchool> slist) {
		sysSchoolMapper.insertBatch(slist);
	}
	
	
	@Override
	public List<SysSchool> findIdAndSchoolNameSelective(SysSchool sysSchool) {
		return sysSchoolMapper.findIdAndSchoolNameSelective(sysSchool);
	}

	@Override
	public void insertBatchFromOld(List<SysSchool> slist) {
		sysSchoolMapper.insertBatchFromOld(slist);
	}

	@Override
	public List<SysSchool> findSchoolClassNotNull(SysSchool sysSchool) {
		return sysSchoolMapper.findSchoolClassNotNull(sysSchool);
	}







}
