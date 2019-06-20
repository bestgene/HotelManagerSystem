package com.woniuxy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 预定信息表
 */
public class Reserve implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//预定信息id
    private Integer reserve_id;
    //预定信息编号
    private String reserve_idnumber;
    //用户信息
    private UserInfo userInfo;
    //预定房间
    private List<House> house;
    //预定入住时间
    private String reserve_checkintime;
    //预定退房时间
    private String reserve_checkouttime;
    //预定抵达时间
    private String reserve_arrivetime;
    //预定取消时间
    private String reserve_canceltime;
    //是否自动取消预定
    private Integer reserve_isauto;
    //预定押金
    private Integer reserve_deposit;
    //支付方式
    private Integer reserve_payment;
    //支付金额
    private BigDecimal reserve_paynumber;
    //客户留言
    private String reserve_message;

    private Integer flag;

    @Override
    public String toString() {
        return "Reserve{" +
                "reserve_id=" + reserve_id +
                ", reserve_idnumber='" + reserve_idnumber + '\'' +
                ", userInfo=" + userInfo +
                ", house=" + house +
                ", reserve_checkintime='" + reserve_checkintime + '\'' +
                ", reserve_checkouttime='" + reserve_checkouttime + '\'' +
                ", reserve_arrivetime='" + reserve_arrivetime + '\'' +
                ", reserve_canceltime='" + reserve_canceltime + '\'' +
                ", reserve_isauto=" + reserve_isauto +
                ", reserve_deposit=" + reserve_deposit +
                ", reserve_payment=" + reserve_payment +
                ", reserve_paynumber=" + reserve_paynumber +
                ", reserve_message='" + reserve_message + '\'' +
                ", flag=" + flag +
                '}';
    }

    public Integer getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(Integer reserve_id) {
        this.reserve_id = reserve_id;
    }

    public String getReserve_idnumber() {
        return reserve_idnumber;
    }

    public void setReserve_idnumber(String reserve_idnumber) {
        this.reserve_idnumber = reserve_idnumber;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<House> getHouse() {
        return house;
    }

    public void setHouse(List<House> house) {
        this.house = house;
    }

    public String getReserve_checkintime() {
        return reserve_checkintime;
    }

    public void setReserve_checkintime(String reserve_checkintime) {
        this.reserve_checkintime = reserve_checkintime;
    }

    public String getReserve_checkouttime() {
        return reserve_checkouttime;
    }

    public void setReserve_checkouttime(String reserve_checkouttime) {
        this.reserve_checkouttime = reserve_checkouttime;
    }

    public String getReserve_arrivetime() {
        return reserve_arrivetime;
    }

    public void setReserve_arrivetime(String reserve_arrivetime) {
        this.reserve_arrivetime = reserve_arrivetime;
    }

    public String getReserve_canceltime() {
        return reserve_canceltime;
    }

    public void setReserve_canceltime(String reserve_canceltime) {
        this.reserve_canceltime = reserve_canceltime;
    }

    public Integer getReserve_isauto() {
        return reserve_isauto;
    }

    public void setReserve_isauto(Integer reserve_isauto) {
        this.reserve_isauto = reserve_isauto;
    }

    public Integer getReserve_deposit() {
        return reserve_deposit;
    }

    public void setReserve_deposit(Integer reserve_deposit) {
        this.reserve_deposit = reserve_deposit;
    }

    public Integer getReserve_payment() {
        return reserve_payment;
    }

    public void setReserve_payment(Integer reserve_payment) {
        this.reserve_payment = reserve_payment;
    }

    public BigDecimal getReserve_paynumber() {
        return reserve_paynumber;
    }

    public void setReserve_paynumber(BigDecimal reserve_paynumber) {
        this.reserve_paynumber = reserve_paynumber;
    }

    public String getReserve_message() {
        return reserve_message;
    }

    public void setReserve_message(String reserve_message) {
        this.reserve_message = reserve_message;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
