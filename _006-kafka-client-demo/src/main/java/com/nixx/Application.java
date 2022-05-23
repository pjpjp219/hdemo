package com.nixx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nixx.producer.*;

/**
 * *******************生产者配置**************************
 * 可以使用脚本创建一个消费者，用于监听生产者的程序运行效果
 * .\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test
 *
 * 最基础的生产者配置，以及一些常用的发送方法示例
 * {@link _001_CustomProducer}
 *
 * 生产者配置自定义分区器，根据不同的条件将数据发往不同的分区
 * {@link _002_CustomPartitionerProducer}
 *
 * 提高了吞吐量的生产者配置
 * {@link _003_ProducerThroughput}
 *
 * 保证了消息可靠性的生产者配置
 * {@link _004_ProducerDataReliable}
 * *****************************************************
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
