package com.coupon.bean;

import java.io.Serializable;
import java.util.List;

public class CartResponse implements Serializable {
    private String status;
    private String message;
    private Integer status_code;
    private String txnId;
    private List<Coupon> coupons;
    private List<Discount> discounts;
    private List<Referral> referrals;
    private List<LoyalityPoint> loyality_points;

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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<Referral> referrals) {
        this.referrals = referrals;
    }

    public List<LoyalityPoint> getLoyality_points() {
        return loyality_points;
    }

    public void setLoyality_points(List<LoyalityPoint> loyality_points) {
        this.loyality_points = loyality_points;
    }
}
