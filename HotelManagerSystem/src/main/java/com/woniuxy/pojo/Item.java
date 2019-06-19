package com.woniuxy.pojo;

import java.io.Serializable;

public class Item implements Serializable{
	//订单项编号
	private int item_id;
	private String item_checkintime;
	private String item_checkouttime;
	private int item_checkinday;
	private int deposit;
	private int house_id;
	private int flag;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
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
	public int getItem_checkinday() {
		return item_checkinday;
	}
	public void setItem_checkinday(int item_checkinday) {
		this.item_checkinday = item_checkinday;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getHouse_id() {
		return house_id;
	}
	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	
	
}
