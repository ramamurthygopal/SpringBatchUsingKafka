package com.avantor.cms.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaContentProducer {
	
	public static Logger logger = LoggerFactory.getLogger(KafkaContentProducer.class);
	
	 @Value(value = "${kafka.bootstrapAddress}")
	 private String bootstrapAddress;
	 
	@Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          bootstrapAddress);
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
 
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
	
    
    @Bean(name="sender")
    public KafkaPublish sender() {
      return new KafkaPublish();
    }
	
	
//	private static Properties producerConfig = new Properties();
//	static
//	{
//		producerConfig.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.3.101.7:9092,10.3.101.6:9092,10.3.101.8:9092");
//		producerConfig.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
//		producerConfig.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
//	}
//	
//	public  static Properties producerProperties()
//	{
//		
//		return producerConfig;
//	}
	
//	public static void main(String[] args) {
//		
//		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerProperties());
//		ProducerRecord<String,String> rec = new ProducerRecord<String, String>("test-topic", "testing the produce");
//		
//		producer.send(rec, new Callback() {
//			
//			public void onCompletion(RecordMetadata metadata, Exception exception) {
//				
//				logger.info("offset " + metadata.offset() + "topic " + metadata.topic() + " partition " + metadata.partition() + " time " + metadata.timestamp());
//			}
//		} );
//		
//		
//		producer.flush();
//		producer.close();
//		
//		
//	}

}
