package com.wzy.utils;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Utils {
    private static DataSource ds = null;
    private static ThreadLocal<Connection> threadLocal;
    static {
        ds = new ComboPooledDataSource();
    }
    public static DataSource getDataSource(){
        return ds;
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        return ds.getConnection();
    }
    //关闭跟本线程相关的连接
    public static void close(){
        Connection con=threadLocal.get();
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocal.remove();
        }
    }
}
