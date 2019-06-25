package com.woniuxy.dao;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Order;

public class OrderProvider {

    private final String order_table = "t_order";

    /**
     * 预定查询
     * 个人查询时，需要添加user_id属性
     * 非个人不需要
     * 用户信息传入参数
     * 姓名、电话、身份证
     *
     * @param
     * @return
     */
    public String query(Order order) {
        SQL sql = new SQL().SELECT("*").FROM(order_table+",t_user_info ")
                .WHERE("t_order.user_info_id=t_user_info.user_info_id ");
        if (order.getUser()!=null) {
            if (order.getUser().getUser_id() != null && order.getUser().getUser_id() > 0) {
                sql.WHERE("t_order.user_id=" + order.getUser().getUser_id());
            }
        }
        if (order.getUserInfo()!=null) {
            if (order.getUserInfo().getUser_info_id() != null && order.getUserInfo().getUser_info_id() > 0) {
                sql.WHERE("t_user_info.user_info_id='" + order.getUserInfo().getUser_info_id() + "'");
            }
            if (order.getUserInfo().getUser_info_tel() != null && order.getUserInfo().getUser_info_tel().length() > 0) {
                sql.WHERE("t_user_info.user_info_tel='" + order.getUserInfo().getUser_info_tel() + "'");
            }
            if (order.getUserInfo().getUser_info_idcard() != null && order.getUserInfo().getUser_info_idcard().length() > 0) {
                sql.WHERE("t_user_info.user_info_idcard='" + order.getUserInfo().getUser_info_idcard() + "'");
            }
            if (order.getUserInfo().getUser_info_name() != null && order.getUserInfo().getUser_info_name().length() > 0) {
                sql.WHERE("t_user_info.user_info_name like '%" + order.getUserInfo().getUser_info_name() + "%'");
            }
        }
        if (order.getOrder_number()!=null&&order.getOrder_number().length()>0){
            sql.WHERE("order_number='"+order.getOrder_number()+"'");
        }
        sql.WHERE("t_order.flag="+order.getFlag());
        sql.WHERE("order.state="+order.getOrder_state());
        return sql.toString();
    }

    /**
     * 动态修改
     * @param order
     * @return
     */
   public String update(Order order){
        SQL sql = new SQL().UPDATE(order_table);
        if (order.getUserInfo()!=null){
            sql.SET("user_info_id="+order.getUserInfo().getUser_info_id());
        }
        if (order.getOrder_message()!=null&&order.getOrder_message().length()>0){
            sql.SET("order_message='"+order.getOrder_message()+"'");
        }
        sql.WHERE("order_id="+order.getOrder_id());
        sql.WHERE("flag=0");
        return sql.toString();
   }
}
