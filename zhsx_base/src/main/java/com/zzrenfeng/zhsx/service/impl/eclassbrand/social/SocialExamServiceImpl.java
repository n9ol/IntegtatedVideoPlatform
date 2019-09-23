package com.zzrenfeng.zhsx.service.impl.eclassbrand.social;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.mapper.eclassbrand.social.SocialExamMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.social.SocialExam;
import com.zzrenfeng.zhsx.service.eclassbrand.social.SocialExamService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-20 18:37:27
 * @see com.zzrenfeng.classbrand.service.impl.SocialExam
 */

@Service
public class SocialExamServiceImpl implements SocialExamService {

	@Resource
	private SocialExamMapper socialExamMapper;

	@Override
	public int updateClassroomName(String classroomId, String classroomName) {
		SocialExam socialExam = new SocialExam();
		socialExam.setClassroomId(classroomId);
		socialExam.setClassroomName(classroomName);
		return socialExamMapper.updateClassroomName(socialExam);
	}

}
