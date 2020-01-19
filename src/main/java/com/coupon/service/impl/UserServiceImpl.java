package com.coupon.service.impl;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coupon.bean.CreateUserRequest;
import com.coupon.bean.CreateUserResponse;
import com.coupon.bean.jpa.ReferralCodeUserMappingEntity;
import com.coupon.repository.ReferralCodeUserMappingRepository;
import com.coupon.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private ReferralCodeUserMappingRepository referralCodeUserMappingRepository;


    @Override
    public CreateUserResponse createUser(CreateUserRequest requestBody) {
        List<ReferralCodeUserMappingEntity> temp = referralCodeUserMappingRepository.findByUserId(requestBody.getUser_id());
        CreateUserResponse response = new CreateUserResponse();

        if (!temp.isEmpty()) {
            response.setMessage("User Already Exists");
            response.setStatus("error");
            response.setStatus_code(3000);

            return response;
        }

        if (requestBody.getReferral_code() != null) {
            temp = referralCodeUserMappingRepository.findByReferrerCode(requestBody.getReferral_code());

            if (temp.isEmpty()) {
                response.setMessage("Invalid Referral Code");
                response.setStatus("error");
                response.setStatus_code(3001);

                return response;
            }
        }

        ReferralCodeUserMappingEntity entity = new ReferralCodeUserMappingEntity();

        entity.setUserId(requestBody.getUser_id());
        entity.setReferrerCode(requestBody.getReferral_code());
        entity.setUserReferrerCode(createReferralCode());
        entity.setCreatedOn(new Date());

        ReferralCodeUserMappingEntity savedEntity = referralCodeUserMappingRepository.save(entity);

        response.setMessage("User Created");
        response.setStatus("success");
        response.setStatus_code(2000);
        response.setReferral_code(savedEntity.getUserReferrerCode());

        return response;
    }

    private String createReferralCode() {
        byte[] array = new byte[256];
        int n = 8;
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }
}
