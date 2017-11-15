package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.GradeServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("gradeService")
@Transactional
public class GradeServiceImpl extends CommonServiceImpl implements GradeServiceI {
	
}