package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.isolatationrecord
 * @ClassName: IsolationRecordRelieveCommand
 * @Author: zcy
 * @Description: 隔离人员信息RelieveCommand
 * @Date: 2022/7/20 13:39
 * @Version: 1.0
 */
@Data
@ApiModel("解除隔离")
public class IsolationRecordRelieveCommand implements Serializable {

  @ApiModelProperty(value = "统一编号",required = true)
  @NotBlank(message = "不能为空")
  private String code;

  @ApiModelProperty(value = "用户类型",required = true)
  @NotNull(message = "不能为空")
  private Integer type;



}
