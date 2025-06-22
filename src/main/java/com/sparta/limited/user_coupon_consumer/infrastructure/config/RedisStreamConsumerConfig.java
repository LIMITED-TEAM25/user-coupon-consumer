package com.sparta.limited.user_coupon_consumer.infrastructure.config;

import com.sparta.limited.user_coupon_consumer.application.service.UserCouponEventConsumer;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer.StreamMessageListenerContainerOptions;

@Configuration
@RequiredArgsConstructor
public class RedisStreamConsumerConfig {

    private final RedisConnectionFactory redisConnectionFactory;
    private final UserCouponEventConsumer userCouponEventConsumer;

    @Bean(destroyMethod = "stop")
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer() {
        StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
            StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofMillis(2000))
                .build();

        StreamMessageListenerContainer<String, MapRecord<String, String, String>> container =
            StreamMessageListenerContainer.create(redisConnectionFactory, options);

        try {
            RedisConnection connection = redisConnectionFactory.getConnection();
            connection.streamCommands().xGroupCreate(
                "user-coupon:queue".getBytes(),
                "user-coupon-group",
                ReadOffset.latest(),
                true
            );
        } catch (Exception ignored) {}

        container.receive(
            Consumer.from("user-coupon-group", "consumer-1"),
            StreamOffset.create("user-coupon:queue", ReadOffset.lastConsumed()),
            userCouponEventConsumer
        );

        container.start();
        return container;
    }

}
