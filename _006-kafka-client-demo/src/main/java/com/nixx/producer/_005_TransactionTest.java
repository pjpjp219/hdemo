package com.nixx.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 生产者如何开启事务
 *
 * @author nixx
 * @date   2022年5月23日
 */
public class _005_TransactionTest {

    public static void main(String[] args) {
        // 配置生产者
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置事务ID，必须
        properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional_id_1");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // 初始化事务
        producer.initTransactions();
        // 开启事务
        producer.beginTransaction();

        //发送10条消息往kafka，假如中间有异常，所有消息都会
        try {
            for (int i = 0; i < 10; i++) {
                // 发到一半突然给个异常
//                if (i == 6) {
//                    int a = 1 / 0;
//                }
                producer.send(new ProducerRecord<>("topic-test", "a message" + i));
            }
            // 提交事务
            producer.commitTransaction();
        } catch (Exception e) {
            // 终止事务
            producer.abortTransaction();
        } finally {
            producer.close();
        }
    }

}
