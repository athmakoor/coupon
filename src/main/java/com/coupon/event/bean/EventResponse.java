package com.coupon.event.bean;

import java.io.Serializable;

import com.coupon.bean.StatusDTO;

public class EventResponse implements Serializable {
    private String status;
    private Integer statusCode;
    private String msg;

    public EventResponse(StatusDTO statusDTO) {
        super();
        this.status = statusDTO.getStatus();
        this.statusCode =statusDTO.getStatusCode();
        this.msg = statusDTO.getDescription();
    }

    public EventResponse() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
