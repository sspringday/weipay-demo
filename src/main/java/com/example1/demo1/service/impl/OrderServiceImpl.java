package com.example1.demo1.service.impl;

import com.example1.demo1.bean.Cart;
import com.example1.demo1.bean.Order;
import com.example1.demo1.bean.OrderItem;
import com.example1.demo1.bean.PayLog;
import com.example1.demo1.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 13:03
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public void updateOrderStatus(String outTradeNo, String transactionId) {
        //1.修改支付的日志的状态和及相关字段
        //数据库查找支付日志，根据outTradeNo（主键）查找
        PayLog payLog = new PayLog();
        payLog.setPayTime(new Date());
        payLog.setTradeState("1");//交易成功
        payLog.setTransactionId(transactionId);
        //修改数据库

        //2.修改订单表的状态
        String orderIds = payLog.getOrderIds();//订单ID串
        String[] split = orderIds.split(",");
        for (String s : split) {
            //查询订单
            Order order = new Order();
            order.setStatus((byte)2);//已付款状态
            //更新数据库
        }


        //3.清除缓存中的payLog
        //清除redis缓存
    }

    @Override
    public void add(Order order) {
        List<String> orderIds = new ArrayList<>();
        //1.从redis中提取购物车列表
        List<Cart> carts = new ArrayList<>();
        //2.循环购物车列表添加订单
        for (Cart cart : carts) {
            Order order1 = new Order();
            //循环购物车每条明细记录，计算合计金额
            for (OrderItem orderItem : cart.getOrderItemList()) {
                
            }
            //4.插入订单
        }
        //添加支付日志
        Byte paymenttype = 1;
        if(paymenttype.equals(order.getPaymentType())){
            PayLog log = new PayLog();
            //插入日志
            //把支付日志放入放入缓存 hash key是userId
        }

        //4.清除redis中的购物车

    }
}
