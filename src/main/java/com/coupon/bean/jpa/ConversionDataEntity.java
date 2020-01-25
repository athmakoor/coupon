package com.coupon.bean.jpa;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coupon.bean.ConversionRequest;
import com.coupon.constants.Status;
import com.coupon.utils.TimeUtil;
import com.coupon.utils.UTCDateTimeConverter;

@Entity
@Table(name = "conversion_data")
public class ConversionDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "msg")
    private String msg;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "coupons_used")
    private String couponsUsed;

    @Column(name = "rewards_used")
    private Double rewardsUsed;

    @ManyToOne
    @JoinColumn(name = "cart_id_ref", referencedColumnName = "id")
    private CartDataEntity cartDataEntity;

    @Column(name="created_on")
    @Convert(converter = UTCDateTimeConverter.class)
    private ZonedDateTime createdOn;

    public ConversionDataEntity () {
        super();
    }

    public ConversionDataEntity(ConversionRequest data, Integer cartDataId) {
        CartDataEntity cartDataEntity1 = new CartDataEntity();

        cartDataEntity1.setId(cartDataId);
        this.msg = data.getMsg();
        this.status = Status.success;
        this.createdOn = TimeUtil.getCurrentUTCTime();
        this.cartDataEntity = cartDataEntity1;
        this.couponsUsed = data.getCoupon_codes();
        this.rewardsUsed = data.getRewards_used();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCouponsUsed() {
        return couponsUsed;
    }

    public void setCouponsUsed(String couponsUsed) {
        this.couponsUsed = couponsUsed;
    }

    public Double getRewardsUsed() {
        return rewardsUsed;
    }

    public void setRewardsUsed(Double rewardsUsed) {
        this.rewardsUsed = rewardsUsed;
    }

    public CartDataEntity getCartDataEntity() {
        return cartDataEntity;
    }

    public void setCartDataEntity(CartDataEntity cartDataEntity) {
        this.cartDataEntity = cartDataEntity;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
