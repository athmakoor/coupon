package com.coupon.service.impl;

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
import com.coupon.bean.Referral;
import com.coupon.bean.jpa.CartDataEntity;
import com.coupon.bean.jpa.CartItemEntity;
import com.coupon.bean.jpa.CouponEntity;
import com.coupon.bean.jpa.ReferralBonusMappingEntity;
import com.coupon.bean.jpa.ReferralCodeUserMappingEntity;
import com.coupon.bean.jpa.ReferralTransactionEntity;
import com.coupon.bean.jpa.RuleCalendarMappingEntity;
import com.coupon.bean.jpa.RuleCategoryMappingEntity;
import com.coupon.bean.jpa.RuleOfferMappingEntity;
import com.coupon.constants.CalenderType;
import com.coupon.constants.CouponType;
import com.coupon.constants.ReferralUserType;
import com.coupon.constants.Relation;
import com.coupon.constants.TransactionType;
import com.coupon.repository.CartDataRepository;
import com.coupon.repository.CartItemRepository;
import com.coupon.repository.ConversionDataRepository;
import com.coupon.repository.CouponRepository;
import com.coupon.repository.ReferralBonusMappingRepository;
import com.coupon.repository.ReferralCodeUserMappingRepository;
import com.coupon.repository.ReferralTransactionRepository;
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
    @Resource
    private ReferralBonusMappingRepository referralBonusMappingRepository;
    @Resource
    private ReferralCodeUserMappingRepository referralCodeUserMappingRepository;
    @Resource
    private ReferralTransactionRepository referralTransactionRepository;
    @Resource
    private CartDataRepository cartDataRepository;
    @Resource
    private CartItemRepository cartItemRepository;

    @Override
    public CartResponse getCartResponse(CartRequest cartRequest) {
        CartResponse response = new CartResponse();

        saveCart(cartRequest);
        cartRequest.copyCartData();

        response.setCouponsAndDiscounts(getCoupons(cartRequest));
        if (cartRequest.getFields() != null && !cartRequest.getFields().isEmpty()) {
            if (!cartRequest.getFields().contains("coupons")) {
                response.setCoupons(new ArrayList<Coupon>());
            }

            if (!cartRequest.getFields().contains("discounts")) {
                response.setDiscounts(new ArrayList<Coupon>());
            }
        }

        response.setReferrals(getReferrals(cartRequest));
        response.setReferralBonus(getUserReferralBonus(cartRequest.getUser_data().getUser_id()));
        response.setTxnId(cartRequest.getTxn_id());
        response.setStatus("success");
        response.setMessage("ok");
        response.setStatus_code(2000);

        return response;
    }

    @Override
    public void saveCart(CartRequest cartRequest) {
        CartDataEntity cartDataEntity = new CartDataEntity();

        cartDataEntity.setTxnId(cartRequest.getTxn_id());
        cartDataEntity.setUserId(cartRequest.getUser_data().getUser_id());
        cartDataEntity.setInvoiceAmount(cartRequest.getTotalCartValue());
        cartDataEntity.setCreatedOn(new Date());

        CartDataEntity savedCartDataEntity = cartDataRepository.save(cartDataEntity);

        CartItemEntity cartItemEntity;
        List<CartItemEntity> cartItemEntities = new ArrayList<>();

        for (CartItem item : cartRequest.getCart_data()) {
            cartItemEntity = new CartItemEntity();
            cartItemEntity.setAmount(item.getAmount());
            cartItemEntity.setCategory(item.getCategory());
            cartItemEntity.setItemName(item.getItem_name());
            cartItemEntity.setQuantity(item.getQuantity());
            cartItemEntity.setType(item.getType());
            cartItemEntity.setSku(item.getSku());
            cartItemEntity.setCartDataEntity(savedCartDataEntity);

            cartItemEntities.add(cartItemEntity);
        }

        cartItemRepository.saveAll(cartItemEntities);
    }

    private Double getUserReferralBonus(String userId) {
        Double amount = 0.0;
        List<ReferralTransactionEntity> transactionEntities = referralTransactionRepository.findByUserId(userId);

        for (ReferralTransactionEntity entity : transactionEntities) {
            if (entity.getTransactionType().equals(TransactionType.credit)) {
                amount += entity.getAmount();
            } else {
                amount -= entity.getAmount();
            }
        }

        return amount;
    }

    private List<Referral> getReferrals(CartRequest cartRequest) {
        Date now = new Date();
        List<Referral> referrals = new ArrayList<>();
        List<ReferralCodeUserMappingEntity> referralCodeUserMappingEntityList = referralCodeUserMappingRepository.findByUserId(cartRequest.getUser_data().getUser_id());

        if (referralCodeUserMappingEntityList.isEmpty()) {
            return referrals;
        }

        if (referralCodeUserMappingEntityList.get(0).getReferrerCode() == null) {
            return referrals;
        }

        List<ReferralBonusMappingEntity> activeReferralBonusList = referralBonusMappingRepository.findAllValidByUserType(now, cartRequest.getTotalCartValue(), ReferralUserType.referee);

        for (ReferralBonusMappingEntity entity : activeReferralBonusList) {
            referrals.add(new Referral(entity, cartRequest));
        }

        return referrals;
    }

    private List<Coupon> getCoupons(CartRequest cartRequest) {
        Date now = new Date();
        Iterable<CouponEntity> allCoupons = couponRepository.findAllValid(now, cartRequest.getTotalCartValue(), 0.7);

        Map<Integer, CouponEntity> couponIdCouponMap = new HashMap<>();

        for (CouponEntity entity : allCoupons) {
            couponIdCouponMap.put(entity.getId(), entity);
        }

        List<Integer> couponIds = new ArrayList<>(couponIdCouponMap.keySet());
        List<Integer> invalidOfferCoupons = filterInvalidOfferCoupons(cartRequest, allCoupons);
        List<Integer> invalidCalendarCoupons = filterInvalidCalendarCoupons(couponIdCouponMap);
        List<Integer> invalidCategoryCoupons = filterInvalidCategoryCoupons(cartRequest, couponIdCouponMap);
        List<CouponEntity> validCoupons = new ArrayList<>();

        for (Integer couponId : couponIds) {
            if (!(invalidOfferCoupons.contains(couponId)
                    || invalidCalendarCoupons.contains(couponId)
                    || invalidCategoryCoupons.contains(couponId))
            ) {
                validCoupons.add(couponIdCouponMap.get(couponId));
            }
        }

        List<Coupon> filteredCoupons = filterCoupons(cartRequest, validCoupons);

        updateFilteredCoupons(filteredCoupons, couponIdCouponMap, cartRequest);

        return filteredCoupons;
    }

    private List<String> sortSkus(CartRequest cartRequest, List<String> skus) {
        return cartRequest.sortSkusByPrice(skus);
    }
    private void updateFilteredCoupons(List<Coupon> filteredCoupons, Map<Integer, CouponEntity> couponIdCouponMap, CartRequest cartRequest) {
        CouponEntity couponEntity;
        List<RuleOfferMappingEntity> ruleOfferMappingEntities;
        Map<String, Integer> cartSkus = cartRequest.getSkuQuantityMap();
        Map<String, Integer> skusForOffer;
        Boolean enabled;
        Integer offerProductsNeeded;
        String category;
        Double categoryPrice, priceDifference;
        Map<String, Double> categoryCartPriceMap = cartRequest.geCategoryCartPriceMap();

        for (Coupon coupon : filteredCoupons) {
            couponEntity = couponIdCouponMap.get(coupon.getCouponId());
            ruleOfferMappingEntities = couponEntity.getRuleOfferMappingEntities();
            enabled = true;

            for (RuleOfferMappingEntity offerRule : ruleOfferMappingEntities) {
                offerProductsNeeded = checkOfferSkus(cartSkus, offerRule, cartRequest);

                if (offerProductsNeeded == 0) {
                    skusForOffer = removeBuySkus(cartSkus, sortSkus(cartRequest, new ArrayList<>(cartSkus.keySet())), offerRule);
                    coupon.setAmount(getBestOfferPrice(cartRequest, skusForOffer, offerRule.getOfferQuantity()));
                } else {
                    enabled = false;
                    coupon.setNote("Add " + offerProductsNeeded + " or more products from the list below to avail this offer.");
                    coupon.setOffer_skus(offerRule.getOfferSkus());
                    break;
                }
            }

            if (enabled) {
                for (RuleCategoryMappingEntity categoryRule : couponEntity.getRuleCategoryMappingEntities()) {
                    category = categoryRule.getCategoryName();
                    categoryPrice = categoryCartPriceMap.get(category);

                    if (categoryPrice == null) {
                        categoryPrice = 0.0;
                    }

                    priceDifference = couponEntity.getMinCartValue() - categoryPrice;

                    if (priceDifference >= 0.0) {
                        enabled = false;
                        coupon.setNote("Add " + priceDifference + " or more worth of " + category + " to avail this offer.");
                        break;
                    }
                }
            }

            coupon.setDisabled(!enabled);
        }
    }

    private Double getBestOfferPrice(CartRequest cartRequest, Map<String, Integer> skusForOffer, Integer offerQuantity) {
        Double amount = 0.0;
        Map<String, CartItem> cartSkusMap = cartRequest.getSkuCartItemMap();
        Integer tempQuantity = offerQuantity, tempCount;

        for (String cartSku : skusForOffer.keySet()) {
            tempCount = Math.min(tempQuantity, skusForOffer.get(cartSku));
            amount += tempCount * cartSkusMap.get(cartSku).getAmount();

            tempQuantity -= tempCount;

            if (tempQuantity == 0) {
                break;
            }
        }

        return amount;
    }

    private List<Coupon> filterCoupons(CartRequest cartRequest, List<CouponEntity> validCoupons) {
        Map<String, List<CouponEntity>> couponCodeCouponMap = new HashMap<>();
        String couponCode;
        List<CouponEntity> couponEntities = new ArrayList<>();
        Double minCartValue;
        CouponEntity requiredCoupon;

        for (CouponEntity couponEntity : validCoupons) {
            if (!couponEntity.getListOfCouponCodes().isEmpty()) {
                couponCode = couponEntity.getListOfCouponCodes().get(0).getCouponCode();
                if (!couponCodeCouponMap.containsKey(couponCode)) {
                    couponCodeCouponMap.put(couponCode, new ArrayList<CouponEntity>());
                }

                couponCodeCouponMap.get(couponCode).add(couponEntity);
            }
        }

        for (String code : couponCodeCouponMap.keySet()) {
            minCartValue = 0.0;
            requiredCoupon = null;

            for (CouponEntity entity : couponCodeCouponMap.get(code)) {
                if (entity.getMinCartValue() != null && entity.getMinCartValue() > minCartValue) {
                    minCartValue = entity.getMinCartValue();
                    requiredCoupon = entity;
                }
            }

            if (requiredCoupon != null) {
                couponEntities.add(requiredCoupon);
            } else if (couponCodeCouponMap.get(code).size() == 1) {
                couponEntities.add(couponCodeCouponMap.get(code).get(0));
            }
        }

        return convertCoupons(couponEntities, cartRequest);
    }

    private List<Integer> filterInvalidCategoryCoupons(CartRequest cartRequest, Map<Integer, CouponEntity> couponIdCouponMap) {
        List<Integer> invalidCoupons = new ArrayList<>();
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
            if (!validateCategoryRule(cartRequest, couponIdRuleMap.get(coupId))) {
                invalidCoupons.add(coupId);
            }
        }

        return invalidCoupons;
    }

    private List<Integer> filterInvalidCalendarCoupons(Map<Integer, CouponEntity> couponIdCouponMap) {
        List<Integer> invalidCoupons = new ArrayList<>();
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
            if (!validateCalendarRule(couponIdRuleMap.get(coupId))) {
                invalidCoupons.add(coupId);
            }
        }

        return invalidCoupons;
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

            if (categoryPrice == null) {
                categoryPrice = 0.0;
            }

            valid = (quantity == null ||
                    (categoryCartItemMap.get(category) != null && categoryCartItemMap.get(category).size() >= quantity));
            valid = valid ||couponEntity.getMinCartValueToShowCoupon() < categoryPrice;

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

    private List<Integer> filterInvalidOfferCoupons(CartRequest cartRequest, Iterable<CouponEntity> allCoupons) {
        List<Integer> invalidCoupons = new ArrayList<>();
        List<Integer> couponIds = new ArrayList<>();

        for (CouponEntity couponEntity : allCoupons) {
            if (couponEntity.getCouponType().equals(CouponType.offer)) {
                couponIds.add(couponEntity.getId());
            }
        }

        Iterable<RuleOfferMappingEntity> offers = ruleOfferMappingRepository.findByCouponEntityIdIn(couponIds);
        Map<String, Integer> cartSkus = cartRequest.getSkuQuantityMap();

        for (RuleOfferMappingEntity offerRule : offers) {
            if (!validateOffer(cartSkus, offerRule)) {
                invalidCoupons.add(offerRule.getCouponEntity().getId());
            }
        }

        return invalidCoupons;
    }

    private Map<String, Integer> removeBuySkus (Map<String, Integer> cartSkus, List<String> sortedSkus, RuleOfferMappingEntity offerRule) {
        String buySkus = offerRule.getOfferSkus();
        String[] buySkusSplit = buySkus.split(",");
        List<String> buySkuList = Arrays.asList(buySkusSplit);
        Integer buyCount = offerRule.getBuyQuantity(), tempCount;
        Map<String, Integer> cartSkusCopy = new HashMap<>(cartSkus);

        for (String cartSku : sortedSkus) {
            if (buySkuList.contains(cartSku)) {
                tempCount = Math.min(buyCount, cartSkusCopy.get(cartSku));

                cartSkusCopy.put(cartSku, cartSkusCopy.get(cartSku) - tempCount);
                buyCount -= tempCount;

                if (buyCount == 0) {
                    break;
                }
            }
        }

        return cartSkusCopy;
    }

    private Integer checkOfferSkus (Map<String, Integer> cartSkus, RuleOfferMappingEntity offerRule, CartRequest cartRequest) {
        String offerSkus = offerRule.getOfferSkus();
        String[] offerSkusSplit = offerSkus.split(",");
        List<String> offerSkuList = Arrays.asList(offerSkusSplit);

        Integer count = 0;

        Map<String, Integer> cartSkusCopy = removeBuySkus(cartSkus, sortSkus(cartRequest, new ArrayList<>(cartSkus.keySet())), offerRule);

        for (String cartSku : cartSkusCopy.keySet()) {
            if (offerSkuList.contains(cartSku)) {
                count += cartSkusCopy.get(cartSku);

                if (count >= offerRule.getOfferQuantity()) {
                    break;
                }
            }
        }

        return offerRule.getOfferQuantity() - count;
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

    private List<Coupon> convertCoupons(List<CouponEntity> entities, CartRequest cartRequest) {
        List<Coupon> coupons = new ArrayList<>();

        for (CouponEntity entity : entities) {
            coupons.add(new Coupon(entity, cartRequest));
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
