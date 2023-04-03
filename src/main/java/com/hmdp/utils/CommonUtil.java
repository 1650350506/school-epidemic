package com.hmdp.utils;

import cn.hutool.core.util.ObjectUtil;
import com.hmdp.constant.CommonConstant;
import com.hmdp.entity.Dept;
import com.hmdp.entity.Village;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.util
 * @ClassName: CommonUtil
 * @Author: ChnyTrcy
 * @Description: 常用工具类
 * @Date: 2022/7/19 14:10
 * @Version: 1.0
 */
@Component
public class CommonUtil {

//  @Autowired
//  private ProvinceRepository provinceRepository;
//
//  @Autowired
//  private CityRepository cityRepository;
//
//  @Autowired
//  private CountyRepository countyRepository;
//
//  @Autowired
//  private TownRepository townRepository;
//
//  @Autowired
//  private VillageRepository villageRepository;
//
//  @Autowired
//  private DeptRepository deptRepository;

  @Autowired
  private RedisTemplate redisTemplate;

  /**
   * @Description: 获取近七天日期
   * @Author: ws
   * @Date: 2022/8/12 9:58
   * @Param: [Several]
   * @Return: java.util.List<java.lang.String>
   */
  public List<String> getSevenDate(int Several) {
    List<String> dateList = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < Several; i++) {
      Date date = DateUtils.addDays(new Date(), -i);
      String formatDate = sdf.format(date);
      dateList.add(formatDate);
    }
    return dateList;
  }

  /*把一个文件中的内容读取成一个String字符串*/
  public static String getStr(File jsonFile){
    String jsonStr = "";
    try {
      FileReader fileReader = new FileReader(jsonFile);
      Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
      int ch = 0;
      StringBuffer sb = new StringBuffer();
      while ((ch = reader.read()) != -1) {
        sb.append((char) ch);
      }
      fileReader.close();
      reader.close();
      jsonStr = sb.toString();
      return jsonStr;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

//  /**
//   * @Description: 获取社区区划代码和风险等级的hashMap
//   * @Author: yepeng
//   * @Date: 21:27 2022/8/9
//   * @Return: java.util.HashMap<java.lang.String,java.lang.Integer>
//  */
//  public HashMap<String, Integer> villageAndRisk(){
//    HashMap<String, Integer> riskHashMap = new HashMap<>();
//    List<Village> villageComplexList = villageRepository.getComplexList();
//    for (Village village : villageComplexList) {
//      riskHashMap.put(village.getCode(), village.getRiskLevel());
//    }
//    return riskHashMap;
//  }
//
//  /**
//   * @Description: 获取院系编号和院系名称的hashMap
//   * @Author: yepeng
//   * @Date: 21:27 2022/8/9
//   * @Return: java.util.HashMap<java.lang.String,java.lang.String>
//  */
//  public  HashMap<String,String> deptCodeAndName(){
//    HashMap<String,String> deptHashMap = new HashMap<>();
//    List<Dept> deptComplexList = deptRepository.getDeptComplexList();
//    for (Dept dept : deptComplexList) {
//      deptHashMap.put(dept.getCode(),dept.getName());
//    }
//    return deptHashMap;
//  }
//
//  /**
//   * @Description: 通过社区区划代码获取社区全称
//   * @Author: yepeng
//   * @Date: 21:27 2022/8/9
//   * @Param code: 社区区划代码
//   * @Return: java.lang.String
//  */
//  public String getFullNameByVillageCode(String code){
//    String value = (String) redisTemplate.boundHashOps(CommonConstant.REDIS_KEY_VILLAGE_HASHMAP).get(code);
//    if(ObjectUtil.isNull(value) || "".equals(value)){
//
//       //获取 省 市 县 乡 村 五级的区划代码和名称对应的 Map
//      HashMap<String, String> provinceMap = new HashMap<>();
//      HashMap<String, String> cityMap = new HashMap<>();
//      HashMap<String, String> countyMap = new HashMap<>();
//      HashMap<String, String> townMap = new HashMap<>();
//      HashMap<String, String> villageMap = new HashMap<>();
//      List<HashMap<String, String>> provinceMapList = provinceRepository.queryCodeAndNameAll();
//      List<HashMap<String, String>> cityMapList = cityRepository.queryCodeAndNameAll();
//      List<HashMap<String, String>> countyMapList = countyRepository.queryCodeAndNameAll();
//      List<HashMap<String, String>> townMapList = townRepository.queryCodeAndNameAll();
//      List<HashMap<String, String>> villageMapList = villageRepository.queryCodeAndNameAll();
//      for (HashMap<String, String> hashMap : provinceMapList) {
//        provinceMap.put(hashMap.get("code"),hashMap.get("name"));
//      }
//      for (HashMap<String, String> hashMap : cityMapList) {
//        cityMap.put(hashMap.get("code"),hashMap.get("name"));
//      }
//      for (HashMap<String, String> hashMap : countyMapList) {
//        countyMap.put(hashMap.get("code"),hashMap.get("name"));
//      }
//      for (HashMap<String, String> hashMap : townMapList) {
//        townMap.put(hashMap.get("code"),hashMap.get("name"));
//      }
//
//      //将社区的名称转换为全称
//      for (HashMap<String, String> hashMap : villageMapList) {
//        villageMap.put(hashMap.get("code"),
//            provinceMap.get((hashMap.get("code")).substring(0,2))+
//                cityMap.get((hashMap.get("code")).substring(0,4))+
//                countyMap.get((hashMap.get("code")).substring(0,6))+
//                townMap.get((hashMap.get("code")).substring(0,9))+ hashMap.get("name"));
//      }
//      redisTemplate.boundHashOps(CommonConstant.REDIS_KEY_VILLAGE_HASHMAP).putAll(villageMap);
//      value = (String) redisTemplate.boundHashOps(CommonConstant.REDIS_KEY_VILLAGE_HASHMAP).get(code);
//    }
//    return value;
//  }
//
//  /**
//   * @Description: 通过城市区划代码获取城市全称
//   * @Author: yepeng
//   * @Date: 21:28 2022/8/9
//   * @Param code: 城市区划代码
//   * @Return: java.lang.String
//  */
//  public String getFullNameByCityCode(String code){
//    String value = (String) redisTemplate.boundHashOps(CommonConstant.REDIS_KEY_CITY_FULL_NAME_HASHMAP).get(code);
//    if(ObjectUtil.isNull(value) || "".equals(value) ){
//      HashMap<String,String> hashMap = new HashMap<>(512);
//      List<HashMap<String, String>> mapList = cityRepository.getAllFullName();
//      for (HashMap<String, String> map : mapList) {
//        hashMap.put(map.get("code"),map.get("fullname"));
//      }
//      redisTemplate.boundHashOps("CityFullNameAndCode").putAll(hashMap);
//      value = (String) redisTemplate.boundHashOps(CommonConstant.REDIS_KEY_CITY_FULL_NAME_HASHMAP).get(code);
//    }
//    return value;
//  }

}
