package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApiModel("新增隔离人员日期参数")
public class IsolationrecordAnalysisCommand implements Serializable {

  @ApiModelProperty(value = "统一编号",required = true)
  @NotNull(message = "不能为空")
  private Integer code;
}
