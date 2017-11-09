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
import org.jeecgframework.core.common.entity.IdEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 活动空间
 * @author zhangdaihao
 * @date 2017-11-07 17:16:56
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_activity_space", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ActivitySpaceEntity extends IdEntity implements java.io.Serializable {

	/**活动id*/
	private java.lang.String activityId;
	private String filePath;
	private String crtTime;
	private String crtBy;
	private String crtByUserName;
	/**0未审核，1通过审核，未通过*/
	private java.lang.Integer checkStatus;
	private java.lang.String checkTime;
	/**审核人*/
	private java.lang.String checkBy;
	private java.lang.String checkByUserName;
	/**0未删除，1已删除*/
	private java.lang.Integer deleteFlag;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动id
	 */
	@Column(name ="ACTIVITY_ID",nullable=true,length=32)
	public java.lang.String getActivityId(){
		return this.activityId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动id
	 */
	public void setActivityId(java.lang.String activityId){
		this.activityId = activityId;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  0未审核，1通过审核，未通过
	 */
	@Column(name ="CHECK_STATUS",nullable=true,precision=3,scale=0)
	public java.lang.Integer getCheckStatus(){
		return this.checkStatus;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  0未审核，1通过审核，未通过
	 */
	public void setCheckStatus(java.lang.Integer status){
		this.checkStatus = status;
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

	@Column(name ="FILE_PATH",nullable=true,length=255)
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name ="CRT_TIME",nullable=true,length=255)
	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	@Column(name ="CRT_BY",nullable=true,length=255)
	public String getCrtBy() {
		return crtBy;
	}

	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}

	@Column(name ="CRT_BY_USER_NAME",nullable=true,length=255)
	public String getCrtByUserName() {
		return crtByUserName;
	}

	public void setCrtByUserName(String crtByUserName) {
		this.crtByUserName = crtByUserName;
	}

	@Column(name ="CHECK_TIME",nullable=true,length=255)
	public java.lang.String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(java.lang.String checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name ="CHECK_BY_USER_NAME",nullable=true,length=255)
	public java.lang.String getCheckByUserName() {
		return checkByUserName;
	}

	public void setCheckByUserName(java.lang.String checkByUserName) {
		this.checkByUserName = checkByUserName;
	}
}
