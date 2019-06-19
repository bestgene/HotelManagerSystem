package com.woniuxy.pojo;

import java.io.Serializable;

public class DateHouseState implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer dhs_id;
	//主键id
	private String dhs_day;
	//可选预定时间 字符串类型2019-06-18
	private String dhs_hids;
	//填入hid加上分隔符:组成的
	private Integer flag;
	//软删除
	public Integer getDhs_id() {
		return dhs_id;
	}
	public void setDhs_id(Integer dhs_id) {
		this.dhs_id = dhs_id;
	}
	public String getDhs_day() {
		return dhs_day;
	}
	public void setDhs_day(String dhs_day) {
		this.dhs_day = dhs_day;
	}
	public String getDhs_hids() {
		return dhs_hids;
	}
	public void setDhs_hids(String dhs_hids) {
		this.dhs_hids = dhs_hids;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public DateHouseState(Integer dhs_id, String dhs_day, String dhs_hids, Integer flag) {
		this.dhs_id = dhs_id;
		this.dhs_day = dhs_day;
		this.dhs_hids = dhs_hids;
		this.flag = flag;
	}
	public DateHouseState() {}
	@Override
	public String toString() {
		return "DateHouseState [dhs_id=" + dhs_id + ", dhs_day=" + dhs_day + ", dhs_hids=" + dhs_hids + ", flag=" + flag
				+ "]";
	}
	
	
}
