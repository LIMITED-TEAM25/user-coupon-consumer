package com.sparta.limited.user_coupon_consumer.application.mapper;

import com.sparta.limited.user_coupon_consumer.application.dto.UserCouponIssuedEvent;
import java.util.UUID;
import org.springframework.data.redis.connection.stream.MapRecord;

public class EventMapper {

    public static UserCouponIssuedEvent eventToDto(
        MapRecord<String, String, String> record
    ) {
        UUID couponId = UUID.fromString(record.getValue().get("couponId"));
        Long userId = Long.valueOf(record.getValue().get("userId"));
        return UserCouponIssuedEvent.of(
            couponId,
            userId
        );
    }
}
