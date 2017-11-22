package com.alu.service;

import org.jeecgframework.core.common.service.CommonService;


public interface GradeServiceI extends CommonService{
	void setGradePersonnelCount(int type, String gradeId);
}
