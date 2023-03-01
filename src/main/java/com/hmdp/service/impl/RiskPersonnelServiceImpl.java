package com.hmdp.service.impl;

import static com.hmdp.constant.CommonConstant.TEMPERATURE_ABNORMALITY_KEY;
import static com.hmdp.constant.IsolationStatus.THREE;
import static com.hmdp.constant.NucleicAcidResults.POSITIVE;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.command.IsolatePersonnelCreateCommand;
import com.hmdp.command.IsolatePersonnelModifyCommand;
import com.hmdp.command.IsolationRecordCreateCommand;
import com.hmdp.command.IsolationRecordDeleteCommand;
import com.hmdp.dto.Result;
import com.hmdp.entity.IsolatePersonnel;
import com.hmdp.mapper.IsolatePersonnelMapper;
import com.hmdp.service.RiskPersonnelService;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.service.impl
 * @ClassName: RiskPersonnnerlServiceImpl
 * @Author: zcy
 * @Description:风险人员接口实现类
 * @Date: 2022/10/13 17:59
 * @Version: 1.0
 */
@Service
public class RiskPersonnelServiceImpl extends
    ServiceImpl<IsolatePersonnelMapper,IsolatePersonnel> implements RiskPersonnelService {


  @Resource
  private RedisTemplate redisTemplate;

  @Autowired
  private IsolatePersonnelMapper isolatePersonnelMapper;

  @Override
  public Result isolateRecordRepository(IsolationRecordCreateCommand command) {
    float temperature = command.getTemperature();
    String code = command.getCode();
    //判断体温是否异常
    if (temperature == 38f) {
      //将学号作为键值查询然后自增一
      redisTemplate.opsForHash().increment(TEMPERATURE_ABNORMALITY_KEY, code, 1L);
      //查询对应的学号的异常次数
      Object o = redisTemplate.opsForHash().get(TEMPERATURE_ABNORMALITY_KEY, code);
      int count = Integer.parseInt(String.valueOf(o));
      //如果连续异常次数大于二则将该学生加入治疗
      if (count > 2) {
        IsolatePersonnelModifyCommand isolatePersonnelModifyCommand = new IsolatePersonnelModifyCommand();
        isolatePersonnelModifyCommand.setCode(code);
        this.treatmentIsolatePersonnel(isolatePersonnelModifyCommand);
      }
    } else {
      //如果体温不异常则将学号对应的值设为一
      redisTemplate.opsForHash().put(TEMPERATURE_ABNORMALITY_KEY, code, "1");
    }
    //判断核酸是否异常，异常则则将该学生加入治疗
    if (command.getNucleicAcidKey().equals(POSITIVE.getNumber())) {
      IsolatePersonnelModifyCommand isolatePersonnelModifyCommand = new IsolatePersonnelModifyCommand();
      isolatePersonnelModifyCommand.setCode(code);
      this.treatmentIsolatePersonnel(isolatePersonnelModifyCommand);
    }
    return null;
    //return isolateDetailsDomainService.add(command);
  }

  @Transactional(rollbackFor = Exception.class)
  public Result isolatePersonnelRepository(IsolatePersonnelCreateCommand command) {
    IsolatePersonnel isolatePersonnel = new IsolatePersonnel();
    BeanUtil.copyProperties(command,isolatePersonnel);
    int insert = isolatePersonnelMapper.insert(isolatePersonnel);
    return Result.ok(insert);

  }

  /**
   * @Description: 解除隔离人员
   * @Author: zcy
   * @Date: 2022/8/11 15:20
   * @Param: [command]
   * @Return: java.lang.Long
   */

//  public Long modifyIsolatePersonnel(IsolatePersonnelModifyCommand command) {
//    LambdaQueryWrapper<IsolatePersonnel> lambdaQueryWrapper1 =new LambdaQueryWrapper<>();
//    String code = command.getCode();
//    //查询这个学生是否已经在隔离人员表中除了隔离结束
//    lambdaQueryWrapper1.eq(IsolatePersonnel::getCode,command.getCode()).ne(IsolatePersonnel::getState,THREE.getNumber());
//    IsolatePersonnel isolatePersonnel1 = isolatePersonnelRepository.getOne(lambdaQueryWrapper1);
//    Timestamp endTime = isolatePersonnel1.getEndTime();
//    //得到当地时间
//    LocalDateTime now = LocalDateTime.now();
//    //如果隔离时间未到则不予解除隔离
//    if(endTime.after(Timestamp.valueOf(now))){
//      throw new BusinessException(ErrorCode.ISOLATION_TIME_ADDITION_ERROR);
//    }
//    LambdaQueryWrapper<IsolateDetails> lambdaQueryWrapper =new LambdaQueryWrapper<>();
//    //获取最新的一次体温信息
//    lambdaQueryWrapper.select(IsolateDetails::getTemperature).eq(IsolateDetails::getCode,code).
//        orderBy(true,false,IsolateDetails::getNucleicAcidTime)
//        .last("limit 1");
//    IsolateDetails isolateDetails = isolateDetailsRepository.getOne(lambdaQueryWrapper);
//    //如果没有隔离记录就报错，不能进行解除隔离的操作
//    if(isolateDetails==null){
//      throw new BusinessException(ErrorCode.TEMPERATURE_ABNORMAL);
//    }
//    Float temperature = isolateDetails.getTemperature();
//    //如果体温异常则不能接触隔离并将隔离时间延长三天
//    if(temperature==38L){
//      SimpleDateFormat format = new SimpleDateFormat(TIME_COMMON);
//      Calendar ca = Calendar.getInstance();
//      ca.setTime(Timestamp.valueOf(now));
//      //给隔离开始时间加三天天得到隔离结束时间
//      ca.add(Calendar.DATE, 3);
//      Date time = ca.getTime();
//      String lastEndTime = format.format(time);
//      lambdaQueryWrapper1.eq(IsolatePersonnel::getCode,code);
//      IsolatePersonnel isolatePersonnel=new IsolatePersonnel();
//      isolatePersonnel.setEndTime(Timestamp.valueOf(lastEndTime));
//      isolatePersonnelRepository.update(isolatePersonnel,lambdaQueryWrapper1);
//      throw new BusinessException(ErrorCode.MODIFY_ISOLATE_ERROR);
//    }
//    return isolatepersonDomainService.modifyIsolatePersonnel(command);
//  }

  /**
   * @Description: 删除隔离记录
   * @Author: zcy
   * @Date: 2022/8/11 15:20
   * @Param: [command]
   * @Return: java.lang.Long
   */
  @Transactional(rollbackFor = Exception.class)
  public Long deleteRecord(IsolationRecordDeleteCommand command) {
    return null;
    // return isolateDetailsDomainService.deleteRecord(command);
  }

  /**
   * @Description: 通知隔离人员开始隔离
   * @Author: zcy
   * @Date: 2022/8/11 15:22
   * @Param: [command]
   * @Return: java.lang.Long
   */
  @Transactional(rollbackFor = Exception.class)
  public Long noticeIsolatePersonnel(IsolatePersonnelModifyCommand command) {
    return null;
    //return isolatepersonDomainService.noticeIsolatePersonnel(command);
  }

  /**
   * @Description: 通知隔离人员治疗
   * @Author: zcy
   * @Date: 2022/8/11 15:22
   * @Param: [command]
   * @Return: java.lang.Long
   */
  @Transactional(rollbackFor = Exception.class)
  public Long treatmentIsolatePersonnel(IsolatePersonnelModifyCommand command) {
    return null;
    //return isolatepersonDomainService.treatmentIsolatePersonnel(command);

  }
}
