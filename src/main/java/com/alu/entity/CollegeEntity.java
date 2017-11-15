package com.alu.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**   
 * @Title: Entity
 * @Description: 学院
 * @author cuimengtao
 * @date 2017-11-15 09:24:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_college", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CollegeEntity extends BaseEntity implements java.io.Serializable {

	/**学院名称*/
	private java.lang.String name;
	/**0未审核，1通过审核，未通过*/
	private java.lang.Integer checkStatus;
	/**审核人*/
	private java.lang.String checkBy;
	/**审核人账号*/
	private java.lang.String checkByUserName;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学院名称
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学院名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
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
