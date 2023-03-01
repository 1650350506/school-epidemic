package com.hmdp.mapper;

//import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmdp.entity.IsolatePersonnel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

///**
// * @ProjectName: campus-epidemic-group2
// * @Package: com.tianque.grid.ce2.infrastructure.mybatis.mapper
// * @ClassName: asad
// * @Author: zcy ws
// * @Description: 学生隔离人员和分析接口用的mapper层
// * @Date: 2022/7/19 16:27
// * @Version: 1.0
// */
//
@Mapper
public interface IsolatePersonnelMapper extends BaseMapper<IsolatePersonnel> {
//
///**
// * @Description: 隔离人员查询
// * @Author: zcy
// * @Date: 2022/8/8 10:30
// * @Param: [command]
// * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.riskpersonnel.IsolatepersonnelVO>
// */
//  List<IsolatepersonnelVO> queryPageIsolate(IsolatePersonnelQueryCommand command);

  /**
   * @Description: 查询出昨天新增隔离人员
   * @Author: ws
   * @Date: 2022/8/8 17:22
   * @Param: []
   * @Return: java.lang.Integer
   */
  @Select("select count(id) from isolate_personnel where to_char(create_date,'dd')=to_char((CURRENT_DATE-interval '1 day'),'dd')  AND is_delete = 0 and \"state\" = 1\n"
      + "group by to_char(create_date,'yyyy-mm-dd')\n")
  Integer addIsolateAnaly();

  /**
   * @Description: 查询出昨天新增解除隔离人员
   * @Author: ws
   * @Date: 2022/8/8 17:22
   * @Param: []
   * @Return: java.lang.Integer
   */
  @Select("select count(id) from isolate_personnel where to_char(create_date,'dd')=to_char((CURRENT_DATE-interval '1 day'),'dd')  AND is_delete = 0 and \"state\" = 2\n"
      + "group by to_char(create_date,'yyyy-mm-dd')\n ")
  Integer relieveIsolateAnaly();

  /**
   * @Description: 查询分析出近七或十四天的新增隔离人数
   * @Author: ws
   * @Date: 2022/8/8 17:22
   * @Param: [command]
   * @Return: java.util.List<java.util.Map < java.lang.String, java.lang.Integer>>
   */
  @Select(" SELECT count(code) FROM isolate_personnel WHERE state = 1 and to_char(start_time,'yyyy-MM-dd') = #{command}")
  Integer IsolateAnaly(String command);

  /**
   * @Description: 统计查询院系隔离人员的人数
   * @Author: ws
   * @Date: 2022/8/8 17:19
   * @Param: [command]
   * @Return: java.lang.Integer
   */
//  @SqlParser(filter = true)
//  @Select("select count(*) \n"
//      + "      from isolate_personnel\n"
//      + "       where   state = 1  AND is_delete = 0 and isolate_personnel.code like Concat ('19',#{command},'%')\n")
//  Integer countQuarantine(String command);

  /**
   * @Description: 查询出隔离人员总人数
   * @Author: ws
   * @Date: 2022/8/8 17:21
   * @Param: []
   * @Return: java.lang.Integer
   */
  @Select("SELECT count(code) FROM isolate_personnel WHERE  is_delete = 0 AND state in(1,3)")
  Integer getCountByCode();

  /**
   * @Description: 查询需要隔离人员是否已在隔离人员表
   * @Author: ws
   * @Date: 2022/8/8 17:21
   * @Param: [code]
   * @Return: int
   */
  @Select("SELECT count(code) FROM isolate_personnel WHERE  code = #{code} and is_delete = 0 and state IN (0,1,3)")
  int checkIsolatePerson(String code);

  /**
   * @Description: 获得该同学在隔离人员表的数量
   * @Author: ChnyTrcy
   * @Date: 2022/8/8 17:22
   * @Param: [code]
   * @Return: java.lang.Integer
   */
  Integer getNumOfStudent(String code);
//
//  /**
//   * @Description: 检查该学生是否已经在隔离人员表
//   * @Author: ChnyTrcy
//   * @Date: 2022/8/9 15:04
//   * @Param: [list]
//   * @Return: java.util.List<java.lang.String>
//   */
//  List<String> checkIsExistByList(List<CrossCityTravelRecordComplexDTO> list);
}




