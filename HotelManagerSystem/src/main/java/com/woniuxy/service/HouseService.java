package com.woniuxy.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.DateHouse;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.HouseType;

public interface HouseService {
	
	public List<House> allHouseType(Integer house_type_id);
	
	public House findHouseByHouseId(Integer house_id);
	
	public List<HouseType> houseType();
	
	public List<Integer> findHidByHouseTypeIdAndTime(Integer house_type_id,String dh_day);
	
	public HouseType findHouseTypeByHouseTypeId(Integer house_type_id);
	
	//添加入住信息根据起止时间和房间id
	public boolean addDateHouseOperation(Integer house_id,Integer house_type_id,String startTime,String endTime) throws ParseException;
	
	//删除入住信息根据起止时间和房间id
	public boolean deleteDateHouseOperation(Integer house_id,String startTime,String endTime) throws ParseException;
	
	//根据起止时间和房间类型和数量 查询可入住房间
	public List<House> addOperation(String startTime,String endTime,
			Integer house_type_id,Integer number) throws ParseException;
	
	//查询所有可用的房间
	public Map<Integer,List<House>> allAvailableRooms(
			String startTime,String endTime) throws ParseException;
	
//根据入住时间和离开时间和房间类型查询该类型下所有可入住房间
	public List<House> allAvailableTypeRooms(
			String startTime,String endTime,Integer house_type_id) throws ParseException;
//根据房间类型和数量添加房间
	public boolean addHouse(Integer house_type_id, Integer num);
//根据房间类型查找房间信息
	public HouseType findHouseInfoByType(Integer house_type_id);
//根据房间类型更新房间信息
	public String updateHouseInfo(Integer house_type_id, Integer house_type_price, String house_type_msg);
}
