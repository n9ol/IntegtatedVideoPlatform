package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysDict;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysDict
 */

public interface SysDictMapper extends BaseMapper<SysDict> {

	/**
	 * 删除3级数据
	 * 
	 * @param id
	 * @return
	 */
	int deleteLevel3Data(String id);

	/**
	 * 删除2级数据
	 * 
	 * @param id
	 * @return
	 */
	int deleteLevel2Data(String id);

	/**
	 * 批量删除版本信息
	 * 
	 * @param ids
	 */
	void batchDelVersion(List<String> ids);

	List<SysDict> findAreaByProvince(SysDict sysDicttema);

	/**
	 * 修改皮肤
	 * 
	 * @param skinName
	 */
	void updateSkin(String skinName);
}
