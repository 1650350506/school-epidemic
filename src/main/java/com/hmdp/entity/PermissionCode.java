package com.hmdp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @BelongsProject: school-epidemic
 * @BelongsPackage: com.hmdp.entity
 * @Author: zcy
 * @CreateTime: 2023-03-01  10:19
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@TableName("permission_code")
public class PermissionCode {
    private Long id;

    private String path;

    private String icon;

    private String name;

    private Long userId;

    private int isDeleted;

    private String title;
}
