package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Item;

public interface ItemDAO {
	/**
	 * 新增订单项
	 * @param item
	 * @return
	 */
	@Insert("insert into t_item(house_id,order_id," +
			"item_checkintime,item_checkouttime,item_checkinday,item_arrivetime,item_canceltime,item_isauto)"
			+ "values (#{house.house_id},#{order_id}," +
			"#{item_checkintime},#{item_checkouttime},#{item_checkinday},#{item_arrivetime},#{item_canceltime},#{item_isauto})")
	public boolean createItems(Item item);
	

	
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
	 * @param
	 * @return
	 */
	@Results({
			@Result(id = true,column = "item_id",property = "item_id"),
			@Result(column = "order_id",property = "order_id"),
			@Result(column = "house_id",property = "house",
				one = @One(
						select = "com.woniuxy.dao.HouseDAO.findHouseByHouseId"
				)),
			@Result(column = "item_checkintime",property = "item_checkintime"),
			@Result(column = "item_checkouttime",property = "item_checkouttime"),
			@Result(column = "item_checkinday",property = "item_checkinday"),
			@Result(column = "item_arrivetime",property = "item_arrivetime"),
			@Result(column = "item_canceltime",property = "item_canceltime"),
			@Result(column = "item_isauto",property = "item_isauto"),
			@Result(column = "flag",property = "flag")
	})
	@Select("select * from t_item where order_id=#{order_id}")
	public List<Item> findItemsByOrderId(Integer order_id);
	
	/**
	 * 根据当天日期查找所有已入住房间信息
	 * @param
	 * @return
	 */
	@Results({
			@Result(id = true,column = "item_id",property = "item_id"),
			@Result(column = "order_id",property = "order_id"),
			@Result(column = "house_id",property = "house",
				one = @One(
						select = "com.woniuxy.dao.HouseDAO.findHouseByHouseId"
				)),
			@Result(column = "item_checkintime",property = "item_checkintime"),
			@Result(column = "item_checkouttime",property = "item_checkouttime"),
			@Result(column = "item_checkinday",property = "item_checkinday"),
			@Result(column = "item_arrivetime",property = "item_arrivetime"),
			@Result(column = "item_canceltime",property = "item_canceltime"),
			@Result(column = "item_isauto",property = "item_isauto"),
			@Result(column = "flag",property = "flag")
	})
	@Select("select * from t_item where item_checkintime=#{item_checkintime}")
	public List<Item> findItemsByCheckintime(String item_checkintime);
}
