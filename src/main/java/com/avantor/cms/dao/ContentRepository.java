package com.avantor.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.avantor.cms.model.Content;
@EnableJpaRepositories(basePackages = "com.avantor.cms.model")
public interface  ContentRepository  extends CrudRepository<Content, String> {

	// custom query example and return a stream
	@Query("SELECT m FROM Content m WHERE m.pageId LIKE ?1%")
    List<Content> findPageIdStartsWith( String pageId);
	

}
