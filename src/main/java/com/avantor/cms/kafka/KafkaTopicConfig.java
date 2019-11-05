package com.avantor.cms.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration 
public class KafkaTopicConfig {
	
	
		@Value(value = "${kafka.bootstrapAddress}")
		private String bootstrapAddress;
	
	    @Value(value = "${cleanup.env}")
	    private String cleanEnv;
	 
	    @Bean
	    public KafkaAdmin kafkaAdmin() {
	        Map<String, Object> configs = new HashMap();
	        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
	        return new KafkaAdmin(configs);
	    }
	     
	    @Bean
	    public NewTopic fileTopic() {
	    	NewTopic topic =  new NewTopic(cleanEnv, 2, (short) 2);
	    	HashMap<String,String> config = new HashMap();
//	    	config.put("retention.ms", "12800000");
//	    	topic.configs(config);
	    	return topic;
	    }

}
