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
import com.woniuxy.service.ChargingService;
import com.woniuxy.service.HouseService;
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
    @Resource
    private HouseService houseService;
    @Resource
    private ChargingService chargingService;


    /**
     * 线上预定、线下预定查询
     * 根据查询条件查询所有订单
     * 当前预定state1 flag1
     * 历史预定state3 flag2
     *
     * 查询当前入住state2 flag1   操作员操作
     *
     * @param
     * @return
     */
    @RequestMapping("/showAllOrder")
    @ResponseBody
    public List<Order> showAllOrder(Order order,String cxfs, HttpServletRequest request) {
        System.out.println(order);
        //如果是正常用户操作
        User user = (User) request.getSession().getAttribute("user");
        user = new User();
        user.setUser_id(1);
        user.setRole_id(1);
        if (user.getRole_id() == 1) {
            order.setUser(user);
        }
        if (cxfs.equals("当前预定")) {
            order.setOrder_state(1);
            order.setFlag(1);
        } else if (cxfs.equals("历史预定")) {
            order.setOrder_state(3);
            order.setFlag(2);
        } else if (cxfs.equals("当前入住")) {
            if (user.getRole_id() == 2) {
                //查询当前入住
                order.setOrder_state(2);
                order.setFlag(1);
            }
        } else if (cxfs.equals("所有订单")) {
            if (user.getRole_id() == 2) {
                order.setFlag(3);
            }
        }


        return orderService.showAllOrder(order);
    }

    /**
     * 线下开单
     * 后台人员操作
     * 生成订单
     *
     * 状态state2 flag1 支付方式0
     * 不添加额外预定信息
     *
     * @param reserve
     * @return
     */
    @RequestMapping("/createKdOrder")
    @ResponseBody
    public String createKdOrder(Reserve reserve, HttpServletRequest request) throws ParseException {
        //根据房间类型、选择数量、入住时间、退房时间查询数据库，获取房间

        List<House> houses = new ArrayList<>();
        //1.创建 订单表
        Order order = new Order();
        //设置用户信息
        //先查询输入用户信息的id，如果没有则新创建一个对应已手机号为账户的账户，以及新增一条用户信息表
        //并且获取这个新增的用户信息id，最后给order赋值，这里应该进行注释操作，而不是直接赋值
        order.setUserInfo(reserve.getUserInfo());
        //获取session中的用户
        User user = (User) request.getSession().getAttribute("user");
        user = new User();
        user.setUser_id(1);
        //设置操作角色
        order.setUser(user);
        //更具用户填入信息，查询其会员等级


        order.setOrder_state(2);
        order.setFlag(1);
        //设置订单编号
        order.setOrder_number(System.currentTimeMillis() + "" + new Random().nextInt(9000000) + 1000000);

        //设置订单生成时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setOrder_createtime(sdf.format(new Date()));
        //设置押金(简单)
        order.setOrder_deposit(houses.size() * 50);
        //设置支付类型,线下后台选择线下支付
        order.setOrder_payment(0);
        //赋值用户留言
        order.setOrder_message(reserve.getReserve_message());
        //设置房间总净额
        BigDecimal housestotalpay = new BigDecimal(0);
        List<Item> items = new ArrayList<>();
        Integer day = getday(reserve.getReserve_checkintime(), reserve.getReserve_checkouttime());
        for (House house : houses
        ) {
            housestotalpay = housestotalpay.add(new BigDecimal(String.valueOf(house.getHouseType().getHouse_type_price())));
            Item item = new Item();
            item.setHouse(house);
            item.setItem_checkintime(reserve.getReserve_checkintime());
            item.setItem_checkouttime(reserve.getReserve_checkouttime());
            item.setItem_checkinday(day);
            items.add(item);
        }
        //设置订单项
        order.setItems(items);
        //金额初步赋值
        order.setOrder_totalpay(housestotalpay);


        //查询计费表，线上是否打折，线上打折额度,sql语句
        Charging charging = chargingService.queryCharging();
        //计算金额
        order = chargingpay(order, charging);

        System.out.println(order);

        //新增order、item
        boolean flag = orderService.createOrder(order);

        //默认已付款
        return "线下开单";

    }

    /**
     * 线上预定、线下预定
     * 生成订单
     * 线上预定状态state0 flag0
     * 线下预定状态state1 flag1 支付方式0
     *
     * @param reserve
     * @return
     */
    @RequestMapping("/createYdOrder")
    public String createYdOrder(Reserve reserve, HttpServletRequest request) throws ParseException {
        //根据房间类型、选择数量、入住时间、退房时间查询数据库，获取房间

        List<House> houses = new ArrayList<>();
        //1.创建 订单表
        Order order = new Order();
        //设置用户信息
        //先查询输入用户信息的id，如果没有则新创建一个对应已手机号为账户的账户，以及新增一条用户信息表
        //并且获取这个新增的用户信息id，最后给order赋值，这里应该进行注释操作，而不是直接赋值
        order.setUserInfo(reserve.getUserInfo());
        //获取session中的用户
        User user = (User) request.getSession().getAttribute("user");
        user = new User();
        user.setUser_id(1);
        //设置操作角色
        order.setUser(user);
        //判断操作用户的角色
        //如果线上用户获取其账户会员等级，如果是后台人员操作则更具用户填入信息，查询其会员等级


        //设置订单状态
        if (user.getRole_id() == 1) {
            order.setOrder_state(0);
            order.setFlag(0);
        } else if (user.getRole_id() == 2) {
            order.setOrder_state(1);
            order.setFlag(1);
        }

        //设置订单编号
        order.setOrder_number(System.currentTimeMillis() + "" + new Random().nextInt(9000000) + 1000000);
        //设置订单生成时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setOrder_createtime(sdf.format(new Date()));
        //设置押金(简单)
        order.setOrder_deposit(houses.size() * 50);
        //设置支付类型,线下后台选择线下支付
        order.setOrder_payment(reserve.getReserve_payment());
        //赋值用户留言
        order.setOrder_message(reserve.getReserve_message());
        //设置房间总净额
        BigDecimal housestotalpay = new BigDecimal(0);
        List<Item> items = new ArrayList<>();
        Integer day = getday(reserve.getReserve_checkintime(), reserve.getReserve_checkouttime());
        for (House house : houses
        ) {
            housestotalpay = housestotalpay.add(new BigDecimal(String.valueOf(house.getHouseType().getHouse_type_price())));
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
        order.setOrder_totalpay(housestotalpay);


        //查询计费表，线上是否打折，线上打折额度,sql语句
        Charging charging = chargingService.queryCharging();
        //计算金额
        order = chargingpay(order, charging);

        System.out.println(order);

        //新增order、item
        boolean flag = orderService.createOrder(order);
        //线上支付，跳转支付页面
        if (flag && order.getOrder_state() == 0 && order.getFlag() == 0) {

        } else {
        }
        return "预定添加";

    }


    /**
     * 取消预定，这里必须是已支付后可见的订单
     *
     * @return
     */
    public String qcOrder(Order order) {
        //先查询这个订单是否存在，并且为正常预定状态
        order = orderService.queryOrderByOrderNumber(order);
        //存在
        if (order != null && order.getOrder_state() == 1 && order.getFlag() == 1) {
            //修改订单状态为取消
            orderService.deleteOrder(order);

            //然后退钱

            //还原房间状态


            return "取消预定成功";
        } else {
            //提示取消失败
            return "取消预定失败";
        }

    }


    /**
     * 修改预定
     * 取消订单+新增订单
     * 可以只修改留言、用户信息
     *
     * @return
     */
    public String xgOrder(Order order) {

        return "修改";
    }


    /**
     * 计费工具
     *
     * @param order
     * @param charging
     * @return
     */
    public Order chargingpay(Order order, Charging charging) {
        //获取预定的房间的总初步金额
        BigDecimal totalpay = order.getOrder_totalpay();
        //0代表线上支付，1代表线下支付
        //如果线上支付
        if (order.getOrder_payment() == 0) {
            if (charging.getOnline_isqc() == 0) {
                totalpay = totalpay.multiply(new BigDecimal(charging.getOnline_ratio()));
            }
        }


        //金额再乘以会员打折比率***********
        //totalpay=XXXXXXX
        totalpay = totalpay.multiply(totalpay);
        //全场打折 0不打折，1打折
        if (charging.getCharging_isqc() == 1) {
            totalpay = totalpay.multiply(new BigDecimal(charging.getCharging_ratio()));
        }
        //最后是否取整 0不去整,1取整
        if (charging.getCharging_isqzdz() == 1) {
            totalpay = new BigDecimal(totalpay.setScale(0, BigDecimal.ROUND_DOWN).longValue());
        }

        //给订单表赋值,总金额=需要支付金额,非押金
        order.setOrder_totalpay(totalpay);

        return order;
    }

    /**
     * 获取天数
     *
     * @param day1
     * @param day2
     * @return
     * @throws ParseException
     */
    public int getday(String day1, String day2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(day1);
        Date date2 = sdf.parse(day2);

        return (int) ((date2.getTime() - date1.getTime()) / 1000 / 60 / 60 / 24);
    }


}
