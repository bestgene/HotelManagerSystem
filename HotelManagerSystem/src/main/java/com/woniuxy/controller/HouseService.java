package com.woniuxy.service;

import java.util.List;

public interface HouseService {
	
	public List<House> allHouseType(Integer house_type_id);
	
	public House findHouseByHouseId(Integer house_id);
	
	public List<HouseType> houseType();
	
	public List<Integer> findHidByHouseTypeIdAndTime(Integer house_type_id,String dh_day);
	
	public HouseType findHouseTypeByHouseTypeId(Integer house_type_id);
}
