package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.major
 * @ClassName: Major
 * @Author: ChnyTrcy
 * @Description: 专业实体类
 * @Date: 2022/7/20 17:31
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("major")
@ApiModel("专业实体类")
public class Major  {

  @ApiModelProperty(value = "专业名称")
  private String name;

  @ApiModelProperty(value = "院系编号")
  @TableField("dept_code")
  private String deptCode;

  @ApiModelProperty(value = "专业编号")
  private String code;
}
