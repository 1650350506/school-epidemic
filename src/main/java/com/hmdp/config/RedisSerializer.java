//package com.hmdp.config;
//
//
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONWriter.Feature;
//import java.nio.charset.StandardCharsets;
//import org.springframework.data.redis.serializer.SerializationException;
//import org.springframework.stereotype.Component;
//
///**
// * @ProjectName: school-epidemic
// * @Package: com.hmdp.config
// * @ClassName: RedisSerializer
// * @Author: zcy
// * @Description:
// * @Date: 2023/4/4 0:45
// * @Version: 1.0
// */
//@Component
//public class RedisSerializer extends GenericFastJsonRedisSerializer {
//
//  @Override
//  public byte[] serialize(Object object) throws SerializationException {
//    if (object == null) {
//      return new byte[0];
//    } else {
//      if(object instanceof String){
//        return ((String) object).getBytes(StandardCharsets.UTF_8);
//      }
//      try {
//        return JSON.toJSONBytes(object,
//            Feature.valueOf(String.valueOf(SerializerFeature.WriteClassName)));
//      } catch (Exception var3) {
//        throw new SerializationException("Could not serialize: " + var3.getMessage(), var3);
//      }
//    }
//  }
//
//}
