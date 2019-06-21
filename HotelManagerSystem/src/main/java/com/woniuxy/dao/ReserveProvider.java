package com.woniuxy.dao;

import com.woniuxy.pojo.Reserve;
import com.woniuxy.pojo.UserInfo;
import org.apache.ibatis.jdbc.SQL;

public class ReserveProvider {
    private final String table = "t_reserve";


    /**
     * 动态查询
     * 传入：预定信息id或者预定信息编号
     * 返回：预定信息pojo类  唯一
     * @return
     */
    public String queryReserve(Reserve reserve){
        SQL sql = new SQL();
        if (reserve.getReserve_id() != null && reserve.getReserve_id()>0){
            sql.SELECT("*").FROM(table).WHERE("reserve_id="+reserve.getReserve_id()+"");
        }else {
            sql.SELECT("reserve_id").FROM(table).WHERE("reserve_idnumber="+reserve.getReserve_idnumber()+"");
        }
        return sql.toString();
    }


    /**
     * 预定查询
     * 个人查询时，需要添加user_id属性
     * 非个人不需要
     * 用户信息传入参数
     * 姓名、电话、身份证
     * @param reserve
     * @return
     */
    public String queryListReserve(Reserve reserve){
        SQL sql = new SQL().SELECT("*").FROM(table+",t_user_info ")
                .WHERE("t_reserve.user_info_id=t_user_info.user_info_id ");
        if (reserve.getUser_id() != null && reserve.getUser_id()>0){
            sql.WHERE("t_reserve.user_id='"+reserve.getUser_id()+"'");
        }
        if (reserve.getUserInfo().getUser_info_id() !=null && reserve.getUserInfo().getUser_info_id()>0){
            sql.WHERE("t_user_info.user_info_id='"+reserve.getUserInfo().getUser_info_id()+"'");
        }
        if (reserve.getUserInfo().getUser_info_tel()!=null&&reserve.getUserInfo().getUser_info_tel().length()>0){
            sql.WHERE("t_user_info.user_info_tel='"+reserve.getUserInfo().getUser_info_tel()+"'");
        }
        if (reserve.getUserInfo().getUser_info_idcard() != null && reserve.getUserInfo().getUser_info_idcard().length()>0){
            sql.WHERE("t_user_info.user_info_idcard='"+reserve.getUserInfo().getUser_info_idcard()+"'");
        }
        if (reserve.getUserInfo().getUser_info_name()!=null&&reserve.getUserInfo().getUser_info_name().length()>0){
            sql.WHERE("t_user_info.user_info_name like '%"+reserve.getUserInfo().getUser_info_name()+"%'");
        }

        sql.WHERE("t_reserve.flag=0");
        return sql.toString();
    }

    /**
     * 动态更新
     * @param reserve
     * @return
     */
    public String update(Reserve reserve){
        SQL sql = new SQL().UPDATE(table);
        if (reserve.getUserInfo().getUser_info_id()!=null&&reserve.getUserInfo().getUser_info_id()>0){
            sql.SET("user_info_id="+reserve.getUserInfo().getUser_info_id());
        }
        if (reserve.getReserve_checkintime()!=null&&reserve.getReserve_checkintime().length()>0){
            sql.SET("reserve_checkintime='"+reserve.getReserve_checkintime()+"'");
        }
        if (reserve.getReserve_checkouttime()!=null&&reserve.getReserve_checkouttime().length()>0){
            sql.SET("reserve_checkouttime='"+reserve.getReserve_checkouttime()+"'");
        }
        if (reserve.getReserve_arrivetime()!=null && reserve.getReserve_arrivetime().length()>0 ){
            sql.SET("reserve_arrivetime='"+reserve.getReserve_arrivetime()+"'");
        }
        if (reserve.getReserve_canceltime()!=null&&reserve.getReserve_canceltime().length()>0){
            sql.SET("reserve_canceltime='"+reserve.getReserve_canceltime()+"'");
        }
        if (reserve.getReserve_isauto() != null){
            sql.SET("reserve_isauto="+reserve.getReserve_isauto());
        }
        if (reserve.getReserve_deposit() !=null){
            sql.SET("reserve_deposit="+reserve.getReserve_deposit());
        }
        if (reserve.getReserve_payment() !=null){
            sql.SET("reserve_payment="+reserve.getReserve_payment());
        }
        if (reserve.getReserve_paynumber() !=null){
            sql.SET("reserve_paynumber="+reserve.getReserve_paynumber());
        }
        if (reserve.getReserve_message() != null && reserve.getReserve_message().length()>0){
            sql.SET("reserve_message='"+reserve.getReserve_message()+"'");
        }
        sql.SET("flag=0");

        return sql.toString();
    }
}
