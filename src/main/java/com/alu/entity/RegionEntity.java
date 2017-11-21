package com.alu.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jeecgframework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 活动
 * @author zhangdaihao
 * @date 2017-11-07 17:16:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_region", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RegionEntity extends IdEntity implements java.io.Serializable {
	private java.lang.String name;
	private java.lang.String pid;
	private java.lang.String nameEn;
	
	

	@Column(name ="PID",nullable=true,length=255)
	public java.lang.String getPid() {
		return pid;
	}


	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}

	@Column(name ="NAME_EN",nullable=true,length=255)
	public java.lang.String getNameEn() {
		return nameEn;
	}


	public void setNameEn(java.lang.String nameEn) {
		this.nameEn = nameEn;
	}

	@Column(name ="NAME",nullable=true,length=255)
	public java.lang.String getName() {
		return name;
	}


	public void setName(java.lang.String name) {
		this.name = name;
	}
	
}
