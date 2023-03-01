package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Register;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserLoginDTO;
import com.hmdp.entity.User;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {
  public Result sendCode(String phone, HttpSession session);
  public Result login(LoginFormDTO loginFormDTO, HttpSession session) throws IOException;

  String uploadFileAvatar(MultipartFile file);

  Result userLogin(UserLoginDTO userLogin);

  Result userRegister(Register register);

  Result logout(HttpServletRequest request);
}
