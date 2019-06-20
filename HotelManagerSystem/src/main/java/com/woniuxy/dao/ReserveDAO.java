package com.woniuxy.dao;

import com.woniuxy.pojo.Reserve;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReserveDAO {


    @Insert("insert into t_reserve(reserve_idnumber,user_info_id," +
            "reserve_checkintime,reserve_checkouttime,reserve_arrivetime,reserve_canceltime,reserve_isauto" +
            "reserve_deposit,reserve_payment,reserve_paynumber,reserve_message) " +
            "values(" +
            "#{reserve_idnumber},#{userInfo.user_info_id}," +
            "#{reserve_checkintime},#{reserve_checkouttime},#{reserve_arrivetime}," +
            "#{reserve_canceltime},#{reserve_isauto},#{reserve_deposit},#{reserve_payment}," +
            "#{reserve_paynumber},#{reserve_message})")
    public void addReserve(Reserve reserve);


    @Select("select reserve_id form t_reserve where reserve_idnumber=#{reserve_idnumber}")
    public Integer findReserveIdByIdnumber(Reserve reserve);

    //中间表charu
    @Insert("insert into t_reserve_house values(#{0},#{1})")
    public void addReserveIdAndHouseId(Integer reserve_id,Integer house_id);
}
