package com.alu.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;


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
public class CollegeEntity extends IdEntity implements java.io.Serializable {
	
	/**学院名称*/
	private java.lang.String name;
	/**0未审核，1通过审核，未通过*/
	private java.lang.Integer checkStatus;
	/**审核人*/
	private java.lang.String checkBy;
	/**审核人账号*/
	private java.lang.String checkByUserName;
	/**创建人id*/
	private java.lang.String crtBy;
	/**创建人登录账号*/
	private java.lang.String crtByUserName;
	/**创建时间*/
	private java.lang.String crtTime;
	/**最近一次修改人id*/
	private java.lang.String lastUpdateBy;
	/**最近一次修改人登录账号*/
	private java.lang.String lastUpdateByUserName;
	/**最近一次修改时间*/
	private java.lang.String lastUpdateTime;
	/**0未删除，1已删除*/
	private java.lang.Integer deleteFlag;
	
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
	@Column(name ="CRT_BY_USER_NAME",nullable=true,length=500)
	public java.lang.String getCrtByUserName() {
		return crtByUserName;
	}

	public void setCrtByUserName(java.lang.String crtByUserName) {
		this.crtByUserName = crtByUserName;
	}
	@Column(name ="LAST_UPDATE_BY_USER_NAME",nullable=true,length=500)
	public java.lang.String getLastUpdateByUserName() {
		return lastUpdateByUserName;
	}

	public void setLastUpdateByUserName(java.lang.String lastUpdateByUserName) {
		this.lastUpdateByUserName = lastUpdateByUserName;
	}
	
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
