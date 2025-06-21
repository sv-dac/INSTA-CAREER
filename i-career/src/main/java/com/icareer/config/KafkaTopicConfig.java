package com.icareer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.icareer.constants.AppConstant;

@Configuration
public class KafkaTopicConfig {

	@Bean
    public NewTopic myTopic() {
        return TopicBuilder.name(AppConstant.MY_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}