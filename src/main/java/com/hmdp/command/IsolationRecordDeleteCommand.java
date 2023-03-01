package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.riskpersonnel
 * @ClassName: IsolationRecordDeleteCommand
 * @Author: zcy
 * @Description:删除隔离记录Command
 * @Date: 2022/7/29 17:44
 * @Version: 1.0
 */
@Data
@ApiModel("隔离记录删除")
public class IsolationRecordDeleteCommand implements Serializable {

  @ApiModelProperty(value = "id",required = true)
  @NotNull(message = "不能为空")
  protected Long id;
}
