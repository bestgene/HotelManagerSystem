package com.woniuxy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.dao.ChargingDAO;
import com.woniuxy.pojo.Charging;
import com.woniuxy.service.ChargingService;

@Controller
@RequestMapping("/charging")
public class ChargingController {
	@Resource
	private ChargingService chargingService;
	
	@RequestMapping("/showCharging")
	@ResponseBody
	/**
	 * 展示计费信息
	 * @return
	 */
	public Charging showCharging(){
		Charging charging = chargingService.queryCharging();
		return charging;
	}
	
	@RequestMapping("/modifyCharging")
	@ResponseBody
	/**
	 * 修改计费信息
	 * @return
	 */
	public String modifyCharging(Charging charging){
		String result="修改失败";
		boolean flag = chargingService.updateCharging(charging);
		if(flag){
			result="修改成功";
			return result;
		}
		return result;
	}
}
