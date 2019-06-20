package com.woniuxy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class HouseType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer house_type_id;
	//房间类型id
	private String house_type_name;
	//房间名称
	private BigDecimal house_type_price;
	//房间类型价格
	private String house_type_img;
	//房间头像
	private Integer flag;
	//软删除
<<<<<<< HEAD
	public HouseType(Integer house_type_id, String house_type_name, BigDecimal house_type_price, String house_type_img,
			Integer flag) {
		this.house_type_id = house_type_id;
		this.house_type_name = house_type_name;
		this.house_type_price = house_type_price;
		this.house_type_img = house_type_img;
		this.flag = flag;
	}
	public HouseType() {}
	@Override
	public String toString() {
		return "HouseType [house_type_id=" + house_type_id + ", house_type_name=" + house_type_name
				+ ", house_type_price=" + house_type_price + ", house_type_img=" + house_type_img + ", flag=" + flag
				+ "]";
	}
	public Integer getHouse_type_id() {
		return house_type_id;
	}
	public void setHouse_type_id(Integer house_type_id) {
		this.house_type_id = house_type_id;
	}
	public String getHouse_type_name() {
		return house_type_name;
	}
	public void setHouse_type_name(String house_type_name) {
		this.house_type_name = house_type_name;
	}
	public BigDecimal getHouse_type_price() {
		return house_type_price;
	}
	public void setHouse_type_price(BigDecimal house_type_price) {
		this.house_type_price = house_type_price;
	}
	public String getHouse_type_img() {
		return house_type_img;
	}
	public void setHouse_type_img(String house_type_img) {
		this.house_type_img = house_type_img;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
=======
	private Integer num;
	//相同类型的房间数量
	public HouseType(Integer house_type_id, String house_type_name, BigDecimal house_type_price, String house_type_img,
			Integer flag, Integer num) {
		this.house_type_id = house_type_id;
		this.house_type_name = house_type_name;
		this.house_type_price = house_type_price;
		this.house_type_img = house_type_img;
		this.flag = flag;
		this.num = num;
	}
	public HouseType() {
	}
	public Integer getHouse_type_id() {
		return house_type_id;
	}
	public void setHouse_type_id(Integer house_type_id) {
		this.house_type_id = house_type_id;
	}
	public String getHouse_type_name() {
		return house_type_name;
	}
	public void setHouse_type_name(String house_type_name) {
		this.house_type_name = house_type_name;
	}
	public BigDecimal getHouse_type_price() {
		return house_type_price;
	}
	public void setHouse_type_price(BigDecimal house_type_price) {
		this.house_type_price = house_type_price;
	}
	public String getHouse_type_img() {
		return house_type_img;
	}
	public void setHouse_type_img(String house_type_img) {
		this.house_type_img = house_type_img;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "HouseType [house_type_id=" + house_type_id + ", house_type_name=" + house_type_name
				+ ", house_type_price=" + house_type_price + ", house_type_img=" + house_type_img + ", flag=" + flag
				+ ", num=" + num + "]";
	}
	
>>>>>>> refs/remotes/origin/Tony
}
