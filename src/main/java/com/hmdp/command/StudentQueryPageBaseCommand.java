package com.hmdp.command;

import com.hmdp.dto.GridPageCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author chnytrcy
 */
@Data
@ApiModel("学生查询--分页查询 Command")
public class StudentQueryPageBaseCommand extends GridPageCommand implements Serializable {

  @ApiModelProperty("关键词查询")
  @Size(max = 20,message = "关键词长度为0～20")
  private String keyword;

  @ApiModelProperty(value = "风险地区等级筛选",
      notes = "缺省表示无过滤条件，1表示中风险，2表示高风险")
  @Max(value = 2,message = "请输入1或者2")
  @Min(value = 1,message = "请输入1或者2")
  private Integer riskLevel;

}
