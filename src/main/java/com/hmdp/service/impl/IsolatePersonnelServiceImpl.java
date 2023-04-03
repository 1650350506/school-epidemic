package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.entity.IsolatePersonnel;
import com.hmdp.mapper.IsolatePersonnelMapper;
import com.hmdp.service.IsolatePersonnelService;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.service.impl
 * @ClassName: isolatePersonnelServiceImpl
 * @Author: zcy
 * @Description:接口
 * @Date: 2023/3/30 19:47
 * @Version: 1.0
 */
@Service
public class IsolatePersonnelServiceImpl extends ServiceImpl<IsolatePersonnelMapper, IsolatePersonnel> implements
    IsolatePersonnelService {

}
