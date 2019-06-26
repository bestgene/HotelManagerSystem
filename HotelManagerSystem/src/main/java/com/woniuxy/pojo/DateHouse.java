package com.woniuxy.pojo;

import java.io.Serializable;

public class DateHouse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键id
	private Integer dh_id;
	//房间id
	private Integer house_id;
	//房间类型id
	private Integer house_type_id;
	//日期
	private String dh_day;
	//userinfo信息
	private UserInfo userInfo;
	//软删除
	private Integer flag;
	public Integer getDh_id() {
		return dh_id;
	}
	public void setDh_id(Integer dh_id) {
		this.dh_id = dh_id;
	}
	public Integer getHouse_id() {
		return house_id;
	}
	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}
	public Integer getHouse_type_id() {
		return house_type_id;
	}
	public void setHouse_type_id(Integer house_type_id) {
		this.house_type_id = house_type_id;
	}
	public String getDh_day() {
		return dh_day;
	}
	public void setDh_day(String dh_day) {
		this.dh_day = dh_day;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public DateHouse(Integer dh_id, Integer house_id, Integer house_type_id, String dh_day, UserInfo userInfo,
			Integer flag) {
		super();
		this.dh_id = dh_id;
		this.house_id = house_id;
		this.house_type_id = house_type_id;
		this.dh_day = dh_day;
		this.userInfo = userInfo;
		this.flag = flag;
	}
	public DateHouse() {
		super();
	}
	@Override
	public String toString() {
		return "DateHouse [dh_id=" + dh_id + ", house_id=" + house_id + ", house_type_id=" + house_type_id + ", dh_day="
				+ dh_day + ", userInfo=" + userInfo + ", flag=" + flag + "]";
	}
	
	
}
