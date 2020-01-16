package com.coupon.event.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.event.bean.EventRequest;
import com.coupon.event.bean.EventResponse;
import com.coupon.event.service.EventService;

@RestController
public class EventController {
    @Resource
    private EventService eventService;

    @PostMapping("/v1/eventapi")
    EventResponse requestInfo(@RequestBody final EventRequest requestBody) {
        return eventService.saveEvent(requestBody);
    }
}
