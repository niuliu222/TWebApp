package com.study.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.model.User;
import com.study.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc.xml")
public class TestUser {

    @Autowired
    IUserService userService;
    
    @Test
    public void TestGetUser()
    {
        User user = userService.getUserById("1");
        assertTrue(user.getName().equals("student1"));
        System.out.println(user.getName());
    }
}