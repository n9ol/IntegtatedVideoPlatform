package com.zzrenfeng.zhsx.model;

/**
 * 上传文件类
 * @author 田杰熠
 *
 */
public class WuFile {
	
	/**
	 * 文件id
	 */
	private String id;
	
	/**
	 * 文件名称
	 */
	private String name;
	
	/**
	 * 文件大小
	 */
	private String size;
	
	/**
	 * 文件后缀
	 */
	private String ext;
	
	
	//get and set
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "WuFile [id=" + id + ", name=" + name + ", size=" + size + ", ext=" + ext + "]";
	}

	

	
	
	
	
	
	
	
	
	
	
}
