package com.mzy.dao;

import com.mzy.bean.User;
import com.mzy.db.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CREATE TABLE web_user(id INT PRIMARY KEY AUTO_INCREMENT,username VARCHAR(32),PASSWORD VARCHAR(32) NOT NULL,phonenumer VARCHAR(32) UNIQUE,flag INT,createtime DATETIME,logintime DATETIME)
 */
public class UserDaoImp implements BaseUserDao {
    private static final String SQL_CREATE_TABLE = "CREATE TABLE web_user(id INT PRIMARY KEY AUTO_INCREMENT,username VARCHAR(32),PASSWORD VARCHAR(32) NOT NULL,phonenumer VARCHAR(32) UNIQUE,flag INT,createtime DATETIME,logintime DATETIME)";
    private static final String SQL_FIND_USER_ALL = "select * from web_user";
    private static final String SQL_FIND_USER_BY_FLAG = "select * from web_user where flag=?";
    private static final String SQL_FIND_USER_BY_PHONE = "select * from web_user where phoneNumber=?and flag=?";
    private static final String SQL_FIND_USER_BY_PHONE_AND_PASSWORD = "select flag from web_user where phonenumber=? and password=?";
    private static final String SQL_INSERT = "insert into web_user(username,password,phonenumber,flag,createtime) values(?,?,?,?,now())";
    private static final String SQL_UPDATE_BY_ID = "update web_user set username=?,password=?,phonenumber=? where id=?";
    private static final String SQL_UPDATE_PASSWORD_BY_PHONE_AND_PASSWORD = "update web_user set password=? where phoneNumber=? and password=?";
    private static final String SQL_UPDATE_LOGINTIME = "update web_user set loginti me=now() where phonenumber=?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM WEB_USER WHERE ID=?";

    static{
        //1.	创建本地用户表格
        Connection conn = DruidUtil.getConnection();
        Statement state = null;
        try {
            state = conn.createStatement();
            state.execute(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            //e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }

    }
    /**
     * 查询所有用户
     *
     * @return 查询结果
     */
    @Override
    public List<User> findAll() {
        List<User> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_FIND_USER_ALL);
            result = state.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String phoneNumber = result.getString("phoneNumber");
                int flag = result.getInt("flag");
                Date createTime = result.getTimestamp("createTime");
                Date loginTime = result.getTimestamp("loginTime");
                User u = new User(id,username,password,phoneNumber,flag,createTime,loginTime);
                data.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 根据用户类型标记， 查询用户
     *
     * @param flag 用户标记： 0 普通用户  1快递员
     * @return 查询的结果
     */
    @Override
    public List<User> findUsersByFlag(int flag) {
        List<User> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_FIND_USER_BY_FLAG);
            state.setInt(1,flag);
            result = state.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String phoneNumber = result.getString("phoneNumber");
                Date createTime = result.getTimestamp("createTime");
                Date loginTime = result.getTimestamp("loginTime");
                User u = new User(id,username,password,phoneNumber,flag,createTime,loginTime);
                data.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 根据昵称或电话号码查询用户
     *
     * @param phoneNumber 用户昵称或电话号
     * @param type                  查询的用户类型 0 普通用户 1快递员
     * @return 查询结果
     */
    @Override
    public User findUserByPhoneNumber(String phoneNumber, int type) {
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_FIND_USER_BY_PHONE);
            state.setString(1,phoneNumber);
            state.setInt(2,type);
            result = state.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                Date createTime = result.getTimestamp("createTime");
                Date loginTime = result.getTimestamp("loginTime");
                User u = new User(id,username,password,phoneNumber,type,createTime,loginTime);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * 根据电话号码和密码查询用户信息
     *
     * @param phoneNumber 电话号
     * @param password    密码
     * @return 查询结果 -1 登录失败 0用户登录成功 1快递员登录成功
     */
    @Override
    public int findUserByPhoneAndPassword(String phoneNumber, String password) {
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_FIND_USER_BY_PHONE_AND_PASSWORD);
            state.setString(1,phoneNumber);
            state.setString(2,password);
            result = state.executeQuery();
            while(result.next()){
                int flag = result.getInt("flag");
                updateLoginTimeByPhone(phoneNumber);
                return flag;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return -1;
    }

    /**
     * 插入用户
     *
     * @param user 用户信息 ， 至少包含账号，密码，电话号，标记（0用户，1快递员）
     * @return 插入的结果  1成功
     */
    @Override
    public int insert(User user) {
        Connection conn = null;
        PreparedStatement state = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_INSERT);
            state.setString(1,user.getUsername());
            state.setString(2,user.getPassword());
            state.setString(3,user.getPhoneNumber());
            state.setInt(4,user.getFlag());
            return state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return 0;
    }

    /**
     * 根据ID 修改用户信息
     *
     * @param id   用户ID
     * @param user 新的用户信息
     * @return 修改结果 1成功
     */
    @Override
    public int updateUserById(int id, User user) {
        Connection conn = null;
        PreparedStatement state = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_UPDATE_BY_ID);
            state.setString(1,user.getUsername());
            state.setString(2,user.getPassword());
            state.setString(3,user.getPhoneNumber());
            state.setInt(4,id);
            return state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return 0;
    }

    /**
     * 根据ID 删除用户信息
     *
     * @param id 用户ID
     * @return 删除结果 1成功
     */
    @Override
    public int deleteUserById(int id) {
        //1.    使用阿里德鲁伊连接池， 获取一个数据库的链接
        Connection conn = DruidUtil.getConnection();
        //2.    通过链接对象， 预编译SQL语句
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_DELETE_BY_ID);
            //3.    填充预编译的参数
            state.setInt(1,id);
            //4.    执行SQL语句 ， 并根据执行的结果返回
            return state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return 0;
    }

    /**
     * 根据帐号+密码 更改密码
     *
     * @param phone   手机号
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return 修改结果 ，1成功
     */
    @Override
    public int updatePasswordByPhoneAndPassword(String phone, String oldPass, String newPass) {
        Connection conn = null;
        PreparedStatement state = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_UPDATE_PASSWORD_BY_PHONE_AND_PASSWORD);
            state.setString(1,newPass);
            state.setString(2,phone);
            state.setString(3,oldPass);
            return state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return 0;
    }

    /**
     * 根据手机号码 修改登录时间
     *
     * @param phone 电话号
     * @return 修改结果 1成功
     */
    private int updateLoginTimeByPhone(String phone) {
        Connection conn = null;
        PreparedStatement state = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_UPDATE_LOGINTIME);
            state.setString(1,phone);
            return state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return 0;
    }

}
