package com.mzy.dao;

import com.mzy.bean.User;

import java.util.List;

/**
 *
 CREATE TABLE web_user(id INT PRIMARY KEY AUTO_INCREMENT,username VARCHAR(32) UNIQUE,PASSWORD VARCHAR(32) NOT NULL,phonenumer VARCHAR(32) UNIQUE,flag INT DEFAULT 0,createtime DATETIME,logintime DATETIME)
 */
public interface BaseUserDao {

    /**
     * 查询所有用户
     * @return 查询结果
     */
    List<User> findAll();

    /**
     *根据用户类型标记， 查询用户
     * @param flag 用户标记： 0 普通用户  1快递员
     * @return 查询的结果
     */
    List<User> findUsersByFlag(int flag);


    /**
     *  根据昵称或电话号码查询用户
     * @param userNameOrPhoneNumber 用户昵称或电话号
     * @param type 查询的用户类型 0 普通用户 1快递员
     * @return 查询结果
     */
    User findUserByPhoneNumber(String userNameOrPhoneNumber, int type);

    /**
     * 根据电话号码和密码查询用户信息
     * @param phoneNumber 电话号
     * @param password 密码
     * @return 查询结果 -1 登录失败 0用户登录成功 1快递员登录成功
     */
    int findUserByPhoneAndPassword(String phoneNumber, String password);

    /**
     * 插入用户
     * @param user 用户信息
     * @return 插入的结果  1成功
     */
    int insert(User user);

    /**
     *  根据ID 修改用户信息
     * @param id 用户ID
     * @param user 新的用户信息
     * @return 修改结果 1成功
     */
    int updateUserById(int id, User user);

    /**
     *  根据ID 删除用户信息
     * @param id 用户ID
     * @return 删除结果 1成功
     */
    int deleteUserById(int id);


    /**
     * 根据帐号+密码 更改密码
     * @param phone 手机号
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return 修改结果 ，1成功
     */
    int updatePasswordByPhoneAndPassword(String phone, String oldPass, String newPass);



}
