package com.woniuxy.dao;

import com.woniuxy.model.FlagReserveId;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.Reserve;
import com.woniuxy.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ReserveDAO {

    /**
     * 新增预定信息
     * @param reserve
     */
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

    /**
     * 查询指定预定信息
     * @param reserve
     * @return
     */
    @SelectProvider(type = ReserveProvider.class,method = "queryReserve")
    public Reserve queryReserve(Reserve reserve);

    /**
     * 中间表添加值
     * @param reserve
     * @param house
     */
    @Insert("insert into t_reserve_house values(#{reserve.reserve_id},#{house.house_id})")
    public void addReserveIdAndHouseId(Reserve reserve,House house);

    /**
     * 预定信息查询
     * @param reserve
     * @return
     */
    @Results({
            @Result(id = true,column = "reserve_id",property = "reserve_id"),
            @Result(column = "reserve_idnumber",property = "reserve_idnumber"),
            @Result(column = "user_id",property = "user_id"),

            @Result(column = "user_info_id",property = "userInfo.user_info_id"),
            @Result(column = "user_info_name",property = "userInfo.user_info_name"),
            @Result(column = "user_info_tel",property = "userInfo.user_info_tel"),
            @Result(column = "user_info_idcard",property = "userInfo.user_info_idcard"),
            @Result(column = "user_info_cost",property = "userInfo.user_info_cost"),

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
    public List<Reserve> queryListReserve(Reserve reserve);


    /**
     * 根据预定信息id查询对应房间
     * @param reserve_id
     * @return
     */
    @Select("select t_house.house_id,house_name,house_state,house_type_id,flag " +
            "from t_house,t_reserve_house where " +
            "t_house.house_id=t_reserve_house.house_id " +
            "and reserve_id=#{reserve_id}")
    public List<House> selectHousesByReserveId(Integer reserve_id);


    /**
     * 根据预定信息id修改预定信息状态
     * @param reserve
     */
    @Update("update t_reserve set flag=#{flag} where reserve_id=#{reserve_id}")
    public void updateReserveState(Reserve reserve);


    /**
     * 动态修改预定信息表
     * @param reserve
     */
    @UpdateProvider(type = ReserveProvider.class,method = "update")
    public void updateReserve(Reserve reserve);

}
