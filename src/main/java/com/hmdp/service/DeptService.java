package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.entity.Dept;
import com.hmdp.vo.DeptInfoVO;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.classPositioning
 * @ClassName: DeptRepository
 * @Author: ChnyTrcy
 * @Description: 院系
 * @Date: 2022/7/20 18:19
 * @Version: 1.0
 */
public interface DeptService extends IService<Dept> {

  /**
   * @Description: 获取dept的HashMap
   * @Author: ChnyTrcy
   * @Date: 2022/8/9 14:47
   * @Param: []
   * @Return: java.util.List<com.tianque.grid.ce2.domain.model.dept.Dept>
   */
  List<Dept> getDeptComplexList();

  /**
   * @Description: 获取所有院系的名称和编号
   * @Author: yepeng
   * @Date: 15:05 2022/8/11
   * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.workperson.DeptInfoVO>
  */
  List<DeptInfoVO> getDeptInfoList();

  /**
   * @Description: 根据二级学院编号查询二级学院名称
   * @Author: yepeng
   * @Date: 15:05 2022/8/11
   * @Param code: 二级学院编号
   * @Return: java.lang.String
  */
  String selectCollegeName(String code);

}
