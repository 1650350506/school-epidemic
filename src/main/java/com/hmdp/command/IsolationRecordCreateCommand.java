package com.hmdp.command;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.isolatationrecord
 * @ClassName: IsolationRecordCreateCommand
 * @Author: zcy
 * @Description: 隔离人员信息CreateCommand
 * @Date: 2022/7/20 13:39
 * @Version: 1.0
 */
@Data
@ApiModel("新增隔离记录")
public class IsolationRecordCreateCommand implements Serializable {

  @ApiModelProperty(value = "统一编号",required = true)
  @NotBlank(message = "不能为空")
  private String code;

  @ApiModelProperty(value = "核酸时间",required = true)
  @NotNull(message = "不能为空")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp nucleicAcidTime;


  @ApiModelProperty(value = "核酸结果",required = true)
  @NotNull(message = "不能为空")
  private Integer nucleicAcidKey;


  @ApiModelProperty(value = "体温",required = true)
  @NotNull(message = "不能为空")
  private float temperature;


  @ApiModelProperty(value = "防疫人员编号",required = true)
  @NotBlank(message = "不能为空")
  private String protector;
}
