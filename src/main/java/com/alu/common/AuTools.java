package com.alu.common;

import org.jeecgframework.core.util.StringUtil;

public class AuTools {
	public static String createLikeStr(String str){
		if(StringUtil.isNotEmpty(str))
			return "*"+str+"*";
		return null;
	}
}
