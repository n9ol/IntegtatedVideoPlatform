package com.zzrenfeng.zhsx.mapper.eclassbrand.social;

import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.social.SocialExam2;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-08-13 16:17:15
 * @see com.zzrenfeng.service.SocialExam2
 */
@DataSource(value = "2")
public interface SocialExam2Mapper {

	void updateByPrimaryKeySelective(SocialExam2 socialExam2);

	int updateClassroomNameByClassroomId(SocialExam2 socialExam2);
}
