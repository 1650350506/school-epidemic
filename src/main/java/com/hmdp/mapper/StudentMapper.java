package com.hmdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmdp.command.QueryDailyRecordByKeyAndPageCommand;
import com.hmdp.command.QueryDailyRecordDetailByCodeCommand;
import com.hmdp.command.StudentCheckCommand;
import com.hmdp.command.StudentQueryPageBaseCommand;
import com.hmdp.entity.Student;
import com.hmdp.entity.StudentClass;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.mapper
 * @ClassName: StudentMapper
 * @Author: zcy
 * @Description:学生Mapper
 * @Date: 2022/9/22 17:51
 * @Version: 1.0
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

  List<Student> selectBaseInformation(StudentQueryPageBaseCommand command);

  List<String> getStudentCodeSumList();

  int getSize(String keyword);

  /**
   * @Description: 检查学生是否合法
   * @Author: ChnyTrcy
   * @Date: 2022/8/9 14:44
   * @Param: [command]
   * @Return: int 符合条件的学生在数据表中的个数
   */
  int checkStudent(StudentCheckCommand command);

  /**
   * @Description: 检查学生是否合法
   * @Author: ChnyTrcy
   * @Date: 2022/8/9 14:38
   * @Param: [code]
   * @Return: int
   */
  int checkIsExist(String code);

  /**
   * @Description: 根据学号或姓名或院系名称查询符合条件的学生List
   * @Author: yepeng
   * @Date: 15:00 2022/8/11
   * @Param command:
   * @Return: java.util.List<com.tianque.grid.ce2.domain.model.student.Student>
   */
  List<Student> getCodeByCodeOrNameOrDeptName(QueryDailyRecordByKeyAndPageCommand command);

  /**
   * @Description: 根据学号查询学生详情信息
   * @Author: yepeng
   * @Date: 21:36 2022/8/9
   * @Param command:
   * @Return: com.tianque.grid.ce2.domain.model.student.Student
   */
  Student queryDetailByCode(QueryDailyRecordDetailByCodeCommand command);

  /**
   * @Description: 根据学号查看姓名
   * @Author: yepeng
   * @Date: 21:36 2022/8/9
   * @Param code: 学号
   * @Return: java.lang.String
   */
  String queryNameByCode(@Param("code") String code);

  /**
   * @Description: 根据学号查看手机号
   * @Author: yepeng
   * @Date: 21:36 2022/8/9
   * @Param code: 学号
   * @Return: java.lang.String
   */
  String queryPhoneByCode(@Param("code") String code);

  /**
   * @Description: 获得班级列表
   * @Author: ChnyTrcy
   * @Date: 2022/8/11 18:08
   * @Param: []
   * @Return: java.util.List<java.lang.String>
   */
  List<String> selectClassList();

  /**
   * @Description: 获得班级的HashMap
   * @Author: ChnyTrcy
   * @Date: 2022/8/9 14:52
   * @Param: []
   * @Return: java.util.List<com.tianque.grid.ce2.domain.model.studentclass.StudentClass>
   */
  List<StudentClass> selectStudentClassList();
}
