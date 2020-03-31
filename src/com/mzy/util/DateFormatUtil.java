package com.mzy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy—MM-dd HH:mm:ss");
    public static String format(Date date){
        return sdf.format(date);
    }
}
