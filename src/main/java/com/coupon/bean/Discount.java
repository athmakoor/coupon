package com.coupon.bean;

import java.io.Serializable;

import com.coupon.bean.jpa.DiscountDetailsTableEntity;

public class Discount implements Serializable {
    private String code;
    private String description;
    private Double discount;
    private Double maxDiscountAmount;

    public Discount () {
        super();
    }

    public Discount (DiscountDetailsTableEntity entity) {
        super();
        this.code = entity.getDiscountDesc();
        this.description = entity.getDiscountDesc();
        this.discount = entity.getDiscountPercentage();
        this.maxDiscountAmount = entity.getMaxDiscountAmt();
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public void setMaxDiscountAmount(Double maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }
}
