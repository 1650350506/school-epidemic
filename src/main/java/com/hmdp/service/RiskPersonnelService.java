package com.hmdp.service;

import com.hmdp.command.IsolatePersonnelCreateCommand;
import com.hmdp.command.IsolationRecordCreateCommand;
import com.hmdp.dto.Result;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.service
 * @ClassName: RiskPersonnelService
 * @Author: zcy
 * @Description:风险人员服务层
 * @Date: 2022/10/13 17:55
 * @Version: 1.0
 */
public interface RiskPersonnelService {

  Result isolateRecordRepository(IsolationRecordCreateCommand command);

  Result isolatePersonnelRepository(IsolatePersonnelCreateCommand command);
}
