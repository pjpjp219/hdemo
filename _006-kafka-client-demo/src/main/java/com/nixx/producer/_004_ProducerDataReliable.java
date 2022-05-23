package com.nixx.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 生产者消息可靠性保证
 *
 * @author nixx
 * @date   2022年5月23日
 */
public class _004_ProducerDataReliable {

    public static void main(String[] args) {
        // 配置生产者
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        /*
           配置Kafka几个节点收到消息算完成接收
           ”1“ 只要 leader 节点收到消息就可以
           ”-1 或 all“ 集群内所有节点都接收到消息
           ”0“ 只要发出去，就算成功
           默认设置是 ”1“
           为了保证 acks=all 参数的效果，分区的副本（min.insync.replicas）应该设置为两个以上
         */
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        // 配置重试次数
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "3");
        // 配置重试间隔时间
        properties.setProperty(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "500");

        /*=========测试块========*/
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("test", "key", "message" + i));
        }
        producer.close();
        /*=========测试块========*/
    }

}
