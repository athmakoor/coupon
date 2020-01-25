package com.coupon.bean.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coupon.constants.CouponType;
import com.coupon.constants.Currency;

@Entity
@Table(name = "coupon")
public class CouponEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "coupon_type")
    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "is_manual")
    private Boolean isManual;

    @Column(name = "min_cart_value")
    private Double minCartValue;

    @Column(name = "max_discount")
    private Double maxDiscount;

    @Column(name = "is_mergeable")
    private Boolean isMergeable;

    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name="started_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedOn;

    @Column(name="closed_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedOn;

    @Column(name="active")
    private Boolean active = true;

    @OneToMany(mappedBy = "couponEntity", targetEntity = CouponProductRestrictionsEntity.class)
    private List<CouponProductRestrictionsEntity> couponProductRestrictionsEntities;

    @OneToMany(mappedBy = "couponEntity", targetEntity = RuleCalendarMappingEntity.class)
    private List<RuleCalendarMappingEntity> ruleCalendarMappingEntities;

    @OneToMany(mappedBy = "couponEntity", targetEntity = RuleCategoryMappingEntity.class)
    private List<RuleCategoryMappingEntity> ruleCategoryMappingEntities;

    @OneToMany(mappedBy = "couponEntity", targetEntity = RuleOfferMappingEntity.class)
    private List<RuleOfferMappingEntity> ruleOfferMappingEntities;

    @OneToMany(mappedBy = "couponEntity", targetEntity = RuleTransactionMappingEntity.class)
    private List<RuleTransactionMappingEntity> ruleTransactionMappingEntities;

    @OneToMany(mappedBy = "couponEntity", targetEntity = CouponCodeLanguageMappingEntity.class)
    private List<CouponCodeLanguageMappingEntity> listOfCouponCodes;

    @OneToMany(mappedBy = "couponEntity", targetEntity = CouponDescriptionLanguageMappingEntity.class)
    private List<CouponDescriptionLanguageMappingEntity> listOfCouponDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getManual() {
        if (isManual == null) {
            return true;
        }

        return isManual;
    }

    public void setManual(Boolean manual) {
        isManual = manual;
    }

    public Double getMinCartValue() {
        return minCartValue;
    }

    public void setMinCartValue(Double minCartValue) {
        this.minCartValue = minCartValue;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public Boolean getMergeable() {
        if (isMergeable == null) {
            return false;
        }

        return isMergeable;
    }

    public void setMergeable(Boolean mergeable) {
        isMergeable = mergeable;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public Date getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Date closedOn) {
        this.closedOn = closedOn;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<CouponCodeLanguageMappingEntity> getListOfCouponCodes() {
        return listOfCouponCodes;
    }

    public List<CouponProductRestrictionsEntity> getCouponProductRestrictionsEntities() {
        return couponProductRestrictionsEntities;
    }

    public void setCouponProductRestrictionsEntities(List<CouponProductRestrictionsEntity> couponProductRestrictionsEntities) {
        this.couponProductRestrictionsEntities = couponProductRestrictionsEntities;
    }

    public List<RuleCalendarMappingEntity> getRuleCalendarMappingEntities() {
        return ruleCalendarMappingEntities;
    }

    public void setRuleCalendarMappingEntities(List<RuleCalendarMappingEntity> ruleCalendarMappingEntities) {
        this.ruleCalendarMappingEntities = ruleCalendarMappingEntities;
    }

    public List<RuleCategoryMappingEntity> getRuleCategoryMappingEntities() {
        return ruleCategoryMappingEntities;
    }

    public void setRuleCategoryMappingEntities(List<RuleCategoryMappingEntity> ruleCategoryMappingEntities) {
        this.ruleCategoryMappingEntities = ruleCategoryMappingEntities;
    }

    public List<RuleOfferMappingEntity> getRuleOfferMappingEntities() {
        return ruleOfferMappingEntities;
    }

    public void setRuleOfferMappingEntities(List<RuleOfferMappingEntity> ruleOfferMappingEntities) {
        this.ruleOfferMappingEntities = ruleOfferMappingEntities;
    }

    public List<RuleTransactionMappingEntity> getRuleTransactionMappingEntities() {
        return ruleTransactionMappingEntities;
    }

    public void setRuleTransactionMappingEntities(List<RuleTransactionMappingEntity> ruleTransactionMappingEntities) {
        this.ruleTransactionMappingEntities = ruleTransactionMappingEntities;
    }

    public void setListOfCouponCodes(List<CouponCodeLanguageMappingEntity> listOfCouponCodes) {
        this.listOfCouponCodes = listOfCouponCodes;
    }

    public List<CouponDescriptionLanguageMappingEntity> getListOfCouponDesc() {
        return listOfCouponDesc;
    }

    public void setListOfCouponDesc(List<CouponDescriptionLanguageMappingEntity> listOfCouponDesc) {
        this.listOfCouponDesc = listOfCouponDesc;
    }

    public Double getMinCartValueToShowCoupon() {
        if (this.minCartValue == null) {
            return 0.0;
        }

        return this.minCartValue * 0.2;
    }
}
