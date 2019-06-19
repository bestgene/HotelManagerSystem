package com.woniuxy.pojo;

import java.io.Serializable;

public class DateHouse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer dh_id;
	private Integer house_id;
	private Integer house_type_id;
	private String dh_day;
	private Integer flag;
	public DateHouse(Integer dh_id, Integer house_id, Integer house_type_id, String dh_day, Integer flag) {
		this.dh_id = dh_id;
		this.house_id = house_id;
		this.house_type_id = house_type_id;
		this.dh_day = dh_day;
		this.flag = flag;
	}
	public DateHouse() {
	}
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
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "DateHouse [dh_id=" + dh_id + ", house_id=" + house_id + ", house_type_id=" + house_type_id + ", dh_day="
				+ dh_day + ", flag=" + flag + "]";
	}
	
}
