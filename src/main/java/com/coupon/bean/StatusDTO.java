package com.coupon.bean;

import java.io.Serializable;

public class StatusDTO implements Serializable {
    private String status;
    private Integer statusCode;
    private String description;

    public StatusDTO (Integer statusCode, String status, String description) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
