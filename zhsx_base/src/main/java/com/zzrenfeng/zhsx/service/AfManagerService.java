package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.AfManager;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-06-22 17:02:47
 * @see com.zzrenfeng.zhsx.service.AfManager
 */
public interface AfManagerService extends BaseService<AfManager> {

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void deleteBatch(List<String> ids);

	/**
	 * 首页获得所有学校及区域
	 */
	public List<AfManager> findAfSchool();

	/**
	 * 点击学校获得区域
	 */
	public List<AfManager> findAfSchoolArea(AfManager af);

	/**
	 * 点击学校获得区域
	 */
	public Page<AfManager> findDefault(AfManager af, int p, int pageSize);

	/**
	 * 点击导航查询
	 * 
	 * @param af
	 * @return
	 */
	public Page<AfManager> finddhSelect(AfManager af, int p, int pageSize);

	public List<AfManager> findCamearname(AfManager af);

}
