package com.hmdp.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-group2
 * @Package: com.tianque.grid.ce2.domain.model.isolatepersonnel
 * @ClassName: epPersonnel
 * @Author: zcy
 * @Description:防疫人员查询
 * @Date: 2022/7/26 9:51
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class epPersonnelVO implements Serializable {

  @ApiModelProperty(name = "姓名", required = true)
  private String name;
  @ApiModelProperty(name = "防疫人员编号", required = true)
  private String code;
}
