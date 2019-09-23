package com.zzrenfeng.zhsx.base;

import java.util.List;

import com.github.pagehelper.Page;

public interface BaseService<T> {

	/**
	 * 添加一条数据到数据库
	 * 
	 * @param t
	 * @return
	 */
	int insert(T t);

	/**
	 * 通过主键删除信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByKey(String id);

	/**
	 * 通过主键有选择性的修改数据
	 * 
	 * @param t
	 * @return
	 */
	int updateByKeySelective(T t);

	/**
	 * 通过主键修改数据
	 * 
	 * @param t
	 * @return
	 */
	int updateByKey(T t);

	/**
	 * 通过主键查询数据
	 * 
	 * @param id
	 * @return
	 */
	T findByKey(String id);

	/**
	 * 查询所有数据
	 * 
	 * @param id
	 * @return
	 */
	List<T> findAll();

	/**
	 * 有选择性的查询数据
	 * 
	 * @param hm
	 * @return
	 */
	List<T> findSelective(T t);

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @return
	 */
	Page<T> findPageSelective(T t, int p, int pageSize);

}
