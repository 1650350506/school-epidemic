package com.hmdp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hmdp.entity.User;
import java.util.Calendar;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.utils
 * @ClassName: JwtUtils
 * @Author: zcy
 * @Description:JWT
 * @Date: 2023/2/28 19:58
 * @Version: 1.0
 */
public class JwtUtils {

  public static String getToken(User user) {
    //时间工具类
    Calendar instance = Calendar.getInstance();
    //设置过期时间  单位：SECOND秒  一个小时失效
    instance.add(Calendar.SECOND, 60 * 60);

    JWTCreator.Builder builder = JWT.create()
        //添加键值对数据
        .withClaim("username", user.getUsername())
        //添加过期时间 exp 也可以添加key为exp valus为到期时间的时间戳和这个效果一样
        .withClaim("password", user.getPassword())
        .withClaim("id", user.getId())
        .withExpiresAt(instance.getTime());
    // 选择签名算法HMAC256 添加密钥字符串mango（盐）
    String token = builder.sign(Algorithm.HMAC256("cheng"));
    //输出token
    return token;
  }

  public static User check(String token) {
    //验证的token
    User user = new User();
    try {

      //提供密钥字符串（盐）和token
      DecodedJWT jwt = JWT.require(Algorithm.HMAC256("cheng")).build().verify(token);
      //输出存储在Payload中键值对key为id的value 即id
      Claim username = jwt.getClaim("username");
      Claim password = jwt.getClaim("password");
      Claim id=jwt.getClaim("id");
      String name = String.valueOf(username);
      String word = String.valueOf(password);
      Long useId = Long.valueOf(String.valueOf(id));
      user.setUsername(name);
      user.setPassword(word);
      user.setId(useId);
      //token设置过期时间就会有key为exp的键值对 value是到期时间的时间戳
    } catch (TokenExpiredException e) {
      //令牌过期抛出异常
      throw new RuntimeException("令牌过期");
    } catch (Exception e) {
      //token非法验证失败抛出异常
      throw new RuntimeException("token非法验证失败");
    }
    return user;
  }

}
