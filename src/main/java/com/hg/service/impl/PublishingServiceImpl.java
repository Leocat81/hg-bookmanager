package com.hg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.dao.PublishingDao;
import com.hg.pojo.Publishing;
import com.hg.service.PublishingService;

@Service("publishingService")
public class PublishingServiceImpl implements PublishingService {

	@Autowired
	PublishingDao publishingDao;
	
	@Override
	public List<Publishing> getPublishing() {
		return publishingDao.get();
	}

}
