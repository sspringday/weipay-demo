package com.example1.demo1.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 13:10
 */
public class Cart implements Serializable {
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
