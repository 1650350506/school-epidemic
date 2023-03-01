package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;


/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.isolatepersonnel
 * @ClassName: IsolaterpersonnelQueryCommand
 * @Author: zcy
 * @Description: 隔离人员QueryCommand
 * @Date: 2022/7/18 13:39
 * @Version: 1.0
 */
@Data
@ApiModel("隔离人员搜索并分页Command")
public class IsolatePersonnelQueryCommand  implements Serializable {

  @ApiModelProperty("关键词查询")
  private String keyword;

  @ApiModelProperty(value = "隔离状态")
  private Integer state;
}
