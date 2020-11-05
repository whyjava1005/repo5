package com.deyuan.controller;

import com.deyuan.pojo.UserInfo;
import com.deyuan.service.IUserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Demo {
    @Autowired
    private IUserService userService;

    @Test
    public void select(){
        UserInfo userInfo = userService.findById("1");
        System.out.println(userInfo);
    }
}
