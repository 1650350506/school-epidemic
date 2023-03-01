package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chnytrcy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cross_city_travel_record")
@ApiModel("跨市行程记录表")
public class CrossCityTravelRecord  {

  @ApiModelProperty("学生学号")
  private String code;

  @ApiModelProperty("途径的地区（用4位地区码存储，多个地区用逗号隔开）")
  private String region;


}
