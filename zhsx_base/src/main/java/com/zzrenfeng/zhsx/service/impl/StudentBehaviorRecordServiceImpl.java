package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.StudentBehaviorRecordMapper;
import com.zzrenfeng.zhsx.model.StudentBehaviorRecord;
import com.zzrenfeng.zhsx.service.StudentBehaviorRecordService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2019-05-21 18:17:22
 * @see com.zzrenfeng.zhsx.service.impl.StudentBehaviorRecord
 */

@Service
public class StudentBehaviorRecordServiceImpl extends BaseServiceImpl<BaseMapper<StudentBehaviorRecord>, StudentBehaviorRecord> implements StudentBehaviorRecordService {

	
	@Resource
	private StudentBehaviorRecordMapper studentBehaviorRecordMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<StudentBehaviorRecord> studentBehaviorRecordMapper) {
		super.setBaseMapper(studentBehaviorRecordMapper);
	}

	@Override
	public int inStudentBehaviorRecord(StudentBehaviorRecord sbr) {
		return studentBehaviorRecordMapper.inStudentBehaviorRecord(sbr);
	}







}
