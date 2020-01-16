package com.coupon.event.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class EventRequest implements Serializable {
    private String userId;
    private String eventCode;
    private String txnId;
    private List<Map<String, Object>> fields;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public List<Map<String, Object>> getFields() {
        return fields;
    }

    public void setFields(List<Map<String, Object>> fields) {
        this.fields = fields;
    }
}
