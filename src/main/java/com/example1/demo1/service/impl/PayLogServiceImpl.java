package com.example1.demo1.service.impl;

import com.example1.demo1.bean.PayLog;
import com.example1.demo1.service.PayLogService;
import org.springframework.stereotype.Service;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 13:46
 */
@Service
public class PayLogServiceImpl implements PayLogService {
    @Override
    public PayLog getPayLogFromRedis(String userId) {
        //从redis中获取
        return null;
    }
}
