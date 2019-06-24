package com.woniuxy.service.impl;

import com.woniuxy.dao.ChargingDAO;
import com.woniuxy.pojo.Charging;
import com.woniuxy.service.ChargingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("chargingService")
@Transactional
public class ChargingServiceImpl implements ChargingService {
    @Resource
    private ChargingDAO chargingDAO;

    @Override
    public Charging queryCharging() {
        return chargingDAO.showCharging();
    }

    @Override
    public boolean updateCharging(Charging charging) {
        return chargingDAO.modifyCharging(charging);
    }
}
