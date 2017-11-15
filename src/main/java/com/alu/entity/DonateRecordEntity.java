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
 * @Description: 捐赠记录
 * @author cuimengtao
 * @date 2017-11-15 09:26:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_donate_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DonateRecordEntity extends BaseEntity implements java.io.Serializable {
	/**捐赠项目id*/
	private java.lang.String donateId;
	private java.lang.String donateName;
	/**捐赠人姓名*/
	private java.lang.String donatePersonName;
	/**捐赠人手机号*/
	private java.lang.String donatePersonPhone;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  捐赠项目id
	 */
	@Column(name ="DONATE_ID",nullable=true,length=32)
	public java.lang.String getDonateId(){
		return this.donateId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  捐赠项目id
	 */
	public void setDonateId(java.lang.String donateId){
		this.donateId = donateId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  捐赠人姓名
	 */
	@Column(name ="DONATE_PERSON_NAME",nullable=true,length=255)
	public java.lang.String getDonatePersonName(){
		return this.donatePersonName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  捐赠人姓名
	 */
	public void setDonatePersonName(java.lang.String donatePersonName){
		this.donatePersonName = donatePersonName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  捐赠人手机号
	 */
	@Column(name ="DONATE_PERSON_PHONE",nullable=true,length=255)
	public java.lang.String getDonatePersonPhone(){
		return this.donatePersonPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  捐赠人手机号
	 */
	public void setDonatePersonPhone(java.lang.String donatePersonPhone){
		this.donatePersonPhone = donatePersonPhone;
	}
	@Column(name ="DONATE_NAME",nullable=true,length=255)
	public java.lang.String getDonateName() {
		return donateName;
	}

	public void setDonateName(java.lang.String donateName) {
		this.donateName = donateName;
	}
	
}
