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
public class ActivitySpaceEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**活动id*/
	private java.lang.String activityId;
	/**附件id*/
	private java.lang.String attachmentId;
	/**0未审核，1通过审核，未通过*/
	private java.lang.Integer status;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  附件id
	 */
	@Column(name ="ATTACHMENT_ID",nullable=true,length=32)
	public java.lang.String getAttachmentId(){
		return this.attachmentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  附件id
	 */
	public void setAttachmentId(java.lang.String attachmentId){
		this.attachmentId = attachmentId;
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
