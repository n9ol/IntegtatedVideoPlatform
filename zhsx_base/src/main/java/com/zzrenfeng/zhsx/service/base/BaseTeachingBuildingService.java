package com.zzrenfeng.zhsx.service.base;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.model.base.BaseTeachingBuilding;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-03-09 17:09:12
 * @see com.zzrenfeng.classbrand.service.BaseTeachingBuilding
 */
public interface BaseTeachingBuildingService {

	BaseTeachingBuilding insert(BaseTeachingBuilding t);

	int deleteByKey(String id);

	void deleteBatch(String[] ids);

	BaseTeachingBuilding updateByKey(BaseTeachingBuilding t);

	int updateByKeySelective(BaseTeachingBuilding t);

	BaseTeachingBuilding findByKey(String id);

	List<BaseTeachingBuilding> findAll();

	List<BaseTeachingBuilding> findSelective(BaseTeachingBuilding t);

	Page<BaseTeachingBuilding> findPageSelective(BaseTeachingBuilding t, int p, int pageSize);

	/**
	 * 验证教学楼名称是否 不存在
	 * 
	 * @param buildName
	 * @return
	 */
	Boolean validateBuildName(String buildName);

	/**
	 * 通过名字获得教学楼
	 * 
	 * @param buildName
	 * @return
	 */
	BaseTeachingBuilding getBaseTeachingBuilding(String buildName);

	/**
	 * 添加教学楼并返回
	 * 
	 * @param buildName
	 * @return
	 */
	BaseTeachingBuilding getBaseTeachingBuildingByInster(String buildName, Date date);

}