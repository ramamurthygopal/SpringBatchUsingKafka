package com.avantor.cms.kafka;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@Value( "${application.folder}" )
	private String folder;
	
	@Autowired
	private ResourceLoader resourceLoader;

	 @Value(value = "${cleanup.env}")
	    private String cleanEnv;
	 
    Resource[] loadResources(String pattern) throws IOException {
        return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern);
    }
    
	
	 private static final Logger LOGGER =
		      LoggerFactory.getLogger(KafkaConsumer.class);

		  private CountDownLatch latch = new CountDownLatch(1);

		  public CountDownLatch getLatch() {
		    return latch;
		  }

		  @KafkaListener(topics = "${cleanup.env}")
		  public void receive(String payload) {
		    String[] pathVersion = payload.split("::");
		    String pattern = "file:"+folder + pathVersion[0].replace(".jsp", "*.jsp");
		    try {
				Resource[] resources = loadResources(pattern);
				
				for(Resource r: resources) {
					if(!pathVersion[1].substring(pathVersion[1].lastIndexOf("/")+1).equals(r.getFilename()))
					{
						LOGGER.info(pathVersion[1] + " file to dlete " + r.getFilename());
						 File file = r.getFile();
				            boolean deleted = file.delete();
				            if (!deleted) {
				            	LOGGER.error(file + " noty deleted");
				            }
					}
					else
					{
						LOGGER.info(pathVersion[1] + " file persist " + r.getFilename());
					}
		           
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		    latch.countDown();
		  }
}
