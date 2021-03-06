package com.alu.service.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.StatisticsServiceI;

@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl extends CommonServiceImpl implements StatisticsServiceI {
	@Override
	public List<Map<String, Object>> getStatisticsByYearPeriodData(String collegeId) {
		if(StringUtil.isNotEmpty(collegeId))
			return this.findForJdbc(" select    year_period 'name',   count(id) 'value' from t_classmate   "
				+ "tc where tc.college_id='"+collegeId+"' and tc.delete_flag=0 GROUP BY year_period ", null);
		return this.findForJdbc(" select    year_period 'name',   count(id) 'value' from t_classmate where delete_flag=0  "
				+ " GROUP BY year_period ", null);
	}
	
	@Override
	public List<Map<String, Object>> getStatisticsByProvinceData(String collegeId) {
		if(StringUtil.isNotEmpty(collegeId))
			return this.findForJdbc(" select    provinceName 'name',   count(id) 'value' from  t_classmate "
				+ " tc where tc.college_id='"+collegeId+"' and tc.delete_flag=0  GROUP BY provinceName ", null);
		
		return this.findForJdbc(" select    provinceName 'name',   count(id) 'value' from  t_classmate where  delete_flag=0 "
				+ "  GROUP BY provinceName ", null);
	}

	@Override
	public List<Map<String, Object>> getStatisticsByEducationStageData(String collegeId) {
		if(StringUtil.isNotEmpty(collegeId))
			return this.findForJdbc(" select    (select typename from t_s_type  where typecode= tc.educationStage ) 'name' ,  "
				+ " count(id) 'value' from t_classmate tc where tc.college_id='"+collegeId+"' and tc.delete_flag=0 GROUP BY educationStage ", null);
		
		return this.findForJdbc(" select    (select typename from t_s_type  where typecode= tc.educationStage ) 'name' ,  "
				+ " count(id) 'value' from t_classmate tc  where  tc.delete_flag=0 GROUP BY educationStage ", null);
	}

	@Override
	public List<Map<String, Object>> getStatisticsByCompanyNatureData(String collegeId) {
		if(StringUtil.isNotEmpty(collegeId))
			return this.findForJdbc(" select    (select typename from t_s_type  where typecode= tc.companyNature ) 'name' ,  "
				+ " count(id) 'value' from t_classmate tc where tc.college_id='"+collegeId+"' and tc.delete_flag=0 GROUP BY companyNature ", null);
		
		return this.findForJdbc(" select    (select typename from t_s_type  where typecode= tc.companyNature ) 'name' ,  "
				+ " count(id) 'value' from t_classmate tc where tc.delete_flag=0  GROUP BY companyNature ", null);
	}

	@Override
	public Object getAwaitHandCount(String type, String collegeId) {
		String sql = null;
		if(StringUtil.isNotEmpty(collegeId))
			sql = "select count(id) 'count' from "+type+" where college_id='"+collegeId+"' and checkStatus =? and delete_flag=0 ";
		else
			sql = "select count(id) 'count' from "+type+" where  checkStatus =? and delete_flag=0 ";
		Map map = this.findOneForJdbc(sql, 0);
		return map.get("count");
	}
}