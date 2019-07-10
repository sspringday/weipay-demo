package com.example1.demo1.weixinpay;

import java.util.Map;

/**
 * @author 程二狗
 * @since 2019/7/9 0009 23:32
 */
public interface WeiXinPayService {

    /**
     * 生成订单号
     * @param outTradeNo
     * @param totalFee
     * @return
     */
    Map createNative(String outTradeNo,String totalFee);

    Map queryStatus(String outTradeNo);

}
