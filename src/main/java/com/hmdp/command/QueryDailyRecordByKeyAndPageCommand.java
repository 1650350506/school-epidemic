package com.hmdp.command;

import com.hmdp.dto.GridPageCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.groupleader
 * @ClassName: GroupLeaderQueryByKeyAndPageCommand
 * @Author: yepeng
 * @Description: 学生行程信息-根据关键字查询学生本市行程信息列表command
 * @Date: 2022/7/15 17:37
 * @Version: 1.0
 */
@Data
@ApiModel("学生行程信息-根据关键字查询学生本市行程信息列表command")
public class QueryDailyRecordByKeyAndPageCommand extends GridPageCommand implements Serializable {

  @ApiModelProperty(value = "关键字(姓名、学号、二级学院)")
  private String key;

  @ApiModelProperty(value = "地区风险等级(默认为null，不将其视为查询条件)")
  private Integer risk;

  @ApiModelProperty(value = "满足风险条件的社区列表,此参数不用前端填写，后端根据用户选择的情况填写")
  private List<String> riskTowns;

  @ApiModelProperty(value = "学生学号列表，此参数不用前端填写，后端根据用户选择的情况填写")
  private Set<String> codeList;

  @ApiModelProperty(value = "当前时间-14天的时间(后端操作)")
  private String startTime;
}
