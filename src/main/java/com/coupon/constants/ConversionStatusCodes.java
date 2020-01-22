package com.coupon.constants;

import java.util.HashMap;
import java.util.Map;

import com.coupon.bean.StatusDTO;

public class ConversionStatusCodes {
    public static final Map<Integer, StatusDTO> VALUES;

    static {
        VALUES = new HashMap<>();
        VALUES.put(2000, new StatusDTO(2000, "success", "processed successfully"));
        VALUES.put(3100, new StatusDTO(3100, "error", "Invalid transaction id"));
        VALUES.put(3101, new StatusDTO(3101, "error", "Transaction status missing"));
        VALUES.put(3103, new StatusDTO(3102, "error", "Transaction id missing"));
    }

    public static final Integer SUCCESS = 2000;
    public static final Integer INVALID_TRANSACTION_ID = 3100;
    public static final Integer TRANSACTION_STATUS_MISSING = 3101;
    public static final Integer MANDATORY_EVENT_TRANSACTION_ID_MISSING = 3102;
}
