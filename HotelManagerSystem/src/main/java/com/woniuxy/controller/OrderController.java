package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	
	
	@RequestMapping("/showAllOrder")
	@ResponseBody
	/**
	 * 根据查询条件查询所有订单
	 * @param order
	 * @return
	 */
	public List<Order> showAllOrder(Order order){
		List<Order> allOrder = orderService.showAllOrder(order);	
		System.out.println(allOrder);
		return allOrder;
	}
	
	/**
	 * 生成订单
	 * @param order
	 * @return
	 */
	public String createOrder(Order order){
		String result="订单生成失败";
		boolean flag = orderService.createOrder(order);
		if(flag){
			result="订单生成成功";
			return result;
		}
		return result;
	}
	
	/**
	 * 支付订单（若支付完成，更改该订单的状态，并插入支付编号）
	 * @param order
	 * @return
	 */
	public String payOrder(Order order){
		return "支付完成";
	}
}
