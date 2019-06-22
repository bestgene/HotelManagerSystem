package com.woniuxy.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Charging;

public interface ChargingDAO {
	/**
	 * 显示计费表的信息（即判断是否全场打折和线上打折）
	 * @return
	 */
	@Select("select * from t_charging")
	public Charging showCharging();
	
	/**
	 * 修改计费表的信息
	 * @param charging
	 * @return
	 */
	@Update("update t_charging set charging_isqc=#{charging_isqc},charging_ratio=#{charging_ratio},"
			+ "charging_isqz=#{charging_isqz},online_isqc=#{online_isqc},online_ratio=#{online_ratio}")
	public boolean modifyCharging(Charging charging);
}
