package com.hmdp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class HmDianPingApplicationTests {
  
  public static final String a="hhhh";
  @Autowired
  private static ceshi S;
  private static final int SIZE = 6;
  private static final String SYMBOL = "*";

@Test
void get ()throws Exception{
  HttpClient client = new HttpClient();
  PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
  post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
  NameValuePair[] data ={ new NameValuePair("Uid", "张老三啊"),new NameValuePair("Key", "8F0BF3E3D33617B4A82487F4DD208350"),new NameValuePair("smsMob","18905759595"),new NameValuePair("smsText","成功了吗")};
  post.setRequestBody(data);
  client.executeMethod(post);
  Header[] headers = post.getResponseHeaders();
  int statusCode = post.getStatusCode();
  System.out.println("statusCode:"+statusCode);
  for(Header h : headers)
  {
    System.out.println(h.toString());
  }
  String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
  System.out.println(result); //打印返回消息状态
  post.releaseConnection();
  }
@Test
  void sendmessage() {
//  try {
//    HttpClientSingle httpClientSingle = HttpClientSingle.getInstance(smsConfigProperties.getUrl());
//    CloseableHttpClient httpClient = httpClientSingle.getHttpClient();
//    HttpPost httpPost = httpClientSingle.getHttpPost();
//    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//    String finalMessage1 = finalMessage;
//    String responseBody = httpClient.execute(httpPost, httpResponse -> {
//      int status = httpResponse.getStatusLine().getStatusCode();
//      if (status < 200 || status >= 300) {
//        throw new RuntimeException("短信发送失败：phone:" + dests + ", message:" + finalMessage1 + "status:" + status);
//      }
//      HttpEntity entity = httpResponse.getEntity();
//      return entity != null ? EntityUtils.toString(entity) : null;
//    });
//    log.info("短信发送成功: phone: {}, 内容: {}", dests, message);
//    return true;
//  } catch (IOException e) {
//    throw new RuntimeException("短信发送失败：e:" + e.getMessage());
//}
}
@Test
  void hh(){
String s="";
  if (s == null) {
    System.out.println(1);
  } else {
    System.out.println(2);
  }
}

/**
   * 通用脱敏工具类
   * 可用于：
   *      用户名
   *      手机号
   *      邮箱
   *      地址等
   */



    @Test
    void main() {
      String name = commonDisplay("小小杰");
      String mobile = commonDisplay("153998805255");
      String mail = commonDisplay("1412112323@qq.com");
      String address = commonDisplay("湖南省湘潭市岳塘区岳塘大道");

      System.out.println(name);
      System.out.println(mobile);
      System.out.println(mail);
      System.out.println(address);
    }

    /**
     * 通用脱敏方法
     * @param value
     * @return
     */
    public static String commonDisplay(String value) {
      if (null == value || "".equals(value)) {
        return value;
      }
      int len = value.length();
      int pamaone = len / 2;
      int pamatwo = pamaone - 1;
      int pamathree = len % 2;
      StringBuilder stringBuilder = new StringBuilder();
      if (len <= 2) {
        if (pamathree == 1) {
          return SYMBOL;
        }
        stringBuilder.append(SYMBOL);
        stringBuilder.append(value.charAt(len - 1));
      } else {
        if (pamatwo <= 0) {
          stringBuilder.append(value.substring(0, 1));
          stringBuilder.append(SYMBOL);
          stringBuilder.append(value.substring(len - 1, len));

        } else if (pamatwo >= SIZE / 2 && SIZE + 1 != len) {
          int pamafive = (len - SIZE) / 2;
          stringBuilder.append(value.substring(0, pamafive));
          for (int i = 0; i < SIZE; i++) {
            stringBuilder.append(SYMBOL);
          }
          if ((pamathree == 0 && SIZE / 2 == 0) || (pamathree != 0 && SIZE % 2 != 0)) {
            stringBuilder.append(value.substring(len - pamafive, len));
          } else {
            stringBuilder.append(value.substring(len - (pamafive + 1), len));
          }
        } else {
          int pamafour = len - 2;
          stringBuilder.append(value.substring(0, 1));
          for (int i = 0; i < pamafour; i++) {
            stringBuilder.append(SYMBOL);
          }
          stringBuilder.append(value.substring(len - 1, len));
        }
      }
      return stringBuilder.toString();
    }


//  public static void main(String[] args) {
//    //时间工具类
//    Calendar instance = Calendar.getInstance();
//    //设置过期时间  单位：SECOND秒  一个小时失效
//    instance.add(Calendar.SECOND,60*60);
//
//    JWTCreator.Builder builder = JWT.create()
//        //添加键值对数据
//        .withClaim("id", 1)
//        //添加过期时间 exp 也可以添加key为exp valus为到期时间的时间戳和这个效果一样
//        //.withClaim("exp",1664815770)
//        .withExpiresAt(instance.getTime());
//    // 选择签名算法HMAC256 添加密钥字符串mango（盐）
//    String token = builder.sign(Algorithm.HMAC256("mango"));
//    //输出token
//    System.out.println(token);
//  }
  public static void main(String[] args)  {
    //验证的token
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjc3NTg4NDk5fQ.5s9nArx_13leGhJ726BndDLGa3_acHk0A9v6LGKbJeE";
    try {
      //提供密钥字符串（盐）和token
      DecodedJWT jwt = JWT.require(Algorithm.HMAC256("mango")).build().verify(token);
      //输出存储在Payload中键值对key为id的value 即id
      System.out.println(jwt.getClaim("id"));
      //token设置过期时间就会有key为exp的键值对 value是到期时间的时间戳
      System.out.println(jwt.getClaim("exp"));
    } catch (TokenExpiredException e) {
      //令牌过期抛出异常
      System.out.println("令牌过期");
    } catch (Exception e) {
      //token非法验证失败抛出异常
      System.out.println("检验失败");
    }
  }

  }



