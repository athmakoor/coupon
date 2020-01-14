package com.coupon.bean;

import java.io.Serializable;

public class ConversionResponse implements Serializable {
    private String status;
    private String txnId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
