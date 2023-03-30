package com.hmdp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hmdp.entity.PermissionCode;
import com.hmdp.entity.User;
import com.hmdp.service.IUserInfoService;
import com.hmdp.service.PermissionCodeService;
import com.hmdp.vo.PermissionCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-24
 */
@Service
public class UserInfoServiceImpl  implements IUserInfoService {
   @Autowired
    PermissionCodeService permissionCodeService;
    @Override
    public PermissionCodeVO listMenu(User user) {
        QueryWrapper<PermissionCode> permissionCodeQueryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PermissionCode> eq = permissionCodeQueryWrapper.lambda().eq(PermissionCode::getUserId, user.getId());
        List<PermissionCode> list = permissionCodeService.list(eq);
        PermissionCodeVO permissionCodeVO = new PermissionCodeVO();
        permissionCodeVO.setPermissionCodeList(list);
        permissionCodeVO.setUsername(user.getUsername());
        return permissionCodeVO;
    }
}
