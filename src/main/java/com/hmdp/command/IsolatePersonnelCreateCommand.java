package com.hmdp.command;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.isolatepersonnel
 * @ClassName: IsolatepersonnelCreateCommand
 * @Author: zcy
 * @Description: 隔离人员CreateCommand
 * @Date: 2022/7/18 13:39
 * @Version: 1.0
 */

@Data
@ApiModel("添加隔离人员")
public class IsolatePersonnelCreateCommand implements Serializable {

  @ApiModelProperty(value = "统一编号", required = true)
  @NotBlank(message = "不能为空")
  private String code;

  @ApiModelProperty("地址")
  private String address;

  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "开始隔离时间", required = true)
  private Timestamp startTime;

  @ApiModelProperty(value = "隔离地点", required = true)
  @NotBlank(message = "不能为空")
  private String isolationLocation;


  @ApiModelProperty(value = "隔离状态",required = true)
  @NotNull(message = "不能为空")
  private Integer state;

  @ApiModelProperty(value = "隔离原因", required = true)
  @NotBlank(message = "不能为空")
  private String isolationReason;


}
