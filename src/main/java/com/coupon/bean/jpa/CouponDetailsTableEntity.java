package com.coupon.bean.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "coupon_details_table")
public class CouponDetailsTableEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    @Column(name = "coupon_desc", nullable = false)
    private String couponDesc;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "coupon_value", nullable = false)
    private Double couponValue;

    @Column(name="started_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedOn;

    @Column(name="closed_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Double couponValue) {
        this.couponValue = couponValue;
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
}
