package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.entity.StudentClass;
import com.hmdp.mapper.StudentClassMapper;
import com.hmdp.service.StudentClassService;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: school-epidemic
 * @Package: com.hmdp.service.impl
 * @ClassName: StudentClassServiceImpl
 * @Author: zcy
 * @Description:班级
 * @Date: 2023/3/30 20:48
 * @Version: 1.0
 */
@Service
public class StudentClassServiceImpl  extends ServiceImpl<StudentClassMapper, StudentClass> implements StudentClassService {

}
