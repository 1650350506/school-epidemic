package com.hmdp.controller;

import com.hmdp.dto.Result;
import com.hmdp.dto.StudentDTO;
import com.hmdp.service.StudentService;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.controller
 * @ClassName: StudentController
 * @Author: zcy
 * @Description:学生Controller
 * @Date: 2022/9/22 17:42
 * @Version: 1.0
 */
@RestController
@Api(value = "学生信息Controller")
@RequestMapping("/student")
@Slf4j
public class StudentController {

  @Resource
  private StudentService studentService;

  @PostMapping("/input")
  public Result inputStudent(@RequestBody StudentDTO studentDTO){

    return studentService.inputStudent(studentDTO);
  }

  @PutMapping("/update")
  public Result updateStudent(@RequestBody StudentDTO studentDTO){

    return studentService.updateStudent(studentDTO);
  }

  @GetMapping("/select")
  public  Result getStudent(String code){
    return studentService.selectStudent(code);
  }


  @DeleteMapping("/delete")
  public Result deleteStudent(String code){
    return studentService.deletedStudent(code);
  }

}
