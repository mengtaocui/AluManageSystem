package com.alu.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

public interface StatisticsServiceI extends CommonService{
	List<Map<String, Object>> getStatisticsByYearPeriodData(String collegeId);
	List<Map<String, Object>> getStatisticsByProvinceData(String collegeId);
	List<Map<String, Object>> getStatisticsByEducationStageData(String collegeId);
	List<Map<String, Object>> getStatisticsByCompanyNatureData(String collegeId);
	
	Object getAwaitHandCount(String type, String collegeId);
}
