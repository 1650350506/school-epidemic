package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.command.DelBatchByCodesCommand;
import com.hmdp.command.DelByCodeCommand;
import com.hmdp.command.QueryWorkPersonByCodeCommand;
import com.hmdp.command.QueryWorkPersonByKeyAndPageCommand;
import com.hmdp.command.UpdateWorkPersonByCodeCommand;
import com.hmdp.entity.Dept;
import com.hmdp.entity.WorkPerson;
import com.hmdp.mapper.WorkPersonDailyRecordMapper;
import com.hmdp.mapper.WorkPersonMapper;
import com.hmdp.service.DeptService;
import com.hmdp.service.WorkPersonService;
import com.hmdp.vo.WorkPersonDetailVO;
import com.hmdp.vo.WorkPersonRecordVO;
import com.hmdp.vo.WorkPersonVO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.mybatis.repositoryimpl
 * @ClassName: WorkPersonRepository
 * @Author: yepeng
 * @Description: 工作人员Repository实现类
 * @Date: 2022/7/19 15:05
 * @Version: 1.0
 */
@Repository("workPersonRepository")
public class WorkPersonServiceImpl extends ServiceImpl<WorkPersonMapper, WorkPerson>
    implements WorkPersonService {

  @Autowired
  private WorkPersonMapper workPersonMapper;

  @Autowired
  private WorkPersonDailyRecordMapper workPersonDailyRecordMapper;

  @Autowired
  private DeptService deptRepository;

  @Override
  public List<WorkPersonVO> queryPageByKey(QueryWorkPersonByKeyAndPageCommand command) {
    return workPersonMapper.queryPageByKey(command);
  }

  @Override
  public String selectEpPersonnelName(String code) {
    return getBaseMapper().selectEpPersonnelName(code);
  }

  @Override
  public WorkPersonDetailVO queryDetailsByCode(QueryWorkPersonByCodeCommand command) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.like("code",command.getCode());
    WorkPerson workPerson = workPersonMapper.selectOne(queryWrapper);
    System.out.println(workPerson);
    WorkPersonDetailVO workPersonDetailVO = BeanUtil.copyProperties(workPerson, WorkPersonDetailVO.class);
    System.out.println(workPersonDetailVO);
    List<WorkPersonRecordVO> workPersonDailyRecordList = workPersonDailyRecordMapper
        .queryAllByCode(command.getCode(),
            LocalDateTime.now().minusDays(13).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    System.out.println(workPersonDailyRecordList);
    workPersonDetailVO.setRecordVOList(workPersonDailyRecordList);

    //院系编号和院系名称的hashMap
    HashMap<String,String> deptHashMap = new HashMap<>();
    List<Dept> deptComplexList = deptRepository.getDeptComplexList();
    for (Dept dept : deptComplexList) {
      deptHashMap.put(dept.getCode(),dept.getName());
    }

    //将院系编号转换为院系名称
    workPersonDetailVO.setDeptName(deptHashMap.get(workPerson.getDeptCode()));
    return workPersonDetailVO ;
  }

  @Override
  public Integer delByCode(DelByCodeCommand command) {
    return workPersonMapper.delByCode(command);
  }

  @Override
  public Integer delBatchByCodes(DelBatchByCodesCommand command) {
    return workPersonMapper.delBatchByCodes(command);
  }

  @Override
  public Integer updateByCode(UpdateWorkPersonByCodeCommand command) {
    return workPersonMapper.updateByCode(command);
  }

  @Override
  public List<WorkPerson> getEpidemicPreventionPersonnel() {
    LambdaQueryWrapper<WorkPerson> queryWrapper = new LambdaQueryWrapper();
    queryWrapper.select(WorkPerson::getCode,WorkPerson::getName);
    return workPersonMapper.selectList(queryWrapper);
  }

  @Override
  public String queryNameByCode(String code) {
    return workPersonMapper.queryNameByCode(code);
  }

  @Override
  public List<String> queryCodes() {
    return workPersonMapper.queryCodes();
  }

  @Override
  public List<String> queryAccount() {
    return workPersonMapper.queryAccount();
  }

  @Override
  public List<String> queryPhone() {
    return workPersonMapper.queryPhone();
  }
}
