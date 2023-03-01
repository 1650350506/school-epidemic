package com.hmdp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.leavedetails
 * @ClassName: LeaveDetail
 * @Author: yepeng
 * @Description: 日常行程记录实体类
 * @Date: 2022/7/18 14:26
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("daily_travel_record")
@ApiModel("日常行程记录实体类")
public class DailyTravelRecord {

  @ApiModelProperty(name = "学号", required = true)
  private String code;

  @ApiModelProperty(name = "离校时间", required = true)
  @JSONField(format = "yyyy-MM-dd hh:mm")
  private LocalDateTime startTime;

  @ApiModelProperty(name = "返校时间")
  @JSONField(format = "yyyy-MM-dd hh:mm")
  private LocalDateTime endTime;

  @ApiModelProperty(name = "地区")
  private String whereCode;

  @ApiModelProperty(name = "详细地址")
  private String whereDetail;

  @ApiModelProperty(name = "行程信息(途径哪些地区)", required = true)
  private String travelRecord;

  @ApiModelProperty(name = "是否跨市",required = true)
  private Integer type;
}
