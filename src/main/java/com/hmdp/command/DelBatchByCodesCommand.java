package com.hmdp.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.application.command.supadmin
 * @ClassName: SupAdminBatchDelByIds
 * @Author: yepeng
 * @Description: 防控人员管理-根据code进行批量删除防控人员Command
 * @Date: 2022/7/18 13:39
 * @Version: 1.0
 */
@Data
@ApiModel("防控人员管理-根据code进行批量删除防控人员Command")
public class DelBatchByCodesCommand implements Serializable {

  @NotNull(message = "选中的code列表为空")
  @ApiModelProperty(value = "工号List", required = true)
  private List<String> codes;
}
