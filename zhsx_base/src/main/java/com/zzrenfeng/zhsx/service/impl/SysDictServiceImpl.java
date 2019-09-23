package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysDictMapper;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.SysDict
 */

@Service
public class SysDictServiceImpl extends BaseServiceImpl<BaseMapper<SysDict>, SysDict> implements SysDictService {

	@Resource
	private SysDictMapper sysDictMapper;
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;

	@Resource
	public void setBaseMapper(BaseMapper<SysDict> sysDictMapper) {
		super.setBaseMapper(sysDictMapper);
	}

	@Override
	public int deleteLevel3Data(String id) {
		return sysDictMapper.deleteLevel3Data(id);
	}

	@Override
	public int deleteLevel2Data(String id) {
		return sysDictMapper.deleteLevel2Data(id);
	}

	@Override
	public String getIdByUserbak(String bak1, String bak2) {
		String id = null;
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			if (bak1.equals(User.bak1_province)) {
				id = bak2;
			} else if (bak1.equals(User.bak1_city)) {
				SysDict s = sysDictMapper.selectByPrimaryKey(bak2);
				id = s.getPid();
			} else if (bak1.equals(User.bak1_county)) {
				SysDict s = sysDictMapper.selectByPrimaryKey(bak2);
				SysDict ss = sysDictMapper.selectByPrimaryKey(s.getPid());
				id = ss.getPid();
			}
		}
		return id;
	}

	@Override
	public void batchDelVersion(List<String> ids) {
		sysDictMapper.batchDelVersion(ids);
	}

	@Override
	public List<SysDict> findAreaByProvince(SysDict sysDicttema) {
		return sysDictMapper.findAreaByProvince(sysDicttema);
	}

	@Override
	public void changeSkin(String skinName) {
		sysDictMapper.updateSkin(skinName);
	}

	@Override
	public String getSkinName() {
		String skinName = "default";
		SysDict sysDict = new SysDict();
		sysDict.setKeyname(SysDict.KEYNAME_SKIN);
		List<SysDict> listSysDict = sysDictMapper.findSelective(sysDict);
		if (listSysDict != null && listSysDict.size() > 0) {
			skinName = listSysDict.get(0).getValue();
		}
		return skinName;
	}

	@Override
	public List<SysDict> listAreaInfo(String bak1, String bak2) {
		SysDict sysDict = new SysDict();
		if (User.bak1_city.equals(bak1)) {
			sysDict.setKeyname(SysDict.KEYNAME_AREA);
			sysDict.setPid(findByKey(bak2).getPid());
		} else if (User.bak1_province.equals(bak1)) {
			sysDict.setKeyname(SysDict.KEYNAME_CITY);
			sysDict.setPid(findByKey(findByKey(bak2).getPid()).getPid());
		} else if (User.bak1_operator.equals(bak1)) {
			if ("N".equals(platformLevel)) {
				sysDict.setKeyname(SysDict.KEYNAME_PROVINCE);
			} else if ("P".equals(platformLevel)) {
				sysDict.setKeyname(SysDict.KEYNAME_CITY);
				sysDict.setPid(platformLevelId);
			} else if ("C".equals(platformLevel)) {
				sysDict.setKeyname(SysDict.KEYNAME_AREA);
				sysDict.setPid(platformLevelId);
			}
		}
		boolean b = "A".equals(platformLevel) || "T".equals(platformLevel) || User.bak1_county.equals(bak1)
				|| User.bak1_schoool.equals(bak1);
		if (b) {
			return null;
		}
		List<SysDict> sysDicts = findSelective(sysDict);
		return sysDicts;
	}
}
