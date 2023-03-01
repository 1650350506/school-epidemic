package com.hmdp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.dto
 * @ClassName: UserLogin
 * @Author: zcy
 * @Description:账号密码登录
 * @Date: 2022/9/21 14:03
 * @Version: 1.0
 */
@Data
@ApiModel(value = "密码登录对象",description = "密码登录")
public class UserLoginDTO {
  @ApiModelProperty(value = "账号",required = true)
  @NotBlank(message = "账号不能为空")
  private  String username;


  @ApiModelProperty(value = "密码",required = true)
  @NotBlank(message = "密码不能为空")
  private  String password;
}
