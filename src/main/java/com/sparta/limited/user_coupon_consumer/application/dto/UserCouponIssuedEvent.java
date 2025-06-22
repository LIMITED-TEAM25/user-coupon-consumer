package com.sparta.limited.user_coupon_consumer.application.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class UserCouponIssuedEvent {

    private final UUID couponId;
    private final Long userId;

    private UserCouponIssuedEvent(
        UUID couponId,
        Long userId
    ) {
        this.couponId = couponId;
        this.userId = userId;
    }

    public static UserCouponIssuedEvent of(
        UUID couponId,
        Long userId
    ) {
        return new UserCouponIssuedEvent(
            couponId,
            userId
        );
    }
}
