package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.isolatationrecord
 * @ClassName: IsolationRecordQueryCommand
 * @Author: zcy
 * @Description: 隔离人员信息QueryCommand
 * @Date: 2022/7/20 13:39
 * @Version: 1.0
 */
@Data
@ApiModel("隔离记录查询")
public class IsolationRecordQueryCommand  implements Serializable {

  @ApiModelProperty(value = "统一编号",required = true)
  @NotBlank(message = "不能为空")
  private String code;
}
