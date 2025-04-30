package com.sparta.limited.user_coupon_consumer.infrastructure.persistence;

import com.sparta.limited.user_coupon_consumer.domain.model.UserCoupon;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserCouponRepository extends JpaRepository<UserCoupon, UUID> {

}
