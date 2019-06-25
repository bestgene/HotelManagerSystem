
package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import com.woniuxy.pojo.Order;
import org.mockito.internal.matchers.Or;

public interface OrderDAO {
    /**
     * 动态查询订单（预定）信息
     * @param order
     * @return
     */
    @Results({
            @Result(id = true, column = "order_id", property = "order_id"),
            @Result(column = "user_id", property = "user",
                    one = @One(
                            select = "查询用户账户通过账户id"
                    )),
            @Result(column = "user_info_id", property = "userInfo.user_info_id"),
            @Result(column = "user_info_name", property = "userInfo.user_info_name"),
            @Result(column = "user_info_tel", property = "userInfo.user_info_tel"),
            @Result(column = "user_info_idcard", property = "userInfo.user_info_idcard"),
            @Result(column = "user_info_cost", property = "userInfo.user_info_cost"),

            @Result(column = "order_id", property = "items",
                    one = @One(
                            select = "com.woniuxy.dao.ItemDAO.findItemsByOrderId"
                    )),
            @Result(column = "order_number", property = "order_number"),
            @Result(column = "order_paynumber", property = "order_paynumber"),
            @Result(column = "order_createtime", property = "order_createtime"),
            @Result(column = "order_updatetime", property = "order_updatetime"),
            @Result(column = "order_payment", property = "order_payment"),
            @Result(column = "order_totalpay", property = "order_totalpay"),
            @Result(column = "order_deposit", property = "order_deposit"),
            @Result(column = "order_message", property = "order_message"),
            @Result(column = "order_state", property = "order_state"),
            @Result(column = "flag", property = "flag")
    })
    @SelectProvider(type = OrderProvider.class, method = "query")
    public List<Order> showAllOrder(Order order);


    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @Insert("insert into t_order(user_id,user_info_id,order_number," +
            "order_createtime,order_payment,order_totalpay,order_deposit," +
            "order_message,order_state,flag) "
            + "values (#{user_id},#{user_info_id},#{order_number}," +
            "#{order_createtime},#{order_payment},#{order_totalpay},#{order_deposit}," +
            "#{order_message},#{order_state},#{flag})")
    public boolean creatOrder(Order order);

    /**
     * 根据唯一的订单编号  获取订单id
     *
     * @param order
     * @return
     */
    @Select("select * from t_order where order_number=#{order_number}")
    public Order queryOrderId(Order order);


    //当支付完成后，更新订单项中的支付编号
    @Update("update t_order set order_paynumber=#{1},order_state=1 where order_number=#{0}")
    public boolean payOreder(String order_number, String order_paynumber);

    /**
     * 取消预定
     * @param order
     * @return
     */
    @Update("update t_order set flag=0 where order_id=#{order_id} and flag=1 and order_state=1")
    public boolean deleteOrder(Order order);


    @UpdateProvider(type = OrderProvider.class,method = "update")
    public boolean updateOrder(Order order);


}
