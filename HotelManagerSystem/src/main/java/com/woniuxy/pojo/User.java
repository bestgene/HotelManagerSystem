package com.woniuxy.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer user_id;  //用户id
	@NotBlank(message="账号不能为空")
	private String user_acc;  //账号
	@Size(min=6,max=10,message="密码必须要在6-10位之间！")
	private String user_pwd;  //密码
	private String user_img;  //头像
	private Integer role_id;  //角色id（外键）
	private Integer flag;
	private String Email;  //邮箱
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
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

	
	
	public User(Integer user_id, String user_acc, String user_pwd, String user_img, Integer role_id, Integer flag,
			String email) {
		super();
		this.user_id = user_id;
		this.user_acc = user_acc;
		this.user_pwd = user_pwd;
		this.user_img = user_img;
		this.role_id = role_id;
		this.flag = flag;
		Email = email;
	}
	public User(){}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_acc=" + user_acc + ", user_pwd=" + user_pwd + ", user_img="
				+ user_img + ", role_id=" + role_id + ", flag=" + flag + ", Email=" + Email + "]";
	}
	
	
}

