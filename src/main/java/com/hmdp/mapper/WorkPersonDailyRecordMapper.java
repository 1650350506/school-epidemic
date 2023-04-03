package com.hmdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmdp.entity.WorkPersonDailyRecord;
import com.hmdp.vo.WorkPersonRecordVO;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.mybatis.mapper
 * @ClassName: WorkPersonDailyRecordMapper
 * @Author: yepeng
 * @Description:
 * @Date: 2022/8/2 8:58
 * @Version: 1.0
 */
@Mapper
public interface WorkPersonDailyRecordMapper extends BaseMapper<WorkPersonDailyRecord> {

  /**
   * @Description: 防控人员提交出行记录(提交离校时间)
   * @Author: yepeng
   * @Date: 21:32 2022/8/9
   * @Param id: 主键
   * @Return: java.lang.Integer
  */
  Integer clockOut(@Param("id") Long id);

  /**
   * @Description: 根据当天日期查询工号和当日健康码颜色
   * @Author: yepeng
   * @Date: 21:32 2022/8/9
   * @Param today: 今日日期
   * @Return: java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>
  */
  List<HashMap<String, Object>> queryCodeAndColorByDate(@Param("today") String today);

  /**
   * @Description: 根据工号查询某员工14天的出行记录
   * @Author: yepeng
   * @Date: 21:33 2022/8/9
   * @Param code: 工号
   * @Param start: 今日日期
   * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.workperson.WorkPersonRecordVO>
  */
  List<WorkPersonRecordVO> queryAllByCode(@Param("code") String code, @Param("start") String start);
}
