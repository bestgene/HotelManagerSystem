package com.woniuxy.controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Charging;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Reserve;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.service.ChargingService;
import com.woniuxy.service.HouseService;
import com.woniuxy.service.OrderService;
import com.woniuxy.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private HouseService houseService;
    @Resource
    private ChargingService chargingService;
    @Resource
    private UserService userService;
    @Resource
    private PayController payController;


    /**
     * 线上预定、线下预定查询
     * 根据查询条件查询所有订单
     * 当前预定state1 flag1
     * 历史预定state3 flag2
     * 查询当前入住state2 flag1   操作员操作
     * @param
     * @return
     */
    @RequestMapping("/showAllOrder")
    @ResponseBody
    public List<Order> showAllOrder(Order order,String cxfs, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        
        if (user!=null&&user.getRole_id() == 3) {
            order.setUser(user);
        }
        if (cxfs.equals("当前预定")) {
            order.setOrder_state(1);
            order.setFlag(1);
        } else if (cxfs.equals("历史订单")) {
            order.setOrder_state(3);
            order.setFlag(2);
        } else if (cxfs.equals("当前入住")) {
            if (user.getRole_id() == 2) {
                order.setOrder_state(2);
                order.setFlag(1);
            }
        } else if (cxfs.equals("所有订单")) {
            order.setFlag(3);
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
    public String createKdOrder(Reserve reserve, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        //根据房间类型、选择数量、入住时间、退房时间查询数据库，获取房间
        List<House> houses = houseService.addOperation(reserve.getReserve_checkintime(),reserve.getReserve_checkouttime()
        ,reserve.getHouseType().getHouse_type_id(),reserve.getHouse_number());
        if(houses==null||houses.size()<reserve.getHouse_number()){
            return "房间不足";
        }
        Order order = new Order();
        //获取session中的用户
        User user = (User) request.getSession().getAttribute("user");
        //设置操作角色
        order.setUser(user);
        //根据用户信息获得用户信息id以及对应会员等级折扣
        Map<String ,Object> map =
                userService.getDiscountByTelOrIdcard(reserve.getUserInfo().getUser_info_tel(),
                        reserve.getUserInfo().getUser_info_idcard(),
                        reserve.getUserInfo().getUser_info_name());
        order.setUserInfo((UserInfo) map.get("user_info"));
        //设置订单状态
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
        //会员打折
        housestotalpay  = housestotalpay.multiply((BigDecimal) map.get("discount"));
        //金额初步赋值
        order.setOrder_totalpay(housestotalpay);
        //查询计费表，线上是否打折，线上打折额度,sql语句
        Charging charging = chargingService.queryCharging();
        //计算金额
        order = chargingpay(order, charging);
        //新增order、item
        boolean flag = orderService.createOrder(order);
        //默认已付款
        return "线下开单成功";

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
    public String createYdOrder(Reserve reserve, HttpServletRequest request,HttpServletResponse response) throws ParseException {
        //根据房间类型、选择数量、入住时间、退房时间查询数据库，获取房间
        List<House> houses = houseService.addOperation(reserve.getReserve_checkintime(),reserve.getReserve_checkouttime()
                ,reserve.getHouseType().getHouse_type_id(),reserve.getHouse_number());
        //应该跳转到房间不足页面
        if(houses==null||houses.size()<reserve.getHouse_number()){
        	return null;
        }
        //1.创建 订单表
        Order order = new Order();
        //获取session中的用户
        User user = (User) request.getSession().getAttribute("user");
        //设置操作角色
        order.setUser(user);
        //根据输入用户信息查询用户信息id以及对应会员打折
        Map<String ,Object> map =
                userService.getDiscountByTelOrIdcard(reserve.getUserInfo().getUser_info_tel(),
                        reserve.getUserInfo().getUser_info_idcard(),
                        reserve.getUserInfo().getUser_info_name());
        order.setUserInfo((UserInfo) map.get("user_info"));
        //判断操作用户的角色
        //如果线上用户获取其账户会员等级，如果是后台人员操作则更具用户填入信息，查询其会员等级
        if (user.getRole_id()==3){
            Map<String,Object> map1 = userService.getVipByUserid(user.getUser_id());
            map.put("discount",map1.get("discount"));
        }
        //设置订单状态
        order.setOrder_state(0);
        order.setFlag(0);
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
        //会员打折
        housestotalpay  = housestotalpay.multiply((BigDecimal) map.get("discount"));
        //金额初步赋值
        order.setOrder_totalpay(housestotalpay);
        //查询计费表，线上是否打折，线上打折额度,sql语句
        Charging charging = chargingService.queryCharging();
        //计算金额
        order = chargingpay(order, charging);
        //新增order、item
        boolean flag = orderService.createOrder(order);
        //支付测试(押金)
        payController.payMoney(response, order.getOrder_number(), order.getOrder_deposit()+"",
                order.getOrder_number()+order.getOrder_deposit(), "");
        return null;
    }
    /**
     * 取消预定，这里必须是已支付后可见的订单
     *
     * @return
     */
    @RequestMapping("/qcOrder")
    @ResponseBody
    public String qcOrder(Order order) throws ParseException {
        //先查询这个订单是否存在，并且为正常预定状态
        order = orderService.queryOrderByOrderNumber(order);
        //存在
        if (order != null && order.getOrder_state() == 1 && order.getFlag() == 1) {
            List<Order> orders = orderService.showAllOrder(order);
            if (orders.size()==1){

                //还原房间状态
                for (Item item:orders.get(0).getItems()
                ) {
                    houseService.deleteDateHouseOperation(item.getHouse().getHouse_id(),item.getItem_checkintime()
                            ,item.getItem_checkouttime());
                }

                //修改订单状态为取消
                orderService.deleteOrder(order);
                //然后退钱
                return "取消预定成功";
            }
        }
        //提示取消失败
        return "取消预定失败";
    }
    /**
     * 结账
     * 传入订单编号
     * @param order
     * @return
     */
    @RequestMapping("/payAccounts")
    public String payAccounts(Order order,HttpServletResponse response){
        //根据order_number查询order信息
        order = orderService.queryOrderByOrderNumber(order);
        //满足已入住且支付押金，存在
        if (order!=null&&order.getOrder_state()==2&&order.getFlag()==1){
        	payController.payMoney(response, order.getOrder_number()+"A", order.getOrder_totalpay()+"",
                    order.getOrder_number()+order.getOrder_totalpay(), "");
        }
        return null;
    }
    /**
     * 入住
     * 将预定的订单修改为入住状态
     * @param order
     * @return
     */
    @RequestMapping("/checkin")
    @ResponseBody
    public String checkIn(Order order){
        //根据order_number查询order信息
        order = orderService.queryOrderByOrderNumber(order);
        if (order!=null&&order.getOrder_state()==1&order.getFlag()==1){
            orderService.checkIn(order);
            return "入住成功";
        }else {
            return "入住失败";
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
