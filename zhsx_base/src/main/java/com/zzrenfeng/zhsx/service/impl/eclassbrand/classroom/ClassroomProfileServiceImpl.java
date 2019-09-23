package com.zzrenfeng.zhsx.service.impl.eclassbrand.classroom;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.mapper.eclassbrand.classroom.ClassroomProfileMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.classroom.ClassroomProfile;
import com.zzrenfeng.zhsx.service.eclassbrand.classroom.ClassroomProfileService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-08-28 09:48:18
 * @see com.zzrenfeng.service.impl.ClassroomProfile
 */

@Service
public class ClassroomProfileServiceImpl implements ClassroomProfileService {

	@Resource
	private ClassroomProfileMapper classroomProfileMapper;

	@Override
	public void updateClassroomName(String classroomId, String classroomName) {
		ClassroomProfile classroomProfile = new ClassroomProfile();
		classroomProfile.setClassroomId(classroomId);
		classroomProfile.setClassroomName(classroomName);
		classroomProfileMapper.updateClassroomNameByclassroomId(classroomProfile);
	}

}
