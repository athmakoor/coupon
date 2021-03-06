package com.coupon.event.bean.jpa;

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
import javax.persistence.Table;

import com.coupon.event.constants.EventStatus;
import com.coupon.utils.UTCDateTimeConverter;

@Entity
@Table(name = "custom_events")
public class CustomEventsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "event_code", unique = true, length = 5, nullable = false)
    private String eventCode;

    @Column(name = "event_name", length = 50, nullable = false)
    private String eventName;

    @Column(name = "event_description", length = 200, nullable = false)
    private String eventDescription;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name="inserted_on")
    @Convert(converter = UTCDateTimeConverter.class)
    private ZonedDateTime insertedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public ZonedDateTime getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(ZonedDateTime insertedOn) {
        this.insertedOn = insertedOn;
    }
}
