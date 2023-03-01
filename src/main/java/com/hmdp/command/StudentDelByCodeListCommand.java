package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.riskpersonnel
 * @ClassName: StudentDelByCodeListCommand
 * @Author: ChnyTrcy
 * @Description: StudentDelByCodeListCommand
 * @Date: 2022/7/27 10:31
 * @Version: 1.0
 */
@Data
@ApiModel("根据学号列表批量删除学生行程 Command")
public class StudentDelByCodeListCommand implements Serializable {

  @ApiModelProperty(value = "学号列表",required = true)
  @NotEmpty(message = "学号列表不能为空")
  List<String> codeList;

}
