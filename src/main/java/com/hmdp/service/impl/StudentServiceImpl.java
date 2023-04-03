package com.hmdp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.command.QueryDailyRecordByKeyAndPageCommand;
import com.hmdp.command.QueryDailyRecordDetailByCodeCommand;
import com.hmdp.command.StudentCheckCommand;
import com.hmdp.command.StudentQueryPageBaseCommand;
import com.hmdp.dto.Result;
import com.hmdp.dto.StudentDTO;
import com.hmdp.entity.Student;
import com.hmdp.entity.StudentClass;
import com.hmdp.mapper.StudentMapper;
import com.hmdp.service.StudentService;
import com.hmdp.utils.ValidateUtil;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.service.impl
 * @ClassName: StudentServiceImpl
 * @Author: zcy
 * @Description:学生实现类
 * @Date: 2022/9/22 17:55
 * @Version: 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService{



  @Autowired
  private StudentMapper studentMapper;


  @Override
  public Result inputStudent(StudentDTO studentDTO) {
    boolean b = ValidateUtil.validateCard(studentDTO.getIdCard());
    if(!b){
      return Result.fail("身份证校验错误");
    }
    Student student =new Student();
    BeanUtils.copyProperties(studentDTO,student);
    boolean save = save(student);
    return Result.ok(save);
  }

  @Override
  public Result updateStudent(StudentDTO studentDTO) {
    boolean b = ValidateUtil.validateCard(studentDTO.getIdCard());
    if(!b){
      return Result.fail("身份证校验错误");
    }
    Student student =new Student();
    BeanUtils.copyProperties(studentDTO,student);
    LambdaUpdateWrapper<Student> lambdaUpdateWrapper =new LambdaUpdateWrapper<>();
    UpdateWrapper<Student> studentUpdateWrapper = new UpdateWrapper<>();
    studentUpdateWrapper.eq("code",student.getCode());
    boolean update = update(student,studentUpdateWrapper);
    return Result.ok(update);
  }



  @Override
  public Result deletedStudent(String code) {
    QueryChainWrapper<Student> studentQueryChainWrapper = query().eq("code", code);
    boolean remove = remove(studentQueryChainWrapper);
    if(remove){
      return Result.ok();
    }
    return Result.fail("删除失败");
  }

  @Override
  public Integer addStudentInformation(Student convert) {
    return studentMapper.insert(convert);
  }

  @Override
  public List<Student> queryPageBase(StudentQueryPageBaseCommand command) {
    return studentMapper.selectBaseInformation(command);
  }

  @Override
  public List<Student> getStudentByCodeOrNameOrDeptName(
      QueryDailyRecordByKeyAndPageCommand command) {
    return studentMapper.getCodeByCodeOrNameOrDeptName(command);
  }

  @Override
  public Student queryDetailByCode(QueryDailyRecordDetailByCodeCommand command) {
    return studentMapper.queryDetailByCode(command);
  }

  @Override
  public String queryNameByCode(String code) {
    return studentMapper.queryNameByCode(code);
  }

  @Override
  public List<String> getClassList() {
    return studentMapper.selectClassList();
  }


  @Override
  public Student selectStudent(String code) {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("code", code);
    Student student = studentMapper.selectOne(queryWrapper);

    return student;
  }

  @Override
  public Integer checkStudent(StudentCheckCommand command) {
    return studentMapper.checkStudent(command);
  }

  @Override
  public Boolean checkIsExist(String code) {
    int count = studentMapper.checkIsExist(code);
    return count >= 1;
  }


}
