
package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Order;

public interface OrderDAO {
	/*
	 * 动态SQL：可以根据uid、订单创建时间、订单状态分别查询所有订单
	 * 若什么查询条件都没输入，则查询所有订单
	 */
	@Select({"<script>",
		"select * from t_order",
		"<where>",
			"<if test='userInfo!=null and userInfo.length()!=0'>",
			"user_info_id=#{userInfo.user_info_id}",
			"</if>",
			"<if test='order_createtime!=null and order_createtime.length()!=0'>",
			"and order_createtime=#{order_createtime}",
			"</if>",
			"<if test='order_state!=null and order_state.length()!=0'>",
			"and order_state=#{order_state}",
			"</if>",
		"</where>",
	"</script>"	
	})
	public List<Order> showAllOrder(Order order);
	
	//创建订单
	@Insert("insert into t_order(user_info_id,order_number,order_createtime,order_totalpay,order_deposit) "
	+ "values (#{user_info_id},#{order_number},#{order_createtime},#{order_totalpay},#{order_deposit}")
	public boolean creatOrder(Order order);
	
	//当支付完成后，更新订单项中的支付编号
	@Update("update t_order set order_paynumber=#{1},order_state=1 where order_number=#{0}")
	public boolean payOreder(String order_number,String order_paynumber);
	

}
