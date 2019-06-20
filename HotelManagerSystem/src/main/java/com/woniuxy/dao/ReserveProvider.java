package com.woniuxy.dao;

import com.woniuxy.pojo.Reserve;
import com.woniuxy.pojo.UserInfo;
import org.apache.ibatis.jdbc.SQL;

public class ReserveProvider {
    private final String table = "t_reserve";

    public static boolean isnullkong(String info){
        if (info == null || info.length()==0){
            return true;
        }
        return false;
    }

    /**
     * 动态查询
     * 传入：用户手机、姓名、身份证
     * 查询：用户信息
     * @param userInfo
     * @return
     */
    public String queryInfoId(UserInfo userInfo){
        SQL sql = new SQL().SELECT("*").FROM("t_user_info");
        if (!isnullkong(userInfo.getUser_info_tel())){
            sql.WHERE("user_info_tel='"+userInfo.getUser_info_tel()+"'");
        }
        if (!isnullkong(userInfo.getUser_info_idcard())){
            sql.WHERE("user_info_idcard='"+userInfo.getUser_info_idcard()+"'");
        }
        if (!isnullkong(userInfo.getUser_info_name())){
            sql.WHERE("user_info_name like '%"+userInfo.getUser_info_name()+"%'");
        }

        sql.WHERE("flag=0");
        return sql.toString();
    }

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
     * 个人查询，或者后台根据用户信息id查询
     * @param reserve
     * @return
     */
    public String queryListReserve(Reserve reserve){
        SQL sql = new SQL().SELECT("*").FROM(table);
        if (reserve.getUser_id() != null && reserve.getUser_id()>0){
            sql.WHERE("user_id='"+reserve.getUser_id()+"'");
            return sql.toString();
        }
        if (reserve.getUserInfo().getUser_info_id() !=null && reserve.getUserInfo().getUser_info_id()>0){
            sql.WHERE("user_info_id='"+reserve.getUserInfo().getUser_info_id()+"'");
        }
        sql.WHERE("flag=0");

        return sql.toString();
    }


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
