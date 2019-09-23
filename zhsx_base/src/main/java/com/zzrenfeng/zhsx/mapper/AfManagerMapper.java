package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.AfManager;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-06-22 17:02:47
 * @see com.zzrenfeng.zhsx.service.AfManager
 */

public interface AfManagerMapper extends BaseMapper<AfManager> {

	/**
	 * 批量删除
	 */
	public void deleteBatch(Map<String, Object> hm);

	/**
	 * 首页获得所有学校
	 */
	public List<AfManager> findAfSchool();

	/**
	 * 点击学校获得区域
	 */
	public List<AfManager> findAfSchoolArea(AfManager af);

	/**
	 * 默认首页信息
	 */
	public Page<AfManager> findDefault(AfManager af);

	/**
	 * 点击导航查询
	 * 
	 * @param af
	 * @return
	 */
	public Page<AfManager> finddhSelect(AfManager af);

	public List<AfManager> findCamearname(AfManager af);
}
