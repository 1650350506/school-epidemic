package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.workperson
 * @ClassName: DelByCodeCommand
 * @Author: yepeng
 * @Description: 防控人员管理-删除防控人员command
 * @Date: 2022/7/24 21:55
 * @Version: 1.0
 */
@Data
@ApiModel("防控人员管理-删除防控人员command")
public class DelByCodeCommand implements Serializable {

  @NotNull(message = "未选择code")
  @ApiModelProperty(value = "工号", required = true)
  private String code;
}
