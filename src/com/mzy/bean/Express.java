package com.mzy.bean;

import com.google.gson.Gson;

import java.util.Date;


/**
 * 我们描述的不是快递公司的 快递表格.
 *
 * 我们描述的是 快递柜 在存储快递时, 需要的信息.
 * @author JIE
 *
 */
public class Express {

    //ID	编号
    private int id;
    //eNumber	单号
    private String eNumber;
    //username		收件人姓名
    private String username;
    //userphone		收件人电话
    private String userphone;
    //company		快递公司
    private String company;
    //code			取件码
    private String code;
    //inTime		入库时间
    private Date inTime;
    //outTime		出库时间(被签收)
    private Date outTime;
    //status		快递状态 (0代取件 1已取件 2已退回 3滞留件)
    private int status;
    //sysPhone		录入人手机号
    private String sysPhone;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getENumber() {
        return eNumber;
    }
    public void setENumber(String eNumber) {
        this.eNumber = eNumber;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserphone() {
        return userphone;
    }
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Date getInTime() {
        return inTime;
    }
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
    public Date getOutTime() {
        return outTime;
    }
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getSysPhone() {
        return sysPhone;
    }
    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }
    public Express(int id, String eNumber, String username, String userphone, String company, String code,
                   Date inTime, Date outTime, int status, String sysPhone) {
        super();
        this.id = id;
        this.eNumber = eNumber;
        this.username = username;
        this.userphone = userphone;
        this.company = company;
        this.code = code;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.sysPhone = sysPhone;
    }
    public Express() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + id;
        result = prime * result + ((inTime == null) ? 0 : inTime.hashCode());
        result = prime * result + ((eNumber == null) ? 0 : eNumber.hashCode());
        result = prime * result + ((outTime == null) ? 0 : outTime.hashCode());
        result = prime * result + status;
        result = prime * result + ((sysPhone == null) ? 0 : sysPhone.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((userphone == null) ? 0 : userphone.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Express other = (Express) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (company == null) {
            if (other.company != null)
                return false;
        } else if (!company.equals(other.company))
            return false;
        if (id != other.id)
            return false;
        if (inTime == null) {
            if (other.inTime != null)
                return false;
        } else if (!inTime.equals(other.inTime))
            return false;
        if (eNumber == null) {
            if (other.eNumber != null)
                return false;
        } else if (!eNumber.equals(other.eNumber))
            return false;
        if (outTime == null) {
            if (other.outTime != null)
                return false;
        } else if (!outTime.equals(other.outTime))
            return false;
        if (status != other.status)
            return false;
        if (sysPhone == null) {
            if (other.sysPhone != null)
                return false;
        } else if (!sysPhone.equals(other.sysPhone))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (userphone == null) {
            if (other.userphone != null)
                return false;
        } else if (!userphone.equals(other.userphone))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Express [id=" + id + ", eNumber=" + eNumber + ", username=" + username + ", userphone=" + userphone
                + ", company=" + company + ", code=" + code + ", inTime=" + inTime + ", outTime=" + outTime
                + ", status=" + status + ", sysPhone=" + sysPhone + "]";
    }
    public Express(String eNumber, String username, String userphone, String company, String sysPhone) {
        super();
        this.eNumber = eNumber;
        this.username = username;
        this.userphone = userphone;
        this.company = company;
        this.sysPhone = sysPhone;
    }

    /*
     * public static void main(String[] args) throws InterruptedException {
     * while(true) { Express e = new Express(null, null, null, null, null);
     * System.out.println(e.code); Thread.sleep(300); } }
     */
    public String toJSON(){
        Gson g = new Gson();
        String json = g.toJson(this);
        return json;

    }

    public static void main(String[] args) {
        Express e = new Express();
        String json = e.toJSON();
        System.out.println(json);
    }


}
