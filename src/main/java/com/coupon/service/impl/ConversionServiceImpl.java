package com.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coupon.bean.CartItem;
import com.coupon.bean.ConversionRequest;
import com.coupon.bean.ConversionResponse;
import com.coupon.bean.jpa.ConversionDataEntity;
import com.coupon.bean.jpa.ConverstionCartDataEntity;
import com.coupon.repository.ConversionCartDataRepository;
import com.coupon.repository.ConversionDataRepository;
import com.coupon.service.ConversionService;

@Service
public class ConversionServiceImpl implements ConversionService {
    @Resource
    private ConversionDataRepository conversionDataRepository;
    @Resource
    private ConversionCartDataRepository conversionCartDataRepository;

    @Override
    public ConversionResponse saveConversions(ConversionRequest data) {
        ConversionDataEntity conversionDataEntity = new ConversionDataEntity(data);
        List<ConverstionCartDataEntity> converstionCartDataEntities = new ArrayList<>();

        for (CartItem item : data.getCart_data()) {
            converstionCartDataEntities.add(new ConverstionCartDataEntity(item,data));
        }

        conversionDataRepository.save(conversionDataEntity);
        conversionCartDataRepository.save(converstionCartDataEntities);

        ConversionResponse response = new ConversionResponse();
        response.setStatus("success");
        response.setTxn_id(data.getTxn_id());

        return response;
    }
}
