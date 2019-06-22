package com.woniuxy.service;

import com.woniuxy.pojo.Charging;

public interface ChargingService {
	/**
	 * 显示计费表的信息（即判断是否全场打折和线上打折）
	 * @return
	 */
	public Charging showCharging();
	
	/**
	 * 修改计费表的信息（即修改是否全场打折和线上打折）
	 * @param charging
	 * @return
	 */
	public boolean modifyCharging(Charging charging);
}
