package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.DataServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("dataService")
@Transactional
public class DataServiceImpl extends CommonServiceImpl implements DataServiceI {
	
}