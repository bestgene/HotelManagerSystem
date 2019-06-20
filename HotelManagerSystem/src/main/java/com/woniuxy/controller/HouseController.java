package com.woniuxy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


	@PostMapping("/alltype/{startTime}/{endTime}")
	@ResponseBody
	/*
	 * 展示房间类型信息
	 * startTime 	入住时间
	 * endTime		离开时间
	 */
	public List<HouseType> allHouseType(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime) throws ParseException{
		Map<Integer,List<House>> allAvailableRooms = allAvailableRooms(startTime,endTime);
		List<HouseType> all = houseService.houseType();
		for (HouseType houseType : all) {
			Integer key = houseType.getHouse_type_id();
			int num = allAvailableRooms.get(key).size();
			houseType.setNum(num);
		}
		return all;
	}
	
	@PostMapping("/addCheckInOperation/{startTime}/{endTime}")
	@ResponseBody
	/*
	 * 添加入住历史信息
	 */
	public void addOperation(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime){
		
	}
	public Map<Integer,List<House>> allAvailableRooms(String startTime,String endTime) throws ParseException{
		Map<Integer,List<House>> all = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse(startTime);
		Date end = sdf.parse(endTime);
		int day = (int) ((end.getTime()-start.getTime())/1000/3600/24);
		//获取所有房间类型数据
		List<HouseType> houseType = houseService.houseType();
		for (int t = 0;t<houseType.size();t++){
			//根据房间类型id 查找 t_house 表 查找当没有人住房的时候各个类型的房间有多少间
			List<House> allHouses = houseService.allHouseType(houseType.get(t).getHouse_type_id());
			Set<House> allCheckHouse = new HashSet<>();
			if (day>0){
				for (int d=0;d<day;d++){
					//每一天入住的时间
					String currentDay = sdf.format(new Date(start.getTime()+d*3600*24*1000));
					//对于各类型每天所有已入住房间的 house_id
					List<Integer> temp = houseService.findHidByHouseTypeIdAndTime(houseType.get(t).getHouse_type_id(),currentDay);
					for (Integer hid : temp) {
						House single = houseService.findHouseByHouseId(hid);
						allCheckHouse.add(single);
					}
				}	
			}
			for (int m=0;m<allHouses.size();m++){
				House house = allHouses.get(m);
				Iterator<House> iterator = allCheckHouse.iterator();
				while (iterator.hasNext()){
					House next = iterator.next();
					if (next.getHouse_id()==house.getHouse_id()){
						allHouses.remove(m);
					}
				}
			}
			all.put(houseType.get(t).getHouse_type_id(), allHouses);
		}
		return all;
	}
}
