package com.example1.demo1.service;

import com.example1.demo1.bean.Order;
import com.example1.demo1.bean.PayLog;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 13:03
 */
public interface OrderService {
    /**
     * 生成订单
     * @param order
     */
    void add(Order order);

    /**
     * 支付成功后修改订单状态和支付日志状态
     * @param outTradeNo
     * @param transactionId 流水号，微信返回
     */
    void updateOrderStatus(String outTradeNo,String transactionId);
}
