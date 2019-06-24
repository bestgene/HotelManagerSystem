package com.woniuxy.pojo;

import java.io.Serializable;

public class Vip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer vip_id;
	private String vip_number;
	private Integer user_id;
	private Integer level_id;
	private Integer flag;
	public Integer getVip_id() {
		return vip_id;
	}
	public void setVip_id(Integer vip_id) {
		this.vip_id = vip_id;
	}
	public String getVip_number() {
		return vip_number;
	}
	public void setVip_number(String vip_number) {
		this.vip_number = vip_number;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getLevel_id() {
		return level_id;
	}
	public void setLevel_id(Integer level_id) {
		this.level_id = level_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Vip [vip_id=" + vip_id + ", vip_number=" + vip_number + ", user_id=" + user_id + ", level_id="
				+ level_id + ", flag=" + flag + "]";
	}
	
	
	
	

}
