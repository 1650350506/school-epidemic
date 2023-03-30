package com.hmdp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.server.vo.workperson
 * @ClassName: DeptInfoVO
 * @Author: yepeng
 * @Description: 二级学院信息列表
 * @Date: 2022/7/28 14:56
 * @Version: 1.0
 */
@ApiModel("二级学院信息列表 VO")
@Data
@NoArgsConstructor
public class DeptInfoVO implements Serializable {

  @ApiModelProperty(value = "二级学院名称")
  private String name;

  @ApiModelProperty(value = "二级学院编号")
  private String code;
}
