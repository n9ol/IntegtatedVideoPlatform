package com.zzrenfeng.zhsx.service.eclassbrand.social;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-08-13 16:17:15
 * @see com.zzrenfeng.service.SocialExam2
 */
public interface SocialExam2Service {

	/**
	 * 通过 classroomId 修改 classroomName
	 * 
	 * @param classroomId
	 * @param classroomName
	 * @return
	 */
	int updateClassroomName(String classroomId, String classroomName);
}