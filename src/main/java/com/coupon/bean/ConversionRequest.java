package com.coupon.bean;

import java.io.Serializable;
import java.util.List;

public class ConversionRequest implements Serializable {
    private String txn_id;
    private String msg;
    private String status_code;
    private ConversionData conversion_data;
    private List<CartItem> cart_data;

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public ConversionData getConversion_data() {
        return conversion_data;
    }

    public void setConversion_data(ConversionData conversion_data) {
        this.conversion_data = conversion_data;
    }

    public List<CartItem> getCart_data() {
        return cart_data;
    }

    public void setCart_data(List<CartItem> cart_data) {
        this.cart_data = cart_data;
    }
}
