package com.hmdp.controller;

import cn.hutool.core.lang.Validator;
import com.hmdp.command.IsolatePersonnelCreateCommand;
import com.hmdp.command.IsolatePersonnelModifyCommand;
import com.hmdp.command.IsolatePersonnelQueryCommand;
import com.hmdp.command.IsolationRecordCreateCommand;
import com.hmdp.command.IsolationRecordDeleteCommand;
import com.hmdp.command.IsolationRecordQueryCommand;
import com.hmdp.command.StudentCheckCommand;
import com.hmdp.command.StudentJourneyByCodeCommand;
import com.hmdp.command.StudentQueryPageBaseCommand;
import com.hmdp.dto.Result;
import com.hmdp.command.StudentAddCrossCityCommand;
import com.hmdp.command.StudentDelByCodeCommand;
import com.hmdp.command.StudentDelByCodeListCommand;
import com.hmdp.service.RiskPersonnelService;
import com.hmdp.utils.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package:com.tianque.grid.ce2.infrastructure.server.controller
 * @ClassName: IsolateController
 * @Author: zcy
 * @Description: 风险人员管理-操作接口
 * @Date: 2022/7/15 9:04
 * @Version: 1.0 9
 */
@RequestMapping("/riskPersonnel")
@RestController
@Api(tags = "风险人员管理-操作接口")

public class RiskPersonnelController {

  @Autowired
  private RiskPersonnelService riskPersonnelService;

  @GetMapping(value = "/delStudentByCode")
  @ApiOperation(value = "根据学号删除学生跨市行程")
  public Result delStudentByCode(
      @Valid StudentDelByCodeCommand command){
    return null;
    //return riskPersonnelService.delStudentByCode(command);
  }

  @PostMapping("/delStudentByCodeList")
  @ApiOperation(value = "根据学号列表批量删除学生跨市行程")
  public Result delStudentByCodeList(
      @Valid @RequestBody StudentDelByCodeListCommand command){
    return null;
    //return riskPersonnelService.delStudentByCodeList(command);
  }

  @PostMapping("/addIsolatePersonnel")
  @ApiOperation(value = "新增隔离人员", response = Long.class)
  public Result registerIsolatePersonnel(
      @Valid @RequestBody IsolatePersonnelCreateCommand command) {

  return riskPersonnelService.isolatePersonnelRepository(command);
  }
  @PostMapping("/addIsolaterecord")
  @ApiOperation(value = "新增隔离记录", response = Long.class)
  public Result registerIsolatePersonnel(
      @Valid @RequestBody IsolationRecordCreateCommand command) {
    return riskPersonnelService.isolateRecordRepository(command);
  }
  @DeleteMapping("/deleteIsolaterecord")
  @ApiOperation(value = "删除隔离记录", response = Long.class)
  public Result registerIsolateRecord(
      @Valid IsolationRecordDeleteCommand command) {
    return null;
    //return riskPersonnelService.isolateRecordDeleteRepository(command);
  }
  @DeleteMapping("/delete")
  @ApiOperation("解除隔离人员")
  public Result registerIsolatePersonnel(
      @Valid  IsolatePersonnelModifyCommand command) {
    return null;
    //return Result.ok(riskPersonnelService.modifyIsolatePersonnel(command));
  }

  @PutMapping("/notice")
  @ApiOperation("通知隔离人员")
  public Result noticeIsolatePersonnel(
      IsolatePersonnelModifyCommand command) {

    return null;
    //return riskPersonnelService.noticeIsolatePersonnel(command);
  }

  @PutMapping("/treatment")
  @ApiOperation("通知隔离人员治疗")
  public Result treatmentIsolatePersonnel(
      @Valid IsolatePersonnelModifyCommand command) {
    return null;
    //return riskPersonnelService.treatmentIsolatePersonnel(command);
  }

  @PostMapping("/addStudentCrossCity")
  @ApiOperation("添加学生的进校行程记录")
  public Result addStudentCrossCity(
      @Valid @RequestBody StudentAddCrossCityCommand command){
    return null;
   // return riskPersonnelService.addStudentCrossCity(command);
  }

  @GetMapping("/addStudentHundred")
  @ApiOperation("批量添加1000个学生（不对外暴露）")
  public Result addStudentHundred(){
    return Result.ok(riskPersonnelService.addStudent());
  }

  @GetMapping("/toBeIsolateperson")
  @ApiOperation(value = "查询待隔离人员人数")
  public Result toBeIsolatedPerson() {
    return riskPersonnelService.tobePageIsolate();
  }

  @GetMapping("/QuarantinedIsolateperson")
  @ApiOperation(value = "查询已隔离人员人数")
  public Result quarantinedIsolatedPerson() {
    return riskPersonnelService.quarantinedPageIsolate();
  }

  @GetMapping("/selectIsolatedPersonEndNumber")
  @ApiOperation(value = "查询隔离结束人员人数")
  public Result selectIsolatedPersonEndNumber() {
    return riskPersonnelService.selectIsolatedPersonEndNumber();
  }

  @GetMapping("/selectPeopleTreatedNumber")
  @ApiOperation(value = "查询治疗中人数")
  public Result selectPeopleTreatedNumber() {
    return riskPersonnelService.selectPeopletreatedNumber();
  }

  @GetMapping("/findIsolateperson")
  @ApiOperation(value = "查询隔离人员")
  public Result queryPageIsolatePerson(@Valid
  IsolatePersonnelQueryCommand command) {
    return riskPersonnelService.queryPageIsolate(command);
  }

  @GetMapping("/findIsolaterecord")
  @ApiOperation(value = "查询隔离人员信息")
  public Result queryPageIsolatePerson(
      @Valid IsolationRecordQueryCommand command) {
    return riskPersonnelService.queryPageIsolateCord(command);
  }

  @GetMapping("/findStudent")
  @ApiOperation(value = "根据学号查学生信息")
  public Result selectStudent( String code){
    return  riskPersonnelService.selectStudent(code);
  }

  @GetMapping("/getEpidemicPreventionPersonnel")
  @ApiOperation(value = "防疫人员查询名称")
  public Result getEpidemicPreventionPersonnel(){
    return riskPersonnelService.getEpidemicPreventionPersonnel();
  }

  @GetMapping("/queryPageBase")
  @ApiOperation(value = "基本信息跨市分页查询")
  public Result queryPageCrossCityTravelRecord(
      @Valid StudentQueryPageBaseCommand command){
    return riskPersonnelService.queryPageBase(command);
  }

  @GetMapping("/getStudentJourneyByCode")
  @ApiOperation(value = "根据学生学号查找行程")
  public Result getStudentJourneyByCode(
      @Valid StudentJourneyByCodeCommand command){
    return riskPersonnelService.getStudentJourneyByCode(command);
  }

  @GetMapping("/getOutSchoolByCode")
  @ApiOperation(value = "根据学生学号查找出校信息")
  public Result getOutSchoolByCode(
      @Valid StudentJourneyByCodeCommand command){
    return riskPersonnelService.getOutSchoolByCode(command);
  }

  @GetMapping("/checkStudent")
  @ApiOperation(value = "检查学生是否合法")
  public Result checkStudent(
      @Valid StudentCheckCommand command){
    return Result.ok(riskPersonnelService.checkStudent(command));
  }
}
