package com.alu.service.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.StatisticsServiceI;

@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl extends CommonServiceImpl implements StatisticsServiceI {
	@Override
	public List<Map<String, Object>> getStatisticsByYearPeriodData() {
		return this.findForJdbc(" select    year_period 'name',   count(id) 'value' from t_classmate GROUP BY year_period ", null);
	}

	@Override
	public List<Map<String, Object>> getStatisticsByProvinceData() {
		return this.findForJdbc(" select    provinceName 'name',   count(id) 'value' from t_classmate GROUP BY provinceName ", null);
	}

	@Override
	public List<Map<String, Object>> getStatisticsByEducationStageData() {
		return this.findForJdbc(" select    (select typename from t_s_type  where typecode= tc.educationStage ) 'name' ,  "
				+ " count(id) 'value' from t_classmate tc GROUP BY educationStage ", null);
	}

	@Override
	public List<Map<String, Object>> getStatisticsByCompanyNatureData() {
		return this.findForJdbc(" select    (select typename from t_s_type  where typecode= tc.companyNature ) 'name' ,  "
				+ " count(id) 'value' from t_classmate tc GROUP BY companyNature ", null);
	}
	
}