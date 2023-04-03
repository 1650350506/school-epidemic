package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.command.QueryDailyRecordByKeyAndPageCommand;
import com.hmdp.command.QueryDailyRecordDetailByCodeCommand;
import com.hmdp.command.StudentCheckCommand;
import com.hmdp.command.StudentQueryPageBaseCommand;
import com.hmdp.dto.Result;
import com.hmdp.dto.StudentDTO;
import com.hmdp.entity.Student;
import com.hmdp.entity.StudentClass;
import java.util.List;

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

  Student selectStudent(String code);

  Result deletedStudent(String code);
  Integer addStudentInformation(Student convert);

  /**
   * 查询出学号，姓名，班级编号，性别，联系方式，身份证号，地址，紧急联系人，紧急联系方式，涉及风险等级，涉及地区
   * @param command 关键词以及风险等级筛选
   * @return 查询出来的学生结果集
   */
  List<Student> queryPageBase(StudentQueryPageBaseCommand command);

  /**
   * @Description: 根据学号或姓名或院系名称查询符合条件的学生学号List
   * @Author: yepeng
   * @Date: 15:06 2022/8/11
   * @Param command:
   * @Return: java.util.List<com.tianque.grid.ce2.domain.model.student.Student>
   */
  List<Student> getStudentByCodeOrNameOrDeptName(QueryDailyRecordByKeyAndPageCommand command);

  /**
   * @Description: 根据学号查看学生详情信息
   * @Author: yepeng
   * @Date: 15:07 2022/8/11
   * @Param command:
   * @Return: com.tianque.grid.ce2.domain.model.student.Student
   */
  Student queryDetailByCode(QueryDailyRecordDetailByCodeCommand command);

  /**
   * @Description: 检查学生是否合法
   * @Author: ChnyTrcy
   * @Date: 2022/8/11 18:03
   * @Param: [command] 姓名、学号
   * @Return: java.lang.Integer 数据库是否有相匹配的学生
   */
  Integer checkStudent(StudentCheckCommand command);

  /**
   * @Description: 检查学生是否合法
   * @Author: ChnyTrcy
   * @Date: 2022/8/11 18:02
   * @Param: [code]
   * @Return: java.lang.Boolean
   */
  Boolean checkIsExist(String code);

  /**
   * @Description: 根据学号查看姓名
   * @Author: yepeng
   * @Date: 15:07 2022/8/11
   * @Param code: 学号
   * @Return: java.lang.String
   */
  String queryNameByCode(String code);

  /**
   * @Description: 获得班级列表
   * @Author: ChnyTrcy
   * @Date: 2022/8/11 18:08
   * @Param: []
   * @Return: java.util.List<java.lang.String>
   */
  List<String> getClassList();





}
