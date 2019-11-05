package com.avantor.cms.prod;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.avantor.cms.dao.ContentRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.avantor.cms")
@EnableJpaRepositories(basePackages = "com.avantor.cms.dao")
@EntityScan("com.avantor.cms.model")
public class ProdApplication {

	
	@Autowired
    DataSource dataSource;

    @Autowired
    ContentRepository customerRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(ProdApplication.class, args);
	}
	
	

}
