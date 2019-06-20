package com.woniuxy.service.impl;

import com.woniuxy.dao.ReserveDAO;
import com.woniuxy.model.FlagReserveId;
import com.woniuxy.pojo.House;
import com.woniuxy.pojo.Reserve;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.service.ReserveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("reserveService")
@Transactional
public class ReserveServiceImpl implements ReserveService {
    @Resource
    private ReserveDAO reserveDAO;

    /**#########################
     * 添加预定功能
     * 添加预定信息
     * @param reserve
     */
    @Override
    public void addReserve(Reserve reserve) {
        reserveDAO.addReserve(reserve);
    }

    /**
     * 根据唯一的预定信息编号查询预定信息id
     * @param reserve
     * @return
     */
    @Override
    public Reserve findReserveIdByIdnumber(Reserve reserve) {
        return reserveDAO.findReserveIdByIdnumber(reserve);
    }

    /**
     * 给预定信息——房间中间表插入数据
     * @param reserve
     * @param house
     */
    @Override
    public void addReserveIdAndHouseId(Reserve reserve, House house) {
        reserveDAO.addReserveIdAndHouseId(reserve,house);
    }


    /**
     * 根据用户信息id查询对应的预定信息,
     * 或者根据用户账户id查询
     * @param
     * @return
     */
    @Override
    public List<Reserve> selectReserveByUserInfoOrUser(Reserve reserve) {
        return reserveDAO.selectReserveByUserInfoOrUser(reserve);
    }

    /**
     * 根据用户手机或者身份证或者姓名查询对应的用户信息id
     * @param userInfo
     * @return
     */
    @Override
    public List<UserInfo> selectUserInfoIdByNameTelIdcard(UserInfo userInfo) {
        return reserveDAO.selectUserInfoIdByNameTelIdcard(userInfo);
    }

    /**
     * 根据预定信息id查询房间id
     * @param reserve_id
     * @return
     */
    @Override
    public List<House> selectHousesByReserveId(Integer reserve_id) {
        return reserveDAO.selectHousesByReserveId(reserve_id);
    }

    /**
     * 预定取消（删除预定）（修改预定状态）已定、取消、超时
     * @param reserve
     */
    @Override
    public void updateReserveState(Reserve reserve) {
        reserveDAO.updateReserveState(reserve);
    }

    @Override
    public void updateReserve(Reserve reserve) {
        reserveDAO.updateReserve(reserve);
    }


}
