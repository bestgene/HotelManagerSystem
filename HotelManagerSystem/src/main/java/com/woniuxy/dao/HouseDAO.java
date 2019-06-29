package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.DateHouse;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.HouseType;

public interface HouseDAO {
	//通过房间类型id查找所有类型相同的房间
	@Select("select * from t_house where house_type_id = #{house_type_id} and house_state=0 and flag = 0")
	@Results({
		@Result(id=true,column="house_id",property="house_id"),
		@Result(column="house_name",property="house_name"),
		@Result(column="house_type_id",property="houseType",
			one = @One(select="com.woniuxy.dao.HouseDAO.findHouseTypeByHouseTypeId") 
		),
		@Result(column="flag",property="flag"),
	})
	public List<House> allHouseType(Integer house_type_id);
	//根据house_id查找相应的House
	@Select("select * from t_house where house_id=#{house_id}")
	@Results({
		@Result(id=true,column="house_id",property="house_id"),
		@Result(column="house_name",property="house_name"),
		@Result(column="house_type_id",property="houseType",
			one = @One(select="com.woniuxy.dao.HouseDAO.findHouseTypeByHouseTypeId") 
		),
		@Result(column="flag",property="flag"),
	})
	public House findHouseByHouseId(Integer house_id);
	//查找所有的房间类型
	@Select("select * from t_house_type")
	public List<HouseType> houseType();
	//通过房间类型id及订房时间确定房间id
	@Select("select house_id from t_date_house where house_type_id=#{house_type_id} and dh_day = #{dh_day}")
	public List<Integer> findHidByHouseTypeIdAndTime(@Param("house_type_id") Integer house_type_id,@Param("dh_day")String dh_day);
	//插入 入住操作信息
	@Insert("insert into t_date_house(house_id,house_type_id,dh_day) values(#{house_id},#{house_type_id},#{dh_day})")
	public boolean addDateHouseOperation(DateHouse dateHouse);
	//删除入住信息
	@Update("update t_date_house set flag = 1 where house_id=#{arg0} and dh_day=#{arg1}")
	public boolean deleteDateHouseOperation(Integer house_id,String dh_day);
	//根据house_type_id查询HouseType
	@Select("select * from t_house_type where house_type_id = #{house_type_id}")
	public HouseType findHouseTypeByHouseTypeId(Integer house_type_id);
	
	//根据房间类型和数量添加房间，先根据房间类型id查找到相同类型的房间，然后给房间house_name
	@Insert("insert into t_house(house_name,house_type_id) values(#{arg0},#{arg1})")
	public boolean addHouse(String house_name,Integer house_type_id);
	
	@Select("SELECT MAX(house_name)+1 FROM t_house WHERE house_type_id=#{house_type_id}")
	public String selectMaxHouse_name(Integer house_type_id);
	
	//根据房间类型查找房间信息
	@Select("select * from t_house_type where house_type_id=#{house_type_id}")
	public HouseType findHouseInfoByType(Integer house_type_id);
	//根据房间类型更新房间信息
	@Update("update t_house_type set house_type_price=#{arg1},house_type_msg=#{arg2} where house_type_id=#{arg0}")
	public boolean updateHouseInfo(Integer house_type_id, Integer house_type_price, String house_type_msg);
	
}
