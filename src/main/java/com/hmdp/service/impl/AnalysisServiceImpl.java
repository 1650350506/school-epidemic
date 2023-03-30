package com.hmdp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.constant.CommonConstant;
import com.hmdp.dto.Result;
import com.hmdp.entity.Dept;
import com.hmdp.entity.WorkPerson;
import com.hmdp.mapper.DeptMapper;
import com.hmdp.mapper.IsolatePersonnelMapper;
import com.hmdp.mapper.WorkPersonMapper;
import com.hmdp.service.AnalysisService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.service.impl
 * @ClassName: AnalysisServiceImpl
 * @Author: zcy
 * @Description:接口
 * @Date: 2023/3/7 16:03
 * @Version: 1.0
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

  @Autowired
  private IsolatePersonnelMapper isolatePersonnelMapper;

  @Autowired
  private WorkPersonMapper workPersonMapper;

  @Autowired
  private DeptMapper deptMapper;
  @Override
  public Result totalIsolateAnalysis() {
    Integer countByCode = isolatePersonnelMapper.getCountByCode();
    return Result.ok(countByCode);
  }

  @Override
  public Result analysisNewly(Integer command) {

    List<Map<String,Object>> mapList = new LinkedList<>();
    List<String> sevenDate = getSevenDate(command);
    for (String date : sevenDate) {
      Integer count = isolatePersonnelMapper.IsolateAnaly(date);
      Map<String,Object> map = new HashMap<>();
      map.put("count",count);
      map.put("time",date);
      mapList.add(map);
    }
    return Result.ok(mapList);
  }

  @Override
  public Result addIsolateAnalysis() {
    Integer count = isolatePersonnelMapper.addIsolateAnaly();
    if(null!=count){
      return Result.ok(count);}
    else {
      count = CommonConstant.ZERO;
      return Result.ok(count);
    }
  }

  @Override
  public Result relievePerson() {
    Integer count = isolatePersonnelMapper.relieveIsolateAnaly();
    if(null!=count){
      return Result.ok(count);}
    else {
      count = CommonConstant.ZERO;
      return Result.ok(count);
    }

  }

  @Override
  public Result distributionOfEpidemic() {
    Map<String, Integer> epidemicMap = new HashMap<>();
    Map<String, Integer> studentMap = new HashMap<>();
    /*  1，查询出所有学院，  */
    List<Dept> collegeList = deptMapper.selectList(new QueryWrapper<Dept>());
    /*  2，根据学院查询出所有防疫人员，*/
    for (Dept college : collegeList) {
      LambdaQueryWrapper<WorkPerson> wrapper = new LambdaQueryWrapper<>();
      wrapper.eq(WorkPerson::getDeptCode,college.getCode());
      List<WorkPerson> epidemicList = workPersonMapper.selectList(wrapper);
      int epidemicCount = epidemicList.size();
      /*该学院无防疫人员时*/
      if(epidemicCount==0){
        epidemicMap.put(college.getName(),CommonConstant.ZERO);
      }else {
        epidemicMap.put(college.getName(), epidemicCount);
      }
      /*3,根据学院查询出所有学生人数*/
      String command = college.getCode();
      int IsolateCount = isolatePersonnelMapper.countQuarantine(command);
      /*该学院无隔离人员时*/
      if(IsolateCount==0){
        studentMap.put(college.getName(),CommonConstant.ZERO);
      }else {
        studentMap.put(college.getName(), IsolateCount);
      }
    }
    List<Map<String,Integer>> resultListMap = new LinkedList<>();
    resultListMap.add(epidemicMap);
    resultListMap.add(studentMap);
    return Result.ok(resultListMap);
  }

  @Override
  public Result distributionOfIsolated() {
    List<Dept> collegesList =deptMapper.selectList(new QueryWrapper<>());
    Map<String, Integer>  epidemicMap = new LinkedHashMap<>();
    for (Dept college : collegesList) {
      String command = college.getCode();
      int epidemicCount = isolatePersonnelMapper.countQuarantine(command);
      /*该学院无防疫人员时*/
      if(epidemicCount==0){
        epidemicMap.put(college.getName(),CommonConstant.ZERO);
      }else {
        epidemicMap.put(college.getName(), epidemicCount);
      }
    }
    List<Map.Entry<String, Integer>>  resultList = new ArrayList<>(epidemicMap.entrySet());
    Comparator<Entry<String, Integer>> comparator = (Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) -> m1.getValue().compareTo(m2.getValue());
    resultList.sort(comparator.reversed());
    return Result.ok(resultList);
  }

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

}
