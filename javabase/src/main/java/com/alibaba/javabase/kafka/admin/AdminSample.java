package com.alibaba.javabase.kafka.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.internals.Topic;

import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024-03-27 23:12
 */
public class AdminSample {

    public static final String TOPIC_NAME = "tom_topic";

    public static void main(String[] args) throws Exception {
//        AdminClient adminClient = adminClient();
//        System.out.println(adminClient);

//        createTopic();
        topicLists();
    }
    /**
     *
     * @return
     */
    public static AdminClient adminClient() {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "62.234.173.33:9200");


        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }


    public static void createTopic() {
        AdminClient adminClient = adminClient();
        short rs = 1;
        NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, rs);
        CreateTopicsResult topics = adminClient.createTopics(Arrays.asList(newTopic));
        System.out.println(topics);
    }

    public static void topicLists() throws Exception {
        AdminClient adminClient = adminClient();
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Set<String> topics = listTopicsResult.names().get();

        topics.stream().forEach(System.out::println);
    }

    public static void delTopics() {
        AdminClient adminClient = adminClient();
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList(TOPIC_NAME));
        System.out.println(deleteTopicsResult);
    }
}
