package com.lenovo.m2.integral.front.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/3/1.
 */
public class CouponAndIntegralInfoConverter implements Converter<String ,Date> {

    @Override
    public Date convert(String source) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(source);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
