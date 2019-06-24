package com.woniuxy.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Telpojo {

	private String tel;
	private String code;
	@NotBlank(message="账号不能为空")
	private String user_acc;  //账号
	@Size(min=6,max=10,message="密码必须要在6-10位之间！")
	private String user_pwd;  //密码
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	@Override
	public String toString() {
		return "telpojo [tel=" + tel + ", code=" + code + ", user_acc=" + user_acc + ", user_pwd=" + user_pwd + "]";
	}
	
	
	
}
