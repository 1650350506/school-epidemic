package com.hmdp.config;

import com.hmdp.utils.AllInteceptor;
import com.hmdp.utils.RefreshTokenInterceptor;
import com.hmdp.utils.loginInterceptor;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.config
 * @ClassName: MvcConfig
 * @Author: zcy
 * @Description:config
 * @Date: 2022/7/31 17:06
 * @Version: 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new loginInterceptor(stringRedisTemplate))
        .excludePathPatterns(
            "/shop/**",
            "/shop-type/**",
            "/upload/**",
            "/blog/hot",
            "/user/code",
            "/user/*",
            "/user/upload",
            "/v2/api-docs/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/**",
            "/swagger-resources",
            "/login",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html",
            "/doc.html"
            ).order(1);
    registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**")
        .order(0);
  }
   public void addCorsMappings(CorsRegistry registry) {
    //跨域问题 解决
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .exposedHeaders("access-control-allow-headers",
            "access-control-allow-methods",
            "access-control-allow-origin",
            "access-control-max-age",
            "X-Frame-Options")
        .allowCredentials(false).maxAge(3600);
    WebMvcConfigurer.super.addCorsMappings(registry);
  }
}
