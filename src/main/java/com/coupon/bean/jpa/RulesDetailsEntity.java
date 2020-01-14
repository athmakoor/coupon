package com.coupon.bean.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coupon.constants.Condition;

@Entity
@Table(name = "rules_details")
public class RulesDetailsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", nullable = false)
    private Integer ruleId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "cond", nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition cond;

    @Column(name="inserted_date")
    @Temporal(TemporalType.DATE)
    private Date insertedDate;

    @Column(name="inserted_time")
    @Temporal(TemporalType.TIME)
    private Date insertedTime;

    @Column(name="updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    @Column(name="updated_time")
    @Temporal(TemporalType.TIME)
    private Date updatedTime;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
