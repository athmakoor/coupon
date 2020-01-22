package com.coupon.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coupon.bean.ConversionRequest;
import com.coupon.bean.ConversionResponse;
import com.coupon.bean.jpa.ConversionDataEntity;
import com.coupon.constants.ConversionStatusCodes;
import com.coupon.repository.CartDataRepository;
import com.coupon.repository.ConversionDataRepository;
import com.coupon.service.ConversionService;

@Service
public class ConversionServiceImpl implements ConversionService {
    @Resource
    private ConversionDataRepository conversionDataRepository;
    @Resource
    private CartDataRepository cartDataRepository;

    @Override
    public ConversionResponse saveConversions(ConversionRequest data) {
        if (data.getTxn_id() == null) {
            return new ConversionResponse(ConversionStatusCodes.VALUES.get(ConversionStatusCodes.INVALID_TRANSACTION_ID), data.getTxn_id());
        }

        Integer cartDataId = cartDataRepository.findLatestIdByTxnId(data.getTxn_id());

        if (cartDataId == null) {
            return new ConversionResponse(ConversionStatusCodes.VALUES.get(ConversionStatusCodes.INVALID_TRANSACTION_ID), data.getTxn_id());
        }

        ConversionDataEntity conversionDataEntity = new ConversionDataEntity(data, cartDataId);

        conversionDataRepository.save(conversionDataEntity);

        return new ConversionResponse(ConversionStatusCodes.VALUES.get(ConversionStatusCodes.SUCCESS), data.getTxn_id());
    }
}
