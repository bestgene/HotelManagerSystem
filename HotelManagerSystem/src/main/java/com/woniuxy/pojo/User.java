package com.woniuxy.pojo;

import java.io.Serializable;

public class User implements Serializable{
	
	
	private Integer user_id;  //用户id
	private String user_acc;  //账号
	private String user_pwd;  //密码
	private String user_img;  //头像
	private Integer role_id;  //角色id（外键）
	private Integer flag;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_acc() {
		return user_acc;
	}
	public void setUser_acc(String user_acc) {
		this.user_acc = user_acc;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public User(Integer user_id, String user_acc, String user_pwd, String user_img, Integer role_id, Integer flag) {
		super();
		this.user_id = user_id;
		this.user_acc = user_acc;
		this.user_pwd = user_pwd;
		this.user_img = user_img;
		this.role_id = role_id;
		this.flag = flag;
	}
	public User(){}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_acc=" + user_acc + ", user_pwd=" + user_pwd + ", user_img="
				+ user_img + ", role_id=" + role_id + ", flag=" + flag + "]";
	}
	
}
