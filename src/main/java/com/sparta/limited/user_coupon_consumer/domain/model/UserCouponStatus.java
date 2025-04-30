package com.sparta.limited.user_coupon_consumer.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserCouponStatus {
    NOT_USED("미사용 쿠폰"),
    USED("사용 쿠폰");

    private final String description;

}
