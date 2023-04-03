package com.hmdp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.server.vo.workperson
 * @ClassName: WorkPersonRecordVO
 * @Author: yepeng
 * @Description: 通行记录VO
 * @Date: 2022/8/2 17:44
 * @Version: 1.0
 */
@ApiModel("通行记录VO")
@Data
@NoArgsConstructor
public class WorkPersonRecordVO implements Serializable {

  @ApiModelProperty(value = "入校时间", required = true)
  private LocalDateTime startTime;

  @ApiModelProperty(value = "离校时间", required = true)
  private LocalDateTime endTime;

  @ApiModelProperty(value = "健康码颜色", required = true)
  private Byte color;
}
