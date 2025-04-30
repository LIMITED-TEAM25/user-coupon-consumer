package com.sparta.limited.user_coupon_consumer.domain.repository;

import com.sparta.limited.user_coupon_consumer.domain.model.UserCoupon;

public interface UserCouponRepository {

    void save(UserCoupon userCoupon);
}
