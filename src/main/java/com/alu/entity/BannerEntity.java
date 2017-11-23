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
 * @Description: 登录页轮播图
 * @author cuimengtao
 * @date 2017-11-23 16:08:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_banner", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class BannerEntity extends SimpleBaseEntity implements java.io.Serializable {
	/**picPath*/
	private java.lang.String picPath;
	/**status*/
	private java.lang.Integer status;
	
	/**
	 *方法: 取得java.lang.Object
	 *@return: java.lang.Object  picPath
	 */
	@Column(name ="PIC_PATH",nullable=true,length=65535)
	public java.lang.String getPicPath(){
		return this.picPath;
	}

	/**
	 *方法: 设置java.lang.Object
	 *@param: java.lang.Object  picPath
	 */
	public void setPicPath(java.lang.String picPath){
		this.picPath = picPath;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  status
	 */
	@Column(name ="STATUS",nullable=true,precision=3,scale=0)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  status
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
}
