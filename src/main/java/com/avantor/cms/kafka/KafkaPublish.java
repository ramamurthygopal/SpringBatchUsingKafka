package com.avantor.cms.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaPublish {

	
	@Autowired
	  private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private NewTopic fileTopic;
	
	public void sendMessage(String message) {
        
	    ListenableFuture<SendResult<String, String>> future = 
	      kafkaTemplate.send(fileTopic.name(), message);
	     
	    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
	 
	        @Override
	        public void onSuccess(SendResult<String, String> result) {
//	            System.out.println("Sent message=[" + message + 
//	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
//	            System.out.println("Unable to send message=["
//	              + message + "] due to : " + ex.getMessage());
	        }
	    });
	}
}
