package com.hmdp.service.impl;

import static com.hmdp.utils.RedisConstants.LOGIN_CODE_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_CODE_TTL;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;
import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Register;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.dto.UserLoginDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.ContactPropertiesUtils;
import com.hmdp.utils.JwtUtils;
import com.hmdp.utils.RegexUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public Result sendCode(String phone, HttpSession session) {
    if (RegexUtils.isPhoneInvalid(phone)) {
      return Result.fail("手机号格式错误");
    }
    String code = RandomUtil.randomNumbers(6);
    stringRedisTemplate.opsForValue()
        .set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
    log.debug("发送短信验证码成功：{}", code);
    return Result.ok("HHHH");
  }

  @Override
  public Result login(LoginFormDTO loginFormDTO, HttpSession session) throws IOException {
    String phone = loginFormDTO.getPhone();
    if (RegexUtils.isPhoneInvalid(phone)) {
      return Result.fail("手机号格式错误");
    }
    String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
    String code = loginFormDTO.getCode();
    if (cacheCode == null || !cacheCode.equals(code)) {
      return Result.fail("验证码错误");
    }
    User user = query().eq("phone", phone).one();
    if (user == null) {
      user = createUserWithPhone(phone);
    }
    String token = UUID.randomUUID().toString(true);
    UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
    Map<String, Object> map = BeanUtil.beanToMap(userDTO,new HashMap<>(),
        CopyOptions.create()
            .setIgnoreNullValue(true).setFieldValueEditor((fieldName,fieldValue)->fieldValue.toString()));
    stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,map);
    stringRedisTemplate.expire(LOGIN_USER_KEY,LOGIN_USER_TTL,TimeUnit.MINUTES);

    return Result.ok(token);
  }

  @Override
  public String uploadFileAvatar(MultipartFile file) {
    String endpoint = ContactPropertiesUtils.END_POINT;
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = ContactPropertiesUtils.ACCESS_KEY_ID;
    String accessKeySecret = ContactPropertiesUtils.ACCESS_KEY_SECRET;
    String bucketName = ContactPropertiesUtils.BUCKET_NAME;

    try {

      // 获取文件名称
      String realName = file.getOriginalFilename();
      // 创建OSSClient实例。
      OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
      // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
      InputStream inputStream = file.getInputStream();
      // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
      ossClient.putObject(bucketName, realName, inputStream);
      // 关闭OSSClient。
      ossClient.shutdown();
      String url = "https://" + bucketName + "." + endpoint + "/" + realName;
      return url;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Result userLogin(UserLoginDTO userLoginDTO) {
    String username = userLoginDTO.getUsername();
    String password = userLoginDTO.getPassword();
    User user = query().eq("username", username).eq("password", password).one();
    if(user==null){
      return Result.fail("账号密码不正常");
    }
    String token = JwtUtils.getToken(user);
    UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
    Map<String, Object> map = BeanUtil.beanToMap(userDTO,new HashMap<>(),
        CopyOptions.create()
            .setIgnoreNullValue(true).setFieldValueEditor((fieldName,fieldValue)->fieldValue.toString()));
    stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,map);
    stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL,TimeUnit.MINUTES);
    List<String> objects = new ArrayList<>();
    objects.add(token);
    objects.add(user.getRole());
    return Result.ok(objects);
  }
/**
 * @Description: 用户注册
 * @Author: zcy
 * @Date: 2022/9/21 16:58
 * @Param: [register]
 * @Return: com.hmdp.dto.Result
 */
  @Override
  public Result userRegister(Register register) {
     String phone = register.getPhone();
    if (RegexUtils.isPhoneInvalid(phone)) {
      return Result.fail("手机号格式错误");
    }
    User one = query().eq("Phone", register.getPhone()).or()
        .eq("username", register.getUsername()).one();
    if(ObjectUtil.isNotEmpty(one)){
      throw new RuntimeException("账号已存在");
    }
    User user = User.builder().username(register.getUsername())
        .password(register.getPassword()).phone(register.getPhone()).icon(register.getIcon()).build();
    user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
    save(user);
    return Result.ok(user.getId());
  }

  @Override
  public Result logout(HttpServletRequest request) {
    String auth = request.getHeader("auth");
    return Result.ok(stringRedisTemplate.delete(LOGIN_USER_KEY + auth));
  }

  public User createUserWithPhone(String phone) throws IOException {
    User user =new User();
    user.setPhone(phone);
    user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
    user.setUsername(phone);
    String password = RandomUtil.randomNumbers(8);
    user.setPassword(password);
    boolean save = save(user);
    if(save) {
      try {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", "张老三啊"),new NameValuePair("Key", "8F0BF3E3D33617B4A82487F4DD208350"),new NameValuePair("smsMob","18905759595"),new NameValuePair("smsText","成功了吗"+password)};
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
      }catch (Exception e){
        throw new RuntimeException("短信发送失败：e:" + e.getMessage());
      }

    }
    return user;
  }
}
