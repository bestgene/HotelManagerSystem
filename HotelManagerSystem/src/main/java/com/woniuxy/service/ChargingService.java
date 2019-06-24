package com.woniuxy.service;

import com.woniuxy.pojo.Charging;

public interface ChargingService {

    public Charging queryCharging();

    public boolean updateCharging(Charging charging);
}
