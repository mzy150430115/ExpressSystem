package com.mzy.util;
import java.util.HashMap;
import java.util.Random;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

public class SendSms {
    /**
     * 用于随机生成100000 - 999999 范围的随机数字
     * @return
     */
    public static int random() {
        Random r = new Random();
        //100000-999999
        int num = r.nextInt(900000)+100000;
        return num;
    }

    /**
     * 向指定手机号码发送短信
     * @param phoneNumber 手机号码
     * @param code 验证码内容想
     * @return true表示发送成功 false表示发送失败
     */
    public static boolean send(String phoneNumber,int code) {
        return send(phoneNumber, code+"");
    }

    /**
     * 向指定手机号码发送短信
     * @param phoneNumber 手机号码
     * @param code 验证码内容
     * @return true表示发送成功 false表示发送失败
     */
    public static boolean send(String phoneNumber,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FwqTrTuAq4bFL9rAeJn", "EQhGhoAN5vihHJosygJqOPWbxILK9t");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "瓜子快递");
        request.putQueryParameter("TemplateCode", "SMS_185230677");
        //{"code":code}
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String json = response.getData();
            Gson g = new Gson();
            HashMap result = g.fromJson(json, HashMap.class);
            if("OK".equals(result.get("Message"))) {
                return true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}