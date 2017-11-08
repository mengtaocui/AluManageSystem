package com.alu.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.NoticesServiceI;

@Service("noticesService")
@Transactional
public class NoticesServiceImpl extends CommonServiceImpl implements NoticesServiceI {
	
}