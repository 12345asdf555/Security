package com.spring.model;

/**
 * 用户
 * 
 *
 */
public class Authority {
	private int id;
	private String authorityName;
	private String authorityDesc;
	private int status;
	private String resourceName;
	public Authority(){
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getAuthorityDesc() {
		return authorityDesc;
	}
	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Authority(int id, String authorityName, String resourceName) {
		super();
		this.id = id;
		this.authorityName = authorityName;
		this.resourceName = resourceName;
	}
}
