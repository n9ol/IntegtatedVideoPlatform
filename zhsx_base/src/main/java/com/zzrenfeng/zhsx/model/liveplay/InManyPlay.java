package com.zzrenfeng.zhsx.model.liveplay;

import org.hibernate.validator.constraints.NotBlank;

public class InManyPlay {

	@NotBlank
	private String id;
	@NotBlank
	private String ip;
	@NotBlank
	private String port1;
	@NotBlank
	private String port2;
	@NotBlank
	private String rid;
	@NotBlank
	private String uid;
	@NotBlank
	private String title;
	private String uid1;
	private String title1;
	private String uid2;
	private String title2;
	private String uid3;
	private String title3;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort1() {
		return port1;
	}

	public void setPort1(String port1) {
		this.port1 = port1;
	}

	public String getPort2() {
		return port2;
	}

	public void setPort2(String port2) {
		this.port2 = port2;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUid1() {
		return uid1;
	}

	public void setUid1(String uid1) {
		this.uid1 = uid1;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getUid2() {
		return uid2;
	}

	public void setUid2(String uid2) {
		this.uid2 = uid2;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getUid3() {
		return uid3;
	}

	public void setUid3(String uid3) {
		this.uid3 = uid3;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	@Override
	public String toString() {
		return "InManyPlay [id=" + id + ", ip=" + ip + ", port1=" + port1 + ", port2=" + port2 + ", rid=" + rid
				+ ", uid=" + uid + ", title=" + title + ", uid1=" + uid1 + ", title1=" + title1 + ", uid2=" + uid2
				+ ", title2=" + title2 + ", uid3=" + uid3 + ", title3=" + title3 + "]";
	}

}
