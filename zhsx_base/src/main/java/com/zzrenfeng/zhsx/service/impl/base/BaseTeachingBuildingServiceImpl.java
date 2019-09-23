package com.zzrenfeng.zhsx.service.impl.base;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.mapper.base.BaseTeachingBuildingMapper;
import com.zzrenfeng.zhsx.model.base.BaseTeachingBuilding;
import com.zzrenfeng.zhsx.service.base.BaseTeachingBuildingService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-03-09 17:09:12
 * @see com.zzrenfeng.classbrand.service.impl.BaseTeachingBuilding
 */

@Service
public class BaseTeachingBuildingServiceImpl implements BaseTeachingBuildingService {

	@Resource
	private BaseTeachingBuildingMapper baseTeachingBuildingMapper;

	@Override
	public Boolean validateBuildName(String buildName) {
		BaseTeachingBuilding baseTeachingBuilding = getBaseTeachingBuilding(buildName);
		if (baseTeachingBuilding != null) {
			return false;
		}
		return true;
	}

	@Override
	public BaseTeachingBuilding getBaseTeachingBuilding(String buildName) {
		BaseTeachingBuilding baseTeachingBuilding = new BaseTeachingBuilding();
		baseTeachingBuilding.setBuildName(buildName);
		List<BaseTeachingBuilding> buildList = findSelective(baseTeachingBuilding);
		if (buildList != null && buildList.size() > 0) {
			return buildList.get(0);
		}
		return null;
	}

	@Override
	public BaseTeachingBuilding getBaseTeachingBuildingByInster(String buildName, Date date) {
		BaseTeachingBuilding baseTeachingBuilding = new BaseTeachingBuilding();
		baseTeachingBuilding.setBuildName(buildName);
		baseTeachingBuilding.setCreateDate(date);
		baseTeachingBuilding.setModiyDate(date);
		insert(baseTeachingBuilding);
		return baseTeachingBuilding;
	}

	@Override
	public BaseTeachingBuilding insert(BaseTeachingBuilding t) {
		baseTeachingBuildingMapper.insert(t);
		return t;
	}

	@Override
	public int deleteByKey(String id) {
		return baseTeachingBuildingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteBatch(String[] ids) {
		baseTeachingBuildingMapper.deleteBatch(ids);
	}

	@Override
	public BaseTeachingBuilding updateByKey(BaseTeachingBuilding t) {
		baseTeachingBuildingMapper.updateByPrimaryKey(t);
		return t;
	}

	@Override
	public int updateByKeySelective(BaseTeachingBuilding t) {
		return baseTeachingBuildingMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public BaseTeachingBuilding findByKey(String id) {
		return baseTeachingBuildingMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BaseTeachingBuilding> findAll() {
		return baseTeachingBuildingMapper.selectAll();
	}

	@Override
	public List<BaseTeachingBuilding> findSelective(BaseTeachingBuilding t) {
		return baseTeachingBuildingMapper.findSelective(t);
	}

	@Override
	public Page<BaseTeachingBuilding> findPageSelective(BaseTeachingBuilding t, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		Page<BaseTeachingBuilding> pages = baseTeachingBuildingMapper.findPageSelective(t);
		return pages;
	}

}
