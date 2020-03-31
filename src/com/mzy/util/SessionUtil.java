package com.mzy.util;

import javax.servlet.http.HttpSession;

/**
 * 用于快速的向Session中存储和捕获数据
 */
public class SessionUtil {
    /**
     * 用户向session中存储管理员登录成功的标记
     * @param session
     */
    public static void setManagerFlag(HttpSession session){
            session.setAttribute("managerFlag",1);
    }

    /**
     * 用户判断session中是否包含管理员成功的标记
     * @param session
     * @return  true表示管理员已经登录
     */

    public static boolean getManagerFlag(HttpSession session){
        Integer i = (Integer) session.getAttribute("managerFlag");
        return i==null?false:true;
    }
}
