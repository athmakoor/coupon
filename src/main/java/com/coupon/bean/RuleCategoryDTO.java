package com.coupon.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class RuleCategoryDTO implements Serializable {
    @NotBlank
    private String category_name;

    private Integer quantity;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
