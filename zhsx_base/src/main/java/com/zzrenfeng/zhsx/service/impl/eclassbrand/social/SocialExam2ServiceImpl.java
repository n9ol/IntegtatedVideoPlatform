package com.zzrenfeng.zhsx.service.impl.eclassbrand.social;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.mapper.eclassbrand.social.SocialExam2Mapper;
import com.zzrenfeng.zhsx.model.eclassbrand.social.SocialExam2;
import com.zzrenfeng.zhsx.service.eclassbrand.social.SocialExam2Service;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-08-13 16:17:15
 * @see com.zzrenfeng.service.impl.SocialExam2
 */

@Service
public class SocialExam2ServiceImpl implements SocialExam2Service {

	@Resource
	private SocialExam2Mapper socialExam2Mapper;

	@Override
	public int updateClassroomName(String classroomId, String classroomName) {
		SocialExam2 socialExam2 = new SocialExam2();
		socialExam2.setClassroomId(classroomId);
		socialExam2.setClassroomName(classroomName);
		return socialExam2Mapper.updateClassroomNameByClassroomId(socialExam2);
	}

}