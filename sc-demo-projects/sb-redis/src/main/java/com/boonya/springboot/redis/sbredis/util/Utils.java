package com.boonya.springboot.redis.sbredis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date StringToDate(String date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DateToString() throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    public static String nullToString(String column){
        if(null == column || "null".equals(column))
            return "";
        else
            return column;
    }
    public static String objectToString(Object obj){
        if(obj == null || "null".equals(obj))
            return "";
        else
            return obj.toString();
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean IsNullOrEmpty(String value) {
        if ((value == null) || ("".equals(value))) {
             return true;
        }
        return false;
        }
}
