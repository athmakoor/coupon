package com.coupon.bean;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String sku;
    private String category;
    private String type;
    private String item_name;
    private Double amount;
    private Integer quantity;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        if (quantity == null) {
            return 1;
        }

        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CartItem clone() {
        CartItem item = new CartItem();

        item.setAmount(this.getAmount());
        item.setCategory(this.getCategory());
        item.setItem_name(this.getItem_name());
        item.setQuantity(this.getQuantity());
        item.setSku(this.getSku());
        item.setType(this.getType());

        return item;
    }
}
