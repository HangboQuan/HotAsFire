package com.alibaba.javabase.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author quanhangbo
 * @date 2024-03-28 0:00
 */
public class ProducerSample {


    private static final String TOPIC_NAME = "tom_topic";

    public static void main(String[] args) throws Exception {

        producerSyncSend();
    }

    /**
     * 异步发送
     */
    public static void producerSend() {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "62.234.173.33:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, "0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(properties);

        // 消息对象
        for (int i = 0; i < 5; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "time" + i, System.currentTimeMillis() + "@" + i);

            producer.send(record);
        }
        producer.close();
    }

    /**
     * 同步发送 / 异步阻塞发送
     */

    public static void producerSyncSend() throws Exception {

        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "62.234.173.33:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, "0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(properties);

        // 消息对象
        for (int i = 0; i < 500; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "time" + i, System.currentTimeMillis() + "@" + i);

            Future<RecordMetadata> recordMetadataFuture = producer.send(record);
            RecordMetadata recordMetadata = recordMetadataFuture.get();
            System.out.println(recordMetadata.partition() + "-" + recordMetadata.offset());
        }
        producer.close();

    }


    /**
     * 回调发送
     */
    public static void producerCallbackSend() {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "62.234.173.33:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, "0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(properties);

        // 消息对象
        for (int i = 0; i < 5; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "time" + i, System.currentTimeMillis() + "@" + i);

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("partition:" + recordMetadata.partition() + ", offset:" + recordMetadata.offset());
                }
            });
        }
        producer.close();
    }

    /**
     * 消息传递保障
     * 1. 最多一次：只发送，不等待响应
     * 2. 至少一次：发送后等待响应，如果没有收到响应则会重发，但会出现一个问题是第一次发送的内容已经收到，但是在响应的途中发生故障，导致重新发送
     * 3. 正好一次：分配id，id + 消息体，broker会保存id信息，会做去重处理，防止重发
     */

}
