package com.lenovo.m2.integral.front.controller;

import com.lenovo.m2.arch.framework.domain.PageModel2;
import com.lenovo.m2.arch.framework.domain.PageQuery;
import com.lenovo.m2.arch.framework.domain.RemoteResult;
import com.lenovo.m2.integral.soa.api.CouponAndIntegralInfoService;
import com.lenovo.m2.integral.soa.api.ExchangeCouponRecordService;
import com.lenovo.m2.integral.soa.api.IntegralResultCode;
import com.lenovo.m2.integral.soa.domain.CouponAndIntegralInfo;
import com.lenovo.m2.integral.soa.utils.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/2/22.
 */
@Controller
@RequestMapping("/integralExchange")
public class CouponAndIntegralController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CouponAndIntegralController.class);

    @Autowired
    private CouponAndIntegralInfoService couponAndIntegralInfoService;

    @Autowired
    private ExchangeCouponRecordService exchangeCouponRecordService;

    //新建优惠券和积分绑定记录
    @RequestMapping("/addCouponInfo")
    @ResponseBody
    public RemoteResult addIntegralBindingCoupon(String couponId,String memberId,Integer integralNum,Integer state){
        LOGGER.info("addIntegralBindingCoupon Start:"+couponId+";"+memberId+";"+integralNum+";"+state);

        RemoteResult remoteResult = new RemoteResult();
        try {
            remoteResult = couponAndIntegralInfoService.addCouponInfo(couponId, memberId, integralNum, state);
        }catch (Exception e){
            remoteResult.setResultMsg("系统异常");
            remoteResult.setResultCode(IntegralResultCode.FAIL);
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("addIntegralBindingCoupon End:"+ JacksonUtil.toJson(remoteResult));
        return remoteResult;
    }

    //根据优惠券id获取优惠券和积分绑定记录
    @RequestMapping("/getCouponInfo")
    @ResponseBody
    public RemoteResult getCouponInfo(String couponId){
        LOGGER.info("getCouponInfo Start:"+couponId);

        RemoteResult<CouponAndIntegralInfo> remoteResult = new RemoteResult<CouponAndIntegralInfo>();
        try {
            remoteResult = couponAndIntegralInfoService.getCouponInfo(couponId);
        }catch (Exception e){
            remoteResult.setResultMsg("系统异常");
            remoteResult.setResultCode(IntegralResultCode.FAIL);
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("getCouponInfo End:"+ JacksonUtil.toJson(remoteResult));
        return remoteResult;
    }

    //查询优惠券和积分绑定记录加分页
    @RequestMapping("/getCouponInfoByPage")
    @ResponseBody
    public RemoteResult getCouponInfoByPage(PageQuery pageQuery, CouponAndIntegralInfo couponAndIntegralInfo){
        LOGGER.info("getCouponInfoByPage Start:"+JacksonUtil.toJson(pageQuery)+";"+JacksonUtil.toJson(couponAndIntegralInfo));

        RemoteResult<PageModel2<CouponAndIntegralInfo>> remoteResult = new RemoteResult<PageModel2<CouponAndIntegralInfo>>();
        try {
            if (pageQuery.getPageSize()==0){
                pageQuery.setPageSize(10);
            }
            if (pageQuery.getPageNum()==0){
                pageQuery.setPageNum(1);
            }
            remoteResult = couponAndIntegralInfoService.getCouponInfoByPage(pageQuery, couponAndIntegralInfo);
        }catch (Exception e){
            remoteResult.setResultMsg("系统异常");
            remoteResult.setResultCode(IntegralResultCode.FAIL);
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("getCouponInfoByPage End:"+ JacksonUtil.toJson(remoteResult));
        return remoteResult;
    }

    @RequestMapping("/updateCouponInfo")
    @ResponseBody
    public RemoteResult updateCouponInfo(String couponId,String memberId, Integer integralNum, Integer state){
        RemoteResult remoteResult = couponAndIntegralInfoService.updateCouponInfo(couponId, memberId, integralNum, state);
        return remoteResult;
    }



}
