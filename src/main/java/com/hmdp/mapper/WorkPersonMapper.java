package com.hmdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmdp.command.DelBatchByCodesCommand;
import com.hmdp.command.DelByCodeCommand;
import com.hmdp.command.QueryWorkPersonByKeyAndPageCommand;
import com.hmdp.command.UpdateWorkPersonByCodeCommand;
import com.hmdp.entity.WorkPerson;
import com.hmdp.vo.WorkPersonVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.mapper
 * @ClassName: WorkPersonMapper
 * @Author: zcy
 * @Description:
 * @Date: 2023/3/7 16:26
 * @Version: 1.0
 */
@Mapper
public interface WorkPersonMapper extends BaseMapper<WorkPerson> {

  /**
   * @Description: 根据关键字及健康码状态查询防疫人员列表
   * @Author: yepeng
   * @Date: 21:31 2022/8/9
   * @Param command:
   * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.workperson.WorkPersonVO>
   */
  List<WorkPersonVO> queryPageByKey(QueryWorkPersonByKeyAndPageCommand command);

  /**
   * @Description: 删除防控人员
   * @Author: yepeng
   * @Date: 21:29 2022/8/9
   * @Param command:
   * @Return: java.lang.Integer
   */
  Integer delByCode(DelByCodeCommand command);

  /**
   * @Description: 批量删除防控人员
   * @Author: yepeng
   * @Date: 21:29 2022/8/9
   * @Param command:
   * @Return: java.lang.Integer
   */
  Integer delBatchByCodes(DelBatchByCodesCommand command);

  /**
   * @Description: 修改防疫人员信息
   * @Author: yepeng
   * @Date: 21:30 2022/8/9
   * @Param command:
   * @Return: java.lang.Integer
   */
  Integer updateByCode(UpdateWorkPersonByCodeCommand command);

  /**
   * @Description: 根据工号查看姓名
   * @Author: yepeng
   * @Date: 21:30 2022/8/9
   * @Param code:
   * @Return: java.lang.String
   */
  String queryNameByCode(@Param("code") String code);

  /**
   * @Description: 根据工号列表查询防疫人员列表
   * @Author: yepeng
   * @Date: 21:30 2022/8/9
   * @Param codes:
   * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.workperson.WorkPersonVO>
   */
  List<WorkPersonVO> queryByCodes(@Param("codes") List<String> codes);

  /**
   * @Description: 查询已存在的工号
   * @Author: yepeng
   * @Date: 21:30 2022/8/9
   * @Return: java.util.List<java.lang.String>
   */
  List<String> queryCodes();

  /**
   * @Description: 查询已存在的账号
   * @Author: yepeng
   * @Date: 21:30 2022/8/9
   * @Return: java.util.List<java.lang.String>
   */
  List<String> queryAccount();

  /**
   * @Description: 查询已存在的手机号
   * @Author: yepeng
   * @Date: 15:23 2022/8/10
   * @Return: java.util.List<java.lang.String>
   */
  List<String> queryPhone();

  /**
   * @Description: 获得工作人员的信息
   * @Author: zcy
   * @Date: 2022/8/8 10:33
   * @Param: [code]
   * @Return: java.lang.String
   */
  @Select("select name from work_person where code=#{code}")
  String selectEpPersonnelName(String code);
}
