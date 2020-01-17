package com.coupon.bean;

import java.io.Serializable;

public class ConversionResponse implements Serializable {
    private String status;
    private String txn_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }
}
