package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.ClassmateServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("classmateService")
@Transactional
public class ClassmateServiceImpl extends CommonServiceImpl implements ClassmateServiceI {
	
}