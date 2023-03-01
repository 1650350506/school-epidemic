package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.county
 * @ClassName: County
 * @Author: yepeng
 * @Description: 区县实体类
 * @Date: 2022/7/29 13:45
 * @Version: 1.0
 */
@Data
@TableName("county")
@NoArgsConstructor
@ApiModel("区县实体类")
public class County {

  @ApiModelProperty(name = "区县名称", required = true)
  private String name;

  @ApiModelProperty(name = "区县编号", required = true)
  private String code;

  @ApiModelProperty(name = "所属城市编号", required = true)
  private String cityCode;
}
