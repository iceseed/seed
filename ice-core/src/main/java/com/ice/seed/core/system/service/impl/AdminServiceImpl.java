package com.ice.seed.core.system.service.impl;

import com.ice.seed.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ice.seed.core.system.mapper.AdminMapper;
import com.ice.seed.core.system.domain.AdminDomain;
import com.ice.seed.core.system.service.IAdminService;

/**
 * 管理员信息表的业务实现类
 * @author : luxor
 * @since : 2018年10月19日
 * @version : v0.0.1
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl<AdminDomain> implements IAdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	  
}
