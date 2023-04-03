package com.hmdp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.infrastructure.server.vo.supadmin
 * @ClassName: WorkPersonDetailVO
 * @Author: yepeng
 * @Description: 防疫人员详情信息VO
 * @Date: 2022/7/15 17:13
 * @Version: 1.0
 */
@ApiModel("防疫人员详情VO")
@Data
@NoArgsConstructor
public class WorkPersonDetailVO implements Serializable {

  @ApiModelProperty(value = "姓名", required = true)
  private String name;

  @ApiModelProperty(value = "工号", required = true)
  private String code;

  @ApiModelProperty(value = "性别", required = true)
  private int sex;

  @ApiModelProperty(value = "当前职务", required = true)
  private String systemPost;

  @ApiModelProperty(value = "校内职务", required = true)
  private String schoolPost;

  @ApiModelProperty(value = "手机号", required = true)
  private String phone;

  @ApiModelProperty(value = "二级学院", required = true)
  private String deptName;

  @ApiModelProperty(value = "通行记录", required = true)
  private List<WorkPersonRecordVO> recordVOList;
}
