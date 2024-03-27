package com.alibaba.javabase.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author quanhangbo
 * @date 2024-03-28 0:00
 */
public class ProducerSample {


    private static final String TOPIC_NAME = "yangming11_topic";

    public static void main(String[] args) {

    }

    public static void producerSend() {
        Properties properties = new Properties();

        Producer<String, String> producer = new KafkaProducer<>(properties);

        // 消息对象
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "alibaba");

    }

}
