package com.hmdp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.server.vo
 * @ClassName: EppPerson
 * @Author: yepeng
 * @Description: 防疫人员列表item VO
 * @Date: 2022/7/15 10:22
 * @Version: 1.0
 */
@ApiModel("防疫人员列表item VO")
@Data
@NoArgsConstructor
public class WorkPersonVO implements Serializable{

  @ApiModelProperty(value = "职工工号")
  private String code;

  @ApiModelProperty(value = "教职工姓名")
  private String name;

  @ApiModelProperty(value = "联系电话")
  private String phone;

  @ApiModelProperty(value = "当前职务")
  private String systemPost;

  @ApiModelProperty(value = "二级学院")
  private String secondCollage;

  @ApiModelProperty(value = "健康码颜色")
  private Byte color;
}
