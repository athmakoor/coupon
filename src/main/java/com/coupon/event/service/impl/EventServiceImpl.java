package com.coupon.event.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupon.constants.EventStatusCodes;
import com.coupon.event.bean.EventRequest;
import com.coupon.event.bean.EventResponse;
import com.coupon.event.bean.jpa.EventEntity;
import com.coupon.event.bean.jpa.EventFieldsEntity;
import com.coupon.event.bean.jpa.CustomEventsEntity;
import com.coupon.event.bean.jpa.CustomEventFieldEntity;
import com.coupon.event.constants.EventStatus;
import com.coupon.event.constants.EventType;
import com.coupon.event.repository.EventFieldRepository;
import com.coupon.event.repository.EventRepository;
import com.coupon.event.repository.StaticCustomEventRepository;
import com.coupon.event.repository.StaticEventFieldRepository;
import com.coupon.event.service.EventService;

@Service
public class EventServiceImpl implements EventService {
    @Resource
    private EventRepository eventRepository;
    @Resource
    private EventFieldRepository eventFieldRepository;
    @Resource
    private StaticCustomEventRepository staticCustomEventRepository;
    @Resource
    private StaticEventFieldRepository staticEventFieldRepository;

    @Transactional
    @Override
    public EventResponse saveEvent(EventRequest eventRequest) {
        if (eventRequest.getEvent_code() == null) {
            return new EventResponse(EventStatusCodes.VALUES.get(EventStatusCodes.EVENT_CODE_MISSING));
        }

        if (!eventRepository.findByTxnId(eventRequest.getTxn_id()).isEmpty()) {
            return new EventResponse(EventStatusCodes.VALUES.get(EventStatusCodes.DUPLICATE_TRANSACTION_ID));
        }

        Map<String, CustomEventsEntity> staticCustomEventEntityMap = getStaticEventsAsMap();
        Iterable<CustomEventFieldEntity> staticEventFieldEntities = staticEventFieldRepository.findByEventCodeRefAndStatus(eventRequest.getEvent_code(), EventStatus.active);
        Map<String, CustomEventFieldEntity> mandatoryStaticFields = getMandatoryStaticEventFieldsAsMap(staticEventFieldEntities);
        EventResponse response = new EventResponse(EventStatusCodes.VALUES.get(EventStatusCodes.SUCCESS));
        List<String> missingFields = checkAllMandatoryFields(eventRequest.getFields(), mandatoryStaticFields);

        if (!missingFields.isEmpty()) {
            response = new EventResponse(EventStatusCodes.VALUES.get(EventStatusCodes.MANDATORY_EVENT_FIELDS_MISSING));
            String strList = missingFields.toString();

            strList = strList.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");
            response.setMsg(response.getMsg().replace("%fields%", strList));
        }



        if (!staticCustomEventEntityMap.containsKey(eventRequest.getEvent_code())) {
            return new EventResponse(EventStatusCodes.VALUES.get(EventStatusCodes.INVALID_EVENT_CODE));
        }

        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventCode(eventRequest.getEvent_code());
        eventEntity.setTxnId(eventRequest.getTxn_id());
        eventEntity.setUserId(eventRequest.getUser_id());

        EventFieldsEntity eventFieldEntity;
        List<EventFieldsEntity> eventFieldEntityList = new ArrayList<>();

        EventEntity savedEventEntity = eventRepository.save(eventEntity);
        EventEntity tempEntity = new EventEntity();
        tempEntity.setId(savedEventEntity.getId());

        for (Map<String, Object> map: eventRequest.getFields()) {
            if (map.keySet().size() != 1) {
                response = new EventResponse(EventStatusCodes.VALUES.get(EventStatusCodes.INVALID_FIELD_VALUES));
                break;
            }

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (!validateFieldValue(entry.getKey(), entry.getValue(), staticEventFieldEntities)) {
                    response = new EventResponse(EventStatusCodes.VALUES.get(EventStatusCodes.INVALID_FIELD_VALUES));
                }

                eventFieldEntity = new EventFieldsEntity();
                eventFieldEntity.setFieldName(entry.getKey());
                eventFieldEntity.setFieldValue(entry.getValue().toString());
                eventFieldEntity.setIdRef(tempEntity);
                eventFieldEntityList.add(eventFieldEntity);
            }
        }

        if (!"error".equals(response.getStatus())) {
            eventFieldRepository.saveAll(eventFieldEntityList);
        }

        return response;
    }

    private List<String> checkAllMandatoryFields(List<Map<String, Object>> fields, Map<String, CustomEventFieldEntity> mandatoryStaticFields) {
        List<String> missingFields = new ArrayList<>();
        List<String> fieldNamesSent = new ArrayList<>();

        for (Map<String, Object> map : fields) {
            fieldNamesSent.addAll(map.keySet());
        }

        for (String mandatoryName : mandatoryStaticFields.keySet()) {
            if (fieldNamesSent.indexOf(mandatoryName) == -1) {
                missingFields.add(mandatoryName);
            }
        }

        return missingFields;
    }

    private Map<String, CustomEventsEntity> getStaticEventsAsMap () {
        Iterable<CustomEventsEntity> staticCustomEventEntities = staticCustomEventRepository.findByStatus(EventStatus.active);
        Map<String, CustomEventsEntity> map = new HashMap<>();

        for (CustomEventsEntity entity : staticCustomEventEntities) {
            map.put(entity.getEventCode(), entity);
        }

        return map;
    }

    private Map<String, CustomEventFieldEntity> getMandatoryStaticEventFieldsAsMap (Iterable<CustomEventFieldEntity> staticEventFieldEntities) {
        Map<String, CustomEventFieldEntity> map = new HashMap<>();

        for (CustomEventFieldEntity entity : staticEventFieldEntities) {
            if (entity.getMandatory()) {
                map.put(entity.getFieldName(), entity);
            }

        }

        return map;
    }


    private Boolean validateFieldValue (String fieldName, Object fieldValue, Iterable<CustomEventFieldEntity> staticEvents) {
        Boolean valid = false;
        EventType eventType;

        for (CustomEventFieldEntity entity : staticEvents) {
            if (entity.getFieldName().equals(fieldName)) {
                eventType = entity.getType();

                switch (eventType) {
                    case intType:
                        if (fieldValue instanceof Integer) {
                            valid = true;
                        }
                        break;
                    case floatType:
                        if (fieldValue instanceof Double) {
                            valid = true;
                        }
                        break;
                    case stringType:
                        if (fieldValue instanceof String) {
                            valid = true;
                        }
                        break;
                    case booleanType:
                        if (fieldValue instanceof Boolean) {
                            valid = true;
                        }
                        break;
                    case dateType:
                        valid = isValidDate(fieldValue.toString(), "yyyy-MM-dd");
                        break;
                    case datetimeType:
                        valid = isValidDate(fieldValue.toString(), "yyyy-MM-dd HH:mm:ss");
                        break;
                }

                if (valid) {
                    break;
                }
            }
        }

        return valid;
    }

    private static boolean isValidDate(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateString.trim());
        } catch (ParseException pe) {
            return false;
        }

        return true;
    }
}
