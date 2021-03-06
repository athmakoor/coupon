package com.coupon.bean.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coupon.constants.Language;

@Entity
@Table(name = "referral_coupon_description_language_mapping")
public class ReferralCouponDescriptionLanguageMappingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "referral_coupon_id_ref", referencedColumnName = "id")
    private ReferralCouponEntity referralCouponEntity;

    @Column(name = "language", nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "coupon_desc", nullable = false)
    private String couponDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReferralCouponEntity getReferralCouponEntity() {
        return referralCouponEntity;
    }

    public void setReferralCouponEntity(ReferralCouponEntity referralCouponEntity) {
        this.referralCouponEntity = referralCouponEntity;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }
}
