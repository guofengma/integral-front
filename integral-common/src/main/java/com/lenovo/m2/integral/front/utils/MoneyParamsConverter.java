package com.lenovo.m2.integral.front.utils;

import com.lenovo.m2.arch.framework.domain.Money;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/**
 * Created by admin on 2017/3/1.
 */
public class MoneyParamsConverter implements Converter<Integer,Money> {
    @Override
    public Money convert(Integer source) {
        try {
            BigDecimal decimal = new BigDecimal(source);
            Money money = new Money();
            money.setAmount(decimal);
            return money;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
