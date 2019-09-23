package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.OffLineRecordVideo;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-04-18 14:33:31
 * @see com.zzrenfeng.zhsx.service.OffLineRecordVideo
 */
public interface OffLineRecordVideoService extends BaseService<OffLineRecordVideo> {

	/**
	 * 重写添加方法-避免日志切面拦截
	 * 
	 * @param offLineRecordVideo
	 * @return
	 */
	int tInsert(OffLineRecordVideo offLineRecordVideo);

	/**
	 * 获取同一次录制的视频路径
	 * 
	 * @param offlinevideoid
	 * @return
	 */
	List<OffLineRecordVideo> listOffLineRecordVideoByOfflinevideoId(String offlinevideoid);
	
	/**
	 * 根据教室ID和创建日期边界获取该教室自动录制的离线视频记录
	 * 
	 * @param classroomId 教室ID
	 * @param createDateStr1 记录创建日期左边界
	 * @param createDateStr2 记录创建日期右边界
	 * @return
	 */
	List<OffLineRecordVideo> getListByCidStime(String classroomId, String createDateStr1, String createDateStr2);

}