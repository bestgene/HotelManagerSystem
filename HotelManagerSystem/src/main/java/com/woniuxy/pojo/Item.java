package com.woniuxy.pojo;

import java.io.Serializable;

/**
 * 订单项类
 */
public class Item implements Serializable{
	//订单项编号
	private Integer item_id;
	//入住时间
	private String item_checkintime;
	//退房时间
	private String item_checkouttime;
	//住宿天数
	private Integer item_checkinday;
	//押金
	private Integer item_deposit;
	//房间
	private House house;
	private Integer flag;


	@Override
	public String toString() {
		return "Item{" +
				"item_id=" + item_id +
				", item_checkintime='" + item_checkintime + '\'' +
				", item_checkouttime='" + item_checkouttime + '\'' +
				", item_checkinday=" + item_checkinday +
				", item_deposit=" + item_deposit +
				", house=" + house +
				", flag=" + flag +
				'}';
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_checkintime() {
		return item_checkintime;
	}

	public void setItem_checkintime(String item_checkintime) {
		this.item_checkintime = item_checkintime;
	}

	public String getItem_checkouttime() {
		return item_checkouttime;
	}

	public void setItem_checkouttime(String item_checkouttime) {
		this.item_checkouttime = item_checkouttime;
	}

	public Integer getItem_checkinday() {
		return item_checkinday;
	}

	public void setItem_checkinday(Integer item_checkinday) {
		this.item_checkinday = item_checkinday;
	}

	public Integer getItem_deposit() {
		return item_deposit;
	}

	public void setItem_deposit(Integer item_deposit) {
		this.item_deposit = item_deposit;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
