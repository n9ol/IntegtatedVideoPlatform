package com.zzrenfeng.zhsx.mapper.base;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.base.BaseTeachingBuilding;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-03-09 17:09:12
 * @see com.zzrenfeng.classbrand.service.BaseTeachingBuilding
 */

public interface BaseTeachingBuildingMapper extends BaseMapper<BaseTeachingBuilding> {

	void deleteBatch(String[] ids);

}
