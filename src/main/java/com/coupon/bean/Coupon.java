package com.coupon.bean;

import java.io.Serializable;

import com.coupon.bean.jpa.CouponEntity;

public class Coupon implements Serializable {
    private String code;
    private String description;
    private Double amount;

    public Coupon () {
        super();
    }

    public Coupon (CouponEntity entity) {
        super();
        /*this.code = entity.getCouponCode();
        this.description = entity.getCouponDesc();
        this.amount = entity.getCouponValue();*/
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
