package com.woniuxy.pojo;

import java.io.Serializable;

public class Perm implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer perm_id;
	private String perm_name;
	
	public Integer getPerm_id() {
		return perm_id;
	}
	public void setPerm_id(Integer perm_id) {
		this.perm_id = perm_id;
	}
	public String getPerm_name() {
		return perm_name;
	}
	public void setPerm_name(String perm_name) {
		this.perm_name = perm_name;
	}
	public Perm(Integer perm_id, String perm_name) {
		super();
		this.perm_id = perm_id;
		this.perm_name = perm_name;
	}
	public Perm(){}
	@Override
	public String toString() {
		return "Perm [perm_id=" + perm_id + ", perm_name=" + perm_name + "]";
	}
}
