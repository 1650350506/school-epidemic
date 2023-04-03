package com.hmdp.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("查看隔离记录")
@NoArgsConstructor
public class IsolaterecordVO  implements Serializable {

  @ApiModelProperty(value = "统一编号", required = true)
  @NotNull(message = "不能为空")
  private String code;


  @ApiModelProperty(value = "核酸时间", required = true)
  @NotNull(message = "不能为空")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp nucleicAcidTime;


  @ApiModelProperty(value = "核酸结果", required = true)
  @NotNull(message = "不能为空")
  private int nucleicAcidKey;
  @ApiModelProperty(value = "体温")


  private float temperature;

  @ApiModelProperty(value = "防疫人员编号")
  private String protector;
  @ApiModelProperty(value = "防疫人员名称")
  private String protectorName;

}
