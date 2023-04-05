package com.hmdp.service.impl;

import static com.hmdp.constant.CommonConstant.LIST_EPPERSONNEL_KEY;
import static com.hmdp.constant.CommonConstant.TEMPERATURE_ABNORMALITY_KEY;
import static com.hmdp.constant.CommonConstant.TIME_COMMON;
import static com.hmdp.constant.IsolationStatus.FOUR;
import static com.hmdp.constant.IsolationStatus.ONE;
import static com.hmdp.constant.IsolationStatus.THREE;
import static com.hmdp.constant.IsolationStatus.TWO;
import static com.hmdp.constant.NucleicAcidResults.POSITIVE;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.command.IsolatePersonnelCreateCommand;
import com.hmdp.command.IsolatePersonnelModifyCommand;
import com.hmdp.command.IsolatePersonnelQueryCommand;
import com.hmdp.command.IsolationRecordCreateCommand;
import com.hmdp.command.IsolationRecordDeleteCommand;
import com.hmdp.command.IsolationRecordQueryCommand;
import com.hmdp.command.StudentCheckCommand;
import com.hmdp.command.StudentJourneyByCodeCommand;
import com.hmdp.command.StudentQueryPageBaseCommand;
import com.hmdp.constant.IsolationStatus;
import com.hmdp.dto.Result;
import com.hmdp.entity.Dept;
import com.hmdp.entity.IsolateDetails;
import com.hmdp.entity.IsolatePersonnel;
import com.hmdp.entity.Student;
import com.hmdp.entity.StudentClass;
import com.hmdp.entity.WorkPerson;
import com.hmdp.mapper.DeptMapper;
import com.hmdp.mapper.IsolateDetailsMapper;
import com.hmdp.mapper.IsolatePersonnelMapper;
import com.hmdp.service.IsolateDetailsService;
import com.hmdp.service.IsolatePersonnelService;
import com.hmdp.service.RiskPersonnelService;
import com.hmdp.service.StudentClassService;
import com.hmdp.service.StudentService;
import com.hmdp.service.WorkPersonService;
import com.hmdp.utils.BusinessException;
import com.hmdp.utils.CommonUtil;
import com.hmdp.vo.IsolatepersonnelVO;
import com.hmdp.vo.IsolaterecordVO;
import com.hmdp.vo.StudentVO;
import com.hmdp.vo.epPersonnelVO;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.reflections.serializers.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: hm-dianping
 * @Package: com.hmdp.service.impl
 * @ClassName: RiskPersonnnerlServiceImpl
 * @Author: zcy
 * @Description:风险人员接口实现类
 * @Date: 2022/10/13 17:59
 * @Version: 1.0
 */
