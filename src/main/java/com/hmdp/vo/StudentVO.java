package com.hmdp.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.server.vo.riskpersonnel
 * @ClassName: studentVo
 * @Author: zcy
 * @Description:学生基本信息
 * @Date: 2022/7/27 11:21
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@ApiModel("演示对象")
public class StudentVO  implements Serializable {

  @ApiModelProperty("学号")
  private String code;


  @ApiModelProperty("二级学院")
  private String deptName;


  @ApiModelProperty(value = "班级")
  private String className;


  @ApiModelProperty(value = "性别")
  private Byte sex;

  @ApiModelProperty("联系方式")
  private String phoneNumber;

  @ApiModelProperty(value = "住址")
  private String address;


  @ApiModelProperty("名称")
  private String name;


}
