package com.alu.service.impl;


import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.GradeServiceI;

@Service("gradeService")
@Transactional
public class GradeServiceImpl extends CommonServiceImpl implements GradeServiceI {
	@Override
	public void setGradePersonnelCount(int type, String gradeId) {
		String sql = null;
		if(type >0)
			sql = "update  t_grade  set stu_count  = (stu_count +1 ) where id=?";
		else
			sql = "update  t_grade  set stu_count  = (stu_count -1 ) where id=?";
		commonDao.executeSql(sql, gradeId);
	}
}