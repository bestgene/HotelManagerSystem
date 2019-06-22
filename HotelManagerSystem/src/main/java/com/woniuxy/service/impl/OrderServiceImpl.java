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
		//新增order
		boolean flag = orderDAO.creatOrder(order);
		if (flag){
			//获取新生成的orderid
			Integer order_id = orderDAO.queryOrderId(order);
			// 若订单生成成功，继续生成订单项
			for (Item item:order.getItems()
			) {
				item.setOrder_id(order_id);
				itemDAO.createItems(item);
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

	@Override
	public Integer queryOrderId(Order order) {
		return orderDAO.queryOrderId(order);
	}

}