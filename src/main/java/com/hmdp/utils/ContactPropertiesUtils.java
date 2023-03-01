package com.hmdp.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.utils
 * @ClassName: ContactPropertiesUtils
 * @Author: zcy
 * @Description:oss
 * @Date: 2022/9/20 11:20
 * @Version: 1.0
 */
@Component
public class ContactPropertiesUtils implements InitializingBean {


    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    public static String END_POINT;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    public static String BUCKET_NAME;
  @Override
  public void afterPropertiesSet() throws Exception {
    END_POINT = endpoint;
    ACCESS_KEY_ID = keyId;
    ACCESS_KEY_SECRET = keySecret;
    BUCKET_NAME = bucketName;
  }


}
