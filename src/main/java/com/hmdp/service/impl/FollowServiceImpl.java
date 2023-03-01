package com.hmdp.service.impl;

import com.hmdp.entity.Follow;
import com.hmdp.mapper.FollowMapper;
import com.hmdp.service.IFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

  public static void main(String[] args) {
    Properties properties = new Properties();

    KafkaProducer<String, String> stringStringKafkaProducer = new KafkaProducer<>(properties);
    stringStringKafkaProducer.send(new ProducerRecord<>("hh", 1,"niubi", "nihao"), new Callback() {
      @Override
      public void onCompletion(RecordMetadata recordMetadata, Exception e) {

      }
    });
    stringStringKafkaProducer.close();
  }

}
