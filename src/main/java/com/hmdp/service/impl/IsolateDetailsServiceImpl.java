package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.entity.IsolateDetails;
import com.hmdp.mapper.IsolateDetailsServiceMapper;
import com.hmdp.mapper.IsolatePersonnelMapper;
import com.hmdp.service.IsolateDetailsService;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.service.impl
 * @ClassName: IsolateDetailsServiceImpl
 * @Author: zcy
 * @Description:预警信息
 * @Date: 2023/3/30 19:51
 * @Version: 1.0
 */
@Service
public class IsolateDetailsServiceImpl extends ServiceImpl<IsolateDetailsServiceMapper, IsolateDetails> implements
    IsolateDetailsService {

}
