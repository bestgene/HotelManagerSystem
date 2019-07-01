package com.woniuxy.main;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Test {
	public static void main(String[] args) {
		System.out.println(new SimpleHash("MD5", "root", null,1024).toString());
	}
}
