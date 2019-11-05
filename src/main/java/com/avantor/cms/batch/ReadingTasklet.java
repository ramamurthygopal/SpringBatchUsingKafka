package com.avantor.cms.batch;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.avantor.cms.dao.ContentRepository;
import com.avantor.cms.kafka.KafkaPublish;
import com.avantor.cms.model.Content;

public class ReadingTasklet implements Tasklet{
 
   
	
	public ReadingTasklet( ContentRepository customerRepository, KafkaPublish publish) {
		this.customerRepository = customerRepository;
		this.publish = publish;
		
	}
	private  ContentRepository customerRepository;
	private  KafkaPublish publish;
//	 
//	private  KafkaPublish sender;
	 
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
         
		System.out.println("Reading start..");
		String path = (String)chunkContext.getStepContext().getJobParameters().get("path");
		System.out.println("Reading start.." + path);
		List<Content> contents = customerRepository.findPageIdStartsWith(path);
		for(Content content: contents)
		{
			publish.sendMessage(content.getPageId()+"::"+content.getTemplateValue());
		}
        System.out.println("Reading done.." + contents);
        return RepeatStatus.FINISHED;
    }
 

}
