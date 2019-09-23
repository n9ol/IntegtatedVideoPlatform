package com.zzrenfeng.zhsx.mapper.eclassbrand.classroom;

import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.classroom.ClassroomProfile;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-08-28 09:48:18
 * @see com.zzrenfeng.service.ClassroomProfile
 */
@DataSource(value = "2")
public interface ClassroomProfileMapper {

	void updateByPrimaryKeySelective(ClassroomProfile classroomProfile);

	void updateClassroomNameByclassroomId(ClassroomProfile classroomProfile);
}
