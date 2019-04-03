package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户
 */
@ApiModel(value="账户",description="账户")
//@Data
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户id
	 */
	@ApiModelProperty(value="账户id")
	private Integer accountId;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value="姓名")
	private String realname;
	/**
	 * 学号
	 */
	@ApiModelProperty(value="学号")
	private String sid;
	/**
	 * 性别,默认为0未知，为1男性，为2女性
	 */
	@ApiModelProperty(value="性别,默认为0未知，为1男性，为2女性")
	private Integer sex;
	/**
	 * 民族
	 */
	@ApiModelProperty(value="民族")
	private String nation;
	/**
	 * 身份证
	 */
	@ApiModelProperty(value="身份证")
	private String identityCards;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value="联系电话")
	private String phone;
	/**
	 * 密码
	 */
	@ApiModelProperty(value="密码")
	private String password;

	/**
	 * 头像
	 */
	@ApiModelProperty(value="头像")
	private String icon;

	/**
	 * 出生年月日
	 */
	@ApiModelProperty(value="出生年月日")
	private Date birthDate;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createDate;
	/**
	 * 登陆时间
	 */
	@ApiModelProperty(value="登陆时间")
	private Date loginDate;
	/**
	 * 状态 0正常，1封禁，2异常
	 */
	@ApiModelProperty(value="状态 0正常，1封禁，2异常")
	private Integer status;
	/**
	 * 角色id外键
	 */
	@ApiModelProperty(value="角色id外键")
	private Integer roleId;
	/**
	 * 角色
	 */
	@ApiModelProperty(value="角色")
	private Role role;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdentityCards() {
		return identityCards;
	}

	public void setIdentityCards(String identityCards) {
		this.identityCards = identityCards;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
