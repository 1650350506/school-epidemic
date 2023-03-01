package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.riskpersonnel
 * @ClassName: StudentAddCrossCityCommand
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/7/28 16:48
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加学生入校Command")
public class StudentAddCrossCityCommand implements Serializable {

  @ApiModelProperty(value = "学号",required = true)
  @NotBlank(message = "学号不能为空")
  @Size(max = 10,min = 10,message = "学号长度错误")
  private String code;

  @ApiModelProperty(value = "城市列表",required = true)
  @NotEmpty(message = "城市列表不能为空")
  private List<String> cityList;

}
