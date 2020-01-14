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

import com.coupon.bean.CartItem;
import com.coupon.bean.ConversionRequest;

@Entity
@Table(name = "converstion_cart_data")
public class ConverstionCartDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name="inserted_date")
    @Temporal(TemporalType.DATE)
    private Date insertedDate;

    @Column(name="inserted_time")
    @Temporal(TemporalType.TIME)
    private Date insertedTime;

    public ConverstionCartDataEntity () {
        super();
    }

    public ConverstionCartDataEntity(CartItem item, ConversionRequest data) {
        Date date = new Date();
        this.amount = item.getAmount();
        this.category = item.getCategory();
        this.itemName = item.getItemName();
        this.quantity = item.getQuantity();
        this.type = item.getType();
        this.txnId = data.getTxnId();
        this.userId = data.getConversionData().getUserId();
        this.insertedDate = date;
        this.insertedTime = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Date getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(Date insertedTime) {
        this.insertedTime = insertedTime;
    }
}
