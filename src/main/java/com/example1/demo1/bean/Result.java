package com.example1.demo1.bean;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 11:21
 */
public class Result {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result() {
    }
}
