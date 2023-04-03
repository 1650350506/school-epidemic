package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.command.DelBatchByCodesCommand;
import com.hmdp.command.DelByCodeCommand;
import com.hmdp.command.QueryWorkPersonByCodeCommand;
import com.hmdp.command.QueryWorkPersonByKeyAndPageCommand;
import com.hmdp.command.UpdateWorkPersonByCodeCommand;
import com.hmdp.entity.WorkPerson;
import com.hmdp.vo.WorkPersonDetailVO;
import com.hmdp.vo.WorkPersonVO;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.workperson
 * @ClassName: WorkPersonRepository
 * @Author: yepeng
 * @Description:
 * @Date: 2022/7/18 11:18
 * @Version: 1.0
 */
public interface WorkPersonService extends IService<WorkPerson> {

  /**
   * @Description: 根据关键字(可空)分页查询防疫人员列表
   * @Author: yepeng
   * @Date: 15:11 2022/8/11
   * @Param command:
   * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.workperson.WorkPersonVO>
  */
  List<WorkPersonVO> queryPageByKey(QueryWorkPersonByKeyAndPageCommand command);

  /**
   * @Description: 根据code查询防疫人员详情信息
   * @Author: yepeng
   * @Date: 15:11 2022/8/11
   * @Param command:
   * @Return: com.tianque.grid.ce2.constant.infrastructure.server.vo.workperson.WorkPersonDetailVO
  */
  WorkPersonDetailVO queryDetailsByCode(QueryWorkPersonByCodeCommand command);

  /**
   * @Description: 根据code删除防疫人员
   * @Author:
   * @Date: 15:11 2022/8/11
   * @Param command:
   * @Return: java.lang.Integer
  */
  Integer delByCode(DelByCodeCommand command);

  /**
   * @Description: 根据code List 批量删除防疫人员
   * @Author: yepeng
   * @Date: 15:11 2022/8/11
   * @Param command:
   * @Return: java.lang.Integer
  */
  Integer delBatchByCodes(DelBatchByCodesCommand command);

  /**
   * @Description: 修改防疫人员员信息
   * @Author: yepeng
   * @Date: 15:11 2022/8/11
   * @Param command:
   * @Return: java.lang.Integer
  */
  Integer updateByCode(UpdateWorkPersonByCodeCommand command);

  List<WorkPerson> getEpidemicPreventionPersonnel();

  /**
   * @Description: 根据工号查看姓名
   * @Author: yepeng
   * @Date: 15:12 2022/8/11
   * @Param code: 工号
   * @Return: java.lang.String
  */
  String queryNameByCode(String code);

  /**
   * @Description:查询工作人员信息
   * @Author: zcy
   * @Date: 2022/8/8 10:38
   * @Param: [code]
   * @Return: java.lang.String
   */
  String selectEpPersonnelName(String code);

  /**
   * @Description: 查询已存在的工号
   * @Author: yepeng
   * @Date: 15:12 2022/8/11
   * @Return: java.util.List<java.lang.String>
  */
  List<String> queryCodes();

  /**
   * @Description: 查询已存在的账号
   * @Author: yepeng
   * @Date: 15:12 2022/8/11
   * @Return: java.util.List<java.lang.String>
  */
  List<String> queryAccount();

  /**
   * @Description: 查询已有的手机号
   * @Author: yepeng
   * @Date: 15:23 2022/8/10
   * @Return: java.util.List<java.lang.String>
  */
  List<String> queryPhone();
}
