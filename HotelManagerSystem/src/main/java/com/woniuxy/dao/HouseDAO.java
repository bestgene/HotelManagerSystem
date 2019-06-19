package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.House;

public interface HouseDAO {
	@Select("select * from t_house")
	public List<House> allHouse();
}
