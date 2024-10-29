package com.backdevfc.msregister.config;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Service
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;


    public void saveKeyValue(String key, String value, int expire) {
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }

    public String getValueByKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key) {
        stringRedisTemplate.delete(key);
    }
}
