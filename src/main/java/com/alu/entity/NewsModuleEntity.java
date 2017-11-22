package com.alu.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 新闻模块
 * @author zhangdaihao
 * @date 2017-11-07 17:15:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_news_module", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class NewsModuleEntity extends SimpleBaseEntity implements java.io.Serializable {
	/**类型id*/
	private java.lang.String newsTypeId;
	/**类型名称*/
	private java.lang.String newsTypeName;
	/**模块名称*/
	private java.lang.String name;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  类型id
	 */
	@Column(name ="NEWS_TYPE_ID",nullable=true,precision=10,scale=0)
	public String getNewsTypeId(){
		return this.newsTypeId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  类型id
	 */
	public void setNewsTypeId(String newsTypeId){
		this.newsTypeId = newsTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模块名称
	 */
	@Column(name ="NAME",nullable=true,length=500)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}

	@Column(name ="NEWS_TYPE_NAME",nullable=true,length=500)
	public java.lang.String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(java.lang.String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	
}
