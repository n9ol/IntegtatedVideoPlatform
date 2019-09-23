package com.zzrenfeng.zhsx.base;

import java.util.List;

import com.github.pagehelper.Page;

public interface BaseMapper<T> {

	int insert(T t);

	int deleteByPrimaryKey(String id);

	int updateByPrimaryKeySelective(T t);

	int updateByPrimaryKey(T t);

	T selectByPrimaryKey(String id);

	List<T> selectAll();

	List<T> findSelective(T t);

	Page<T> findPageSelective(T t);
}
