package com.woniuxy.dao;

import com.woniuxy.pojo.Reserve;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface ReserveDAO {


    @Insert("insert into t_reserve(user_info_id,house_id,reserve_checkintime," +
            "reserve_checkouttime,reserve_arrivetime,reserve_canceltime,reserve_isauto" +
            ")" +
            "")
    public void addReserve(Reserve reserve);
}
