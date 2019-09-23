package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysSchoolMapper;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.SysSchool
 */

@Service
public class SysSchoolServiceImpl extends BaseServiceImpl<BaseMapper<SysSchool>, SysSchool>
		implements SysSchoolService {

	@Resource
	private SysSchoolMapper sysSchoolMapper;
	@Resource
	private UserService userService;

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

	@Override
	public List<SysSchool> listSysSchoolHavePermission(String bak1, String bak2, String schoolId) {
		SysSchool school = new SysSchool();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			school.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, schoolId);
			if (ids != null && ids.size() > 0) {
				school.setIds(ids);
			}
		}
		return findSelective(school);
	}
	public List<SysSchool> findSelectiveData(SysSchool sysSchool) {
		
		return sysSchoolMapper.findSelectiveData(sysSchool);
	}







}
