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
 * @Description: 帖子
 * @author cuimengtao
 * @date 2017-11-15 09:27:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_invitation", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class InvitationEntity extends BaseEntity implements java.io.Serializable {
	/**帖子标题*/
	private java.lang.String title;
	/**内容*/
	private java.lang.String content;
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
	 *@return: java.lang.String  帖子标题
	 */
	@Column(name ="TITLE",nullable=true,length=500)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  帖子标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.Object
	 *@return: java.lang.Object  内容
	 */
	@Column(name ="CONTENT",nullable=true,length=65535)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.Object
	 *@param: java.lang.Object  内容
	 */
	public void setContent(String content){
		this.content = content;
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
