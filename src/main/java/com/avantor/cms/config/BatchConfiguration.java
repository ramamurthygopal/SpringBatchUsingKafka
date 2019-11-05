package com.avantor.cms.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avantor.cms.batch.FileDeletingTasklet;
import com.avantor.cms.batch.ReadingTasklet;
import com.avantor.cms.dao.ContentRepository;
import com.avantor.cms.kafka.KafkaConsumer;
import com.avantor.cms.kafka.KafkaPublish;

@Configuration
@EnableBatchProcessing
//@EnableAutoConfiguration
public class BatchConfiguration extends DefaultBatchConfigurer {

	@Autowired
    ContentRepository customerRepository;
    
	@Autowired
	KafkaConsumer receiver;
	
	@Autowired
	@Qualifier(value ="sender")
	KafkaPublish sender;

	@Override
    public void setDataSource(DataSource dataSource) {
        // override to do not set datasource even if a datasource exist.
        // initialize will use a Map based JobRepository (instead of database)
    }
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step stepOne(){
        return stepBuilderFactory.get("stepOne")
                .tasklet(new ReadingTasklet(customerRepository,sender))
                .build();
    }
     
    @Bean
    public Step stepTwo(){
        return stepBuilderFactory.get("stepTwo")
                .tasklet(new FileDeletingTasklet(receiver))
                .build();
    }   
     
    @Bean
    public Job contentJob(){
        return jobBuilderFactory.get("contentJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())               
                .build();
    }
    
    
   
    
    
}
