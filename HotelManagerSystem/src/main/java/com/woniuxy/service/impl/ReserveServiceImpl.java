package com.woniuxy.service.impl;

import com.woniuxy.dao.ReserveDAO;
import com.woniuxy.pojo.Reserve;
import com.woniuxy.service.ReserveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("reserveService")
@Transactional
public class ReserveServiceImpl implements ReserveService {
    @Resource
    private ReserveDAO reserveDAO;
    @Override
    public void addReserve(Reserve reserve) {
        reserveDAO.addReserve(reserve);
    }

    @Override
    public Integer findReserveIdByIdnumber(Reserve reserve) {
        return reserveDAO.findReserveIdByIdnumber(reserve);
    }

    @Override
    public void addReserveIdAndHouseId(Integer reserve_id, Integer house_id) {
        reserveDAO.addReserveIdAndHouseId(reserve_id,house_id);
    }
}
