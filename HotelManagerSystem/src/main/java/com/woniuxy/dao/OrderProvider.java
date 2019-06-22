package com.woniuxy.dao;

import org.apache.ibatis.jdbc.SQL;

public class OrderProvider {

    private final String order_table = "t_order";


    public String query(){
        SQL sql = new SQL().SELECT("*").FROM(order_table);

        return sql.toString();
    }
}
