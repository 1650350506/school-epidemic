package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.student
 * @ClassName: studentclass
 * @Author: zcy
 * @Description:学生班级类
 * @Date: 2022/7/27 14:29
 * @Version: 1.0
 */
@TableName("class")
@Data
@NoArgsConstructor
@ApiModel("班级实体类")
public class StudentClass  {
  @ApiModelProperty("班级编号")
  private String code;

  @ApiModelProperty( "专业编号")
  @TableField("major_code")
  private String majorCode;


  @ApiModelProperty( "老师编号")
  @TableField("teacher_code")
  private String teacherCode;

  @ApiModelProperty( "班级名称")
  private String name;
}
