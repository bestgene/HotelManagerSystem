package com.woniuxy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.DateHouse;
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

	@PostMapping("/allSingleType/{startTime}/{endTime}/{house_type_id}")
	@ResponseBody
	/*
	 * 展示房间类型信息
	 * startTime 	入住时间
	 * endTime		离开时间
	 */
	public Map<String,Object> allSingleHouseType(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime,@PathVariable("house_type_id") Integer house_type_id) throws ParseException{
		Map<String,Object> all = new Hashtable<>();
		List<House> allAvailableTypeRooms = allAvailableTypeRooms(startTime,endTime,house_type_id);
		HouseType houseType = houseService.findHouseTypeByHouseTypeId(house_type_id);
		houseType.setNum(allAvailableTypeRooms.size());
		all.put("houseType",houseType);
		List<String> date = new ArrayList<String>();
		date.add(startTime);
		date.add(endTime);
		all.put("date",date);
		all.put("houses",allAvailableTypeRooms);
		return all;
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
		Map<Integer, List<House>> allAvailableRooms = allAvailableRooms(startTime, endTime);
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
	@PostMapping("/alltype/{startTime}/{endTime}/{type}")
	@ResponseBody
	public ModelAndView allHouse(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime,@PathVariable("type") Integer type) throws ParseException{
		ModelAndView modelAndView= new ModelAndView();
		Map<String,Object> all = new Hashtable<>();
		Map<Integer,List<House>> allAvailableRooms = allAvailableRooms(startTime,endTime);
		List<HouseType> allhouses = houseService.houseType();
		for (HouseType houseType : allhouses) {
			Integer key = houseType.getHouse_type_id();
			int num = allAvailableRooms.get(key).size();
			houseType.setNum(num);
		}
		modelAndView.addObject("houses",allhouses);
		List<String> date = new ArrayList<String>();
		date.add(startTime);
		date.add(endTime);
		modelAndView.addObject("date",date);
		modelAndView.setViewName("face-user/house-info/housedetailinfo.html");
		return modelAndView;
	}
	

	
	
	
	@PostMapping("/addCheckInOperation/{startTime}/{endTime}/{house_type_id}/{number}")
	@ResponseBody
	/*
	 * 添加入住历史信息
	 */
	public ModelAndView addOperation(@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("house_type_id") Integer house_type_id,
			@PathVariable("number") Integer number) throws ParseException{
		ModelAndView model = new ModelAndView();
		//查询所有可入住房间
		Map<Integer, List<House>> allAvailableRooms = allAvailableRooms(startTime,endTime);
		//通过房间类型id查询对应类型可入住的所有房间
		List<House> list = allAvailableRooms.get(house_type_id);
		//从可入住房间中随机生成两个入住房间
		List<House> findDifferObj = findDifferObj(list,number);
		//添加入住房间
		//获取所有日期
		List<String> allDay = allDay(startTime,endTime);
		for (String currentDay : allDay) {
			for (House singleHouse : findDifferObj) {
				DateHouse single = new DateHouse();
				single.setHouse_id(singleHouse.getHouse_id());
				single.setHouse_type_id(singleHouse.getHouseType().getHouse_type_id());
				single.setDh_day(currentDay);
				houseService.addDateHouseOperation(single);
			}
		}
		//房间id?
		model.addObject("houses", findDifferObj);
		model.addObject("start", startTime);
		model.addObject("end", endTime);
		//设置跳转页面
		model.setViewName("");
		return model;
		
	}
	
	
/*---------------------------------------工具类----------------------------------------	*/
	//查询所有可用的房间
	public Map<Integer,List<House>> allAvailableRooms(String startTime,String endTime) throws ParseException{
		Map<Integer,List<House>> all = new HashMap<>();
		//获取所有房间类型数据
		List<HouseType> houseType = houseService.houseType();
		for (int t = 0;t<houseType.size();t++){
			//根据房间类型id 查找 t_house 表 查找当没有人住房的时候各个类型的房间有多少间
			List<House> allHouses = allAvailableTypeRooms(startTime,endTime,houseType.get(t).getHouse_type_id());
			all.put(houseType.get(t).getHouse_type_id(), allHouses);
		}
		return all;
	}
	
	//根据入住时间和离开时间和房间类型查询该类型下所有可入住房间
	public List<House> allAvailableTypeRooms(String startTime,String endTime,Integer house_type_id) throws ParseException{
		//根据房间类型id 查找 t_house 表 查找当没有人住房的时候各个类型的房间有多少间
		List<House> allHouses = houseService.allHouseType(house_type_id);
		Set<House> allCheckHouse = new HashSet<>();
		//每一天入住的时间
		List<String> allDay = allDay(startTime,endTime);
		for (String currentDay : allDay) {
			//对于各类型每天所有已入住房间的 house_id
			List<Integer> temp = houseService.findHidByHouseTypeIdAndTime(house_type_id,currentDay);
			System.out.println("----------------"+temp);
			for (Integer hid : temp) {
				House single = houseService.findHouseByHouseId(hid);
				allCheckHouse.add(single);
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
		return allHouses;
		
	}
	// 已知 一个集合 Collection 从集合中查找	n个不同的对象;
	public List<House> findDifferObj(List<House> all,Integer n){
		List<House> result = new ArrayList<House>();
		Random random = new Random();
		boolean flag[] = new boolean[all.size()];
		int index;
		for (int i=0;i<n;i++){
			do{
				index = random.nextInt(all.size());
				if (flag[index]==false){
					result.add(all.get(index));
					flag[index]=true;
					break;
				}
			}while(flag[index]);
		}
		random.nextInt(all.size());
		return result;
	}
	
	//根据起止时间差错所有天数
	
	public List<String>  allDay(String startTime,String endTime) throws ParseException{
		List<String> allDay = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse(startTime);
		Date end = sdf.parse(endTime);
		int day = (int) ((end.getTime()-start.getTime())/1000/3600/24);
		if (day>0){
			for (int d=0;d<day;d++){
				String currentDay = sdf.format(new Date(start.getTime()+d*3600*24*1000));
				allDay.add(currentDay);
			}
		}
		return allDay;
	}
}
