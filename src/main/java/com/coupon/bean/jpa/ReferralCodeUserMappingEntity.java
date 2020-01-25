package com.coupon.bean.jpa;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coupon.utils.UTCDateTimeConverter;

@Entity
@Table(name = "referral_code_user_mapping")
public class ReferralCodeUserMappingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name="referrer_code")
    private String referrerCode;

    @Column(name="user_referrer_code")
    private String userReferrerCode;

    @Column(name="created_on")
    @Convert(converter = UTCDateTimeConverter.class)
    private ZonedDateTime createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReferrerCode() {
        return referrerCode;
    }

    public void setReferrerCode(String referrerCode) {
        this.referrerCode = referrerCode;
    }

    public String getUserReferrerCode() {
        return userReferrerCode;
    }

    public void setUserReferrerCode(String userReferrerCode) {
        this.userReferrerCode = userReferrerCode;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
