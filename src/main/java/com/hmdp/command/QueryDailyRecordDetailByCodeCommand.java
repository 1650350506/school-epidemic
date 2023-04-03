package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.groupleader
 * @ClassName: GroupLeaderQueryByCodeCommand
 * @Author: yepeng
 * @Description: 学生行程信息-查询学生本市行程信息详情信息command
 * @Date: 2022/7/15 17:42
 * @Version: 1.0
 */
@Data
@ApiModel("学生行程信息-查询学生本市行程信息详情信息command")
public class QueryDailyRecordDetailByCodeCommand implements Serializable {

  @NotNull(message = "学号不能为空")
  @ApiModelProperty(value = "学号", required = true)
  private String code;
}
