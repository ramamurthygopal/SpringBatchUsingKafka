package com.avantor.cms.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.avantor.cms")
public class DAOConfig {
	
	@Autowired
	DataSource dataSource;

	
	
	@Autowired
	private Environment env;

}
