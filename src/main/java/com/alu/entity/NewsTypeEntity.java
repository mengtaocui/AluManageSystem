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
 * @Description: 新闻类型
 * @author zhangdaihao
 * @date 2017-11-07 17:08:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_news_type", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class NewsTypeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**类型名称*/
	private java.lang.String name;
	/**简介*/
	private java.lang.String detail;
	/**创建人*/
	private java.lang.String crtBy;
	/**创建时间*/
	private java.lang.String crtTime;
	/**最近一次修改人*/
	private java.lang.String lastUpdateBy;
	/**最近一次修改时间*/
	private java.lang.String lastUpdateTime;
	/**0未删除，1已删除*/
	private java.lang.Integer deleteFlag;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型名称
	 */
	@Column(name ="NAME",nullable=true,length=500)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简介
	 */
	@Column(name ="DETAIL",nullable=true,length=255)
	public java.lang.String getDetail(){
		return this.detail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简介
	 */
	public void setDetail(java.lang.String detail){
		this.detail = detail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CRT_BY",nullable=true,length=500)
	public java.lang.String getCrtBy(){
		return this.crtBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCrtBy(java.lang.String crtBy){
		this.crtBy = crtBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建时间
	 */
	@Column(name ="CRT_TIME",nullable=true)
	public java.lang.String getCrtTime(){
		return this.crtTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建时间
	 */
	public void setCrtTime(java.lang.String crtTime){
		this.crtTime = crtTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最近一次修改人
	 */
	@Column(name ="LAST_UPDATE_BY",nullable=true,length=500)
	public java.lang.String getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最近一次修改人
	 */
	public void setLastUpdateBy(java.lang.String lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最近一次修改时间
	 */
	@Column(name ="LAST_UPDATE_TIME",nullable=true)
	public java.lang.String getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最近一次修改时间
	 */
	public void setLastUpdateTime(java.lang.String lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  0未删除，1已删除
	 */
	@Column(name ="DELETE_FLAG",nullable=true,precision=3,scale=0)
	public java.lang.Integer getDeleteFlag(){
		return this.deleteFlag;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  0未删除，1已删除
	 */
	public void setDeleteFlag(java.lang.Integer deleteFlag){
		this.deleteFlag = deleteFlag;
	}
}
