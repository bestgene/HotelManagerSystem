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

	@Override
	/**
	 * 根据查询条件展示所有订单
	 */
	public List<Order> showAllOrder(Order order) {
		// 先判断用户是否传入姓名，若传入了姓名，则根据姓名查询到该UserInfo
		UserInfo userInfo = order.getUserInfo();
		List<Order> allOrder = orderDAO.showAllOrder(order);
		return allOrder;
	}

	@Override
	/**
	 * 生成订单,并生成对应的订单项
	 */
	public boolean createOrder(Order order) {
		boolean flag = orderDAO.creatOrder(order);
		// 若订单生成成功，继续生成订单项
		if (flag) {
			List<Item> items = order.getItems();
			for (Item item : items) {
				// 生成对应的订单项
				flag = itemDAO.createItems(item);
				// 并在中间表生成对应信息（订单-订单项）
				Integer order_id = order.getOrder_id();
				Integer item_id = item.getItem_id();
				flag = itemDAO.createOrderItem(order_id, item_id);
			}
		}
		return flag;
	}

	@Override
	/**
	 * 支付订单
	 */
	public boolean payOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

}
