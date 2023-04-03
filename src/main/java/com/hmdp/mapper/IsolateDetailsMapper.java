package com.hmdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmdp.command.IsolationRecordQueryCommand;
import com.hmdp.entity.IsolateDetails;
import com.hmdp.vo.IsolaterecordVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.mybatis.mapper
 * @ClassName: IsolateDetailsMapper
 * @Author: zcy
 * @Description: 学生隔离记录和分析接口用的mapper层
 * @Date: 2022/7/19 16:27
 * @Version: 1.0
 */
@Mapper
public interface IsolateDetailsMapper extends BaseMapper<IsolateDetails> {

  /**
   * @Description: 查询隔离记录
   * @Author: zcy
   * @Date: 2022/8/8 10:29
   * @Param: [command]
   * @Return: java.util.List<com.tianque.grid.ce2.constant.infrastructure.server.vo.riskpersonnel.IsolaterecordVO>
   */
  @Select("select id,code,nucleic_acid_time as nucleicAcidTime,nucleic_acid_key as nucleicAcidKey,temperature,protector  from isolate_details where isolate_details.code = #{code} and isolate_details.is_delete =0 ")
  List<IsolaterecordVO> queryPageIsolateCord(IsolationRecordQueryCommand command);


  /**
   * @Description:
   * @Author: yepeng
   * @Date: 11:18 2022/8/10
   * @Param start:
   * @Return: java.util.List<java.lang.String>
  */
  List<String> queryCodeForDelWorkPerson(@Param("start") String start);
}

