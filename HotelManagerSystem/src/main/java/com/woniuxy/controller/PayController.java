package com.woniuxy.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;
import com.woniuxy.service.HouseService;
import com.woniuxy.service.OrderService;
import com.woniuxy.util.AlipayConfig;

@Controller
@RequestMapping("/payOrder")
public class PayController {
	@Resource
	private OrderService orderService;
	@Resource
	private HouseService houseService;

	@RequestMapping("/pay")
	public void pay(HttpServletRequest request, HttpServletResponse response, String orderNumber, String totalPay) {

		// 向支付宝发起请求
		payMoney(response, orderNumber, totalPay, orderNumber + totalPay, "");
	}

	@RequestMapping("/savePayResult")
	public void savePayResult(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status)
			throws Exception {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
				AlipayConfig.sign_type); // 调用SDK验证签名

		// ——请在这里编写您的程序（以下代码仅作参考）——

		/*
		 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		 * 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		 * 4、验证app_id是否为该商户本身。
		 */
		if (signVerified) {// 验证成功
			if (trade_status.equals("TRADE_SUCCESS")) {
				System.out.println("验证成功");
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知

				// 付款完成后，只需将order表中的状态改为2（即已支付），然后添加pid

				// 创建order对象，为其赋值订单编号
				Order order = new Order();
				order.setOrder_number(out_trade_no);
				// 通过订单编号查询该订单的id、状态、flag
				Order qo = orderService.queryOrderByOrderNumber(order);
				// 通过订单的状态和flag判断是付押金还是付全款
				if (qo.getOrder_state() == 0 && qo.getFlag() == 0) {
					// 付押金
					orderService.payDeposit(out_trade_no, trade_no);
					// 查询该订单下的所有订单项
					List<Item> items = orderService.queryItemByOid(qo);
					// 通过订单项，改变该房间的状态
					for (Item item : items) {
						boolean b = houseService.addDateHouseOperation(item.getHouse().getHouse_id(),
								item.getHouse().getHouseType().getHouse_type_id(), item.getItem_checkintime(),
								item.getItem_checkouttime());
					}

				}
				// 付全款
				else if (qo.getFlag() == 1 && qo.getOrder_state() == 2) {
					orderService.payOrder(out_trade_no, trade_no);
				}

			}
		}
	}

	public void payMoney(HttpServletResponse response, String out_trade_no, String total_amount, String subject,
			String body) {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no
		// +"\","
		// + "\"total_amount\":\""+ total_amount +"\","
		// + "\"subject\":\""+ subject +"\","
		// + "\"body\":\""+ body +"\","
		// + "\"timeout_express\":\"10m\","
		// + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		// 请求
		String result;
		response.setCharacterEncoding("utf-8");
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
			response.setContentType("text/html;charset=UTF-8");
			// 输出
			PrintWriter out = response.getWriter();
			out.println(result);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}