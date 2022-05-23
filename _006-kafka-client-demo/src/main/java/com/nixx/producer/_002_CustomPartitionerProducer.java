package com.nixx.producer;

import com.nixx.partitioner.MyPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 演示如何自定义一个分区器
 * 控制符合条件的数据发往不同的分区
 * 默认分区逻辑参见 {@link org.apache.kafka.clients.producer.internals.DefaultPartitioner}
 *
 * @author nixx
 * @date   2022年5月20日 10点25分
 */
public class _002_CustomPartitionerProducer {

    /**
     * 执行测试前要先手动创建分区 1 和 2
     * 因为自定义的分区器里面会发往 1 2 分区
     * 没创建的话发送会报错
     */
    public static void main(String[] args) {
        //配置生产者
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());

        /*=========测试块========*/
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        producer.send(new ProducerRecord<>("test", "0", "hello"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.out.println("发送失败，原因：" + exception.getMessage());
                }

                System.out.println("消息被发往分区：" + metadata.partition());
            }
        });
        producer.close();
        /*=========测试块========*/
    }


}
