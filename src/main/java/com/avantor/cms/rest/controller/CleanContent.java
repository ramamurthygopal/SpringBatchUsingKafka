package com.avantor.cms.rest.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avantor.cms.vo.CleanInfo;


@RestController
@RequestMapping("/cms/rest")
public class CleanContent {


    
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    
    
	@RequestMapping(value = { "/clean" }, method = RequestMethod.POST, consumes = {
	"application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllTargeter(@RequestBody CleanInfo info) {
	
		String param = "/"+info.getSiteId()+"/"+info.getLocale()+"/";
		
		
		JobParametersBuilder jobBuilder= new JobParametersBuilder();
		jobBuilder.addString("path", param);
		try {
			JobParameters parameters = jobBuilder.toJobParameters();
			jobLauncher.run(job, parameters);
			
			
			
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Sucess";
	
	}
	
	
	
}
