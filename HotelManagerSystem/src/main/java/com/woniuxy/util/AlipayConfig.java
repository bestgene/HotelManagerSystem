package com.woniuxy.util;
import java.io.FileWriter;
import java.io.IOException;

/**
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100100637009";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDx9dLr5c5YnOREDXF6mH1ytLz85Cff3eWfLE6l0eHMKf+ENsAHQZ05EGzQA2lfr6t03NQuC+za0S77BEUmOJXZ4S0nw8s+DgG2xuSyVJXnBcyhwU9himNWAwhBIlmL9g0/glVGT9vHsOe8KUUE9Pa5wvRN7P3L25aZk4z6NCDNr1jqCRqPkxSiH9UEEAaZ39j/jX35d213WqTGIPLO9I4cO+gALsSk8LQT415iBGUcSuz947BzrftJiW6qBo1wIAPKa9wM0D4A9P/reUlbVVkjVDUl1OvXavwkzAQ36Ndsf/Jl4QCyjLImn+A1bycHoFqei9nphqRbYf9L+miKNbFRAgMBAAECggEBAIh6JhyVJQzqgf64rnd+J9RZifgDIVKusORVc48okXo9fjivQSVWpz4Wu6mhIwCr78QdAzlt1I2gTCWeRPrIFA4vau7xC2TXNbRI/5pvB5aluMTMauAT1PAhCbjjjSrbAKH/++WLCebbK/sANEQVgE1LX83MjWM9WvcJOQ2v9YjN9EIt7a64BoHt0ziIhTCTZnhpd0xszZw4C2uhxJPbWO19ZyMV0xcJzJxJTaXyP2z786ko55fdNcvtgqgCJ3ll4TXEurqVynx3atEOnNe5ZP6s28pHhCQA88ZdCbu7bZ71Jj31VNtPrZ4ZOw2r3CcODZNgKj17Oi8AgRi45r/LOdkCgYEA/aFjlY3f7749EUhR6oq+Xtsi8p2qfsIJnVI6gfxQWPWEaDjguv3nOgkYRz7wGmvGNepo1t+38gaZtKBEVUGRVKCY+4tnKKCvWwOrLcneUeCqHz6bzcvp0vOrBQXbWM6MX+bCwNVUKSFK45zlphqc4aQTC6LKQjTXdGfsj8ASnecCgYEA9DiF8PQWMeHgCrqYRZNUkIo34YmeE2Quqxlynu8XTOYvSdA3yuAxMFCkbxRXzcNDNhsUdhkPOAbmJuSepBSz7JfTSf8EhV61JWvt+oEsz+KnPjNSqzMKUxg4ToMq3L4mECeyQ8sSqU7b13hXktQKIV0ag4b6fd0rg1yKSLKFoAcCgYEAzRuh1AT67v9ylTJeBIWVNFKU7Y0xnva+Hbqs1Ae+1mIeZMZyfuXsA1PtjVhJhaRYk6twgyxCrnhF/vuIp0WyCLUjYnE2yHVBuiG6YfoZTy+XhFL2XrQj8NPbHuHApA3NGZUdNCKQv/5LeMyeLvjgm907BbLtTCM9hcaxWQLkS0sCgYEAhpto5+gBG7ldnUw/hYmxAIHOTxdk81cyicfa71LkytPMmKiVN9LeCb1JBJh31MedEgNXAcjKJJ1Y2XMFJXbrvKnqIUzYFxKeUWPtpTbxlhtdBuGO3SGivS4KSuvtAgIkRJ88mDUjsgHvFhIUYq9Qbhc89+W7xwfSZfLpnSwYjp0CgYEAjxIRMXUZiH9Hpqt/NRI8q7A+nYxOE9qTq7q+sK54q6B19dO9R3TJiHkGNkqdaW6jhQHNQV+s/zdCceDIz6BWKOgn/kILM6aBZ84qw6jB38YFc7XeJDKQWMCyYQ6Zk13rjdGxiX9feV9tlleTMjmrvc9F8+mDGSciDAfxgNMXY+E=";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
	// 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAssTc5n9cXYZTxlPTbMBGsZmsnhQyi2uP29oHogjujA/00K7B4KhqmD+jusF6XPSFl1e0SRT6XyXvzGyz0d1a9SeU+zE/BG7KPOXzWrZXplheY50y4yyOUlOUPNssh6ihTYi625idny9zDM41F8bAV+fWs5spA0x+yNt8xibb3bsnlaNlYHdUUSkbwPbWQ25DhIYhAtYexCkKtmUTPYQwiEN07AtEVsNuwfpEoKG4gae5pPL9LcMaILoVrk6KJe5YJA2D9L3EYwUIgwZ4MgQ3cO0HeUg9EReFwcsC6wdfcpaucDIDqplSymManiM7gknj5mNnnRHnUfxJII3pI+LWbQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://24808tq526.wicp.vip/HotelManagerSystem/payOrder/savePayResult";
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://24808tq526.wicp.vip/HotelManagerSystem/pay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "D:\\";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 要写入日志里的文本内容
	 * @param
	 *
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
