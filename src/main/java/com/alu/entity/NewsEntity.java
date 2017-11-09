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
 * @Description: 新闻
 * @author zhangdaihao
 * @date 2017-11-07 17:15:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_news", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class NewsEntity extends BaseEntity implements java.io.Serializable {
	/**类型id*/
	private java.lang.String newsTypeId;
	private java.lang.String newsTypeName;
	/**newsModuleId*/
	private java.lang.String newsModuleId;
	private java.lang.String newsModuleName;
	/**新闻标题*/
	private java.lang.String title;
	/**新闻内容*/
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
	 *@return: java.lang.String  类型id
	 */
	@Column(name ="NEWS_TYPE_ID",nullable=true,length=32)
	public java.lang.String getNewsTypeId(){
		return this.newsTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型id
	 */
	public void setNewsTypeId(java.lang.String newsTypeId){
		this.newsTypeId = newsTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  newsModuleId
	 */
	@Column(name ="NEWS_MODULE_ID",nullable=true,length=1000)
	public java.lang.String getNewsModuleId(){
		return this.newsModuleId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  newsModuleId
	 */
	public void setNewsModuleId(java.lang.String newsModuleId){
		this.newsModuleId = newsModuleId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  新闻标题
	 */
	@Column(name ="TITLE",nullable=true,length=1000)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  新闻标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  新闻内容
	 */
	@Column(name ="CONTENT",nullable=true,length=1000)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  新闻内容
	 */
	public void setContent(java.lang.String content){
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
	@Column(name ="news_type_name",nullable=true,length=32)
	public java.lang.String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(java.lang.String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	@Column(name ="news_module_name",nullable=true,length=32)
	public java.lang.String getNewsModuleName() {
		return newsModuleName;
	}

	public void setNewsModuleName(java.lang.String newsModuleName) {
		this.newsModuleName = newsModuleName;
	}
	@Column(name ="checkStatus",nullable=true,length=32)
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	@Column(name ="check_by_user_name",nullable=true,length=255)
	public java.lang.String getCheckByUserName() {
		return checkByUserName;
	}

	public void setCheckByUserName(java.lang.String checkByUserName) {
		this.checkByUserName = checkByUserName;
	}
}
