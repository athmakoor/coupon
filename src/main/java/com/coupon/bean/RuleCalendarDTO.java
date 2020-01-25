package com.coupon.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.coupon.constants.CalenderType;
import com.coupon.constants.Relation;

public class RuleCalendarDTO implements Serializable {
    @NotBlank
    private CalenderType cal_type;

    private Boolean exact_match;

    private String type_from;

    private String type_to;

    private Integer limit_per_user;

    private Integer coupon_limit;

    private Relation relation;

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

    public Integer getLimit_per_user() {
        return limit_per_user;
    }

    public void setLimit_per_user(Integer limit_per_user) {
        this.limit_per_user = limit_per_user;
    }

    public Integer getCoupon_limit() {
        return coupon_limit;
    }

    public void setCoupon_limit(Integer coupon_limit) {
        this.coupon_limit = coupon_limit;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
