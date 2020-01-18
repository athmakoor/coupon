package com.coupon.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import com.coupon.bean.jpa.RuleCalendarMappingEntity;
import com.coupon.bean.jpa.RuleCategoryMappingEntity;
import com.coupon.bean.jpa.RuleOfferMappingEntity;
import com.coupon.constants.CalenderType;
import com.coupon.constants.CouponType;
import com.coupon.constants.Relation;
import com.coupon.repository.ConversionDataRepository;
import com.coupon.repository.CouponRepository;
import com.coupon.repository.RuleCalendarMappingRepository;
import com.coupon.repository.RuleCategoryMappingRepository;
import com.coupon.repository.RuleOfferMappingRepository;
import com.coupon.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private ConversionDataRepository conversionDataRepository;
    @Resource
    private CouponRepository couponRepository;
    @Resource
    private RuleOfferMappingRepository ruleOfferMappingRepository;
    @Resource
    private RuleCalendarMappingRepository ruleCalendarMappingRepository;
    @Resource
    private RuleCategoryMappingRepository ruleCategoryMappingRepository;

    @Override
    public CartResponse getCartResponse(CartRequest cartRequest) {
        CartResponse response = new CartResponse();

        response.setCoupons(getCoupons(cartRequest));
        response.setTxnId(cartRequest.getTxn_id());
        response.setStatus("success");
        response.setMessage("ok");
        response.setStatus_code(2000);

        return response;
    }

    private List<Coupon> getCoupons(CartRequest cartRequest) {
        Date now = new Date();
        Iterable<CouponEntity> allCoupons = couponRepository.findAllValid(now, cartRequest.getTotalCartValue());

        Map<Integer, CouponEntity> couponIdCouponMap = new HashMap<>();

        for (CouponEntity entity : allCoupons) {
            couponIdCouponMap.put(entity.getId(), entity);
        }

        List<Integer> couponIds = new ArrayList<>(couponIdCouponMap.keySet());
        List<CouponEntity> offerCoupons = filterValidOfferCoupons(cartRequest, allCoupons, couponIdCouponMap);
        List<CouponEntity> calendarCoupons = filterValidCalendarCoupons(couponIdCouponMap);
        List<CouponEntity> categoryCoupons = filterValidCategoryCoupons(cartRequest, allCoupons, couponIdCouponMap);

        return convertCoupons(categoryCoupons);
    }

    private List<CouponEntity> filterValidCategoryCoupons(CartRequest cartRequest, Iterable<CouponEntity> allCoupons, Map<Integer, CouponEntity> couponIdCouponMap) {
        List<CouponEntity> validCoupons = new ArrayList<>();
        List<Integer> couponIds = new ArrayList<>(couponIdCouponMap.keySet());

        Iterable<RuleCategoryMappingEntity> rules = ruleCategoryMappingRepository.findByCouponEntityIdIn(couponIds);
        Map<Integer, List<RuleCategoryMappingEntity>> couponIdRuleMap = new HashMap<>();
        Integer couponId;

        for (RuleCategoryMappingEntity rule : rules) {
            couponId = rule.getCouponEntity().getId();

            if (!couponIdRuleMap.containsKey(couponId)) {
                couponIdRuleMap.put(couponId, new ArrayList<RuleCategoryMappingEntity>());
            }

            couponIdRuleMap.get(couponId).add(rule);
        }

        for (Integer coupId : couponIdRuleMap.keySet()) {
            if (validateCategoryRule(cartRequest, couponIdRuleMap.get(coupId))) {
                validCoupons.add(couponIdCouponMap.get(coupId));
            }
        }

        return validCoupons;
    }

    private List<CouponEntity> filterValidCalendarCoupons(Map<Integer, CouponEntity> couponIdCouponMap) {
        List<CouponEntity> validCoupons = new ArrayList<>();
        List<Integer> couponIds = new ArrayList<>(couponIdCouponMap.keySet());

        Iterable<RuleCalendarMappingEntity> rules = ruleCalendarMappingRepository.findByCouponEntityIdIn(couponIds);
        Map<Integer, List<RuleCalendarMappingEntity>> couponIdRuleMap = new HashMap<>();
        Integer couponId;

        for (RuleCalendarMappingEntity rule : rules) {
            couponId = rule.getCouponEntity().getId();

            if (!couponIdRuleMap.containsKey(couponId)) {
                couponIdRuleMap.put(couponId, new ArrayList<RuleCalendarMappingEntity>());
            }

            couponIdRuleMap.get(couponId).add(rule);
        }

        for (Integer coupId : couponIdRuleMap.keySet()) {
            if (validateCalendarRule(couponIdRuleMap.get(coupId))) {
                validCoupons.add(couponIdCouponMap.get(coupId));
            }
        }

        return validCoupons;
    }

    private Boolean validateCategoryRule(CartRequest cartRequest, List<RuleCategoryMappingEntity> ruleCategoryMappingEntities) {
        Boolean valid = false;
        Map<String, List<CartItem>> categoryCartItemMap = cartRequest.geCategoryCartItemMap();
        Map<String, Double> categoryCartPriceMap = cartRequest.geCategoryCartPriceMap();
        CouponEntity couponEntity = ruleCategoryMappingEntities.get(0).getCouponEntity();
        Double categoryPrice;
        Integer quantity;
        String category;

        for (RuleCategoryMappingEntity rule : ruleCategoryMappingEntities) {
            category = rule.getCategoryName();
            quantity = rule.getQuantity();
            categoryPrice = categoryCartPriceMap.get(category);

            valid = (quantity == null ||
                    (categoryCartItemMap.get(category) != null && categoryCartItemMap.get(category).size() >= quantity));
            valid = valid ||couponEntity.getMinCartValue() < categoryPrice;

            if (valid) {
                break;
            }
        }

        return valid;
    }

    private Boolean validateCalendarRule(List<RuleCalendarMappingEntity> ruleCalendarMappingEntities) {
        Relation relation = ruleCalendarMappingEntities.get(0).getRelation();
        Date now = new Date();
        Boolean valid = false, tempValid, previousValid = true;
        CalenderType calenderType;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        Integer date = calendar.get(Calendar.DATE);
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
        Integer minute = calendar.get(Calendar.MINUTE);
        Integer second = calendar.get(Calendar.SECOND);
        Integer week = calendar.get(Calendar.DAY_OF_WEEK);
        Integer month = calendar.get(Calendar.MONTH);
        Integer currentHrMs = convertHourStringToMs("" + hour + ":" + minute + ":" + second);
        String start, end;
        Integer startInt, endInt;
        Boolean exactMatch;
        List<String> split;

        for (RuleCalendarMappingEntity rule : ruleCalendarMappingEntities) {
            calenderType = rule.getCalType();
            start = rule.getTypeFrom();
            end = rule.getTypeTo();
            exactMatch = rule.getExactMatch();
            tempValid = false;

            switch (calenderType) {
                case hour:
                    startInt = convertHourStringToMs(start);
                    endInt = convertHourStringToMs(end);
                    tempValid = (startInt < currentHrMs) && (endInt > currentHrMs);
                    break;

                case date:
                    startInt = Integer.parseInt(start);
                    if (exactMatch) {
                        tempValid = date.equals(startInt);
                    } else {
                        endInt = Integer.parseInt(end);
                        tempValid = (startInt <= date) && (endInt >= date);
                    }
                    break;

                case day:
                    split = Arrays.asList(start.split(","));
                    tempValid = split.contains(week + "");
                    break;

                case month:
                    split = Arrays.asList(start.split(","));
                    tempValid = split.contains(month + "");
                    break;
            }

            if ((relation.equals(Relation.or) || relation.equals(Relation.na)) && tempValid) {
                valid = true;
            }

            previousValid = previousValid && tempValid;

            if (valid) {
                previousValid = true;
                break;
            }
        }

        return valid || previousValid;
    }

    private List<CouponEntity> filterValidOfferCoupons(CartRequest cartRequest, Iterable<CouponEntity> allCoupons, Map<Integer, CouponEntity> couponIdCouponMap) {
        List<CouponEntity> validCoupons = new ArrayList<>();
        List<Integer> couponIds = new ArrayList<>();

        for (CouponEntity couponEntity : allCoupons) {
            if (couponEntity.getCouponType().equals(CouponType.offer)) {
                couponIds.add(couponEntity.getId());
            }
        }

        Iterable<RuleOfferMappingEntity> offers = ruleOfferMappingRepository.findByCouponEntityIdIn(couponIds);
        Map<String, Integer> cartSkus = cartRequest.getSkuQuantityMap();

        for (RuleOfferMappingEntity offerRule : offers) {
            if (validateOffer(cartSkus, offerRule)) {
                validCoupons.add(couponIdCouponMap.get(offerRule.getCouponEntity().getId()));
            }
        }

        return validCoupons;
    }

    private Boolean validateOffer (Map<String, Integer> cartSkus, RuleOfferMappingEntity offerRule) {
        String buySkus = offerRule.getBuySkus();
        String[] buySkusSplit = buySkus.split(",");
        List<String> buySkuList = Arrays.asList(buySkusSplit);
        Integer count = 0;

        for (String cartSku : cartSkus.keySet()) {
            if (buySkuList.contains(cartSku)) {
                count += cartSkus.get(cartSku);

                if (count >= offerRule.getBuyQuantity()) {
                    break;
                }
            }
        }

        return count >= offerRule.getBuyQuantity();
    }

    private List<Coupon> convertCoupons(List<CouponEntity> entities) {
        List<Coupon> coupons = new ArrayList<>();

        for (CouponEntity entity : entities) {
            coupons.add(new Coupon(entity));
        }

        return coupons;
    }

    private Integer convertHourStringToMs (String str) {
        String[] tokens = str.split(":");
        int hoursToMs = Integer.parseInt(tokens[0]) * 3600000;
        int secondsToMs = 0;
        int minutesToMs = 0;

        if (tokens.length > 1) {
            secondsToMs = Integer.parseInt(tokens[1]) * 60000;
        }

        if (tokens.length > 2) {
            secondsToMs = Integer.parseInt(tokens[2]) * 1000;
        }


        return secondsToMs + minutesToMs + hoursToMs;
    }
}
