package com.alu.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**   
 * @Title: Entity
 * @Description: 活动
 * @author zhangdaihao
 * @date 2017-11-07 17:16:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_activity", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ActivityEntity extends BaseEntity  implements java.io.Serializable {
	/**活动名称*/
	private java.lang.String name;
	/**活动地点*/
	private java.lang.String places;
	/**活动详情*/
	private java.lang.String detail;
	/**开始时间*/
	private java.lang.String startTime;
	/**结束时间*/
	private java.lang.String endTime;
	/**报名截止时间*/
	private java.lang.String applyEndTime;
	/**浏览次数*/
	private java.lang.Integer browseCount;
	/**审核状态*/
	private Integer checkStatus;
	/**审核人*/
	private java.lang.String checkBy;
	/**审核人账号*/
	private java.lang.String checkByUserName;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动名称
	 */
	@Column(name ="NAME",nullable=true,length=255)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动地点
	 */
	@Column(name ="PLACES",nullable=true,length=255)
	public java.lang.String getPlaces(){
		return this.places;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动地点
	 */
	public void setPlaces(java.lang.String places){
		this.places = places;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动详情
	 */
	@Column(name ="DETAIL",nullable=true,length=4000)
	public java.lang.String getDetail(){
		return this.detail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动详情
	 */
	public void setDetail(java.lang.String detail){
		this.detail = detail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开始时间
	 */
	@Column(name ="START_TIME",nullable=true,length=255)
	public java.lang.String getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开始时间
	 */
	public void setStartTime(java.lang.String startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结束时间
	 */
	@Column(name ="END_TIME",nullable=true,length=255)
	public java.lang.String getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结束时间
	 */
	public void setEndTime(java.lang.String endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报名截止时间
	 */
	@Column(name ="APPLY_END_TIME",nullable=true,length=255)
	public java.lang.String getApplyEndTime(){
		return this.applyEndTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报名截止时间
	 */
	public void setApplyEndTime(java.lang.String applyEndTime){
		this.applyEndTime = applyEndTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  浏览次数
	 */
	@Column(name ="BROWSE_COUNT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBrowseCount(){
		return this.browseCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  浏览次数
	 */
	public void setBrowseCount(java.lang.Integer browseCount){
		this.browseCount = browseCount;
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
