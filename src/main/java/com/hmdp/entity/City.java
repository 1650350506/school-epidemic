package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.city
 * @ClassName: City
 * @Author: ChnyTrcy
 * @Description: 城市表
 * @Date: 2022/7/19 15:30
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("city")
@ApiModel(value = "城市实体类")
public class City  {

  @ApiModelProperty("城市编号")
  private String code;

  @ApiModelProperty("城市名称")
  private String name;

  @ApiModelProperty("风险等级")
  @TableField("risk_level")
  private Byte riskLevel;

  @ApiModelProperty("所属省份编号")
  @TableField("province_code")
  private String provinceCode;
}
