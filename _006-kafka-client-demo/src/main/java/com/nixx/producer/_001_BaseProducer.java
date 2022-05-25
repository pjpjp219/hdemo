package com.nixx.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 该示例通过手动创建一个生产者，简单的配置参数后，使用不同的方式往分区里面发送消息
 *
 * @author nixx
 * @date 2022年5月16日 22点04分
 */
public class _001_BaseProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //配置生产者
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        /*=========测试块========*/
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // 1. 往指定的 Topic 里面发送 Value
        producer.send(new ProducerRecord<>("test", "hello"));
        // 2. 往指定的 Topic 里面发送 Value，同步发送
        producer.send(new ProducerRecord<>("test", "hello")).get();
        // 3. 往指定的 Topic 里面发送 Key Value（Key 相同的数据一定会被发往同一个 Topic）
        producer.send(new ProducerRecord<>("test", "01", "hello"));
        // 4. 往指定的 Topic 和 Partition 中发送 Value
        producer.send(new ProducerRecord<>("test", 0, null, "hello"));
        // 5. 往指定的 Topic 里面发送 Value，带回调函数，在发送完毕后会
        producer.send(new ProducerRecord<>("test", "hello"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.out.println("消息发送失败，原因：" + exception.getMessage());
                }
                System.out.println("消息发送成功，" + metadata.offset() + metadata.hasOffset() + metadata.topic() + metadata.hasTimestamp() + metadata.timestamp() + metadata.partition());
            }
        });
        producer.close();
        /*=========测试块========*/
    }

}
