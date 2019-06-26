package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;

public interface OrderService {
	/**
	 * 根据查询条件动态查询所有订单信息
	 * @param order
	 * @return
	 */
	public List<Order> showAllOrder(Order order);
	/**
	 * 生成订单，并生成订单项（加入事务）
	 * @param order
	 * @return
	 */
	public boolean createOrder(Order order);
	/**
	 * 支付订单
	 * @param
	 * @return
	 */
	public boolean payOrder(String order_number,String pay_number);


	/**
	 * 通过订单编号查询订单
	 * @param order
	 * @return
	 */
	public Order queryOrderByOrderNumber(Order order);


	/**
	 * 删除订单
	 * @param order
	 * @return
	 */
	public boolean deleteOrder(Order order);

	/**
	 * 修改留言或者用户信息
	 * @param order
	 * @return
	 */
	public boolean updateOrder(Order order);

	public List<Item> queryItemByOid(Order order);
	
	/**
	 * 付押金
	 */
	public int payDeposit(String order_number,String pay_number);
}
