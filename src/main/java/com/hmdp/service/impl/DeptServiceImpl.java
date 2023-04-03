package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.entity.Dept;
import com.hmdp.mapper.DeptMapper;
import com.hmdp.service.DeptService;
import com.hmdp.vo.DeptInfoVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.mybatis.repositoryimpl
 * @ClassName: DeptRepositoryImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/7/20 18:20
 * @Version: 1.0
 */
@Repository("deptRepository")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

  @Autowired
  private DeptMapper deptMapper;

  @Override
  public List<Dept> getDeptComplexList() {
    return deptMapper.selectList(null);
  }

  @Override
  public List<DeptInfoVO> getDeptInfoList() {
    return deptMapper.getDeptInfoList();
  }

  @Override
  public String selectCollegeName(String code) {
    return getBaseMapper().selectCollegeName(code);
  }
}
