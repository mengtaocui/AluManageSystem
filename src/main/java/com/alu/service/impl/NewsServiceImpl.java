package com.alu.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.NewsServiceI;

@Service("newsService")
@Transactional
public class NewsServiceImpl extends CommonServiceImpl implements NewsServiceI {
	
}