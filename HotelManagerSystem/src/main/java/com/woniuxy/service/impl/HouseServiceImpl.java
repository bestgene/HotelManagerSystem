package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.HouseDAO;
import com.woniuxy.pojo.DateHouse;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.HouseType;
import com.woniuxy.service.HouseService;

@Service("houseService")
public class HouseServiceImpl implements HouseService {
	@Resource
	private HouseDAO houseDAO;
	
	
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
		return houseDAO.findHidByHouseTypeIdAndTime(house_type_id, dh_day);
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
	public boolean addDateHouseOperation(DateHouse dateHouse) {
		return houseDAO.addDateHouseOperation(dateHouse);
	}
}
