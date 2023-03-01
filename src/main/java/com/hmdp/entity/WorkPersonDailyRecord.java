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
 * @Package: com.tianque.grid.ce2.domain.model.workpersondailyrecord
 * @ClassName: WorkPersonDailyRecord
 * @Author: yepeng
 * @Description: 防控人员日常记录表实体类
 * @Date: 2022/8/1 13:58
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("work_person_daily_record")
@ApiModel("防控人员日常记录表实体类")
public class WorkPersonDailyRecord  {

  @ApiModelProperty(name = "职工工号", required = true)
  private String code;

  @ApiModelProperty(name = "进入时间", required = true)
  @JSONField(format = "yyyy-MM-dd hh:mm")
  private LocalDateTime startTime;

  @ApiModelProperty(name = "离开时间", required = true)
  @JSONField(format = "yyyy-MM-dd hh:mm")
  private LocalDateTime endTime;

  @ApiModelProperty(name = "健康码颜色", required = true)
  private Byte color;
}
