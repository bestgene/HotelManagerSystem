package com.woniuxy.pojo;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer role_id;
	private String role_name;
	private List<Perm> perms;
	public List<Perm> getPerms() {
		return perms;
	}
	public void setPerms(List<Perm> perms) {
		this.perms = perms;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public Role(){}
	public Role(Integer role_id, String role_name, List<Perm> perms) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
		this.perms = perms;
	}
	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name + ", perms=" + perms + "]";
	}	
}
