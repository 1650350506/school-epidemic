package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.Result;
import com.hmdp.dto.StudentDTO;
import com.hmdp.entity.Student;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.service
 * @ClassName: StudentService
 * @Author: zcy
 * @Description:学生service接口
 * @Date: 2022/9/22 17:48
 * @Version: 1.0
 */
public interface StudentService extends IService<Student> {


  Result inputStudent(StudentDTO studentDTO);


  Result updateStudent(StudentDTO studentDTO);

  Result selectStudent(String code);

  Result deletedStudent(String code);
}
