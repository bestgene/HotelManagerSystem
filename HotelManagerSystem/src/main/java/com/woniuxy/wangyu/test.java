package com.woniuxy.wangyu;

import java.util.List;

import javax.annotation.Resource;

import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.service.impl.UserServieImpl;

public class test {
	@Resource
	private static UserServieImpl userServie;
	
	public static UserServieImpl getUserServie() {
		return userServie;
	}

	public static void setUserServie(UserServieImpl userServie) {
		test.userServie = userServie;
	}

	public static void main(String[] args) {
		List<User> allUser = userServie.allUser();
		System.out.println(allUser);
	}
}
