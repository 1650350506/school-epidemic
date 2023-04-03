package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.supadmin
 * @ClassName: SupAdminQueryByCodeCommand
 * @Author: yepeng
 * @Description: 防疫人员管理-根据code查询工作人员详情信息
 * @Date: 2022/7/15 17:20
 * @Version: 1.0
 */
@Data
@ApiModel("防疫人员管理-根据code查询工作人员详情信息Command")
@NoArgsConstructor
public class QueryWorkPersonByCodeCommand implements Serializable {

  @NotNull(message = "编号不能为空")
  @ApiModelProperty(value = "编号",required = true)
  private String code;
}
