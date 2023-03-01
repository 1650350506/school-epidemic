package com.hmdp.utils;

/**
 * @ProjectName: standard-contradiction-mediation
 * @Package: com.tianque.mediation.main.matter.utils
 * @ClassName: ValidateUtil
 * @Author: zcy
 * @Description:证件验证工具类
 * @Date: 2022/9/22 14:39
 * @Version: 1.0
 */
public class ValidateUtil {
  /**
   * 功能描述:身份证校验
   * @param
   * @return boolean
   * @date 2020-07-07 16:30
   */
  public static boolean validateCard(String cardNo) {
    String reg = "(^\\d{8}(0\\d|10|11|12)([0-2]\\d|30|31)\\d{3}$)|(^\\d{6}(18|19|20)\\d{2}(0\\d|10|11|12)([0-2]\\d|30|31)\\d{3}(\\d|X|x)$)";
    return cardNo.matches(reg);
  }
}
