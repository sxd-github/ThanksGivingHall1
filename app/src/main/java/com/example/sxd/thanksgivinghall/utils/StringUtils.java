package com.example.sxd.thanksgivinghall.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/23.
 */
public class StringUtils {

    public static final int INCREMENT = 0;
    public static final int DECREASE = 1;

    public static boolean notIsBlankAndEmpty(String string){

        if(string != null && string.length() > 0  && string.trim().length() > 0){
            return true;
        }
        return false;
    }


    public static String changeLabelNumber(String labelNumber, int changeType) {

        // L-0081
        //indexof 返回"-"的位置数 1
        String number = labelNumber.substring(labelNumber.indexOf("-")+1);   //0081

        int i = 0;
        //去除最前面的0
        for(; i < number.length(); i++){

            //charAt(i)返回指定位置的字符
            if(number.charAt(i) == '0'){

            }else{
                break;
            }
        }

        //取出第一个字母非0的字符串，并转化为Int类型
        int increteNumber = Integer.valueOf(number.substring(i));  //81

        if(changeType == INCREMENT){
            increteNumber++;
        }else {
            if(increteNumber > 0){
                increteNumber--;
            }
        }

        int mod = String.valueOf(increteNumber).length();
        //组合新的标签号
        String newNumberString = "L-";
        for(int j=0; j<5-mod; j++){
            newNumberString += "0";
        }

        return newNumberString + String.valueOf(increteNumber) ;
    }

    //去除换行
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static boolean isNumber(String runTime) {
        try{
            Integer.valueOf(runTime);
        }catch (Exception e){
            return false;
        }
        return true;

    }

    public static String getSystemCurrentTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    public static String fromatTime(Date calibrationTime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(calibrationTime);
    }

    public static String changNumber(String number, int type) {

        int i = 0;
        //去除最前面的0
        for(; i < number.length(); i++){
            if(number.charAt(i) == '0'){

            }else{
                break;
            }
        }

        if(i == number.length()){//全0
            return number;
        }

        int increteNumber = Integer.valueOf(number.substring(i));

        if(type == INCREMENT){
            increteNumber++;
        }else {
            if(increteNumber > 0){
                increteNumber--;
            }
        }

        String newFlagNumber = "";
        for(int j=0; j<i; j++){
            newFlagNumber += "0";
        }

        return newFlagNumber + String.valueOf(increteNumber);
    }

}
