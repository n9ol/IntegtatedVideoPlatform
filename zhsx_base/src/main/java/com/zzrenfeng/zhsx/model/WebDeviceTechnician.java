package com.zzrenfeng.zhsx.model;


/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-08-02 11:52:51
 * @see com.zzrenfeng.zhsx.model.WebDeviceTechnician
 */

public class WebDeviceTechnician {
	//alias
	/*
	public static final String TABLE_ALIAS = "WebDeviceTechnician";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_PHONE = "联系电话";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 姓名
	 */
	private java.lang.String name;
	/**
	 * 联系电话
	 */
	private java.lang.String phone;
	/**
	 * bak1
	 */
	private java.lang.String bak1;
	/**
	 * bak2
	 */
	private java.lang.String bak2;
	/**
	 * bak3
	 */
	private java.lang.String bak3;
	
	
	//columns END 数据库字段结束
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getPhone() {
		return phone;
	}
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	public java.lang.String getBak1() {
		return bak1;
	}
	public void setBak1(java.lang.String bak1) {
		this.bak1 = bak1;
	}
	public java.lang.String getBak2() {
		return bak2;
	}
	public void setBak2(java.lang.String bak2) {
		this.bak2 = bak2;
	}
	public java.lang.String getBak3() {
		return bak3;
	}
	public void setBak3(java.lang.String bak3) {
		this.bak3 = bak3;
	}

	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("name[").append(getName()).append("],")
			.append("phone[").append(getPhone()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
			.toString();
	}
}

	



