package com.woniuxy.pojo;

import java.io.Serializable;

public class House implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer house_id;
	//房间id
	private String house_name;
	//房间名
	private Integer house_state;
	//房间状态
	//0:空闲、1:正在使用、2:打扫、3:损坏
	private HouseType houseType;
	//房间名称
	private Integer flag;
	//软删除
//	0:存在、1:删除
	public House(Integer house_id, String house_name, Integer house_state, HouseType houseType, Integer flag) {
		this.house_id = house_id;
		this.house_name = house_name;
		this.house_state = house_state;
		this.houseType = houseType;
		this.flag = flag;
	}
	public House() {}
	public Integer getHouse_id() {
		return house_id;
	}
	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}
	public String getHouse_name() {
		return house_name;
	}
	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}
	public Integer getHouse_state() {
		return house_state;
	}
	public void setHouse_state(Integer house_state) {
		this.house_state = house_state;
	}
	public HouseType getHouseType() {
		return houseType;
	}
	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "House [house_id=" + house_id + ", house_name=" + house_name + ", house_state=" + house_state
				+ ", houseType=" + houseType + ", flag=" + flag + "]";
	}
	
}
