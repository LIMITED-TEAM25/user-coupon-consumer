package com.sparta.limited.user_coupon_consumer.infrastructure.repository;

import com.sparta.limited.user_coupon_consumer.domain.model.UserCoupon;
import com.sparta.limited.user_coupon_consumer.domain.repository.UserCouponRepository;
import com.sparta.limited.user_coupon_consumer.infrastructure.persistence.JpaUserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final JpaUserCouponRepository jpaUserCouponRepository;

    @Override
    public void save(UserCoupon userCoupon) {
        jpaUserCouponRepository.save(userCoupon);
    }

}