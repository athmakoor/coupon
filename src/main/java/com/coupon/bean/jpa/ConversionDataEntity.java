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

import com.coupon.bean.ConversionRequest;
import com.coupon.constants.Status;

@Entity
@Table(name = "conversion_data")
public class ConversionDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "txn_id", nullable = false)
    private String txnId;

    @Column(name = "status_code", nullable = false)
    private String statusCode;

    @Column(name = "msg")
    private String msg;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "type")
    private String type;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private Double value;

    @Column(name = "invoice_amount", nullable = false)
    private Double invoiceAmount;

    @Column(name="inserted_date")
    @Temporal(TemporalType.DATE)
    private Date insertedDate;

    @Column(name="inserted_time")
    @Temporal(TemporalType.TIME)
    private Date insertedTime;

    public ConversionDataEntity () {
        super();
    }

    public ConversionDataEntity(ConversionRequest data) {
        Date date = new Date();
        this.code = data.getConversion_data().getCode();
        this.insertedDate = date;
        this.insertedTime = date;
        this.msg = data.getMsg();
        this.invoiceAmount = data.getConversion_data().getInvoice_amount();
        this.value = data.getConversion_data().getValue();
        this.type = data.getConversion_data().getType();
        this.status = Status.success;
        this.statusCode = data.getStatus_code();
        this.userId = data.getConversion_data().getUser_id();
        this.txnId = data.getTxn_id();
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
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
