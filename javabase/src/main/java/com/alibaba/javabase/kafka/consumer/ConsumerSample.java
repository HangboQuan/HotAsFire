package com.alibaba.javabase.kafka.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author quanhangbo
 * @date 2024-04-04 22:46
 */
public class ConsumerSample {

    private static final String TOPIC_NAME = "tom_topic";


    public static void main(String[] args) {
        consumerTopic();
    }

    public static void consumerTopic() {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "62.234.173.33:9092");
        properties.setProperty("group.id", "test");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(properties);

        kafkaConsumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition=%d, offset=%d, key=%s, value=%s%n", record.partition(), record.offset(), record.key(), record.value());
            }
        }


    }


}
