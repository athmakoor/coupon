package com.coupon.bean;

import java.util.HashMap;
import java.util.Map;

import com.coupon.constants.CalenderType;
import com.coupon.constants.Relation;

public class RuleTransactionDTO {
    private CalenderType cal_type;

    private Boolean exact_match;

    private String type_from;

    private String type_to;

    private Relation relation;

    Map<String, String> fields = new HashMap<>();

    public CalenderType getCal_type() {
        return cal_type;
    }

    public void setCal_type(CalenderType cal_type) {
        this.cal_type = cal_type;
    }

    public Boolean getExact_match() {
        return exact_match;
    }

    public void setExact_match(Boolean exact_match) {
        this.exact_match = exact_match;
    }

    public String getType_from() {
        return type_from;
    }

    public void setType_from(String type_from) {
        this.type_from = type_from;
    }

    public String getType_to() {
        return type_to;
    }

    public void setType_to(String type_to) {
        this.type_to = type_to;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
