package com.hmdp.controller;

import com.hmdp.dto.Result;
import com.hmdp.service.AnalysisService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.controller
 * @ClassName: AnalysisController
 * @Author: zcy
 * @Description:分析接口
 * @Date: 2023/3/7 15:57
 * @Version: 1.0
 */

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {

  private final AnalysisService analysisService;

  @GetMapping(value = "/totalPerson")
  @ApiOperation(value = "总隔离人员")
  public Result countIsolate() {
    return analysisService.totalIsolateAnalysis();
  }

  @GetMapping(value = "/newlyAnalysis")
  @ApiOperation(value = "新增人员分析")
  public Result newlyAnalysis(
      Integer command) {
    return analysisService.analysisNewly(command);
  }

  @GetMapping(value = "/add")
  @ApiOperation(value = "新增隔离人数")
  public Result addAnalysis() {
    return analysisService.addIsolateAnalysis();
  }

  @GetMapping(value = "/relieve")
  @ApiOperation(value = "新增解除隔离人数")
  public Result relieveAnalysis() {
    return analysisService.relievePerson();
  }

  @GetMapping(value = "/epidemic")
  @ApiOperation(value = "防疫人员分布")
  public Result epidemicDistribution() {
    return analysisService.distributionOfEpidemic();
  }

  @GetMapping(value = "/isolate")
  @ApiOperation(value = "隔离人员各学院分布查询")
  public Result collegeDistribution() {
    return analysisService.distributionOfIsolated();

  }
}
