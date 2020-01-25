package com.coupon.bean;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.coupon.constants.CouponType;
import com.coupon.constants.Currency;
import com.coupon.constants.Language;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CouponRequestDTO {
    private Currency currency;

    private CouponType coupon_type;

    private String item_type;

    private Double discount_percentage;

    private Boolean is_manual;

    private Double min_cart_value = 0.0;

    private Double max_discount;

    private Boolean is_mergeable;
    @NotBlank
    private Map<Language, String> code;
    @NotBlank
    private Map<Language, String> description;
    private List<RuleCalendarDTO> calendar_rules = new ArrayList<>();
    private List<RuleCategoryDTO> coupon_rules = new ArrayList<>();
    private List<RuleOfferDTO> offer_rules = new ArrayList<>();
    private List<RuleTransactionDTO> transaction_rules = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime started_on;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime closed_on;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public CouponType getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(CouponType coupon_type) {
        this.coupon_type = coupon_type;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public Double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(Double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public Boolean getIs_manual() {
        return is_manual;
    }

    public void setIs_manual(Boolean is_manual) {
        this.is_manual = is_manual;
    }

    public Double getMin_cart_value() {
        return min_cart_value;
    }

    public void setMin_cart_value(Double min_cart_value) {
        this.min_cart_value = min_cart_value;
    }

    public Double getMax_discount() {
        return max_discount;
    }

    public void setMax_discount(Double max_discount) {
        this.max_discount = max_discount;
    }

    public Boolean getIs_mergeable() {
        return is_mergeable;
    }

    public void setIs_mergeable(Boolean is_mergeable) {
        this.is_mergeable = is_mergeable;
    }

    public Map<Language, String> getCode() {
        return code;
    }

    public void setCode(Map<Language, String> code) {
        this.code = code;
    }

    public Map<Language, String> getDescription() {
        return description;
    }

    public void setDescription(Map<Language, String> description) {
        this.description = description;
    }

    public List<RuleCalendarDTO> getCalendar_rules() {
        return calendar_rules;
    }

    public void setCalendar_rules(List<RuleCalendarDTO> calendar_rules) {
        this.calendar_rules = calendar_rules;
    }

    public List<RuleCategoryDTO> getCoupon_rules() {
        return coupon_rules;
    }

    public void setCoupon_rules(List<RuleCategoryDTO> coupon_rules) {
        this.coupon_rules = coupon_rules;
    }

    public List<RuleOfferDTO> getOffer_rules() {
        return offer_rules;
    }

    public void setOffer_rules(List<RuleOfferDTO> offer_rules) {
        this.offer_rules = offer_rules;
    }

    public List<RuleTransactionDTO> getTransaction_rules() {
        return transaction_rules;
    }

    public void setTransaction_rules(List<RuleTransactionDTO> transaction_rules) {
        this.transaction_rules = transaction_rules;
    }

    public ZonedDateTime getStarted_on() {
        return started_on;
    }

    public void setStarted_on(ZonedDateTime started_on) {
        this.started_on = started_on;
    }

    public ZonedDateTime getClosed_on() {
        return closed_on;
    }

    public void setClosed_on(ZonedDateTime closed_on) {
        this.closed_on = closed_on;
    }
}
