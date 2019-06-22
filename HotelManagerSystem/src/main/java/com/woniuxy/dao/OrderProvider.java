package com.woniuxy.dao;

import com.woniuxy.pojo.Order;
import org.apache.ibatis.jdbc.SQL;

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
                .WHERE("t_order.user_info_id=t_user_info.user_info_id ");;
        if (order.getUser().getUid() != null && order.getUser().getUid() > 0) {
            sql.WHERE("t_order.user_id=" + order.getUser().getUid());
        }
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
        sql.WHERE("t_order.flag=0");
        return sql.toString();
    }
}
