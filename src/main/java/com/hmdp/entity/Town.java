package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.town
 * @ClassName: Town
 * @Author: yepeng
 * @Description: 乡镇/街道实体类
 * @Date: 2022/7/25 15:00
 * @Version: 1.0
 */
@Data
@TableName("town")
@NoArgsConstructor
@ApiModel("乡镇/街道实体类")
public class Town  {

  @ApiModelProperty(name = "乡镇/街道名称", required = true)
  private String name;

  @ApiModelProperty(name = "乡镇/街道编号", required = true)
  private String code;

  @ApiModelProperty(name = "所属区县编号", required = true)
  private String countyCode;

}
