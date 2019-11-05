package com.avantor.cms.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.avantor.cms.kafka.KafkaConsumer;

public class FileDeletingTasklet implements Tasklet {
 
	KafkaConsumer receiver;
 
    public FileDeletingTasklet(KafkaConsumer receiver) {
    	this.receiver = receiver;
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
         
    	System.out.println("Delete start.." + receiver);
    	
        // ... your code
         
        System.out.println("Delete done..");
        return RepeatStatus.FINISHED;
    }
 
 

}
