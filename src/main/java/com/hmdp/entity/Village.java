package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.village
 * @ClassName: Village
 * @Author: yepeng
 * @Description: 村/社区实体类
 * @Date: 2022/7/25 16:04
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("village")
@ApiModel("村/社区实体类")
public class Village  {

  @ApiModelProperty(name = "村/社区名称", required = true)
  private String name;

  @ApiModelProperty(name = "村/社区编号", required = true)
  private String code;

  @ApiModelProperty(name = "所属乡镇/街道编号", required = true)
  private String townCode;

  @ApiModelProperty(name = "风险等级-0:低风险 1:中风险 2:高风险", required = true)
  private Integer riskLevel;
}
