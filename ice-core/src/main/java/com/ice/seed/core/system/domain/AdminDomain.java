package com.ice.seed.core.system.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


/**
 * 管理员信息表的domain
 * @author : luxor
 * @since : 2018年10月19日
 * @version : v0.0.1
 */
@Data
@NoArgsConstructor
@Table(name = "t_admin")
public class AdminDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*管理员id*/
	@Id
	private Long id;
	
	/*姓名*/
	private String realName;
	
	/*用户名*/
	private String userName;
	
	/*密码*/
	private String password;
	
	/*盐*/
	private String salt;
	
	/*手机*/
	private String phone;
	
	/*邮箱*/
	private String email;
	
	/*是否可用 0:true 1:false*/
	private Integer locked;
	
	/*描述*/
	private String description;
	
	/*最后登录时间*/
	private Date lastLoginTime;
	
	/*最后登录ip*/
	private String lastLoginIp;
	
	/*登录次数*/
	private Integer loginCount;
	
	/*是否客服 0否 1是*/
	private Boolean isCustomer;
	
	/*客服工号*/
	private String companyCode;
	
	/*外拨地址*/
	private String uncallAdress;
	
	/*外拨分机号*/
	private String customerNo;
	
	/*创建时间*/
	private Date createTime;
	
	/*最后修改信息时间*/
	private Date updateTime;
	
	/*创建人*/
	private Long createAdmin;
	
	/*最后修改人*/
	private Long updateAdmin;

}
