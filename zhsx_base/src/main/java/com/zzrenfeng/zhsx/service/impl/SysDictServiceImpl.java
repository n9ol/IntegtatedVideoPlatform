package com.zzrenfeng.zhsx.service.impl;

import java.util.ArrayList;
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
	public List<SysDict> listSpecialty() {
		SysDict dict = new SysDict(SysDict.KEYNAME_GRADE);
		return sysDictMapper.findSelective(dict);
	}

	@Override
	public List<SysDict> listSubject() {
		SysDict dict = new SysDict(SysDict.KEYNAME_SUBJECTS);
		List<SysDict> subjects = new ArrayList<SysDict>();
		List<SysDict> subject = sysDictMapper.findSelective(dict);
		String tem = "1";
		for (SysDict o : subject) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		return subjects;
	}

}
