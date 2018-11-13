package com.ice.seed.admin.com.ice.seed.admin.user.controller;
import com.alibaba.fastjson.JSONObject;
import com.ice.seed.common.cache.EhcacheManager;
import com.ice.seed.common.cache.RedisCache;
import com.ice.seed.common.web.validation.SlidingValidation;
import com.ice.seed.core.system.domain.AdminDomain;
import com.ice.seed.core.system.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/10/24
 */
@Controller
public class UserController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private RedisCache redisCache;

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
        redisCache.putCache("iceSeed",adminDomain);
        EhcacheManager.put("aa","aaaa");
        System.out.println(EhcacheManager.get("aa"));
        return json.toString();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> stringStringMap = SlidingValidation.getComponent("D:/迅雷下载/19.png", 150, 80, 62);
        ModelAndView mv = new ModelAndView("/test/test");
        mv.addObject("stringStringMap",stringStringMap);
        return mv;
    }
}
