package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.supadmin
 * @ClassName: SupAdminUpdateWorkPersonByCodeCommand
 * @Author: yepeng
 * @Description: 防控人员管理-修改防控人员信息command
 * @Date: 2022/7/21 10:05
 * @Version: 1.0
 */
@Data
@ApiModel("防控人员管理-修改防控人员信息command")
public class UpdateWorkPersonByCodeCommand implements Serializable {

  @NotNull(message = "工号为空")
  @ApiModelProperty(value = "工号", required = true)
  private String code;

  @NotNull(message = "手机号为空")
  @ApiModelProperty(value = "手机号", required = true)
  private String phone;

  @NotNull(message = "当前职务为空")
  @ApiModelProperty(value = "当前职务", required = true)
  private String systemPost;

  @NotNull(message = "校内职务为空")
  @ApiModelProperty(value =" 校内职务", required = true)
  private String schoolPost;
}
