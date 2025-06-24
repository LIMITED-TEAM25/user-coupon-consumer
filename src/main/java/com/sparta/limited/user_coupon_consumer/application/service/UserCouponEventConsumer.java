package com.sparta.limited.user_coupon_consumer.application.service;

import com.sparta.limited.user_coupon_consumer.application.dto.UserCouponIssuedEvent;
import com.sparta.limited.user_coupon_consumer.application.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCouponEventConsumer implements StreamListener<String, MapRecord<String, String, String>> {

    private final UserCouponConsumerService userCouponConsumerService;

    @Override
    public void onMessage(MapRecord<String, String, String> record) {
        UserCouponIssuedEvent event = EventMapper.eventToDto(record);
        userCouponConsumerService.createUserCoupon(event);
    }
}
