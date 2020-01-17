package com.coupon.bean;

import java.io.Serializable;
import java.util.List;

public class CartRequest implements Serializable {
    private String txnId;
    private List<String> fields;
    private UserData user_data;
    private List<CartItem> cart_data;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public UserData getUser_data() {
        return user_data;
    }

    public void setUser_data(UserData user_data) {
        this.user_data = user_data;
    }

    public List<CartItem> getCart_data() {
        return cart_data;
    }

    public void setCart_data(List<CartItem> cart_data) {
        this.cart_data = cart_data;
    }
}
