package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.riskpersonnel
 * @ClassName: StudentJourneyByCodeCommand
 * @Author: ChnyTrcy
 * @Description: StudentJourneyByCodeCommand
 * @Date: 2022/7/26 16:01
 * @Version: 1.0
 */
@Data
@ApiModel("根据学生学号获得学生行程")
public class StudentJourneyByCodeCommand {

  @ApiModelProperty(value = "学号",required = true)
  @NotBlank(message = "学生学号不能为空")
  @Size(min = 10,max = 10,message = "学号长度不匹配")
  private String code;
}
