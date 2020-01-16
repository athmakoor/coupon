package com.coupon.event.bean.jpa;

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

import com.coupon.event.constants.EventStatus;
import com.coupon.event.constants.EventType;

@Entity
@Table(name = "static_event_field")
public class StaticEventFieldEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "event_code_ref", length = 5, nullable = false)
    private String eventCodeRef;

    @Column(name = "field_name", length = 50, nullable = false)
    private String fieldName;

    @Column(name = "field_description", length = 200, nullable = false)
    private String fieldDescription;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "mandatory", nullable = false)
    private Boolean mandatory;

    @Column(name="inserted_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventCodeRef() {
        return eventCodeRef;
    }

    public void setEventCodeRef(String eventCodeRef) {
        this.eventCodeRef = eventCodeRef;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Date getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(Date insertedOn) {
        this.insertedOn = insertedOn;
    }
}
