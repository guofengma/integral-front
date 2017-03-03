package com.lenovo.m2.integral.front.controller;

import com.lenovo.m2.arch.framework.domain.Money;
import com.lenovo.m2.arch.framework.domain.PageModel2;
import com.lenovo.m2.arch.framework.domain.PageQuery;
import com.lenovo.m2.arch.framework.domain.RemoteResult;
import com.lenovo.m2.integral.front.utils.ResultCode;
import com.lenovo.m2.integral.soa.api.CouponAndIntegralInfoService;
import com.lenovo.m2.integral.soa.api.ExchangeCouponRecordService;
import com.lenovo.m2.integral.soa.domain.CouponAndIntegralInfo;
import com.lenovo.m2.integral.soa.utils.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public RemoteResult addCouponInfo(String couponId,String memberId,Integer integralNum,Integer state){
        LOGGER.info("addCouponInfo Start:"+couponId+";"+memberId+";"+integralNum+";"+state);

        RemoteResult remoteResult = new RemoteResult();

        try {
            if (couponId==null || memberId==null || integralNum==null || state==null){
                remoteResult.setResultCode(ResultCode.PARAMS_FAIL);
                remoteResult.setResultMsg("参数不正确！");
                LOGGER.info("addCouponInfo End:" + JacksonUtil.toJson(remoteResult));
                return remoteResult;
            }

            remoteResult = couponAndIntegralInfoService.addCouponInfo(couponId, memberId, integralNum, state);
        }catch (Exception e){
            remoteResult.setResultMsg("系统异常");
            remoteResult.setResultCode(ResultCode.FAIL);
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("addCouponInfo End:"+ JacksonUtil.toJson(remoteResult));
        return remoteResult;
    }

    //根据优惠券id获取优惠券和积分绑定记录
    @RequestMapping("/getCouponInfo")
    @ResponseBody
    public RemoteResult getCouponInfo(String couponId){
        LOGGER.info("getCouponInfo Start:"+couponId);

        RemoteResult<CouponAndIntegralInfo> remoteResult = new RemoteResult<CouponAndIntegralInfo>();
        try {
            if (couponId==null){
                remoteResult.setResultCode(ResultCode.PARAMS_FAIL);
                remoteResult.setResultMsg("参数不正确");
                LOGGER.info("getCouponInfo End:" + JacksonUtil.toJson(remoteResult));
                return remoteResult;
            }

            remoteResult = couponAndIntegralInfoService.getCouponInfo(couponId);
        }catch (Exception e){
            remoteResult.setResultMsg("系统异常");
            remoteResult.setResultCode(ResultCode.FAIL);
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("getCouponInfo End:"+ JacksonUtil.toJson(remoteResult));
        return remoteResult;
    }

    //查询优惠券和积分绑定记录加分页
    @RequestMapping("/getCouponInfoByPage")
    @ResponseBody
    public RemoteResult getCouponInfoByPage(PageQuery pageQuery, CouponAndIntegralInfo couponAndIntegralInfo,HttpServletRequest request){
        LOGGER.info("getCouponInfoByPage Start:" + JacksonUtil.toJson(pageQuery) + ";" + JacksonUtil.toJson(couponAndIntegralInfo) + ";" + JacksonUtil.toJson(request));

        RemoteResult<PageModel2<CouponAndIntegralInfo>> remoteResult = new RemoteResult<PageModel2<CouponAndIntegralInfo>>();
        try {
            //参数处理
            String money = request.getParameter("money");
            if (money!=null){
                /*BigDecimal decimal = new BigDecimal(Integer.parseInt(money));
                Money money1 = new Money();
                money1.setAmount(decimal);
                couponAndIntegralInfo.setCouponMoney(money1);*/
                Money cny = new Money(Integer.parseInt(money), "CNY");
                couponAndIntegralInfo.setCouponMoney(cny);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fromtime = request.getParameter("usestarttime");
            if (fromtime!=null){
                Date parse = sdf.parse(fromtime);
                couponAndIntegralInfo.setFromtime(parse);
            }
            String totime = request.getParameter("useendtime");
            if (totime!=null){
                Date parse = sdf.parse(totime);
                couponAndIntegralInfo.setTotime(parse);
            }
            String gainstarttime = request.getParameter("gainstarttime");
            if (gainstarttime!=null){
                Date parse = sdf.parse(gainstarttime);
                couponAndIntegralInfo.setGetstarttime(parse);
            }
            String gainendtime = request.getParameter("gainendtime");
            if (gainendtime!=null){
                Date parse = sdf.parse(gainendtime);
                couponAndIntegralInfo.setGetendtime(parse);
            }
        }catch (Exception e){
            remoteResult.setResultCode(ResultCode.PARAMS_FAIL);
            remoteResult.setResultMsg("参数不正确");
            LOGGER.error(e.getMessage(),e);
            LOGGER.info("getCouponInfo End:" + JacksonUtil.toJson(remoteResult));
            return remoteResult;
        }


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
            remoteResult.setResultCode(ResultCode.FAIL);
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("getCouponInfoByPage End:"+ JacksonUtil.toJson(remoteResult));
        return remoteResult;
    }

    //修改绑定记录
    @RequestMapping("/updateCouponInfo")
    @ResponseBody
    public RemoteResult updateCouponInfo(String couponId,String memberId, Integer integralNum, Integer state){
        LOGGER.info("updateCouponInfo Start:"+couponId+";"+memberId+";"+integralNum+";"+state);

        RemoteResult remoteResult = new RemoteResult();
        try {
            if (couponId==null || memberId==null || integralNum==null || state==null){
                remoteResult.setResultCode(ResultCode.PARAMS_FAIL);
                remoteResult.setResultMsg("参数不正确");
                LOGGER.info("updateCouponInfo End:"+ JacksonUtil.toJson(remoteResult));
                return remoteResult;
            }

            remoteResult = couponAndIntegralInfoService.updateCouponInfo(couponId, memberId, integralNum, state);
        }catch (Exception e){
            remoteResult.setResultMsg("系统异常");
            remoteResult.setResultCode(ResultCode.FAIL);
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("updateCouponInfo End:"+ JacksonUtil.toJson(remoteResult));
        return remoteResult;
    }

}
