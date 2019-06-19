package com.woniuxy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable{
	//订单号
	private int order_id;
	//该订单的用户具体信息
	private UserInfo userInfo;
	//订单编号
	private String order_number;
	//订单的支付编号
	private String order_paynumber;
	//订单的生成时间
	private String order_createtime;
	//订单状态的改变时间
	private String order_updatetime;
	//订单的状态
	private int order_state;
	//该订单的总金额
	private BigDecimal order_totalpay;
	//订单的押金
	private int order_deposit;
	private int flag;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getOrder_paynumber() {
		return order_paynumber;
	}
	public void setOrder_paynumber(String order_paynumber) {
		this.order_paynumber = order_paynumber;
	}
	public String getOrder_createtime() {
		return order_createtime;
	}
	public void setOrder_createtime(String order_createtime) {
		this.order_createtime = order_createtime;
	}
	public String getOrder_updatetime() {
		return order_updatetime;
	}
	public void setOrder_updatetime(String order_updatetime) {
		this.order_updatetime = order_updatetime;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	public BigDecimal getOrder_totalpay() {
		return order_totalpay;
	}
	public void setOrder_totalpay(BigDecimal order_totalpay) {
		this.order_totalpay = order_totalpay;
	}
	public int getOrder_deposit() {
		return order_deposit;
	}
	public void setOrder_deposit(int order_deposit) {
		this.order_deposit = order_deposit;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_number=" + order_number + ", order_paynumber=" + order_paynumber
				+ ", order_createtime=" + order_createtime + ", order_updatetime=" + order_updatetime + ", order_state="
				+ order_state + ", order_totalpay=" + order_totalpay + ", order_deposit=" + order_deposit + ", flag="
				+ flag + "]";
	}
	
	
	



}
