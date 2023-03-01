package com.hmdp.utils;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.UserDTO;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.utils
 * @ClassName: loginInterceptor
 * @Author: zcy
 * @Description:拦截器
 * @Date: 2022/7/31 17:00
 * @Version: 1.0
 */
public class loginInterceptor implements HandlerInterceptor {
  private StringRedisTemplate stringRedisTemplate;

  public loginInterceptor(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate=stringRedisTemplate;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
  if(UserHolder.getUser() ==null){
    response.setStatus(401);
    return false;
  }
    return true;  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    UserHolder.removeUser();
  }
}
