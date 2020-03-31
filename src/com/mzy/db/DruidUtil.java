package com.mzy.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidUtil{

    private static DataSource data = null;
    static {
        InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties ppt = new Properties();
        try {
            ppt.load(is);
            data = DruidDataSourceFactory.createDataSource(ppt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于从DBCP连接池中 获取一个连接
     * @return DBCP连接池中的一个连接对象.
     */
    public static Connection getConnection() {
        try {
            return data.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用于释放连接  , 执行环境 , 结果集 等资源
     * @param conn 要释放的连接资源
     * @param state	要释放的执行环境资源
     * @param result 要释放的结果集资源
     */
    public static void close(Connection conn,Statement state,ResultSet result) {
        if(result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



}
