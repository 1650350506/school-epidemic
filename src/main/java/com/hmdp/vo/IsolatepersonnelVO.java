package com.hmdp.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("查看隔离人员")
@NoArgsConstructor
public class IsolatepersonnelVO implements Serializable {


  @ApiModelProperty(value = "统一编号")

  private String code;
  @ApiModelProperty(value = "名字")
  private String name;


  @ApiModelProperty(value = "性别")
  private int sex;

  @ApiModelProperty(value = "班级")
  private String classname;


  @ApiModelProperty(value = "手机号码")
  private String phoneNumber;

  @ApiModelProperty("地址")
  private String address;
  @ApiModelProperty("核酸结果")
  private Integer nucleicacidkey;

  @ApiModelProperty("体温")
  private  Float temperature;

  @ApiModelProperty(value = "开始隔离时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp startTime;


  @ApiModelProperty(value = "结束隔离时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp endTime;


  @ApiModelProperty(value = "隔离地点")
  private String isolationLocation;


  @ApiModelProperty(value = "隔离状态")
  private int state;

  @ApiModelProperty(value = "学生学院名称")
  private String stuCollege;

  @ApiModelProperty(value = "隔离原因")
  private String isolationReason;
}
