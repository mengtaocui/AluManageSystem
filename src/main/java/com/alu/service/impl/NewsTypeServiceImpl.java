package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.alu.service.NewsTypeServiceI;

@Service("newsTypeService")
@Transactional
public class NewsTypeServiceImpl extends CommonServiceImpl implements NewsTypeServiceI {
	
}