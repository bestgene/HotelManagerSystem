package com.woniuxy.pojo;
import java.io.Serializable;
import java.math.BigDecimal;

public class Level implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer level_id;
	private Integer level_quota;
	private String level_name;
	private BigDecimal  level_discount;
	private Integer flag;

	public Integer getLevel_id() {
		return level_id;
	}
	public void setLevel_id(Integer level_id) {
		this.level_id = level_id;
	}
	public Integer getLevel_quota() {
		return level_quota;
	}
	public void setLevel_quota(Integer level_quota) {
		this.level_quota = level_quota;
	}
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	public BigDecimal getLevel_discount() {
		return level_discount;
	}
	public void setLevel_discount(BigDecimal level_discount) {
		this.level_discount = level_discount;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Level [level_id=" + level_id + ", level_quota=" + level_quota + ", level_name=" + level_name
				+ ", level_discount=" + level_discount + ", flag=" + flag + "]";
	}
	

	
	
}
