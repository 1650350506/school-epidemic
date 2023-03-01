package com.hmdp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.constant.enums
 * @ClassName: Isolationstatus
 * @Author: zcy
 * @Description:隔离状态
 * @Date: 2022/7/26 15:28
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum IsolationStatus  {
  ONE(0, "隔离未通知"),
  TWO(1, "隔离中"),
  THREE(2, "隔离结束"),
  FOUR(3, "治疗中");
  private Integer code;
  private String value;


  public Integer getNumber() {
    return getCode();
  }


  public String getName() {
    return getValue();
  }


  public String getSimplePinYin() {
    return null;
  }


  public String getFullPinYin() {
    return null;
  }

  private Integer getCode() {
    return code;
  }

  private String getValue() {
    return value;
  }
}
