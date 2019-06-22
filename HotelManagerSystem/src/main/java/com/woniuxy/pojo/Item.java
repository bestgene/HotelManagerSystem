package com.woniuxy.pojo;

import java.io.Serializable;

/**
 * 订单项类
 */
public class Item implements Serializable{
	//订单项编号
	private Integer item_id;
	//订单id
	private Integer order_id;
	//房间
	private House house;
	//入住时间
	private String item_checkintime;
	//退房时间
	private String item_checkouttime;
	//住宿天数
	private Integer item_checkinday;
	//预定抵达时间
	private String item_arrivetime;
	//预定取消时间
	private String item_canceltime;
	//是否自动取消预定
	private Integer item_isauto;
	//状态
	private Integer flag;

	@Override
	public String toString() {
		return "Item{" +
				"item_id=" + item_id +
				", order_id=" + order_id +
				", house=" + house +
				", item_checkintime='" + item_checkintime + '\'' +
				", item_checkouttime='" + item_checkouttime + '\'' +
				", item_checkinday=" + item_checkinday +
				", item_arrivetime='" + item_arrivetime + '\'' +
				", item_canceltime='" + item_canceltime + '\'' +
				", item_isauto=" + item_isauto +
				", flag=" + flag +
				'}';
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
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

	public String getItem_arrivetime() {
		return item_arrivetime;
	}

	public void setItem_arrivetime(String item_arrivetime) {
		this.item_arrivetime = item_arrivetime;
	}

	public String getItem_canceltime() {
		return item_canceltime;
	}

	public void setItem_canceltime(String item_canceltime) {
		this.item_canceltime = item_canceltime;
	}

	public Integer getItem_isauto() {
		return item_isauto;
	}

	public void setItem_isauto(Integer item_isauto) {
		this.item_isauto = item_isauto;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
