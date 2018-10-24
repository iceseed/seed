package com.ice.seed.admin.com.ice.seed.admin.user.controller;
import com.alibaba.fastjson.JSONObject;
import com.ice.seed.core.system.domain.AdminDomain;
import com.ice.seed.core.system.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/10/24
 */


@Controller
public class UserController {

    @Autowired
    private IAdminService adminService;

    /**
     * 客户管理界面
     * @return
     * @author : wangbing
     * @since : 2018年10月17日
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String customerList( ) {
        JSONObject json = new JSONObject();
        AdminDomain adminDomain = adminService.get(1L);
        json.put("adminDomain", adminDomain);
        return json.toString();
    }
}
