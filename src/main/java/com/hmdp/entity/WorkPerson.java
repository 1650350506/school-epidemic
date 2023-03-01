package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.workperson
 * @ClassName: WorkPerson
 * @Author: yepeng
 * @Description: 防疫人员实体类
 * @Date: 2022/7/18 10:55
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("work_person")
public class WorkPerson  {

  /*账号*/
  private String account;

  /*职工姓名*/
  private String name;

  /*身份证号*/
  private String idCard;

  /*职工工号*/
  private String code;

  /*性别*/
  private int sex;

  /*当前职务*/
  private String systemPost;

  /*校内职务*/
  private String schoolPost;

  /*联系方式*/
  private String phone;

  /*二级学院编号*/
  private String deptCode;
}
