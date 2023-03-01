package com.hmdp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.dto.StudentDTO;
import com.hmdp.entity.Student;
import com.hmdp.mapper.StudentMapper;
import com.hmdp.service.StudentService;
import com.hmdp.utils.ValidateUtil;
import java.util.List;
import org.springframework.beans.BeanUtils;
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
  public Result selectStudent(String code) {
    LambdaQueryWrapper<Student> lambdaQueryWrapper =new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Student::getCode,code);
    List<Student> list = super.list(lambdaQueryWrapper);
    return Result.ok(list);
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


}
