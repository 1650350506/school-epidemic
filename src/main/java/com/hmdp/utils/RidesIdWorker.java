package com.hmdp.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.utils
 * @ClassName: RidesIdWorker
 * @Author: zcy
 * @Description:gjl
 * @Date: 2022/8/10 22:20
 * @Version: 1.0
 */
@Component
public class RidesIdWorker {
  private static  final long BEGIN_TIMESTAMP =1660089600L;
  private static  final  int COUNT_BITS=32;
  @Resource
  private StringRedisTemplate stringRedisTemplate;
  public long nextId(String keyPrefix){
    LocalDateTime now = LocalDateTime.now();
    long l = now.toEpochSecond(ZoneOffset.UTC);
    long timestamp = l - BEGIN_TIMESTAMP;
    String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    Long increment = stringRedisTemplate.opsForValue().increment("icr:" + keyPrefix + ":" + date);
    return timestamp<<COUNT_BITS | increment;
  }


  }

