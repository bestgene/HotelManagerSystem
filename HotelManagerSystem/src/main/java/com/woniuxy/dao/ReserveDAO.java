package com.woniuxy.dao;

import com.woniuxy.model.FlagReserveId;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.Reserve;
import com.woniuxy.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ReserveDAO {


    @Insert("insert into t_reserve(reserve_idnumber,user_id,user_info_id," +
            "reserve_checkintime,reserve_checkouttime,reserve_arrivetime," +
            "reserve_canceltime,reserve_isauto,reserve_deposit,reserve_payment," +
            "reserve_paynumber,reserve_message) " +
            "values(" +
            "#{reserve_idnumber},#{user_id},#{userInfo.user_info_id}," +
            "#{reserve_checkintime},#{reserve_checkouttime},#{reserve_arrivetime}," +
            "#{reserve_canceltime},#{reserve_isauto},#{reserve_deposit},#{reserve_payment}," +
            "#{reserve_paynumber},#{reserve_message})")
    public void addReserve(Reserve reserve);


    @SelectProvider(type = ReserveProvider.class,method = "queryReserve")
    public Reserve findReserveIdByIdnumber(Reserve reserve);


    @Insert("insert into t_reserve_house values(#{reserve.reserve_id},#{house.house_id})")
    public void addReserveIdAndHouseId(Reserve reserve,House house);


    @Results({
            @Result(id = true,column = "reserve_id",property = "reserve_id"),
            @Result(column = "reserve_idnumber",property = "reserve_idnumber"),
            @Result(column = "reserve_checkintime",property = "reserve_checkintime"),
            @Result(column = "reserve_checkouttime",property = "reserve_checkouttime"),
            @Result(column = "reserve_arrivetime",property = "reserve_arrivetime"),
            @Result(column = "reserve_canceltime",property = "reserve_canceltime"),
            @Result(column = "reserve_isauto",property = "reserve_isauto"),
            @Result(column = "reserve_deposit",property = "reserve_deposit"),
            @Result(column = "reserve_payment",property = "reserve_payment"),
            @Result(column = "reserve_paynumber",property = "reserve_paynumber"),
            @Result(column = "reserve_message",property = "reserve_message"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "reserve_id",property = "houses",
                one = @One(
                        select = "selectHousesByReserveId"
                ))
    })
    @SelectProvider(type = ReserveProvider.class,method = "queryListReserve")
    public List<Reserve> selectReserveByUserInfoOrUser(Reserve reserve);


    @SelectProvider(type = ReserveProvider.class,method = "queryInfoId")
    public List<UserInfo> selectUserInfoIdByNameTelIdcard(UserInfo userInfo);

    @Select("select t_house.house_id,house_name,house_state,house_type_id,flag " +
            "from t_house,t_reserve_house where " +
            "t_house.house_id=t_reserve_house.house_id " +
            "and reserve_id=#{reserve_id}")
    public List<House> selectHousesByReserveId(Integer reserve_id);



    @Update("update t_reserve set flag=#{flag} where reserve_id=#{reserve_id}")
    public void updateReserveState(Reserve reserve);


    @UpdateProvider(type = ReserveProvider.class,method = "update")
    public void updateReserve(Reserve reserve);

}
