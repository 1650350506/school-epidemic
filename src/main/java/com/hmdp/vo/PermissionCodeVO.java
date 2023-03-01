package com.hmdp.vo;

import com.hmdp.entity.PermissionCode;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hm-dianping
 * @BelongsPackage: com.hmdp.vo
 * @Author: zcy
 * @CreateTime: 2023-03-01  09:29
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class PermissionCodeVO {
   private String username;

   private List<PermissionCode> permissionCodeList;
}
