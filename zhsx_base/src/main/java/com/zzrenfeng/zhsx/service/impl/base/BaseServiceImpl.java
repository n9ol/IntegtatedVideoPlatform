package com.zzrenfeng.zhsx.service.impl.base;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.base.BaseService;

public class BaseServiceImpl<D extends BaseMapper<T>, T> implements BaseService<T> {

	protected D baseMapper;

	/**
	 * spring注入时需要
	 * 
	 * @param baseMapper
	 */
	protected void setBaseMapper(D baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public int insert(T t) {
		return baseMapper.insert(t);
	}

	@Override
	public int deleteByKey(String id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByKeySelective(T t) {
		return baseMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByKey(T t) {
		return baseMapper.updateByPrimaryKey(t);
	}

	@Override
	public T findByKey(String id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> findAll() {
		return baseMapper.selectAll();
	}

	@Override
	public List<T> findSelective(T t) {
		return baseMapper.findSelective(t);
	}

	@Override
	public Page<T> findPageSelective(T t, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return baseMapper.findPageSelective(t);
	}

}
