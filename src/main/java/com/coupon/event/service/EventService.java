package com.coupon.event.service;

import com.coupon.event.bean.EventRequest;
import com.coupon.event.bean.EventResponse;

public interface EventService {
    EventResponse saveEvent(EventRequest eventRequest);
}
