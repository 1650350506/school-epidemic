package com.hmdp.utils;

import static com.hmdp.utils.RedisConstants.CACHE_NULL_TTL;
import static com.hmdp.utils.RedisConstants.LOCK_SHOP_KEY;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.utils
 * @ClassName: CacheClient
 * @Author: zcy
 * @Description:hh
 * @Date: 2022/8/10 9:19
 * @Version: 1.0
 */
@Slf4j
@Component
public class  CacheClient {

  private final StringRedisTemplate stringRedisTemplate;

  public CacheClient(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

  public void set(String key, Object value, Long time, TimeUnit timeUnit) {

    stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, timeUnit);
  }

  public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit timeUnit) {

    RedisData redisData = new RedisData();
    redisData.setExpireTime(LocalDateTime.now().plusSeconds(timeUnit.toSeconds(time)));
    redisData.setData(value);
    stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
  }

  public <R, ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type,
      Function<ID, R> dbFallback, Long time, TimeUnit timeUnit) {

    String key = keyPrefix + id;
    String s = stringRedisTemplate.opsForValue().get(key);
    if (StrUtil.isNotBlank(s)) {
      return JSONUtil.toBean(s, type);
    }
    if (s != null) {
      return null;
    }
    R r = dbFallback.apply(id);
    if (r == null) {
      stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
      return null;
    }
    this.set(key, r, time, timeUnit);
    return r;

  }

  private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

  public <R, ID> R queryWithLogicalExpire(String keyPrefix, ID id, Class<R> type,
      Function<ID, R> dbFallback, Long time, TimeUnit timeUnit) {
    String key = keyPrefix + id;
    String json = stringRedisTemplate.opsForValue().get(key);
    if (json != null) {
      return null;
    }
    RedisData redisData = JSONUtil.toBean(json, RedisData.class);
    R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);
    LocalDateTime expireTime = redisData.getExpireTime();
    if (expireTime.isAfter(LocalDateTime.now())) {
      return r;
    }
    String lockKey = LOCK_SHOP_KEY + id;
    Boolean isLock = tryLock(lockKey);
    if (isLock) {

      CACHE_REBUILD_EXECUTOR.submit(() -> {
        try {
          R r1 = dbFallback.apply(id);
          this.setWithLogicalExpire(key, r1, time, timeUnit);
        } catch (Exception e) {
          throw new RuntimeException(e);
        } finally {
          unLock(key);
        }
      });
    }
    return r;
  }


  private boolean tryLock(String key) {
    Boolean aBoolean = stringRedisTemplate.opsForValue()
        .setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
    return BooleanUtil.isTrue(aBoolean);
  }

  private void unLock(String key) {
    stringRedisTemplate.delete(key);
  }
}
