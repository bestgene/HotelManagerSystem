package com.woniuxy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer user_info_id;
	private String user_info_name;
	private String user_info_tel;
	private String user_info_idcard;
	private BigDecimal user_info_cost;
	private Integer user_id;
	private Integer flag;
	public Integer getUser_info_id() {
		return user_info_id;
	}
	public void setUser_info_id(Integer user_info_id) {
		this.user_info_id = user_info_id;
	}
	public String getUser_info_name() {
		return user_info_name;
	}
	public void setUser_info_name(String user_info_name) {
		this.user_info_name = user_info_name;
	}
	public String getUser_info_tel() {
		return user_info_tel;
	}
	public void setUser_info_tel(String user_info_tel) {
		this.user_info_tel = user_info_tel;
	}
	public String getUser_info_idcard() {
		return user_info_idcard;
	}
	public void setUser_info_idcard(String user_info_idcard) {
		this.user_info_idcard = user_info_idcard;
	}
	public BigDecimal getUser_info_cost() {
		return user_info_cost;
	}
	public void setUser_info_cost(BigDecimal user_info_cost) {
		this.user_info_cost = user_info_cost;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public UserInfo(Integer user_info_id, String user_info_name, String user_info_tel, String user_info_idcard,
			BigDecimal user_info_cost, Integer user_id, Integer flag) {
		super();
		this.user_info_id = user_info_id;
		this.user_info_name = user_info_name;
		this.user_info_tel = user_info_tel;
		this.user_info_idcard = user_info_idcard;
		this.user_info_cost = user_info_cost;
		this.user_id = user_id;
		this.flag = flag;
	}
	public UserInfo(){}
	@Override
	public String toString() {
		return "UserInfo [user_info_id=" + user_info_id + ", user_info_name=" + user_info_name + ", user_info_tel="
				+ user_info_tel + ", user_info_idcard=" + user_info_idcard + ", user_info_cost=" + user_info_cost
				+ ", user_id=" + user_id + ", flag=" + flag + "]";
	}
	
}
