package com.zzrenfeng.zhsx.service.eclassbrand.social;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-20 18:37:27
 * @see com.zzrenfeng.classbrand.service.SocialExam
 */
public interface SocialExamService {

	/**
	 * 通过 classroomId 修改 classroomName
	 * 
	 * @param classroomId
	 * @param classroomName
	 * @return
	 */
	int updateClassroomName(String classroomId, String classroomName);
}