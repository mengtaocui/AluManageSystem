package com.alu.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
@MappedSuperclass
public abstract class SimpleBaseEntity {
	private String id;
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
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
