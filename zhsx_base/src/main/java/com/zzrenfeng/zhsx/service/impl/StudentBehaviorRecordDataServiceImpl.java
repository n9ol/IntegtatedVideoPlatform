package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.StudentBehaviorRecordData;
import com.zzrenfeng.zhsx.service.StudentBehaviorRecordDataService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.StudentBehaviorRecordDataMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2019-05-21 18:17:48
 * @see com.zzrenfeng.zhsx.service.impl.StudentBehaviorRecordData
 */

@Service
public class StudentBehaviorRecordDataServiceImpl extends BaseServiceImpl<BaseMapper<StudentBehaviorRecordData>, StudentBehaviorRecordData> implements StudentBehaviorRecordDataService {

	
	@Resource
	private StudentBehaviorRecordDataMapper studentBehaviorRecordDataMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<StudentBehaviorRecordData> studentBehaviorRecordDataMapper) {
		super.setBaseMapper(studentBehaviorRecordDataMapper);
	}

	@Override
	public int inStudentBehaviorRecordData(StudentBehaviorRecordData sbrd) {
		 return studentBehaviorRecordDataMapper.inStudentBehaviorRecordData(sbrd);
	}







}
