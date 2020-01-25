package com.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coupon.bean.CouponRequestDTO;
import com.coupon.bean.CouponResponseDTO;
import com.coupon.bean.RuleCalendarDTO;
import com.coupon.bean.jpa.CouponCodeLanguageMappingEntity;
import com.coupon.bean.jpa.CouponDescriptionLanguageMappingEntity;
import com.coupon.bean.jpa.CouponEntity;
import com.coupon.bean.jpa.RuleCalendarMappingEntity;
import com.coupon.constants.Language;
import com.coupon.repository.CouponCodeLanguageMappingRepository;
import com.coupon.repository.CouponDescriptionLanguageMappingRepository;
import com.coupon.repository.CouponRepository;
import com.coupon.repository.RuleCalendarMappingRepository;
import com.coupon.service.CouponService;
import com.coupon.utils.TimeUtil;

@Service
public class CouponServiceImpl implements CouponService {
    @Resource
    private CouponRepository couponRepository;
    @Resource
    private RuleCalendarMappingRepository ruleCalendarMappingRepository;
    @Resource
    private CouponCodeLanguageMappingRepository couponCodeLanguageMappingRepository;
    @Resource
    private CouponDescriptionLanguageMappingRepository couponDescriptionLanguageMappingRepository;

    @Override
    public CouponResponseDTO create(CouponRequestDTO data) {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setStartedOn(data.getStarted_on());
        couponEntity.setClosedOn(data.getClosed_on());
        couponEntity.setCreatedOn(TimeUtil.getCurrentUTCTime());
        couponEntity.setCouponType(data.getCoupon_type());
        couponEntity.setCurrency(data.getCurrency());
        couponEntity.setDiscountPercentage(data.getDiscount_percentage());
        couponEntity.setItemType(data.getItem_type());
        couponEntity.setManual(data.getIs_manual());
        couponEntity.setMergeable(data.getIs_mergeable());
        couponEntity.setMaxDiscount(data.getMax_discount());
        couponEntity.setMinCartValue(data.getMin_cart_value());

        CouponEntity savedCouponEntity = couponRepository.save(couponEntity);
        Integer id = savedCouponEntity.getId();
        CouponEntity tempEntity = new CouponEntity();
        tempEntity.setId(id);

        RuleCalendarMappingEntity ruleCalendarMappingEntity;
        List<RuleCalendarMappingEntity> ruleCalendarMappingEntities = new ArrayList<>();

        for (RuleCalendarDTO ruleCalendarDTO : data.getCalendar_rules()) {
            ruleCalendarMappingEntity = new RuleCalendarMappingEntity();
            ruleCalendarMappingEntity.setCouponEntity(tempEntity);
            ruleCalendarMappingEntity.setCalType(ruleCalendarDTO.getCal_type());
            ruleCalendarMappingEntity.setCouponLimit(ruleCalendarDTO.getCoupon_limit());
            ruleCalendarMappingEntity.setExactMatch(ruleCalendarDTO.getExact_match());
            ruleCalendarMappingEntity.setLimitPerUser(ruleCalendarDTO.getLimit_per_user());
            ruleCalendarMappingEntity.setRelation(ruleCalendarDTO.getRelation());
            ruleCalendarMappingEntity.setTypeFrom(ruleCalendarDTO.getType_from());
            ruleCalendarMappingEntity.setTypeTo(ruleCalendarDTO.getType_to());

            ruleCalendarMappingEntities.add(ruleCalendarMappingEntity);
        }

        if (!ruleCalendarMappingEntities.isEmpty()) {
            ruleCalendarMappingRepository.saveAll(ruleCalendarMappingEntities);
        }

        CouponCodeLanguageMappingEntity couponCodeLanguageMappingEntity;
        List<CouponCodeLanguageMappingEntity> codes = new ArrayList<>();

        for (Language key : data.getCode().keySet()) {
            couponCodeLanguageMappingEntity = new CouponCodeLanguageMappingEntity();
            couponCodeLanguageMappingEntity.setCouponCode(data.getCode().get(key));
            couponCodeLanguageMappingEntity.setLanguage(key);
            codes.add(couponCodeLanguageMappingEntity);
        }

        couponCodeLanguageMappingRepository.saveAll(codes);

        CouponDescriptionLanguageMappingEntity couponDescriptionLanguageMappingEntity;
        List<CouponDescriptionLanguageMappingEntity> descriptions = new ArrayList<>();

        for (Language key : data.getDescription().keySet()) {
            couponDescriptionLanguageMappingEntity = new CouponDescriptionLanguageMappingEntity();
            couponDescriptionLanguageMappingEntity.setCouponDesc(data.getDescription().get(key));
            couponDescriptionLanguageMappingEntity.setLanguage(key);
            descriptions.add(couponDescriptionLanguageMappingEntity);
        }

        couponDescriptionLanguageMappingRepository.saveAll(descriptions);

        return new CouponResponseDTO();
    }
}
