package com.hmdp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.constant.enums
 * @ClassName: NucleicAcidResults
 * @Author: zcy
 * @Description:核酸结果枚举
 * @Date: 2022/8/11 17:02
 * @Version: 1.0
 */

@AllArgsConstructor
@Getter
public enum NucleicAcidResults {
  POSITIVE(1, "阳性"),
  NEGATIVE(0, "阴性");
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
