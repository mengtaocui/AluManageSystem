package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.DonateRecordServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("donateRecordService")
@Transactional
public class DonateRecordServiceImpl extends CommonServiceImpl implements DonateRecordServiceI {
	
}