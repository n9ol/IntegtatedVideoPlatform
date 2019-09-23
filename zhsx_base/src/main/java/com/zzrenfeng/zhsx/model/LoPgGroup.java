package com.zzrenfeng.zhsx.model;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:30
 * @see com.zzrenfeng.zhsx.model.LoPgGroup
 */

public class LoPgGroup {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 评估小组名称
	 */
	private java.lang.String name;
	/**
	 * 创建人id
	 */
	private java.lang.String createrId;

	// 关联字段
	private String search;

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setCreaterId(java.lang.String value) {
		this.createrId = value;
	}

	public java.lang.String getCreaterId() {
		return this.createrId;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("评估小组名称[").append(getName())
				.append("],").append("创建人id[").append(getCreaterId()).append("],").toString();
	}

}
