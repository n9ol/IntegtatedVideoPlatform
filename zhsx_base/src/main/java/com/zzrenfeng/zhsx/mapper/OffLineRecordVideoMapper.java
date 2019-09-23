package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.OffLineRecordVideo;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version  2018-04-18 14:33:31
 * @see com.zzrenfeng.zhsx.service.OffLineRecordVideo
 */

public interface OffLineRecordVideoMapper extends BaseMapper<OffLineRecordVideo>{


	/**
	 * 根据教室ID和创建日期边界获取该教室自动录制的离线视频记录
	 * @ OffLineRecordVideo 查询参数主要包括：
	 * 							classroomId 教室ID
	 * 							createDateStr1 记录创建日期左边界
	 * 							createDateStr2 记录创建日期右边界
	 * @return
	 */
	List<OffLineRecordVideo> getListByCidStime(OffLineRecordVideo offLineRecordVideo);


}

