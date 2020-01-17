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
import com.coupon.bean.Discount;
import com.coupon.bean.jpa.ConversionDataEntity;
import com.coupon.bean.jpa.CouponDetailsTableEntity;
import com.coupon.bean.jpa.DiscountDetailsTableEntity;
import com.coupon.bean.jpa.RulesDetailsEntity;
import com.coupon.constants.Condition;
import com.coupon.repository.ConversionDataRepository;
import com.coupon.repository.CouponDetailsTableRepository;
import com.coupon.repository.DiscountDetailsTableRepository;
import com.coupon.repository.RulesDetailsRepository;
import com.coupon.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private ConversionDataRepository conversionDataRepository;
    @Resource
    private CouponDetailsTableRepository couponDetailsTableRepository;
    @Resource
    private DiscountDetailsTableRepository discountDetailsTableRepository;
    @Resource
    private RulesDetailsRepository rulesDetailsRepository;
    @Override
    public CartResponse getCartResponse(CartRequest cartRequest) {
        CartResponse response = new CartResponse();

        response.setCoupons(getCoupons(cartRequest));
        response.setDiscounts(getDiscounts(cartRequest));
        response.setTxnId(cartRequest.getTxnId());
        response.setStatus("success");
        response.setMessage("ok");
        response.setStatus_code(2000);

        return response;
    }

    private List<Discount> getDiscounts(CartRequest cartRequest) {
        Iterable<DiscountDetailsTableEntity> allDiscounts = discountDetailsTableRepository.findAll();
        List<String> types = new ArrayList<>();


        for (CartItem cartItem : cartRequest.getCart_data()) {
            if (types.indexOf(cartItem.getCategory()) == -1) {
                types.add(cartItem.getCategory());
            }
        }

        List<RulesDetailsEntity> rules = rulesDetailsRepository.findByTypeAndItemNameIn("discount", types);
        List<DiscountDetailsTableEntity> discounts = new ArrayList<>();
        List<ConversionDataEntity> userConversions = conversionDataRepository.findByUserId(cartRequest.getUser_data().getUser_id());
        Integer length = userConversions.size();
        Map<String, List<RulesDetailsEntity>> rulesCodeMap = new HashMap<>();
        List<RulesDetailsEntity> discountRules;
        Boolean valid;

        for (RulesDetailsEntity entity : rules) {
            if (!rulesCodeMap.containsKey(entity.getCode())) {
                rulesCodeMap.put(entity.getCode(), new ArrayList<RulesDetailsEntity>());
            }

            rulesCodeMap.get(entity.getCode()).add(entity);
        }

        for (DiscountDetailsTableEntity entity : allDiscounts) {
            if (rulesCodeMap.containsKey(entity.getDiscountDesc())) {
                discountRules = rulesCodeMap.get(entity.getDiscountDesc());
                valid = true;

                for (RulesDetailsEntity ruleDetails : discountRules) {
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
                    discounts.add(entity);
                }
            } else {
                discounts.add(entity);
            }

        }

        return convertDiscounts(discounts);
    }

    private List<Coupon> getCoupons(CartRequest cartRequest) {
        Iterable<CouponDetailsTableEntity> allCoupons = couponDetailsTableRepository.findAll();
        List<String> types = new ArrayList<>();


        for (CartItem cartItem : cartRequest.getCart_data()) {
            if (types.indexOf(cartItem.getCategory()) == -1) {
                types.add(cartItem.getCategory());
            }
        }
        List<RulesDetailsEntity> rules = rulesDetailsRepository.findByTypeAndItemNameIn("coupon", types);
        List<CouponDetailsTableEntity> coupons = new ArrayList<>();
        List<ConversionDataEntity> userConversions = conversionDataRepository.findByUserId(cartRequest.getUser_data().getUser_id());
        Integer length = userConversions.size();
        Map<String, List<RulesDetailsEntity>> rulesCodeMap = new HashMap<>();
        List<RulesDetailsEntity> couponRules;
        Boolean valid;

        for (RulesDetailsEntity entity : rules) {
            if (!rulesCodeMap.containsKey(entity.getCode())) {
                rulesCodeMap.put(entity.getCode(), new ArrayList<RulesDetailsEntity>());
            }

            rulesCodeMap.get(entity.getCode()).add(entity);
        }

        for (CouponDetailsTableEntity entity : allCoupons) {
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

        }

        return convertCoupons(coupons);
    }

    private List<Coupon> convertCoupons(List<CouponDetailsTableEntity> entities) {
        List<Coupon> coupons = new ArrayList<>();

        for (CouponDetailsTableEntity entity : entities) {
            coupons.add(new Coupon(entity));
        }

        return coupons;
    }

    private List<Discount> convertDiscounts(List<DiscountDetailsTableEntity> entities) {
        List<Discount> discounts = new ArrayList<>();

        for (DiscountDetailsTableEntity entity : entities) {
            discounts.add(new Discount(entity));
        }

        return discounts;
    }
}