@Service
public class RiskPersonnelServiceImpl extends
    ServiceImpl<IsolatePersonnelMapper,IsolatePersonnel> implements RiskPersonnelService {


  @Resource
  private RedisTemplate redisTemplate;


  @Autowired
  private StudentService studentService;
  @Autowired
  private IsolatePersonnelMapper isolatePersonnelMapper;

  @Autowired
  private IsolateDetailsService isolateDetailsService;

  @Autowired
   private IsolatePersonnelService isolatePersonnelService;

  @Autowired
  private DeptMapper deptMapper;

  @Autowired
  private StudentClassService studentClassService;

  @Autowired
  private WorkPersonService workPersonService;

  @Autowired
  private IsolateDetailsMapper isolateDetailsMapper;

  @Override
  public Result isolateRecordRepository(IsolationRecordCreateCommand command) {
    float temperature = command.getTemperature();
    String code = command.getCode();
    //判断体温是否异常
    if (temperature == 38f) {
      //将学号作为键值查询然后自增一
      redisTemplate.opsForValue().increment(TEMPERATURE_ABNORMALITY_KEY+code, 1);
      //查询对应的学号的异常次数
      Object o = redisTemplate.boundValueOps(TEMPERATURE_ABNORMALITY_KEY+code).get(0,-1);
      int count = Integer.parseInt(String.valueOf(o));
      //如果连续异常次数大于二则将该学生加入治疗
      if (count > 2) {
        IsolatePersonnelModifyCommand isolatePersonnelModifyCommand = new IsolatePersonnelModifyCommand();
        isolatePersonnelModifyCommand.setCode(code);
        this.treatmentIsolatePersonnel(isolatePersonnelModifyCommand);
      }
    } else {
      //如果体温不异常则将学号对应的值设为一
      redisTemplate.opsForValue().set(TEMPERATURE_ABNORMALITY_KEY+code, 1);
    }
    //判断核酸是否异常，异常则则将该学生加入治疗
    if (command.getNucleicAcidKey().equals(POSITIVE.getNumber())) {
      IsolatePersonnelModifyCommand isolatePersonnelModifyCommand = new IsolatePersonnelModifyCommand();
      isolatePersonnelModifyCommand.setCode(code);
      this.treatmentIsolatePersonnel(isolatePersonnelModifyCommand);
    }
    IsolateDetails details = CglibUtil.copy(command, IsolateDetails.class);
    return Result.ok(isolateDetailsService.save(details));
  }

  @Transactional(rollbackFor = Exception.class)
  public Result isolatePersonnelRepository(IsolatePersonnelCreateCommand command) {
    IsolatePersonnel isolatePersonnel = new IsolatePersonnel();
    BeanUtil.copyProperties(command,isolatePersonnel);
    Timestamp startTime = isolatePersonnel.getStartTime();

    if(startTime!=null){
      //设置时间格式
      SimpleDateFormat format = new SimpleDateFormat(TIME_COMMON);
      Calendar ca = Calendar.getInstance();
      ca.setTime(startTime);
      //给隔离开始时间加十四天得到隔离结束时间
      ca.add(Calendar.DATE, 14);
      Date time = ca.getTime();
      String endTime = format.format(time);
      isolatePersonnel.setEndTime(Timestamp.valueOf(endTime));
    }
    String code = isolatePersonnel.getCode();
    LambdaQueryWrapper<IsolatePersonnel> queryWrapper =new LambdaQueryWrapper<>();
    queryWrapper.eq(IsolatePersonnel::getCode, code).ne(IsolatePersonnel::getState,THREE.getNumber());
    IsolatePersonnel isolatePersonnel1 = isolatePersonnelService.getOne(queryWrapper);
    if(ObjectUtil.isEmpty(isolatePersonnel1)){
      int insert = isolatePersonnelMapper.insert(isolatePersonnel);
    } else {
      throw new BusinessException("隔离人员已经被添加");
    }

    return Result.ok(true);

  }
  /**
   * @Description: 统计需隔离未通知人数
   * @Author: zcy
   * @Date: 2022/11/8 10:46
   * @Param: []
   * @Return: com.tianque.grid.ddd.ui.ResponseResult<com.tianque.grid.ddd.ui.vo.SimpleVo < java.lang.Integer>>
   */
  @Override
  public Result tobePageIsolate() {
    LambdaQueryWrapper<IsolatePersonnel> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(IsolatePersonnel::getState, ONE.getNumber());
    int count = isolatePersonnelService.count(queryWrapper);
    return Result.ok(count);
  }
  /**
   * @Description: 统计已隔离人数
   * @Author: zcy
   * @Date: 2022/11/8 10:46
   * @Param: []
   * @Return: com.tianque.grid.ddd.ui.ResponseResult<com.tianque.grid.ddd.ui.vo.SimpleVo < java.lang.Integer>>
   */

  @Override
  public Result quarantinedPageIsolate() {
    LambdaQueryWrapper<IsolatePersonnel> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(IsolatePersonnel::getState, TWO.getNumber());
    int count = isolatePersonnelService.count(queryWrapper);
    return Result.ok(count);
  }

  @Override
  public Result selectIsolatedPersonEndNumber() {
    LambdaQueryWrapper<IsolatePersonnel> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(IsolatePersonnel::getState, THREE.getNumber());
    int count = isolatePersonnelService.count(queryWrapper);
    return Result.ok(count);
  }

  @Override
  public Result selectPeopletreatedNumber() {
    LambdaQueryWrapper<IsolatePersonnel> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(IsolatePersonnel::getState, FOUR.getNumber());
    int count = isolatePersonnelService.count(queryWrapper);
    return Result.ok(count);
  }

  @Override
  public Result queryPageIsolate(IsolatePersonnelQueryCommand command) {
    IPage page=new Page(command.getPageNum(), command.getPageSize());
    IPage<IsolatepersonnelVO> pageData = isolatePersonnelMapper.queryPageIsolate(page,command);
    List<IsolatepersonnelVO> records = pageData.getRecords();
    for (IsolatepersonnelVO isolatepersonnelVO : records) {
      String code = isolatepersonnelVO.getCode();
      /*截取学院号*/
      String substring = code.substring(2, 4);
      /*调用方法得到学院名称*/
      String collegeName = deptMapper.selectCollegeName(substring);
      isolatepersonnelVO.setStuCollege(collegeName);
      String classCord = isolatepersonnelVO.getClassname();
      QueryWrapper<StudentClass> queryWrapper1 = new QueryWrapper<>();
      queryWrapper1.eq("code", classCord);
      StudentClass studentClass = studentClassService.getOne(queryWrapper1);
      /*得到班级名称*/
      String className = studentClass.getName();
      isolatepersonnelVO.setClassname(className);
      String code1 = isolatepersonnelVO.getCode();
      QueryWrapper<IsolateDetails> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("code", code1).orderBy(true, false, "nucleic_acid_time")
          .last("limit 1");
      /*如果有隔离记录就拿到最新的记录*/
      IsolateDetails isolateDetails = isolateDetailsService.getOne(queryWrapper);
      /*判断隔离记录是否为空然后把核酸和体温加入VO类*/
      if (isolateDetails != null) {
        Integer nucleicAcidKey = isolateDetails.getNucleicAcidKey();
        float temperature = isolateDetails.getTemperature();
        isolatepersonnelVO.setNucleicacidkey(nucleicAcidKey);
        isolatepersonnelVO.setTemperature(temperature);
      }
    }
    IPage pages=new Page(page.getPages(),page.getSize(),page.getTotal());
    pages.setRecords(records);
    return Result.ok(pages);
  }

  @Override
  public Result queryPageIsolateCord(IsolationRecordQueryCommand command) {
    String code = command.getCode();
    return Result.ok(isolateDetailsMapper.queryPageIsolateCord(command));
  }

  @Override
  public Result selectStudent(String code) {
    Student student = studentService.selectStudent(code);
    StudentVO studentVO =CglibUtil.copy(student, StudentVO.class);
    LambdaQueryWrapper<StudentClass> queryWrapper1 = new LambdaQueryWrapper<>();
    LambdaQueryWrapper<Dept> queryWrapper2 = new LambdaQueryWrapper<>();
    String classCode = student.getClassCode();
    String deptCode = student.getDeptCode();
    /*得到相应的学院名称和班级名称*/
    queryWrapper1.eq(StudentClass::getCode, classCode);
    StudentClass studentClass = studentClassService.getOne(queryWrapper1);
    String name = studentClass.getName();
    queryWrapper2.eq(Dept::getCode, deptCode);
    Dept dept = deptMapper.selectOne(queryWrapper2);
    String name1 = dept.getName();
    studentVO.setClassName(name);
    studentVO.setDeptName(name1);
    return Result.ok(studentVO);
  }

  @Override
  public Result getEpidemicPreventionPersonnel() {
    //查看Redis中是否有防疫人员信息
    List list = redisTemplate.opsForList()
        .range(LIST_EPPERSONNEL_KEY, 0, 1);
    if(list !=null&& !list.isEmpty()){
      List<epPersonnelVO> epidemicPreventionPersonnelList1 = (List<epPersonnelVO>) list.get(0);
      return Result.ok(epidemicPreventionPersonnelList1);
    }
    //从数据库中查询防疫人员信息
    List<WorkPerson> epidemicPreventionPersonnel = workPersonService.getEpidemicPreventionPersonnel();
    List<epPersonnelVO> epPersonnelVOS = CglibUtil.copyList(epidemicPreventionPersonnel,
        epPersonnelVO::new);
    //把数据库中查到的防疫人员信息添加到Redis中
    if(epPersonnelVOS!=null) {
    redisTemplate.opsForList().rightPush(LIST_EPPERSONNEL_KEY,epPersonnelVOS);
    }
    return Result.ok(epPersonnelVOS);
  }

  @Override
  public Result queryPageBase(StudentQueryPageBaseCommand command) {
    return null;
  }

  @Override
  public Result getStudentJourneyByCode(StudentJourneyByCodeCommand command) {
    return null;
  }

  @Override
  public boolean checkStudent(StudentCheckCommand command) {
  LambdaQueryWrapper<Student> qu =new LambdaQueryWrapper<>();
  qu.eq(Student::getCode,command.getCode());
    Student one = studentService.getOne(qu);
    return ObjectUtil.isEmpty(one);
  }

  @Override
  public Result getOutSchoolByCode(StudentJourneyByCodeCommand command) {
    return null;
  }

  @Override
  public Integer addStudent() {
    int key = 0;
    //该json数据至少2000个数据
    String jsonUrl = "C:/Users/Lenovo/Desktop/学生.json";
    File file = new File(jsonUrl);
    String jsonData = CommonUtil.getStr(file);
    JSONArray jsonArrays = (JSONArray) JSONObject.parse(jsonData);

    //姓名列表,读取Json字符串
    List<String> nameList = new ArrayList<>();
    for (Object jsonObject : jsonArrays) {
      nameList.add(jsonObject.toString());
    }

    //电话列表
    List<String> phoneList = new ArrayList<>();
    phoneList.add("19858104405");
    phoneList.add("17816133124");
    phoneList.add("18358889334");
    phoneList.add("13735344542");
    phoneList.add("18530488695");

    //班级列表
    List<String> classList = studentService.getClassList();
    for (int i = 0; i < 1500; i++) {
      Student student = new Student();
      //学号
      String codeProduce;
      //班级号
      String classProduce;
      do {
        int t = (int) (Math.random() * 50 + 1);
        int no = (int) (Math.random() * classList.size());
        classProduce = classList.get(no);
        if (t < 10) {
          String r = "0" + t;
          codeProduce = classProduce + r;
        } else {
          codeProduce = classProduce + t;
        }
      } while (studentService.checkIsExist(codeProduce));
      student.setCode(codeProduce);
      student.setClassCode(classProduce);
      String nameProduce = nameList.get(i * 2);
      student.setName(nameProduce);
      //性别
      if (Math.random() * 2 > 1) {
        student.setSex((byte) 0);
      } else {
        student.setSex((byte) 1);
      }
      //联系方式
      String phoneProduce = phoneList.get((int) (Math.random() * phoneList.size()));
      student.setPhoneNumber(phoneProduce);
      //region ========== 身份证号码 ==========
      int year = (int) (Math.random() * 20 + 1990);
      int mouth = (int) (Math.random() * 12 + 1);
      int day = (int) (Math.random() * 28 + 1);
      int checkCode = (int) (Math.random() * 9000 + 1000);
      String idCardProduce = "330106";
      idCardProduce += year;
      if (mouth < 10) {
        idCardProduce += "0";
      }
      idCardProduce += mouth;
      if (day < 10) {
        idCardProduce += "0";
      }
      idCardProduce += day;
      idCardProduce += checkCode;
      student.setIdCard(idCardProduce);
      //endregion
      student.setAddress("浙江省杭州市西湖区");
      student.setEmergencyContact(nameList. get(i * 2 + 1));
      String emergencyPhone = phoneList.get((int) (Math.random() * phoneList.size()));
      student.setEmergencyContactPhone(emergencyPhone);
      student.setDeptCode(classProduce.substring(2, 4));
      key += studentService.addStudentInformation(student);
    }
    return key;
  }

  @Override
  public boolean modifyIsolatePersonnel(IsolatePersonnelModifyCommand command) {
    String code = command.getCode();

    LambdaQueryWrapper<IsolatePersonnel> updateWrapper = new LambdaQueryWrapper<>();
    //转换格式
    IsolatePersonnel isolatePersonnel = new IsolatePersonnel();
    //将对应的隔离人员转为隔离结束
    updateWrapper.eq(IsolatePersonnel::getCode, command.getCode());
    isolatePersonnel.setState(THREE.getNumber());
    boolean update = isolatePersonnelService.update(isolatePersonnel, updateWrapper);
    //将隔离结束的人的缓存清零
    if(update)
    redisTemplate.opsForValue().set(TEMPERATURE_ABNORMALITY_KEY+code, "0");
    return update;
  }

  /**
   * @Description: 解除隔离人员
   * @Author: zcy
   * @Date: 2022/8/11 15:20
   * @Param: [command]
   * @Return: java.lang.Long
   */

//  public Long modifyIsolatePersonnel(IsolatePersonnelModifyCommand command) {
//    LambdaQueryWrapper<IsolatePersonnel> lambdaQueryWrapper1 =new LambdaQueryWrapper<>();
//    String code = command.getCode();
//    //查询这个学生是否已经在隔离人员表中除了隔离结束
//    lambdaQueryWrapper1.eq(IsolatePersonnel::getCode,command.getCode()).ne(IsolatePersonnel::getState,THREE.getNumber());
//    IsolatePersonnel isolatePersonnel1 = isolatePersonnelRepository.getOne(lambdaQueryWrapper1);
//    Timestamp endTime = isolatePersonnel1.getEndTime();
//    //得到当地时间
//    LocalDateTime now = LocalDateTime.now();
//    //如果隔离时间未到则不予解除隔离
//    if(endTime.after(Timestamp.valueOf(now))){
//      throw new BusinessException(ErrorCode.ISOLATION_TIME_ADDITION_ERROR);
//    }
//    LambdaQueryWrapper<IsolateDetails> lambdaQueryWrapper =new LambdaQueryWrapper<>();
//    //获取最新的一次体温信息
//    lambdaQueryWrapper.select(IsolateDetails::getTemperature).eq(IsolateDetails::getCode,code).
//        orderBy(true,false,IsolateDetails::getNucleicAcidTime)
//        .last("limit 1");
//    IsolateDetails isolateDetails = isolateDetailsRepository.getOne(lambdaQueryWrapper);
//    //如果没有隔离记录就报错，不能进行解除隔离的操作
//    if(isolateDetails==null){
//      throw new BusinessException(ErrorCode.TEMPERATURE_ABNORMAL);
//    }
//    Float temperature = isolateDetails.getTemperature();
//    //如果体温异常则不能接触隔离并将隔离时间延长三天
//    if(temperature==38L){
//      SimpleDateFormat format = new SimpleDateFormat(TIME_COMMON);
//      Calendar ca = Calendar.getInstance();
//      ca.setTime(Timestamp.valueOf(now));
//      //给隔离开始时间加三天天得到隔离结束时间
//      ca.add(Calendar.DATE, 3);
//      Date time = ca.getTime();
//      String lastEndTime = format.format(time);
//      lambdaQueryWrapper1.eq(IsolatePersonnel::getCode,code);
//      IsolatePersonnel isolatePersonnel=new IsolatePersonnel();
//      isolatePersonnel.setEndTime(Timestamp.valueOf(lastEndTime));
//      isolatePersonnelRepository.update(isolatePersonnel,lambdaQueryWrapper1);
//      throw new BusinessException(ErrorCode.MODIFY_ISOLATE_ERROR);
//    }
//    return isolatepersonDomainService.modifyIsolatePersonnel(command);
//  }

  /**
   * @Description: 删除隔离记录
   * @Author: zcy
   * @Date: 2022/8/11 15:20
   * @Param: [command]
   * @Return: java.lang.Long
   */
  public Long deleteRecord(IsolationRecordDeleteCommand command) {
    return null;
    // return isolateDetailsDomainService.deleteRecord(command);
  }

  /**
   * @Description: 通知隔离人员开始隔离
   * @Author: zcy
   * @Date: 2022/8/11 15:22
   * @Param: [command]
   * @Return: java.lang.Long
   */
  @Transactional(rollbackFor = Exception.class)
  public Result noticeIsolatePersonnel(IsolatePersonnelModifyCommand command) {
    LambdaQueryWrapper<IsolatePersonnel> updateWrapper = new LambdaQueryWrapper<>();
    IsolatePersonnel isolatePerson = CglibUtil.copy(command, IsolatePersonnel.class);
    //设置相应的时间模式
    SimpleDateFormat format = new SimpleDateFormat(TIME_COMMON);
    Calendar ca = Calendar.getInstance();
    //得到最新时间作为隔离开始时间
    LocalDateTime now = LocalDateTime.now();
    ca.setTime(Timestamp.valueOf(now));
    //给隔离开始时间加十四天得到隔离结束时间
    ca.add(Calendar.DATE, 14);
    Date time = ca.getTime();
    String endTime = format.format(time);
    IsolatePersonnel isolatePersonnel = new IsolatePersonnel();
    //将隔离开始时间和结束时间和隔离状态放入对应的实体类
    isolatePersonnel.setStartTime(Timestamp.valueOf(now));
    isolatePersonnel.setEndTime(Timestamp.valueOf(endTime));
    isolatePersonnel.setState(IsolationStatus.TWO.getNumber());
    updateWrapper.eq(IsolatePersonnel::getCode, command.getCode()).ne(IsolatePersonnel::getState,THREE.getNumber());
    //将对应的待隔离人员到已隔离
    boolean update = isolatePersonnelService.update(isolatePersonnel, updateWrapper);
    return Result.ok(update);
  }

  /**
   * @Description: 通知隔离人员治疗
   * @Author: zcy
   * @Date: 2022/8/11 15:22
   * @Param: [command]
   * @Return: java.lang.Long
   */
  @Transactional(rollbackFor = Exception.class)
  public Long treatmentIsolatePersonnel(IsolatePersonnelModifyCommand command) {
    LambdaQueryWrapper<IsolatePersonnel> updateWrapper = new LambdaQueryWrapper<>();
    //转换格式
    IsolatePersonnel isolatePerson = CglibUtil.copy(command, IsolatePersonnel.class);
    IsolatePersonnel isolatePersonnel = new IsolatePersonnel();
    updateWrapper.eq(IsolatePersonnel::getCode, command.getCode());
    //将对应的隔离人员到治疗中
    isolatePersonnel.setState(FOUR.getNumber());
    isolatePersonnelService.update(isolatePersonnel, updateWrapper);
    return isolatePerson.getId();


  }
}

