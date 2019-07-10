package com.example1.demo1.controller;

import com.example1.demo1.bean.PayLog;
import com.example1.demo1.bean.Result;
import com.example1.demo1.service.OrderService;
import com.example1.demo1.service.PayLogService;
import com.example1.demo1.weixinpay.WeiXinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 10:18
 */
@Controller
public class WeiXinPayController {
    @Autowired
    OrderService orderService;

    @Autowired
    private WeiXinPayService weiXinPayService;
    @Autowired
    private PayLogService payLogService;

    public Map createNative(){
        //1.获取当前登录用户
        //2.提取支付日志（redis)
        PayLog logFromRedis = payLogService.getPayLogFromRedis("123");
        if (null != logFromRedis){
            String s = UUID.randomUUID().toString().substring(0, 12).replaceAll("-", "");
            return weiXinPayService.createNative(s,logFromRedis.getTotalFee() + "");
        } else {
            return  new HashMap();
        }

    }

    @RequestMapping("/queryStatus")
    public Result queryPayStatus(String outTradeNo){
        Result result = null;
        int x = 0;
        while (true){
            Map<String,String> map = weiXinPayService.queryStatus(outTradeNo);//调用查询
            System.out.println("查询支付结果。。。");
            if (map == null){
                result = new Result(400,"支付失败");
                break;
            } else {
                if ("SUCCESS".equals(map.get("result_code"))){
                    result = new Result(200,"支付成功");
                    orderService.updateOrderStatus(outTradeNo,map.get("transaction_id"));//修改订单状态
                    break;
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //限定支付时间  5m，前端需要重新生成二维码
            x++;
            if (x >= 100){
                result = new Result(400,"二维码超时");
                break;
            }
        }
        return result;
    }
}
