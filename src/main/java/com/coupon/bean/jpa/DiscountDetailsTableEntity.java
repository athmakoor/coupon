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
@Table(name = "discount_details_table")
public class DiscountDetailsTableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "discount_desc", nullable = false)
    private String discountDesc;

    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;

    @Column(name = "max_discount_amt", nullable = false)
    private Double maxDiscountAmt;

    @Column(name="started_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedOn;

    @Column(name="closed_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closer_on;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getMaxDiscountAmt() {
        return maxDiscountAmt;
    }

    public void setMaxDiscountAmt(Double maxDiscountAmt) {
        this.maxDiscountAmt = maxDiscountAmt;
    }

    public Date getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public Date getCloser_on() {
        return closer_on;
    }

    public void setCloser_on(Date closer_on) {
        this.closer_on = closer_on;
    }
}
