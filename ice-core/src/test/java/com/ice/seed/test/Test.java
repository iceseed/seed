package com.ice.seed.test;


import com.ice.seed.core.system.mapper.AdminMapper;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/10/22
 */
public class Test {

    private ClassPathXmlApplicationContext ixa=  new ClassPathXmlApplicationContext("spring/spring-config-core.xml");

    @org.junit.Test
    public  void  Test()throws Exception{
        System.out.println("-----容器创建-----");
        AdminMapper user = (AdminMapper)ixa.getBean("adminMapper");
        System.out.println(user);

    }
}
