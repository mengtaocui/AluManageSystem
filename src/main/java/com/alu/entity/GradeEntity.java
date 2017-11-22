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
 * @Description: 班级
 * @author cuimengtao
 * @date 2017-11-15 09:24:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_grade", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class GradeEntity extends BaseEntity implements java.io.Serializable {
	/**班级名称*/
	private java.lang.String name;
	/**年届*/
	private java.lang.String yearPeriod;
	/**班级QQ群*/
	private java.lang.String qq;
	/**人数*/
	private java.lang.Integer stuCount;
	/**审核人*/
	private java.lang.String checkBy;
	/**0未审核，1通过审核，未通过*/
	private java.lang.Integer checkStatus;
	/**审核人账号*/
	private java.lang.String checkByUserName;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  班级名称
	 */
	@Column(name ="NAME",nullable=true,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班级名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年届
	 */
	@Column(name ="YEAR_PERIOD",nullable=true,length=50)
	public java.lang.String getYearPeriod(){
		return this.yearPeriod;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年届
	 */
	public void setYearPeriod(java.lang.String yearPeriod){
		this.yearPeriod = yearPeriod;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  班级QQ群
	 */
	@Column(name ="QQ",nullable=true,length=50)
	public java.lang.String getQq(){
		return this.qq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班级QQ群
	 */
	public void setQq(java.lang.String qq){
		this.qq = qq;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  人数
	 */
	@Column(name ="STU_COUNT",nullable=true,precision=3,scale=0)
	public java.lang.Integer getStuCount(){
		return this.stuCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  人数
	 */
	public void setStuCount(java.lang.Integer stuCount){
		this.stuCount = stuCount;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */
	@Column(name ="CHECK_BY",nullable=true,length=32)
	public java.lang.String getCheckBy(){
		return this.checkBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setCheckBy(java.lang.String checkBy){
		this.checkBy = checkBy;
	}
	
	@Column(name ="checkStatus",nullable=true,length=32)
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	@Column(name ="check_by_user_name",nullable=true,length=32)
	public java.lang.String getCheckByUserName() {
		return checkByUserName;
	}

	public void setCheckByUserName(java.lang.String checkByUserName) {
		this.checkByUserName = checkByUserName;
	}
}
