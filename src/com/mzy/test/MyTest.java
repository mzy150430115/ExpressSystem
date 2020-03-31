package com.mzy.test;

import com.mzy.util.SendSms;

public class MyTest {
    public static void main(String[] args) {
        int code = SendSms.random();
        System.out.println("随机数字"+code);
        boolean flag = SendSms.send("13971473587",code);
        System.out.println(flag?"发送成功":"发送失败");
    }
}
