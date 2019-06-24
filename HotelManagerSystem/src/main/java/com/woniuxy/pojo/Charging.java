package com.woniuxy.pojo;

import java.io.Serializable;

public class Charging implements Serializable{
	//计费id
	private Integer charging_id;
	//是否全场打折
	private Integer charging_isqc;
	//全场打折比例
	private Double charging_ratio;
	//是否线上打折
	private Integer online_isqc;
	//线上打折比例
	private Double online_ratio;
	//金额是否取整
	private Integer charging_isqzdz;
	private Integer flag;

	@Override
	public String toString() {
		return "Charging{" +
				"charging_id=" + charging_id +
				", charging_isqc=" + charging_isqc +
				", charging_ratio=" + charging_ratio +
				", online_isqc=" + online_isqc +
				", online_ratio=" + online_ratio +
				", charging_isqz=" + charging_isqzdz +
				", flag=" + flag +
				'}';
	}

	public Integer getCharging_id() {
		return charging_id;
	}

	public void setCharging_id(Integer charging_id) {
		this.charging_id = charging_id;
	}

	public Integer getCharging_isqc() {
		return charging_isqc;
	}

	public void setCharging_isqc(Integer charging_isqc) {
		this.charging_isqc = charging_isqc;
	}

	public Double getCharging_ratio() {
		return charging_ratio;
	}

	public void setCharging_ratio(Double charging_ratio) {
		this.charging_ratio = charging_ratio;
	}

	public Integer getOnline_isqc() {
		return online_isqc;
	}

	public void setOnline_isqc(Integer online_isqc) {
		this.online_isqc = online_isqc;
	}

	public Double getOnline_ratio() {
		return online_ratio;
	}

	public void setOnline_ratio(Double online_ratio) {
		this.online_ratio = online_ratio;
	}

	public Integer getCharging_isqzdz() {
		return charging_isqzdz;
	}

	public void setCharging_isqzdz(Integer charging_isqzdz) {
		this.charging_isqzdz = charging_isqzdz;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
