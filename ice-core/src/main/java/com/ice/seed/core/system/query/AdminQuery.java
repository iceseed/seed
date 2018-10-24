package com.ice.seed.core.system.query;

import com.ice.seed.common.persistence.Query;
import com.ice.seed.common.persistence.criteria.QueryCriteria;
import com.ice.seed.core.system.domain.AdminDomain;
import lombok.Data;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;


/**
 * 管理员信息表的Query
 * @author : luxor
 * @since : 2018年10月19日
 * @version : v0.0.1
 */
@Data
public class AdminQuery extends Query {

	/*管理员id*/
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



	@Override
	public QueryCriteria toCriteria() {
		QueryCriteria queryCriteria = new QueryCriteria(AdminDomain.class);
		Example.Criteria criteria = queryCriteria.createCriteria();
		if (valid(id)) {
            criteria.andEqualTo("id", id);
        }

		if (valid(realName)) {
            criteria.andEqualTo("realName", realName);
        }

		if (valid(userName)) {
            criteria.andEqualTo("userName", userName);
        }

		if (valid(password)) {
            criteria.andEqualTo("password", password);
        }

		if (valid(salt)) {
            criteria.andEqualTo("salt", salt);
        }

		if (valid(phone)) {
            criteria.andEqualTo("phone", phone);
        }

		if (valid(email)) {
            criteria.andEqualTo("email", email);
        }

		if (valid(locked)) {
            criteria.andEqualTo("locked", locked);
        }

		if (valid(description)) {
            criteria.andEqualTo("description", description);
        }

		if (valid(lastLoginTime)) {
            criteria.andEqualTo("lastLoginTime", lastLoginTime);
        }

		if (valid(lastLoginIp)) {
            criteria.andEqualTo("lastLoginIp", lastLoginIp);
        }

		if (valid(loginCount)) {
            criteria.andEqualTo("loginCount", loginCount);
        }

		if (valid(isCustomer)) {
            criteria.andEqualTo("isCustomer", isCustomer);
        }

		if (valid(companyCode)) {
            criteria.andEqualTo("companyCode", companyCode);
        }

		if (valid(uncallAdress)) {
            criteria.andEqualTo("uncallAdress", uncallAdress);
        }

		if (valid(customerNo)) {
            criteria.andEqualTo("customerNo", customerNo);
        }

		if (valid(createTime)) {
            criteria.andEqualTo("createTime", createTime);
        }

		if (valid(updateTime)) {
            criteria.andEqualTo("updateTime", updateTime);
        }

		if (valid(createAdmin)) {
            criteria.andEqualTo("createAdmin", createAdmin);
        }

		if (valid(updateAdmin)) {
            criteria.andEqualTo("updateAdmin", updateAdmin);
        }

		//todo 写查询逻辑
		
		return queryCriteria;
	}

}
