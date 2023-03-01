package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chnytrcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("isolate_details")
@ApiModel("隔离记录详情表")
public class IsolateDetails {

  @ApiModelProperty(value = "统一编号",required = true)
  private String code;

  @TableField("nucleic_acid_time")
  @ApiModelProperty(value = "核酸时间",required = true)
  private Timestamp nucleicAcidTime;

  @TableField("nucleic_acid_key")
  @ApiModelProperty(value = "核酸结果（0:阴性；1:阳性）",required = true)

  private Integer nucleicAcidKey;

  @ApiModelProperty(value = "体温",required = true)
  private Float temperature;

  @ApiModelProperty(value = "防疫人员编号",required = true)
  private String protector;
}
