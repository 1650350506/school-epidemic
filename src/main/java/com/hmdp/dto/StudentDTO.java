package com.hmdp.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.dto
 * @ClassName: StudentDto
 * @Author: zcy
 * @Description:学生传入DTO\
 * @Date: 2022/9/22 17:45
 * @Version: 1.0
 */
@Data
@ApiModel("学生传入类")

public class StudentDTO {
  @ApiModelProperty(value = "学号",required = true)
  private String code;

  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @TableField("class_code")
  @ApiModelProperty(value = "班级",required = true)
  private String classCode;

  @ApiModelProperty(value = "院系编号", required = true)
  @TableField("dept_code")
  private String deptCode;

  @ApiModelProperty(value = "性别（0:男，1:女）",required = true)
  private Byte sex;

  @TableField("phone_number")
  @ApiModelProperty(value = "联系方式",required = true)
  private String phoneNumber;

  @ApiModelProperty(value = "身份证号",required = true)
  @TableField("id_card")
  private String idCard;

  @ApiModelProperty(value = "住址",required = true)
  private String address;

  @ApiModelProperty(value = "紧急联系人",required = true)
  @TableField("emergency_contact")
  private String emergencyContact;

  @ApiModelProperty(value = "紧急联系人联系方式",required = true)
  @TableField("emergency_contact_phone")
  private String emergencyContactPhone;


}
