package com.woniuxy.pojo;

import java.io.Serializable;

public class RolePerm  implements Serializable{
	
	private Integer role_id;
	private Integer perm_id;
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getPerm_id() {
		return perm_id;
	}
	public void setPerm_id(Integer perm_id) {
		this.perm_id = perm_id;
	}
	@Override
	public String toString() {
		return "RolePerm [role_id=" + role_id + ", perm_id=" + perm_id + "]";
	}
	
	

}
