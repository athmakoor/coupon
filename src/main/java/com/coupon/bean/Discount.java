package com.coupon.bean;

import java.io.Serializable;

public class Discount implements Serializable {
    private String code;
    private String description;
    private Double discount;
    private Double max_discount_amount;

    public Discount () {
        super();
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

    public Double getMax_discount_amount() {
        return max_discount_amount;
    }

    public void setMax_discount_amount(Double max_discount_amount) {
        this.max_discount_amount = max_discount_amount;
    }
}
