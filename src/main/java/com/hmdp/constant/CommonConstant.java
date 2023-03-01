package com.hmdp.constant;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.constant.enums
 * @ClassName: CommonConstant
 * @Author: yepeng
 * @Description: 常用常量
 * @Date: 2022/8/4 10:41
 * @Version: 1.0
 */

public class CommonConstant {

  public static final int EXISTENCE = 1;

  public static final int ZERO = 0;

  public static final String LIST_EPPERSONNEL_KEY = "EpidemicPreventionPersonnel";

  /*redis key 值------社区区划代码和社区全名的hashMap*/
  public static final String REDIS_KEY_VILLAGE_HASHMAP = "villageHashMap";

  /*redis key 值------城市区划代码和城市全称的hashMap*/
  public static final String REDIS_KEY_CITY_FULL_NAME_HASHMAP = "CityFullNameAndCode";

  /*redis key 值------学生提交出校信息后，生成的id值*/
  public static final String REDIS_KEY_LEAVE_SCHOOL_ID = "leaveSchool";

  /*行程范围类别-----------本市*/
  public static final Byte DAILY_RECORD_TYPE_LOCAL = 0;

  /*行程范围类别-----------跨市*/
  public static final Byte DAILY_RECORD_TYPE_REMOTE = 1;

  /*社区区划代码长度*/
  public static final int VILLAGE_CODE_LENGTH = 12;

  /*城市区划代码长度*/
  public static final int CITY_CODE_LENGTH = 4;

  /*杭州市区划代码*/
  public static final String HANGZHOU_CODE = "3301";

  //温度异常的KEY

  public static final String TEMPERATURE_ABNORMALITY_KEY = "TemperatureAbnormality";

  //时间格式常量
  public static final String TIME_COMMON ="yyyy-MM-dd HH:mm:ss";

}