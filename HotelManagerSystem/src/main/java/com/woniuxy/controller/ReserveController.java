package com.woniuxy.controller;

import com.woniuxy.pojo.House;
import com.woniuxy.pojo.Reserve;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.service.ReserveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/reserve")
public class ReserveController {
    @Resource
    private ReserveService reserveService;

    /**
     * 添加预定信息
     *  前后台公用
     * @param reserve
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(Reserve reserve, HttpServletRequest request){
        //模拟前端获得的房间集合
        List<House> houses = new ArrayList<>();
        House house1 = new House();
        House house2 = new House();
        house1.setHouse_id(2);
        houses.add(house1);
        house2.setHouse_id(3);
        houses.add(house2);
        reserve.setHouses(houses);
        /*UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");*/
        reserve.setUser_id(1);
        //生成唯一编号
        String idnumber = System.currentTimeMillis()+""+(new Random().nextInt(900)+100)
                +reserve.getUserInfo().getUser_info_tel().substring(reserve.getUserInfo().getUser_info_tel().length()-4);
        reserve.setReserve_idnumber(idnumber);
        //调用插入方法
        reserveService.addReserve(reserve);
        //获取刚刚插入后生成的id
        Reserve newreserve = reserveService.findReserveIdByIdnumber(reserve);
        //中间表数据添加
        for (int i = 0;i<reserve.getHouses().size();i++){
            reserveService.addReserveIdAndHouseId(newreserve,reserve.getHouses().get(i));
        }
        return "新增成功";
    }

    /**
     *  后台管理
     *  预定查询功能
     *  根据用户手机、或者身份证、或者姓名查询预定信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/htquery")
    @ResponseBody
    public List<Reserve> queryAllByUserInfoHt(UserInfo userInfo){
        List<UserInfo> userInfos = reserveService.selectUserInfoIdByNameTelIdcard(userInfo);
        List<Reserve> reserves = new ArrayList<>();
        if (userInfos !=null){
            List<Reserve> selectByUserInfo = null;
            Reserve inforeserve = new Reserve();
            for (UserInfo info:userInfos
            ) {
                inforeserve.setUserInfo(userInfo);
                selectByUserInfo = reserveService.selectReserveByUserInfoOrUser(inforeserve);
                for (Reserve reserve:selectByUserInfo
                ) {
                    reserve.setUserInfo(info);
                    reserves.add(reserve);
                }
            }
        }
        return reserves;
    }


    /**
     *  前台管理
     *  预定查询功能，只能查询个人
     *
     *
     * @return
     */
    @RequestMapping("/qtquery")
    @ResponseBody
    public List<Reserve> queryAllByUserInfoQt(){
        Reserve reserve = new Reserve();
        reserve.setUser_id(1);
        List<Reserve> reserves = reserveService.selectReserveByUserInfoOrUser(reserve);
        return reserves;
    }

    /**
     * 预定状态管理
     * @param reserve
     * @return
     */
    @RequestMapping("/qcyd")
    @ResponseBody
    public String updateReserveState(Reserve reserve){
        reserveService.updateReserveState(reserve);
        return "预定状态修改";
    }

    /**
     * 预定状态管理
     * @param reserve
     * @return
     */
    @RequestMapping("/qcyd")
    @ResponseBody
    public String alertReserve(Reserve reserve){


        return "";

    }

}
