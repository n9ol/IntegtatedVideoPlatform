package com.zzrenfeng.zhsx.mapper.eclassbrand.social;

import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.social.SocialExam;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-20 18:37:27
 * @see com.zzrenfeng.classbrand.service.SocialExam
 */
@DataSource(value = "2")
public interface SocialExamMapper {

	int updateClassroomName(SocialExam SocialExam);

}