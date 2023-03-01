package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model
 * @ClassName: Province
 * @Author: ChnyTrcy
 * @Description: 省份实体类
 * @Date: 2022/7/19 14:53
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("province")
@ApiModel("省份实体类")
public class Province  {

  @ApiModelProperty(value = "省份编码")
  private String code;

  @ApiModelProperty(value = "省份名称")
  private String name;
}
