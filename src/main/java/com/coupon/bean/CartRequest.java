package com.coupon.bean;

import java.io.Serializable;
import java.util.List;

public class CartRequest implements Serializable {
    private String txnId;
    private List<String> fields;
    private UserData userData;
    private List<CartItem> cartData;

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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<CartItem> getCartData() {
        return cartData;
    }

    public void setCartData(List<CartItem> cartData) {
        this.cartData = cartData;
    }
}
