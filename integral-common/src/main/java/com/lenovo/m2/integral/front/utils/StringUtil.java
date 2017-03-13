package com.lenovo.m2.integral.front.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    public static boolean isEmpty(String key) {
        if (key != null && !"".equals(key.trim())) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(String key) {
        if (key!=null && !"".equals(key.trim())){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String... keys) {
        for (String key : keys) {
            if (StringUtil.isEmpty(key)) {
                return true;
            }
        }
        return false;
    }

    public static String decimalFormatPrice(String param) {
        if (param == null || "".equals(param)) {
            param = "0.00";
        }
        double tmp = Double.parseDouble(param);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(tmp);
    }


    public static String cleanXss(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        str = str.replaceAll(" ", "");
        return str;
    }

    /**
     * @param str
     * @param trim
     * @return
     * @description 以*(分号)分隔的字符串，去除首尾的*(分号)。
     * @author qinhc
     * @createTime 2015上午11:05:00
     */
    public static String StrRemoveTrim(String str, String trim) {
        // str=;2;;
        String resultStr = "";
        String[] strList = str.split(trim);
        for (String s : strList) {
            if (!"".equals(s) && null != s) {
                resultStr = resultStr + s + trim;
            }
        }
        if (resultStr.length() > 0) {
            resultStr = resultStr.substring(0, resultStr.length() - 1);
        }
        return resultStr;
    }

    /**
     * 是否手机号 简单校验11位数字
     *
     * @param str
     * @return
     * @author wangrq1
     */
    public static boolean isPhone(String str) {
        Matcher m = NUM_11.matcher(str);
        return m.matches();
    }

    static Pattern NUM_11 = Pattern.compile("\\d{11}");

    /**
     * 生产指定长度的由大写字母和数字组成的字符串 add by yezhenyue on 2016-03-29
     * @param len
     * @return
     */
    public static String randomStr(int len) {
        if (len == 0) {
            return "";
        }
        int a = (int) (Math.random() * 2);
        if (a == 0) {
            return ((int) (Math.random() * 10)) + randomStr(len - 1);
        } else {
            return ((char) ((int) (Math.random() * 26) + 65)) + randomStr(len - 1);
        }
    }

    public static void main(String[] args) {

        System.out.println(isPhone("18001141691"));
        System.out.println(isPhone(""));

    }

}
