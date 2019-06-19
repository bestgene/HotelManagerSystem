package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Order;

public interface OrderDAO {
	@Select({"<script>",
		"select * from t_order",
		"<where>",
			"<if test='user_info_id!=null'>",
			"user_info_id=#{user_info_id}",
			"<if test='order_createtime!=null'>",
			"and order_createtime=#{order_createtime}",
			"<if test='order_state!=null'>",
			"and order_state=#{order_state}",
			"</where>",
			"</srcipt>"	
	})
	public List<Order> showAllOrder();
	
	@Insert("insert into t_order(user_info_id,order_number,order_createtime,order_totalpay,order_deposit) "
	+ "values (#{user_info_id},#{order_number},#{order_createtime},#{order_totalpay},#{order_deposit}")
	public boolean creatOrder(Order order);
	
	
	public boolean payOreder();
	

}
