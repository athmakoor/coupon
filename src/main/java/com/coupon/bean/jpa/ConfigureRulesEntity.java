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

import com.coupon.constants.Relation;

@Entity
@Table(name = "configure_rules")
public class ConfigureRulesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "rule_id", nullable = false)
    private Integer ruleId;

    @Column(name = "relation", nullable = false)
    @Enumerated(EnumType.STRING)
    private Relation relation;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
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
}
