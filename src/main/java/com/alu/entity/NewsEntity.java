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
public class NewsEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**类型id*/
	private java.lang.String newsTypeId;
	/**newsModuleId*/
	private java.lang.String newsModuleId;
	/**新闻标题*/
	private java.lang.String title;
	/**新闻内容*/
	private java.lang.String content;
	/**浏览次数*/
	private java.lang.Integer browseCount;
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
	@Column(name ="CRT_TIME",nullable=true,length=500)
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
	@Column(name ="LAST_UPDATE_TIME",nullable=true,length=500)
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
