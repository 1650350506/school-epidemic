package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.classPositioning
 * @ClassName: Dept
 * @Author: ChnyTrcy
 * @Description: 院系实体类
 * @Date: 2022/7/20 18:17
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dept")
@ApiModel("院系")
public class Dept  {

  @ApiModelProperty("院系名称")
  private String name;

  @ApiModelProperty("院系编号")
  private String code;

}
