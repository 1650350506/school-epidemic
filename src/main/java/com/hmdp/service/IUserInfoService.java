package com.hmdp.service;

import com.hmdp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.vo.PermissionCodeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-24
 */
public interface IUserInfoService  {


    PermissionCodeVO listMenu(User user);
}
