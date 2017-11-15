package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.DonateServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("donateService")
@Transactional
public class DonateServiceImpl extends CommonServiceImpl implements DonateServiceI {
	
}