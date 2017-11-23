package org.jeecgframework.test.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args){
		List<Map<String, Object>> policyList = new ArrayList<Map<String,Object>>();
		Map<String, Object> parMapi=new HashMap<String, Object>();
		Map<String, Object> parMapi2=new HashMap<String, Object>();
		Map<String, Object> parMapi3=new HashMap<String, Object>();
		List<String> numList =new ArrayList<String>();
		 parMapi.put("beneNum","01TX20171108000397");//弹性福利
		 parMapi.put("policyStartDate","2017-11-14");//责任起期
		 parMapi.put("policyEndDate","2017-12-14");//责任终期
		 parMapi.put("policyStatus",11);//保单状态
		 parMapi.put("policyType",1);//保单类型
		 parMapi.put("policyNum","019G17100002780");//团单号
		 parMapi.put("organCode","889976730");//团单号
		 parMapi.put("companyName","----");//团单号
		 parMapi.put("applyId",206907);//团单号
		 policyList.add(parMapi);
		 
		 parMapi2.put("beneNum","01TX20171108000397");//弹性福利
		 parMapi2.put("policyStartDate","2017-11-14");//责任起期
		 parMapi2.put("policyEndDate","2017-12-14");//责任终期
		 parMapi2.put("policyStatus",11);//保单状态
		 parMapi2.put("policyType",1);//保单类型
		 parMapi2.put("policyNum","019G17100002790");//团单号
		 parMapi2.put("organCode","889976730");//团单号
		 parMapi2.put("companyName","----");//团单号
		 parMapi2.put("applyId",206907);//团单号
		 policyList.add(parMapi2);
		 
		 
		 
		 parMapi3.put("beneNum","01TX20171108000397");//弹性福利
		 parMapi3.put("policyStartDate","2017-11-14");//责任起期
		 parMapi3.put("policyEndDate","2017-12-14");//责任终期
		 parMapi3.put("policyStatus",11);//保单状态
		 parMapi3.put("policyType",1);//保单类型
		 parMapi3.put("policyNum","019G17100002770");//团单号
		 parMapi3.put("organCode","889976730");//团单号
		 parMapi3.put("companyName","----");//团单号
		 parMapi3.put("applyId",206907);//团单号
		 policyList.add(parMapi3);
		
		System.out.println(policyList.toString());
		
		for(int i=0; i<policyList.size(); i++){
			for(int j=0; j<policyList.size(); j++){
				if(policyList.get(i).get("applyId").equals(policyList.get(j).get("applyId")) && i != j){
					policyList.get(i).put("policyNum", policyList.get(i).get("policyNum")+","+policyList.get(j).get("policyNum"));
					policyList.remove(j);
					j--;
				}
			}
		}
		
		
		System.out.println(policyList.toString());
		
 	}
}
