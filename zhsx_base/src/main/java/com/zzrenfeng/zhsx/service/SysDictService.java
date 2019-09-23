package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysDict;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysDict
 */
public interface SysDictService extends BaseService<SysDict> {

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
	 * 通过用户权限获得地区对应省级id
	 * 
	 * @param bak1
	 * @param bak2
	 * @return
	 */
	String getIdByUserbak(String bak1, String bak2);

	/**
	 * 批量删除版本信息
	 * 
	 * @param ids
	 */
	void batchDelVersion(List<String> ids);

	List<SysDict> findAreaByProvince(SysDict sysDicttema);

	/**
	 * 获取专业(年级)
	 * 
	 * @return
	 */
	List<SysDict> listSpecialty();

	/**
	 * 获取所有科目(去重)
	 * 
	 * @return
	 */
	List<SysDict> listSubject();
}
