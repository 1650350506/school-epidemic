package com.hmdp.service;

import com.hmdp.command.IsolatePersonnelCreateCommand;
import com.hmdp.command.IsolatePersonnelModifyCommand;
import com.hmdp.command.IsolatePersonnelQueryCommand;
import com.hmdp.command.IsolationRecordCreateCommand;
import com.hmdp.command.IsolationRecordQueryCommand;
import com.hmdp.command.StudentCheckCommand;
import com.hmdp.command.StudentJourneyByCodeCommand;
import com.hmdp.command.StudentQueryPageBaseCommand;
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

  Result tobePageIsolate();

  Result quarantinedPageIsolate();

  Result selectIsolatedPersonEndNumber();

  Result selectPeopletreatedNumber();

  Result queryPageIsolate(IsolatePersonnelQueryCommand command);

  Result queryPageIsolateCord(IsolationRecordQueryCommand command);

  Result selectStudent(String code);

  Result getEpidemicPreventionPersonnel();

  Result queryPageBase(StudentQueryPageBaseCommand command);

  Result getStudentJourneyByCode(StudentJourneyByCodeCommand command);

  boolean checkStudent(StudentCheckCommand command);

  Result getOutSchoolByCode(StudentJourneyByCodeCommand command);

  Integer addStudent();

  boolean modifyIsolatePersonnel(IsolatePersonnelModifyCommand command);

  Result noticeIsolatePersonnel(IsolatePersonnelModifyCommand command);
}
