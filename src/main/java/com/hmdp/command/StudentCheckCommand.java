package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.riskpersonnel
 * @ClassName: StudentCheckCommand
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/7/28 15:49
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("检查学生是否合法")
public class StudentCheckCommand implements Serializable {

  @ApiModelProperty(value = "学号",required = true)
  @NotBlank(message = "学号不能为空")
  @Size(max = 10,min = 10,message = "学号长度错误")
  private String code;

  @ApiModelProperty(value = "姓名",required = true)
  @NotBlank(message = "姓名不能为空")
  @Size(max = 40,message = "姓名长度过长")
  private String name;
}
