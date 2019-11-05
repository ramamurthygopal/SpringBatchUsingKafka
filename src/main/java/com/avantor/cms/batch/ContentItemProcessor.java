package com.avantor.cms.batch;

import org.springframework.batch.item.ItemProcessor;

import com.avantor.cms.vo.ContentVO;

public class ContentItemProcessor implements ItemProcessor<ContentVO, ContentVO>{

	
	@Override
	public ContentVO process(ContentVO arg0) throws Exception {
		
		
		return arg0;
	}
}
