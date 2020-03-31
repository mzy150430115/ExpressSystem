package com.mzy.service;

import com.mzy.bean.User;
import com.mzy.dao.BaseUserDao;
import com.mzy.dao.UserDaoImp;

import java.util.List;

public class UserServiceImp implements BaseUserService {
    private static BaseUserDao dao = new UserDaoImp();


    /**
     * 查询所有用户
     *
     * @return 查询结果
     */
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    /**
     * 查询所有普通用户
     *
     * @return 查询结果
     */
    @Override
    public List<User> findUsers() {
        return dao.findUsersByFlag(0);
    }

    /**
     * 查询所有快递员
     *
     * @return 查询结果
     */
    @Override
    public List<User> findCouriers() {
        return dao.findUsersByFlag(1);
    }

    /**
     * 根据昵称或电话号码查询用户
     *
     * @param userNameOrPhoneNumber 用户昵称或电话号
     * @param type                  查询的用户类型 0 普通用户 1快递员
     * @return 查询结果
     */
    @Override
    public User findUserByPhoneNumber(String userNameOrPhoneNumber, int type) {
        return dao.findUserByPhoneNumber(userNameOrPhoneNumber,type);
    }

    /**
     * 根据电话号码和密码查询用户信息
     *
     * @param phoneNumber 电话号
     * @param password    密码
     * @return 查询结果 -1 登录失败 0用户登录成功 1快递员登录成功
     */
    @Override
    public int login(String phoneNumber, String password) {
        return dao.findUserByPhoneAndPassword(phoneNumber,password);
    }

    /**
     * 插入用户
     *
     * @param user 用户信息
     * @return 插入的结果  1成功
     */
    @Override
    public int insert(User user) {
        return dao.insert(user);
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
        return dao.updateUserById(id,user);
    }

    /**
     * 根据ID 删除用户信息
     *
     * @param id 用户ID
     * @return 删除结果 1成功
     */
    @Override
    public int deleteUserById(int id) {
        return dao.deleteUserById(id);
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
    public int updatePassword(String phone, String oldPass, String newPass) {
        return dao.updatePasswordByPhoneAndPassword(phone,oldPass,newPass);
    }
}
