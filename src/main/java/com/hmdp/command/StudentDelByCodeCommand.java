package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chnytrcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("根据学号删除Student Command")
public class StudentDelByCodeCommand implements Serializable {

  @ApiModelProperty(value = "学号",required = true)
  @NotBlank(message = "学号不能为空")
  private String code;
}
