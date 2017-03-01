package com.lenovo.m2.integral.front.controller;

import com.lenovo.m2.arch.framework.domain.RemoteResult;
import com.lenovo.m2.integral.soa.api.CouponAndIntegralInfoService;
import com.lenovo.m2.integral.soa.api.ExchangeCouponRecordService;
import com.lenovo.m2.integral.soa.domain.CouponAndIntegralInfo;
import com.lenovo.m2.integral.soa.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/2/22.
 */
@Controller
public class CouponAndIntegralController {

    @Autowired
    private CouponAndIntegralInfoService couponAndIntegralInfoService;

    @Autowired
    private ExchangeCouponRecordService exchangeCouponRecordService;

    @RequestMapping("/addCouponInfo")
    @ResponseBody
    public String addIntegralBindingCoupon(){

        RemoteResult remoteResult = couponAndIntegralInfoService.addCouponInfo("couponId", "memberId", 1, 1);
        /*if (remoteResult.isSuccess()){
            return remoteResult.getResultMsg();
        }else {
            return null;
        }*/
        return JacksonUtil.toJson(remoteResult);
    }

    @RequestMapping("/getCouponInfo")
    @ResponseBody
    public String getCouponInfo(){

        RemoteResult<CouponAndIntegralInfo> remoteResult = couponAndIntegralInfoService.getCouponInfo("couponId");
        if (remoteResult.isSuccess()){
            CouponAndIntegralInfo t = remoteResult.getT();
            return JacksonUtil.toJson(t);
        }else {
            return JacksonUtil.toJson(remoteResult);
        }
    }


}
