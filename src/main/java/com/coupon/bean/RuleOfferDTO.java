package com.coupon.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class RuleOfferDTO implements Serializable {
    @NotBlank
    private String buy_skus;

    private String offer_skus;

    @NotBlank
    private Integer buy_quantity;

    private Integer offer_quantity;

    private String offer_description;

    public String getBuy_skus() {
        return buy_skus;
    }

    public void setBuy_skus(String buy_skus) {
        this.buy_skus = buy_skus;
    }

    public String getOffer_skus() {
        return offer_skus;
    }

    public void setOffer_skus(String offer_skus) {
        this.offer_skus = offer_skus;
    }

    public Integer getBuy_quantity() {
        return buy_quantity;
    }

    public void setBuy_quantity(Integer buy_quantity) {
        this.buy_quantity = buy_quantity;
    }

    public Integer getOffer_quantity() {
        return offer_quantity;
    }

    public void setOffer_quantity(Integer offer_quantity) {
        this.offer_quantity = offer_quantity;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public void setOffer_description(String offer_description) {
        this.offer_description = offer_description;
    }
}
