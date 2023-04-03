package com.hmdp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.dto
 * @ClassName: GridPageCommand
 * @Author: zcy
 * @Description:页码
 * @Date: 2023/3/30 19:30
 * @Version: 1.0
 */
@ApiModel("分页查询参数,只需传入pageNum和pageSize两个参数")
public class GridPageCommand implements Serializable {
  @ApiModelProperty(
      value = "页码",
      required = true,
      example = "1"
  )
  protected @NotNull(
      message = "当前页数不能为空"
  ) Integer pageNum;
  @ApiModelProperty(
      value = "每页size",
      required = true,
      example = "10"
  )
  protected @NotNull(
      message = "pageSize不能为空"
  ) Integer pageSize;
  @ApiModelProperty(
      value = "排序方式",
      example = "id"
  )
  protected String orderBy = "id";

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public Integer getPageNum() {
    return this.pageNum;
  }

  public Integer getPageSize() {
    return this.pageSize;
  }

  public String getOrderBy() {
    return this.orderBy;
  }

  public GridPageCommand() {
  }

  public GridPageCommand(Integer pageNum, Integer pageSize, String orderBy) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.orderBy = orderBy;
  }
}