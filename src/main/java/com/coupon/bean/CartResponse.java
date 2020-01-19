package com.coupon.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartResponse implements Serializable {
    private String status;
    private String message;
    private Integer status_code;
    private String txnId;
    private List<Coupon> coupons = new ArrayList<>();
    private List<Coupon> discounts = new ArrayList<>();
    private List<Referral> referrals;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Coupon> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Coupon> discounts) {
        this.discounts = discounts;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<Referral> referrals) {
        this.referrals = referrals;
    }

    public void setCouponsAndDiscounts(List<Coupon> coupons) {
        for (Coupon coupon : coupons) {
            if (coupon.getIs_manual()) {
                this.coupons.add(coupon);
            } else {
                this.discounts.add(coupon);
            }
        }
    }
}
