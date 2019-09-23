package com.zzrenfeng.zhsx.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.model.SysDict
 */

public class SysDict {

	/**
	 * 标识符-省
	 */
	public final static String KEYNAME_PROVINCE = "P";
	/**
	 * 标识符-市
	 */
	public final static String KEYNAME_CITY = "C";
	/**
	 * 标识符-县/区
	 */
	public final static String KEYNAME_AREA = "A";
	/**
	 * 标识符-年级(如果是大学,改字段表示专业)
	 */
	public final static String KEYNAME_GRADE = "G";
	/**
	 * 标识符-科目
	 */
	public final static String KEYNAME_SUBJECTS = "S";

	/**
	 * 标识符-版本
	 */
	public final static String KEYNAME_VERSION = "V";

	/**
	 * 标识符-上下册
	 */
	public final static String KEYNAME_M = "M";

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 父id
	 */
	private java.lang.String pid;
	/**
	 * 标识符（V 版本,G 年级,S 科目,M上下册,P 省,C 市, A区县）
	 */
	@Pattern(regexp = "[V|G|S|M|P|C|A]", message = "{tag}")
	private java.lang.String keyname;
	/**
	 * 值
	 */
	@NotNull(message = "{notnull}")
	private java.lang.String value;
	/**
	 * 经度
	 */
	private java.lang.String posx;
	/**
	 * 纬度
	 */
	private java.lang.String posy;
	/**
	 * 序列
	 */
	private java.lang.Integer sort;
	/**
	 * 添加时间
	 */
	private java.util.Date createTime;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modiyTime;
	// columns END 数据库字段结束

	// concstructor

	public SysDict() {
	}

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setPid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.pid = value;
	}

	public java.lang.String getPid() {
		return this.pid;
	}

	public void setKeyname(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.keyname = value;
	}

	public java.lang.String getKeyname() {
		return this.keyname;
	}

	public void setValue(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.value = value;
	}

	public java.lang.String getValue() {
		return this.value;
	}

	public void setPosx(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.posx = value;
	}

	public java.lang.String getPosx() {
		return this.posx;
	}

	public void setPosy(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.posy = value;
	}

	public java.lang.String getPosy() {
		return this.posy;
	}

	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}

	public java.lang.Integer getSort() {
		return this.sort;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setModiyTime(java.util.Date value) {
		this.modiyTime = value;
	}

	public java.util.Date getModiyTime() {
		return this.modiyTime;
	}

	public String toString() {
		return new StringBuffer().append("id[").append(getId()).append("],").append("父id[").append(getPid())
				.append("],").append("标识符（V 版本,G 年级,S 科目,M上下册,A 地区）[").append(getKeyname()).append("],").append("值[")
				.append(getValue()).append("],").append("经度[").append(getPosx()).append("],").append("纬度[")
				.append(getPosy()).append("],").append("年级序列[").append(getSort()).append("],").append("添加时间[")
				.append(getCreateTime()).append("],").append("最后修改时间[").append(getModiyTime()).append("],").toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof SysDict == false)
			return false;
		if (this == obj)
			return true;
		SysDict other = (SysDict) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	public SysDict(String keyname) {
		super();
		this.keyname = keyname;
	}

}
