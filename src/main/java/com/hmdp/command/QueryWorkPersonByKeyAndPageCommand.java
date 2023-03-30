package com.hmdp.command;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.supadmin
 * @ClassName: SupAdminQueryByKeyAndPageCommand
 * @Author: yepeng
 * @Description: 防疫人员管理-关键字分页查询工作人员
 * @Date: 2022/7/15 16:38
 * @Version: 1.0
 */
@Data
@ApiModel("防疫人员管理-关键字分页查询工作人员command")
@NoArgsConstructor
public class QueryWorkPersonByKeyAndPageCommand  implements Serializable {

  @ApiModelProperty(value = "关键字")
  private String key;

  @ApiModelProperty(value = "健康码颜色(0-绿色 1-黄色 2-红色) 默认为null")
  private Byte color;

  @ApiModelProperty(value = "工号列表(后端使用)")
  private List<String> codes;
}
