package com.superychen.base.redis.repository;

import com.superychen.base.mybatis.entity.test.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, String val) {
        stringRedisTemplate.opsForValue().set(key, val);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void set(String key, Test val) {
        redisTemplate.opsForValue().set(key, val);
    }

    public Test getTest(String key) {
        return (Test) redisTemplate.opsForValue().get(key);
    }

}
