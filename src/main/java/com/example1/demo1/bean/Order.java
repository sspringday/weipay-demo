package com.example1.demo1.bean;

import java.io.Serializable;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 13:09
 */
public class Order implements Serializable {
    private Byte paymentType;
    private Byte status;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }
}
