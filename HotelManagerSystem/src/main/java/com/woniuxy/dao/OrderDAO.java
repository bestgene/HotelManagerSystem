
package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import com.woniuxy.pojo.Order;

public interface OrderDAO {
	/*
	 * 动态SQL：可以根据uid、订单创建时间、订单状态分别查询所有订单
	 * 若什么查询条件都没输入，则查询所有订单
	 */
	@Results({
		@Result(id=true,column="order_id",property="order_id"),
		@Result(column="user_info_id",property="userInfo",
		one=@One(select="aa")),
		@Result(column="order_number",property="order_number"),
		@Result(column="order_paynumber",property="order_paynumber"),
		@Result(column="order_createtime",property="order_createtime"),
		@Result(column="order_updatetime",property="order_updatetime"),
		@Result(column="order_state",property="order_state"),
		@Result(column="order_totalpay",property="order_totalpay"),
		@Result(column="order_deposit",property="order_deposit"),
		@Result(column="order_id",property="items",many=@Many(select="bb")),
		@Result(column="flag",property="flag")
	})
	@SelectProvider(type = OrderProvider.class,method = "query")
	public List<Order> showAllOrder(Order order);



	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	@Insert("insert into t_order(user_id,user_info_id,order_number,order_createtime,order_payment,order_totalpay,order_deposit,order_message) "
	+ "values (#{user_id},#{user_info_id},#{order_number},#{order_createtime},#{order_payment},#{order_totalpay},#{order_deposit},#{order_message}")
	public boolean creatOrder(Order order);

	/**
	 * 根据唯一的订单编号  获取订单id
	 * @param order
	 * @return
	 */
	@Select("select order_id from t_order where order_number=#{order_number}")
	public Integer queryOrderId(Order order);



	//当支付完成后，更新订单项中的支付编号
	@Update("update t_order set order_paynumber=#{1},order_state=1 where order_number=#{0}")
	public boolean payOreder(String order_number,String order_paynumber);
	

}
