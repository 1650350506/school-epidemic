package com.hmdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmdp.entity.Dept;
import com.hmdp.vo.DeptInfoVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.mapper
 * @ClassName: DeptMapper
 * @Author: zcy
 * @Description:
 * @Date: 2023/3/7 16:38
 * @Version: 1.0
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

  /**
   * @Description: 获取所有的二级学院信息(编号+名称)
   * @Author: yepeng
   * @Date: 14:59 2022/8/11
   * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.workperson.DeptInfoVO>
   */
  List<DeptInfoVO> getDeptInfoList();

  @Select("select name from dept where dept.code = #{code}")
  String selectCollegeName(String code);
}