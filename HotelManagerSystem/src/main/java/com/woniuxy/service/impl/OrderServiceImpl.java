package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.ItemDAO;
import com.woniuxy.dao.OrderDAO;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.service.OrderService;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderDAO orderDAO;
	@Resource
	private ItemDAO itemDAO;


	/**
	 * 根据查询条件展示所有订单
	 */
	@Override
	public List<Order> showAllOrder(Order order) {

		return orderDAO.showAllOrder(order);
	}


	/**
	 * 生成订单,并生成对应的订单项
	 */
	@Override
	public boolean createOrder(Order order) {
		//新增order
		boolean flag = orderDAO.creatOrder(order);
		if (flag){
			//获取新生成的orderid
			Integer order_id = orderDAO.queryOrderId(order).getOrder_id();
			// 若订单生成成功，继续生成订单项
			for (Item item:order.getItems()
			) {
				item.setOrder_id(order_id);
				itemDAO.createItems(item);
			}
		}
		return flag;
	}

	/**
	 * 支付订单
	 * @param order
	 * @return
	 */
	@Override
	public boolean payOrder(String order_number,String pay_number) {

		return orderDAO.payOreder(order_number,pay_number);
	}

	@Override
	public Order queryOrderByOrderNumber(Order order) {
		return orderDAO.queryOrderId(order);
	}

	@Override
	public boolean deleteOrder(Order order) {

		return orderDAO.deleteOrder(order);
	}

	@Override
	public boolean updateOrder(Order order) {
		return orderDAO.updateOrder(order);
	}


}
