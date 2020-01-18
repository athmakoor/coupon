package com.coupon.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coupon.bean.jpa.CouponCodeLanguageMappingEntity;
import com.coupon.bean.jpa.CouponDescriptionLanguageMappingEntity;
import com.coupon.bean.jpa.CouponEntity;

public class Coupon implements Serializable {
    private Map<String, String> code = new HashMap<>();
    private Map<String, String> description = new HashMap<>();
    private Double amount;

    public Coupon () {
        super();
    }

    public Coupon (CouponEntity entity) {
        super();
        List<CouponCodeLanguageMappingEntity> codes = entity.getListOfCouponCodes();
        List<CouponDescriptionLanguageMappingEntity> descs = entity.getListOfCouponDesc();

        for (CouponCodeLanguageMappingEntity code : codes) {
            this.code.put(code.getLanguage().name(), code.getCouponCode());
        }

        for (CouponDescriptionLanguageMappingEntity desc : descs) {
            this.description.put(desc.getLanguage().name(), desc.getCouponDesc());
        }

        this.amount = entity.getMaxDiscount();
    }

    public Map<String, String> getCode() {
        return code;
    }

    public void setCode(Map<String, String> code) {
        this.code = code;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
