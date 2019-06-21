package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Item;

public interface ItemDAO {
	/**
	 * 创建订单项
	 * @param item
	 * @return
	 */
	@Insert("insert into t_item(item_checkintime,item_checkouttime,item_checkinday,item_deposit,item_deposit)"
			+ "values (#{item_checkintime},#{item_checkouttime},#{item_checkinday},#{item_deposit},#{item_deposit}")
	public boolean createItems(Item item);
	
	/**
	 * 创建订单-订单项中间表
	 * @param order_id
	 * @param item_id
	 * @return
	 */
	@Insert("insert into t_item_order(order_id,item_id) values (#{0},#{1})")
	public boolean createOrderItem(int order_id,int item_id);
	
	/**
	 * 修改订单项
	 * @param item
	 * @return
	 */
	@Update({"<script>",
		"update t_item ",
		"<set>",
		"</set>",
		"</script>"
	})
	public boolean modifyItems(Item item);
	
	/**
	 * 根据订单id找到其对应的订单项
	 * @param OrderId
	 * @return
	 */
	@Select("select item_id,item_checkouttime,item_checkintime,item_checkinday,item_deposit,house_id from t_item_order "
			+ "inner join t_item on t_item_order.item_id=t_item.item_id where order_id=#{OrderId}")
	public List<Item> findItemsByOrderId(int OrderId);
}
