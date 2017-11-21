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
 * @Description: 校友
 * @author cuimengtao
 * @date 2017-11-15 09:25:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_classmate", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ClassmateEntity extends BaseEntity implements java.io.Serializable {
	/**学院id*/
	private String collegeId;
	private String collegeName;
	private String yearPeriod;
	/**班级id*/
	private java.lang.String gradeId;
	private java.lang.String gradeName;
	private String companyNature;
	private String educationStage;
	/**姓名*/
	private java.lang.String name;
	private Integer sex; 
	/**学号*/
	private java.lang.String stuNo;
	/**电话*/
	private java.lang.String phone;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	/**地址*/
	private java.lang.String address;
	/**邮箱*/
	private java.lang.String email;
	/**简介*/
	private java.lang.String descri;
	/**头像*/
	private java.lang.String headPortrait;
	
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  班级id
	 */
	@Column(name ="GRADE_ID",nullable=true,length=32)
	public java.lang.String getGradeId(){
		return this.gradeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班级id
	 */
	public void setGradeId(java.lang.String gradeId){
		this.gradeId = gradeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学号
	 */
	@Column(name ="STU_NO",nullable=true,length=200)
	public java.lang.String getStuNo(){
		return this.stuNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学号
	 */
	public void setStuNo(java.lang.String stuNo){
		this.stuNo = stuNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="PHONE",nullable=true,length=50)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=1000)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */
	@Column(name ="EMAIL",nullable=true,length=100)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简介
	 */
	@Column(name ="DESCRI",nullable=true,length=4000)
	public java.lang.String getDescri(){
		return this.descri;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简介
	 */
	public void setDescri(java.lang.String descri){
		this.descri = descri;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="HEAD_PORTRAIT",nullable=true,length=4000)
	public java.lang.String getHeadPortrait(){
		return this.headPortrait;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setHeadPortrait(java.lang.String headPortrait){
		this.headPortrait = headPortrait;
	}
	@Column(name ="COLLEGE_ID",nullable=true,length=100)
	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	@Column(name ="COLLEGE_NAME",nullable=true,length=100)
	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	@Column(name ="GRADE_NAME",nullable=true,length=100)
	public java.lang.String getGradeName() {
		return gradeName;
	}

	public void setGradeName(java.lang.String gradeName) {
		this.gradeName = gradeName;
	}
	@Column(name ="YEAR_PERIOD",nullable=true,length=100)
	public String getYearPeriod() {
		return yearPeriod;
	}

	public void setYearPeriod(String yearPeriod) {
		this.yearPeriod = yearPeriod;
	}
	@Column(name ="SEX",nullable=true,length=100)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Column(name ="PROVINCEID",nullable=true,length=100)
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name ="PROVINCENAME",nullable=true,length=100)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	@Column(name ="CITYID",nullable=true,length=100)
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Column(name ="CITYNAME",nullable=true,length=100)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Column(name ="COMPANYNATURE",nullable=true,length=100)
	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	@Column(name ="EDUCATIONSTAGE",nullable=true,length=100)
	public String getEducationStage() {
		return educationStage;
	}

	public void setEducationStage(String educationStage) {
		this.educationStage = educationStage;
	}
	
}
