package com.woniuxy.service;

import java.util.List;

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
	 * @param order
	 * @return
	 */
	public boolean payOrder(Order order);
}
