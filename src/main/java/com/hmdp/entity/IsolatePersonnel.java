package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chnytrcy
 */
@Data
@NoArgsConstructor
@TableName("isolate_personnel")
@ApiModel("隔离人员表")
public class IsolatePersonnel {
  @TableId(
      value = "id",
      type = IdType.ASSIGN_ID
  )
  private Long id;

  @ApiModelProperty(value = "统一编号")
  private String code;

  @ApiModelProperty("现居住地点")
  private String address;

  @ApiModelProperty(value = "开始隔离时间")
  @TableField("start_time")
  private Timestamp startTime;

  @TableField("isolation_location")
  @ApiModelProperty(value = "隔离地点")
  private String isolationLocation;

  @ApiModelProperty(value = "状态（0:已通知未隔离 1:已隔离 2:隔离结束）")
  private Integer state;

  @ApiModelProperty(value = "结束隔离时间")
  @TableField("end_time")
  private Timestamp endTime;


  @ApiModelProperty(value = "隔离原因")
  @TableField("isolation_reason")
  private String isolationReason;
}
