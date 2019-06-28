package com.woniuxy.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.House;
import com.woniuxy.pojo.HouseType;
import com.woniuxy.service.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController {
	@Resource
	private HouseService houseService;

	public HouseService getHouseService() {
		return houseService;
	}

	public void setHouseService(HouseService houseService) {
		this.houseService = houseService;
	}

	// 前端选择时间跳转到房间选择页面
	@PostMapping("/forward/{startTime}/{endTime}")
	public ModelAndView get(@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime)
			throws ParseException {
		ModelAndView model = new ModelAndView();
		List<String> date = new ArrayList<String>();
		date.add(startTime);
		date.add(endTime);
		model.addObject("date", date);
		return model;
	}

	@GetMapping("/allSingleType/{startTime}/{endTime}/{house_type_id}")
	/*
	 * 展示房间类型信息 startTime 入住时间 endTime 离开时间
	 */
	public ModelAndView allSingleHouseType(@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime, @PathVariable("house_type_id") Integer house_type_id)
			throws ParseException {
		//使用ModelAndView返回数据
		ModelAndView model = new ModelAndView();
		//查询该类型下所有可用房间
		List<House> allAvailableTypeRooms = houseService.allAvailableTypeRooms(startTime, endTime, house_type_id);
		//根据房间类型id查询到该类型房间
		HouseType houseType = houseService.findHouseTypeByHouseTypeId(house_type_id);
		//设置该类型可用房间数量
		houseType.setNum(allAvailableTypeRooms.size());
		//将该类型房间放入model中
		model.addObject("houseType", houseType);
		//将 入住时间和离开时间放入model中
		Map<String, Object> date = new Hashtable<String, Object>();
		date.put("startTime", startTime);
		date.put("endTime", endTime);
		model.addObject("date", date);
		//设置页面跳转路径
		model.setViewName("/face-user/booknews.html");
		return model;
	}

	/*
	 * 获取所有可用房间
	 */
	@RequestMapping("/alltype")
	public ModelAndView allHouse(String startTime, String endTime) throws ParseException {
		//传入时间格式为MM/dd/yyyy 将其转化为yyyy-MM-dd
		String[] start = startTime.split("/");
		String startTime1 = start[2] + "-" + start[0] + "-" + start[1];
		String[] end = endTime.split("/");
		String endTime1 = end[2] + "-" + end[0] + "-" + end[1];
		//使用ModelAndView传递参数设置页面跳转路径
		ModelAndView modelAndView = new ModelAndView();
		//查询出使用可用房间
		Map<Integer, List<House>> allAvailableRooms = houseService.allAvailableRooms(startTime1, endTime1);
		//查询出所有房间类型
		List<HouseType> houseType = houseService.houseType();
		//遍历设置各个房间类型下可用房间数量
		for (HouseType singleType : houseType) {
			singleType.setNum(allAvailableRooms.get(singleType.getHouse_type_id()).size());
		}
		//使用map集合插入	入住时间和离开时间
		Map<String, Object> date = new Hashtable<String, Object>();
		modelAndView.addObject("houseTypes", houseType);
		date.put("startTime", startTime1);
		date.put("endTime", endTime1);
		modelAndView.addObject("date", date);
		//设置页面跳转到房间类型显示界面
		modelAndView.setViewName("face-user/house-info/housedetailinfo.html");
		System.out.println(houseType);
		return modelAndView;
	}

	/*
	 * 根据入住时间和离开时间房间类型id 返回该类型下所有可用房间
	 */
	@GetMapping("/queryHouse/{startTime}/{endTime}/{house_type_id}")
	@ResponseBody
	public Map<String, Object> queryHouse(@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime, @PathVariable("house_type_id")
			Integer house_type_id) throws ParseException {
		//查询该类型下所有房间
		List<House> allAvailableTypeRooms = houseService.allAvailableTypeRooms(startTime, endTime,house_type_id);
		//获取该类型下所有房间
		HouseType houseType = houseService.findHouseTypeByHouseTypeId(house_type_id);
		Map<String, Object> date = new Hashtable<String, Object>();
		Map<String, Object> house = new Hashtable<String, Object>();
		//设置该类型下所有房间可用数量
		houseType.setNum(allAvailableTypeRooms.size());
		//将入住和离开时间存入map集合中
		house.put("houseType", houseType);
		date.put("startTime", startTime);
		date.put("endTime", endTime);
		house.put("date", date);
		return house;
	}

	@PostMapping("/addCheckInOperation/{startTime}/{endTime}/{house_type_id}/{number}")
	/*
	 * 添加入住历史信息
	 */
	public ModelAndView addOperation(@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime, @PathVariable("house_type_id") Integer house_type_id,
			@PathVariable("number") Integer number) throws ParseException {
		ModelAndView model = new ModelAndView();
		// 查询所有可入住房间
		List<House> addOperation = houseService.addOperation(startTime, endTime, house_type_id, number);
		model.addObject("houses", addOperation);
		model.addObject("start", startTime);
		model.addObject("end", endTime);
		// 设置跳转页面
		model.setViewName("");
		return model;

	}

	/*
	 * 测试使用
	 */
	@RequestMapping("/test")
	public String test(String startTime, String endTime, Integer house_type_id) throws ParseException {
		List<House> allAvailableTypeRooms = houseService.allAvailableTypeRooms(startTime, endTime, house_type_id);
		System.out.println(allAvailableTypeRooms);
		return null;
	}
}
