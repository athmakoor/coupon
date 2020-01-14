package com.coupon.bean;

import java.io.Serializable;
import java.util.List;

public class ConversionRequest implements Serializable {
    private String txnId;
    private String msg;
    private String statusCode;
    private ConversionData conversionData;
    private List<CartItem> cartData;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public ConversionData getConversionData() {
        return conversionData;
    }

    public void setConversionData(ConversionData conversionData) {
        this.conversionData = conversionData;
    }

    public List<CartItem> getCartData() {
        return cartData;
    }

    public void setCartData(List<CartItem> cartData) {
        this.cartData = cartData;
    }
}
