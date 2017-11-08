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
public class ActivityEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
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
	/**0未审核，1通过审核，未通过*/
	private java.lang.Integer status;
	/**创建人*/
	private java.lang.String crtBy;
	/**创建时间*/
	private java.lang.String crtTime;
	/**最近一次修改人*/
	private java.lang.String lastUpdateBy;
	/**最近一次修改时间*/
	private java.lang.String lastUpdateTime;
	/**审核人*/
	private java.lang.String checkBy;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  0未审核，1通过审核，未通过
	 */
	@Column(name ="STATUS",nullable=true,precision=3,scale=0)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  0未审核，1通过审核，未通过
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CRT_BY",nullable=true,length=255)
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
	@Column(name ="CRT_TIME",nullable=true,length=255)
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
	@Column(name ="LAST_UPDATE_BY",nullable=true,length=255)
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
	@Column(name ="LAST_UPDATE_TIME",nullable=true,length=255)
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
}