package com.hmdp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.dto
 * @ClassName: register
 * @Author: zcy
 * @Description:用户注册
 * @Date: 2022/9/21 15:16
 * @Version: 1.0
 */
@Data
@ApiModel(value = "用户注册对象",description = "用户注册dto")
public class Register {
  @ApiModelProperty(value = "手机号",required = true)
  @NotBlank(message = "手机号不能为空")
  private String phone;

  @NotBlank(message = "用户名不能为空")
  @ApiModelProperty(value = "用户名",required = true)
  private String username;

  @NotBlank(message = "密码不能为空")
  @ApiModelProperty(value = "密码",required = true)
  private String password;


  /**
   * 用户头像
   */
  @ApiModelProperty(value = "头像地址")
  private String icon = "";


}
