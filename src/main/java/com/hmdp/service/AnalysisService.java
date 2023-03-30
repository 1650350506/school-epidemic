package com.hmdp.service;

import com.hmdp.dto.Result;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.service
 * @ClassName: AnalysisService
 * @Author: zcy
 * @Description:service
 * @Date: 2023/3/7 15:58
 * @Version: 1.0
 */
public interface AnalysisService {

  Result totalIsolateAnalysis();

  Result analysisNewly(Integer command);

  Result addIsolateAnalysis();

  Result relievePerson();

  Result distributionOfEpidemic();

  Result distributionOfIsolated();
}
