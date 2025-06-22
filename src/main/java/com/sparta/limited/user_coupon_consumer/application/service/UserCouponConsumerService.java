package com.sparta.limited.user_coupon_consumer.application.service;

import com.sparta.limited.user_coupon_consumer.application.dto.UserCouponIssuedEvent;
import com.sparta.limited.user_coupon_consumer.domain.model.UserCoupon;
import com.sparta.limited.user_coupon_consumer.domain.repository.UserCouponRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCouponConsumerService {

    private final UserCouponRepository userCouponRepository;

    @Transactional
    public void createUserCoupon(
        UserCouponIssuedEvent event
    ) {
        UserCoupon userCoupon = UserCoupon.of(event.getCouponId(), event.getUserId());
        userCouponRepository.save(userCoupon);
    }
}
