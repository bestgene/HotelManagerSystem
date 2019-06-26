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
	
	//前端选择时间跳转到房间选择页面
	@PostMapping("/forward/{startTime}/{endTime}")
	public ModelAndView get(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime) throws ParseException{
		ModelAndView model = new ModelAndView();
		List<String> date = new ArrayList<String>();
		date.add(startTime);
		date.add(endTime);
		model.addObject("date", date);
		return model;
	}

	@GetMapping("/allSingleType/{startTime}/{endTime}/{house_type_id}")
	/*
	 * 展示房间类型信息
	 * startTime 	入住时间
	 * endTime		离开时间
	 */
	public ModelAndView allSingleHouseType(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime,@PathVariable("house_type_id") Integer house_type_id) throws ParseException{
		ModelAndView model = new ModelAndView();
		List<House> allAvailableTypeRooms = houseService.allAvailableTypeRooms(startTime,endTime,house_type_id);
		HouseType houseType = houseService.findHouseTypeByHouseTypeId(house_type_id);
		houseType.setNum(allAvailableTypeRooms.size());
		model.addObject("houseType",houseType);
		System.out.println(houseType.getHouse_type_name());
		Map<String,Object> date = new Hashtable<String, Object>();
		date.put("startTime",startTime);
		date.put("endTime",endTime);
		model.addObject("date",date);
		model.setViewName("/face-user/booknews.html");
		return model;
	}
	
	@PostMapping("/allHouseType/{startTime}/{endTime}")
	@ResponseBody
	/*
	 * 展示房间类型信息
	 * startTime 	入住时间
	 * endTime		离开时间
	 */
	public Map<String,Object> allHouseType(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime) throws ParseException{
		Map<String,Object> all = new Hashtable<>();
		Map<Integer, List<House>> allAvailableRooms = houseService.allAvailableRooms(startTime, endTime);
		all.put("allHouses",allAvailableRooms);
		List<String> date = new ArrayList<String>();
		date.add(startTime);
		date.add(endTime);
		all.put("date",date);
		return all;
	}
	
	
	/*
	 * test:byys更改
	 */
	@RequestMapping("/alltype")
	public ModelAndView allHouse(String startTime,String endTime) throws ParseException{
		String[] start = startTime.split("/");
		String startTime1 = start[2]+"-"+start[0]+"-"+start[1];
		String[] end = endTime.split("/");
		String endTime1 = end[2]+"-"+end[0]+"-"+end[1];
		ModelAndView modelAndView= new ModelAndView();
		Map<Integer,List<House>> allAvailableRooms = houseService.allAvailableRooms(startTime1,endTime1);
		List<HouseType> houseType = houseService.houseType();
		for (HouseType singleType : houseType) {
			singleType.setNum(allAvailableRooms.get(singleType.getHouse_type_id()).size());
		}
		Map<String,Object> date = new Hashtable<String, Object>();
		modelAndView.addObject("houseTypes", houseType);
		date.put("startTime",startTime1);
		date.put("endTime",endTime1);
		modelAndView.addObject("date",date);
		modelAndView.setViewName("face-user/house-info/housedetailinfo.html");
		System.out.println(allAvailableRooms);
		return modelAndView;
	}
	
	@RequestMapping("/test")
	public String test(){
//		return "/face-user/house-info/housedetailinfo.html";
		return "/face-user/house-info/housedetailinfo.html";
	}
	
	
	
	@PostMapping("/addCheckInOperation/{startTime}/{endTime}/{house_type_id}/{number}")
	/*
	 * 添加入住历史信息
	 */
	public ModelAndView addOperation(@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("house_type_id") Integer house_type_id,
			@PathVariable("number") Integer number) throws ParseException{
		ModelAndView model = new ModelAndView();
		//查询所有可入住房间
		List<House> addOperation = houseService.addOperation(startTime, endTime, house_type_id, number);
		model.addObject("houses", addOperation);
		model.addObject("start", startTime);
		model.addObject("end", endTime);
		//设置跳转页面
		model.setViewName("");
		return model;
		
	}
}
