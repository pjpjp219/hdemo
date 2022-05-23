package com.nixx.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 提高生产者的吞吐量
 *
 * @author nixx
 * @date   2022年5月20日
 */
public class _003_ProducerThroughput {

    public static void main(String[] args) {
        // 配置生产者
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 批次大小，默认 16k
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        // 等待时间，默认 0，意味着有消息马上发送
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "0");
        // 缓冲区大小，默认 32m
        properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        /*
          对发送的消息进行压缩，可配置 gzip、snappy、lz4、zstd
          生产环境常用 snappy 压缩
         */
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        /*=========测试块========*/
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("test", "key", "value"));
        }
        producer.close();
        /*=========测试块========*/
    }

}
