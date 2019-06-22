package com.woniuxy.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.woniuxy.pojo.*;
import org.junit.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.service.OrderService;
import org.springframework.web.servlet.ModelAndView;

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
	 * @param reserve
	 * @return
	 */
	public ModelAndView createOrder(Reserve reserve, HttpServletRequest request) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();

	    //1.创建 订单表
		//order表赋值
		Order order = reserveToOrderAndItem(reserve);
		//获取session中的用户
		User user = (User) request.getSession().getAttribute("user");
		user = new User();
		user.setUid(1);
		//设置操作角色
		order.setUser(user);
		//判断操作用户的角色
		//如果线上用户获取其账户会员等级，如果是后台人员操作则更具用户填入信息，查询其会员等级


		//计算金额
		//更具房间计算初步金额
		BigDecimal totalpay = order.getOrder_totalpay();
		//查询计费表，线上是否打折，线上打折额度,sql语句
		Charging charging = new Charging();
		charging.setCharging_ratio((double) 1);
		charging.setOnline_ratio((double) 1);
		//0代表线上支付，1代表线下支付
		//如果线上支付
		if (order.getOrder_payment()==0){
			if (charging.getOnline_isqc()==0){
				totalpay = totalpay.multiply(new BigDecimal(charging.getOnline_ratio()));
			}
		}
		//金额再乘以会员打折比率***********
		//totalpay=XXXXXXX

		//全场打折 0不打折，1打折
		if (charging.getCharging_isqc()==1){
			totalpay = totalpay.multiply(new BigDecimal(charging.getCharging_ratio()));
		}
		//最后是否取整 0不去整,1取整
		if (charging.getCharging_isqz()==1){
			totalpay = new BigDecimal(totalpay.setScale(0,BigDecimal.ROUND_DOWN).longValue());
		}
		//给订单表赋值,总金额=需要支付金额+押金
		order.setOrder_totalpay(totalpay.add(new BigDecimal(order.getOrder_deposit())));

		System.out.println(order);

		//新增order、item
		boolean flag = orderService.createOrder(order);
        if (flag){
            //成功进入支付页面
            modelAndView.setViewName("");
            modelAndView.addObject("order");
        }else {

        }



		return modelAndView;
	}
	
	/**
	 * 支付订单（若支付完成，更改该订单的状态，并插入支付编号）
	 * @param order
	 * @return
	 */
	public String payOrder(Order order){
		return "支付完成";
	}

	/**
	 * 信息装换
	 * @param reserve
	 * @return
	 */
	public Order reserveToOrderAndItem(Reserve reserve) throws ParseException {
		Order order = new Order();
		//设置用户信息
		order.setUserInfo(reserve.getUserInfo());
		//设置订单编号
		String order_number = System.currentTimeMillis()+""+new Random().nextInt(9000000)+1000000;
		order.setOrder_number(order_number);
		//设置订单生成时间
		order.setOrder_createtime(System.currentTimeMillis()+"");
		//设置押金(简单)
		order.setOrder_deposit(reserve.getHouses().size()*50);
		//设置支付类型
		order.setOrder_payment(reserve.getReserve_payment());
		//赋值用户留言
		order.setOrder_message(reserve.getReserve_message());
		//设置房间总净额
		BigDecimal totalpay = new BigDecimal(0);
		List<Item> items = new ArrayList<>();
		Integer day = getday(reserve.getReserve_checkintime(),reserve.getReserve_checkouttime());
		for (House house:reserve.getHouses()
		) {
            totalpay = totalpay.add(new BigDecimal(String.valueOf(house.getHouseType().getHouse_type_price())));
			Item item = new Item();
			item.setHouse(house);
			item.setItem_checkintime(reserve.getReserve_checkintime());
			item.setItem_checkouttime(reserve.getReserve_checkouttime());
			item.setItem_checkinday(day);
			item.setItem_arrivetime(reserve.getReserve_arrivetime());
			item.setItem_canceltime(reserve.getReserve_canceltime());
			item.setItem_isauto(reserve.getReserve_isauto());
			items.add(item);
		}
		//设置订单项
		order.setItems(items);
		//金额初步赋值
        order.setOrder_totalpay(totalpay);
		return order;
	}



	public int getday(String day1,String day2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date1 = sdf.parse(day1);
		Date date2 = sdf.parse(day2);

		return (int) ((date2.getTime()-date1.getTime())/1000/60/60/24);
	}

	@Test
	public void test() throws ParseException {
		System.out.println(getday("2019-06-18 12:30","2019-06-20 12:30"));
	}
}
