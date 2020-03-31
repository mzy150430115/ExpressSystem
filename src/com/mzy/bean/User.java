package com.mzy.bean;

import java.util.Date;
import java.util.Objects;

/**
 *
 CREATE TABLE web_user(id INT PRIMARY KEY AUTO_INCREMENT,username VARCHAR(32),PASSWORD VARCHAR(32) NOT NULL,phonenumber VARCHAR(32) UNIQUE,flag INT DEFAULT 0,createtime DATETIME,logintime DATETIME)
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String phoneNumber;
    private int flag;
    private Date createTime;
    private Date loginTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public User() {
    }

    public User(String username, String password, String phoneNumber, int flag) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.flag = flag;
        this.createTime = new Date();
    }

    public User(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(int id, String username, String password, String phoneNumber, int flag, Date createTime, Date loginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.flag = flag;
        this.createTime = createTime;
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                flag == user.flag &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(loginTime, user.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, phoneNumber, flag, createTime, loginTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", flag=" + flag +
                ", createTime=" + createTime +
                ", loginTime=" + loginTime +
                '}';
    }
}
