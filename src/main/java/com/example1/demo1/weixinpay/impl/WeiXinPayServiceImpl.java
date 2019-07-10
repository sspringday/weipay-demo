package com.example1.demo1.weixinpay.impl;

import com.example1.demo1.configuration.MyWXPayConfig;
import com.example1.demo1.service.OrderService;
import com.example1.demo1.util.HttpUrlConnectionUtils;
import com.example1.demo1.weixinpay.WeiXinPayService;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付通用模块，是不需要连接数据库的
 * @author 程二狗
 * @since 2019/7/9 0009 23:34
 */
@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {


    @Override
    public Map createNative(String outTradeNo, String totalFee) {
        MyWXPayConfig config = new MyWXPayConfig();
        //1.参数封装
        Map param = new HashMap();
        param.put("appid", config.getAppID());
        param.put("mch_id", config.getMchID());
        param.put("nonce_str", WXPayUtil.generateNonceStr());
        param.put("body", "程二狗商铺");
        param.put("out_trade_no", outTradeNo);
        param.put("total_fee", totalFee);
        param.put("spbill_create_ip", "127.0.0.1");
        param.put("notify_url", "http://www.baidu.com");//模式2下没有用
        param.put("trade_type", "NATIVE");

        try {
            String signedXml = WXPayUtil.generateSignedXml(param, config.getKey(), WXPayConstants.SignType.MD5);
            //2.发送请求
            //3.获取结果
            String xmlResult = HttpUrlConnectionUtils.sendGetRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", signedXml);
            Map<String, String> stringStringMap = WXPayUtil.xmlToMap(xmlResult);


            Map map = new HashMap();
            map.put("codeUrl", stringStringMap.get("code_url"));
            map.put("outTradeNo", outTradeNo);
            map.put("totalFee",totalFee);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    /**
     * 怎么知道扫码后订单支付成功了呢？
     * 1.后端循环调用后端（前端去调，但服务器压力很大）
     * 2.后端循环调用微信支付查询结果，前端调用后端（用这种方式）
     * @param outTradeNo
     * @return
     */
    @Override
    public Map queryStatus(String outTradeNo) {
        MyWXPayConfig config = new MyWXPayConfig();
        //1.封装参数
        Map param = new HashMap();
        param.put("appid",config.getAppID());
        param.put("mch_id",config.getMchID());
        param.put("out_trade_no",outTradeNo);
        param.put("nonce_str",WXPayUtil.generateNonceStr());

        param.put("sign","");
        try {
            String signedXml = WXPayUtil.generateSignedXml(param, config.getKey(), WXPayConstants.SignType.MD5);
            //2.发送请求
            //3.获取结果
            String xmlResult = HttpUrlConnectionUtils.sendGetRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", signedXml);
            Map<String, String> stringStringMap = WXPayUtil.xmlToMap(xmlResult);
            return  stringStringMap;
        } catch (Exception e) {
            e.printStackTrace();
            return  new HashMap();
        }
    }
}
