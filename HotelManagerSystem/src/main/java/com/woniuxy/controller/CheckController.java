package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.emailUtil.EmailUtil;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.telpojo;
import com.woniuxy.service.UserService;
import com.woniuxy.testmsg.msg;

@Controller
@RequestMapping("/check")
@Scope("prototype")
public class CheckController {
	
	@Resource
	private UserService userService;
	
   	 public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String randcode="";
	public String getRandcode() {
		return randcode;
	}
	public void setRandcode(String randcode) {
		this.randcode = randcode;
	}
	
	/*@RequestMapping("/emailcheck")
	public void emailCheck(@Validated User user,BindingResult result ){
		
		System.out.println("邮箱验证！");
		List<FieldError>errors=result.getFieldErrors();
		
		if (errors!=null) {
			for (FieldError fieldError : errors) {
				System.out.println(fieldError.getDefaultMessage());
			}
		}else {
			//当不为空的时候开始发送邮件
			System.out.println(user);
			//将前台获取到的邮箱传回到发送邮件中去
		new Thread(new EmailUtil(user.getEmail(), "222", user.getUser_acc(), user.getUser_pwd())).start();
		   System.out.println("测试！");
		}
		
		
	}*/
	
	
	@RequestMapping("/telcheck")
	@ResponseBody
	public String telcheck(telpojo tel,HttpServletRequest request){//短信验证
		System.out.println(randcode+"看看是否有验证码！");
		
		//对前端的数据进行测试
		/*1出现的情况：验证码不正确，账号已经存在，账号。密码，验证码都为空
		
		*/
		String result="";
		HttpSession session=request.getSession();
		Map<String, String> map=(Map<String, String>) session.getAttribute("map");//获取存取的手机号以及验证码
		
		if (tel.getUser_acc()==""||tel.getUser_pwd()==""||tel.getCode()=="") {
			//非空的判定
			result="请检查你有空的输入项目！";
		}else {
			
			
			//判断验证码正确不
			if (tel.getCode().equals(map.get(tel.getTel()))) {//验证码正确的时候
				if (userService.findbyuseracc(tel)!=null) {
					result="注册失败！已经存在此账户！";
				}
				else {
					userService.telregister(tel);
					result="注册成功！";
				}
				
			}
			else {
				result="验证码不正确！";
			}
		}
		
		System.out.println(tel+"得到注册信息");
		
		
		
		return result;
	}
	
	
	@RequestMapping("/send")
	@ResponseBody
	public String sendcode(telpojo po,HttpServletRequest request){
		String re="";
		System.out.println("进入到发送手机验证码！");
		if (po.getTel()=="") {
			re="你输入的手机号码为空";
		}else {
			//进行发送信息
			msg msg=new msg();
			String reult= msg.mobileQuery(po.getTel());
			HttpSession session = request.getSession();
			Map<String, String> map=new HashMap<>();
			map.put(po.getTel(), reult);
			session.setAttribute("map", map);
			 randcode=reult;//将返回的验证码取出来
			System.out.println(map.get(po.getTel())+"验证码获取！！！！");
			re="验证码发送成功！";
			
		}
		System.out.println(po.getTel());
		
		return re;
	}
	
	
}
