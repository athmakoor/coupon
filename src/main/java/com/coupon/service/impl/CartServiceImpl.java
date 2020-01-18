package com.coupon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coupon.bean.CartItem;
import com.coupon.bean.CartRequest;
import com.coupon.bean.CartResponse;
import com.coupon.bean.Coupon;
import com.coupon.bean.jpa.CouponEntity;
import com.coupon.repository.ConversionDataRepository;
import com.coupon.repository.CouponDetailsTableRepository;
import com.coupon.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private ConversionDataRepository conversionDataRepository;
    @Resource
    private CouponDetailsTableRepository couponDetailsTableRepository;

    @Override
    public CartResponse getCartResponse(CartRequest cartRequest) {
        CartResponse response = new CartResponse();

        response.setCoupons(getCoupons(cartRequest));
        response.setTxnId(cartRequest.getTxnId());
        response.setStatus("success");
        response.setMessage("ok");
        response.setStatus_code(2000);

        return response;
    }

    private List<Coupon> getCoupons(CartRequest cartRequest) {
        Iterable<CouponEntity> allCoupons = couponDetailsTableRepository.findAll();
        List<String> types = new ArrayList<>();


        for (CartItem cartItem : cartRequest.getCart_data()) {
            if (types.indexOf(cartItem.getCategory()) == -1) {
                types.add(cartItem.getCategory());
            }
        }
        /*List<RulesDetailsEntity> rules = new ArrayList<>();
        List<CouponEntity> coupons = new ArrayList<>();
        List<ConversionDataEntity> userConversions = conversionDataRepository.findByUserId(cartRequest.getUser_data().getUser_id());
        Integer length = userConversions.size();
        Map<String, List<RulesDetailsEntity>> rulesCodeMap = new HashMap<>();
        List<RulesDetailsEntity> couponRules;
        Boolean valid;*/

        /*for (RulesDetailsEntity entity : rules) {
            if (!rulesCodeMap.containsKey(entity.getCode())) {
                rulesCodeMap.put(entity.getCode(), new ArrayList<RulesDetailsEntity>());
            }

            rulesCodeMap.get(entity.getCode()).add(entity);
        }*/

        /*for (CouponEntity entity : allCoupons) {
            if (rulesCodeMap.containsKey(entity.getCouponCode())) {
                couponRules = rulesCodeMap.get(entity.getCouponCode());
                valid = true;

                for (RulesDetailsEntity ruleDetails : couponRules) {
                    if (ruleDetails.getCond().equals(Condition.eq)) {
                        if (length != (ruleDetails.getQuantity() - 1)) {
                            valid = false;
                            break;
                        }
                    } else if (ruleDetails.getCond().equals(Condition.gt)) {
                        if (length <= (ruleDetails.getQuantity())) {
                            valid = false;
                            break;
                        }
                    } else if (ruleDetails.getCond().equals(Condition.lt)) {
                        if (length >= (ruleDetails.getQuantity())) {
                            valid = false;
                            break;
                        }
                    }
                }

                if (valid) {
                    coupons.add(entity);
                }
            } else {
                coupons.add(entity);
            }

        }*/

        return convertCoupons(new ArrayList<CouponEntity>());
    }

    private List<Coupon> convertCoupons(List<CouponEntity> entities) {
        List<Coupon> coupons = new ArrayList<>();

        for (CouponEntity entity : entities) {
            coupons.add(new Coupon(entity));
        }

        return coupons;
    }
}
