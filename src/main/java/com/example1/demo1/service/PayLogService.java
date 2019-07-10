package com.example1.demo1.service;

import com.example1.demo1.bean.PayLog;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 13:45
 */
public interface PayLogService  {
    PayLog getPayLogFromRedis(String userId);
}
