package com.woniuxy.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.HouseDAO;
import com.woniuxy.dao.UserDAO;
import com.woniuxy.pojo.DateHouse;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.HouseType;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.service.HouseService;
import com.woniuxy.util.HouseUtil;

@Service("houseService")
public class HouseServiceImpl implements HouseService {
	@Resource
	private HouseDAO houseDAO;
	
	@Resource
	private UserDAO userDAO;
	
	public HouseDAO getHouseDAO() {
		return houseDAO;
	}


	public void setHouseDAO(HouseDAO houseDAO) {
		this.houseDAO = houseDAO;
	}


	@Override
	public List<HouseType> houseType() {
		return houseDAO.houseType();
	}


	@Override
	public List<Integer> findHidByHouseTypeIdAndTime(Integer house_type_id, String dh_day) {
		return houseDAO.findHidByHouseTypeIdAndTime(house_type_id,dh_day);
	}


	@Override
	public List<House> allHouseType(Integer house_type_id) {
		return houseDAO.allHouseType(house_type_id);
	}


	@Override
	public House findHouseByHouseId(Integer house_id) {
		return houseDAO.findHouseByHouseId(house_id);
	}


	@Override
	public HouseType findHouseTypeByHouseTypeId(Integer house_type_id) {
		return houseDAO.findHouseTypeByHouseTypeId(house_type_id);
	}

	@Override
	public boolean addDateHouseOperation(Integer house_id,Integer house_type_id,String startTime, String endTime) throws ParseException {
		List<String> allDay = HouseUtil.allDay(startTime, endTime);
		for (String dh_day  : allDay) {
			DateHouse single = new DateHouse();
			single.setDh_day(dh_day);
			single.setHouse_id(house_id);
			single.setHouse_type_id(house_type_id);
			boolean addDateHouseOperation = houseDAO.addDateHouseOperation(single);
			if (addDateHouseOperation==false){
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public boolean deleteDateHouseOperation(Integer house_id, String startTime, String endTime) throws ParseException {
		List<String> allDay = HouseUtil.allDay(startTime, endTime);
		for (String dh_day  : allDay) {
			boolean deleteDateHouseOperation = houseDAO.deleteDateHouseOperation(house_id, dh_day);
			if (deleteDateHouseOperation==false){
				return false;
			}
		}
		return true;
	}


	@Override
	public List<House> addOperation(String startTime, String endTime, Integer house_type_id, Integer number)
			throws ParseException {
		//查询所有可入住房间
		Map<Integer, List<House>> allAvailableRooms = allAvailableRooms(startTime,endTime);
		//通过房间类型id查询对应类型可入住的所有房间
		List<House> list = allAvailableRooms.get(house_type_id);
		//从可入住房间中随机生成若干个入住房间
		List<House> findDifferObj = HouseUtil.findDifferObj(list,number);
		//添加入住房间
		//获取所有日期
		List<String> allDay = HouseUtil.allDay(startTime,endTime);
		for (String currentDay : allDay) {
			for (House singleHouse : findDifferObj) {
				DateHouse single = new DateHouse();
				single.setHouse_id(singleHouse.getHouse_id());
				single.setHouse_type_id(singleHouse.getHouseType().getHouse_type_id());
				single.setDh_day(currentDay);
			}
		}
		return findDifferObj;
	}


	@Override
	//查询所有可用的房间
	public Map<Integer,List<House>> allAvailableRooms(String startTime,String endTime) throws ParseException{
		Map<Integer,List<House>> all = new HashMap<>();
		//获取所有房间类型数据
		List<HouseType> houseType = houseType();
		for (int t = 0;t<houseType.size();t++){
			//根据房间类型id 查找 t_house 表 查找当没有人住房的时候各个类型的房间有多少间
			List<House> allHouses = allAvailableTypeRooms(startTime,endTime,houseType.get(t).getHouse_type_id());
			all.put(houseType.get(t).getHouse_type_id(), allHouses);
		}
		return all;
	}

	@Override
	//根据入住时间和离开时间和房间类型查询该类型下所有可入住房间
	public List<House> allAvailableTypeRooms(String startTime,String endTime,Integer house_type_id) throws ParseException{
		//根据房间类型id 查找 t_house 表 查找当没有人住房的时候各个类型的房间有多少间
		List<House> allHouses = allHouseType(house_type_id);
		Set<House> allCheckHouse = new HashSet<>();
		//每一天入住的时间
		List<String> allDay = HouseUtil.allDay(startTime,endTime);
		for (String currentDay : allDay) {
			//对于各类型每天所有已入住房间的 house_id
			List<Integer> temp = findHidByHouseTypeIdAndTime(house_type_id,currentDay);
			for (Integer hid : temp) {
				House single = findHouseByHouseId(hid);
				allCheckHouse.add(single);
			}
		}
		System.out.println(allCheckHouse);
		Iterator<House> iterator = allCheckHouse.iterator();
		while (iterator.hasNext()){
			House next = iterator.next();
			allHouses.remove(next);
		}
		return allHouses;
		
	}
    
	//根据房间类型和数量添加房间
	@Transactional
	@Override
	public boolean addHouse(Integer house_type_id, Integer num) {
		boolean state=false;
		//先查询有哪些类型的房间
		while(num>0){
			String house_name=houseDAO.selectMaxHouse_name(house_type_id);
			state=houseDAO.addHouse(house_name,house_type_id);	
			num--;
		}
		return state;
	}

    //根据房间类型查找房间信息
	@Override
	public HouseType findHouseInfoByType(Integer house_type_id) {
		HouseType houseType=new HouseType();
		houseType=houseDAO.findHouseInfoByType(house_type_id);
		return houseType;
	}

	//根据房间类型更新房间信息
	@Override
	public String updateHouseInfo(Integer house_type_id, Integer house_type_price, String house_type_msg) {
		boolean state=houseDAO.updateHouseInfo(house_type_id,house_type_price,house_type_msg);
		String result="";
		if(state){
			result="修改成功";
		}else{
			result="修改失败";
		}
		return result;
	}

	
}
