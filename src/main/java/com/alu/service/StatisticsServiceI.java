package com.alu.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

public interface StatisticsServiceI extends CommonService{
	List<Map<String, Object>> getStatisticsByYearPeriodData();
	List<Map<String, Object>> getStatisticsByProvinceData();
	List<Map<String, Object>> getStatisticsByEducationStageData();
	List<Map<String, Object>> getStatisticsByCompanyNatureData();
}
