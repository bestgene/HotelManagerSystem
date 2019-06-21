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
        //1.从前端获取Reserve对象，预定时间段、房间由别人给我

        //2.获取session中保存的User对象的id赋值给Reserve对象，指出操作人
        User user = (User) request.getSession().getAttribute("user");
        reserve.setUser_id(user.getUid());
        //
        /*UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");*/

        //3.生成唯一预定编号，赋值
        String idnumber = System.currentTimeMillis()+""+(new Random().nextInt(900)+100)
                +reserve.getUserInfo().getUser_info_tel().substring(reserve.getUserInfo().getUser_info_tel().length()-4);
        reserve.setReserve_idnumber(idnumber);
        //4.新增预定信息
        reserveService.addReserve(reserve);
        //5.获取刚刚插入后生成的id
        Reserve newreserve = reserveService.findReserveIdByIdnumber(reserve);
        //6.中间表数据添加
        for (int i = 0;i<reserve.getHouses().size();i++){
            reserveService.addReserveIdAndHouseId(newreserve,reserve.getHouses().get(i));
        }
        return "新增成功";
    }

    /**
     *  后台管理
     *  预定查询功能
     *  根据用户手机、或者身份证、或者姓名查询预定信息
     * @param reserve
     * @return
     */
    @RequestMapping("/htquery")
    @ResponseBody
    public List<Reserve> queryAllByUserInfoHt(Reserve reserve){
        List<Reserve> reserves = reserveService.selectReserveByUserInfoOrUser(reserve);
        return reserves;
    }

    /**
     *  前台管理
     *  预定查询功能，只能查询个人
     *  也可以根据用户账号id和输入用户信息查询
     *
     * @return
     */
    @RequestMapping("/qtquery")
    @ResponseBody
    public List<Reserve> queryAllByUserInfoQt(Reserve reserve){
        //获取当前登录用户id
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
     * 修改预定信息管理
     * @param reserve
     * @return
     */
    @RequestMapping("/xgyd")
    @ResponseBody
    public String alertReserve(Reserve reserve){
        reserveService.updateReserveState(reserve);
        return "修改测试";

    }

}
