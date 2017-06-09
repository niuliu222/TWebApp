package com.study.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.study.controller.UserController;
import com.study.model.User;
import com.study.service.IUserService;
import static org.hamcrest.Matchers.hasSize;
import org.junit.runners.MethodSorters;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc.xml")
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserControllerMockService
{
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private IUserService userService;
    
    @Before
    public void init()
    {
        //support @Mock annotation
        MockitoAnnotations.initMocks(this);

        //解决Controller返回字符串和Mapping字符串相同时提示错误的问题
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        FastJsonHttpMessageConverter fastjsonMsgConv = this.wac.getBean(com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter.class);
        StringHttpMessageConverter stringMsgConv = this.wac.getBean(StringHttpMessageConverter.class);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .setMessageConverters(fastjsonMsgConv,stringMsgConv)
                .build();
    }
    
    @Test
    public void testApiGetUsers() throws Exception
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try
        {
            date = df.parse("2017-06-08 13:59:12");
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        List<User> users = new ArrayList<User>();
        
        for (int i = 0; i < 3; ++i)
        {
            User user = new User();
            user.setId(1L + i);
            user.setName("user" + Integer.toString(i + 1));
            user.setAge(23 + i);
            user.setGender("女");
            user.setEmail("user1@126.com");
            user.setTeacherId(1L);
            user.setCreateTime(date);
            user.setLoginTime(date);
            
            users.add(user);
        }

        when(userService.getCount()).thenReturn(3L);
        when(userService.getUsersPagenition(1, 3)).thenReturn(users);

        mockMvc.perform(get("/getUsers").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8).param("page", "1").param("rows", "3")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.total").exists()).andExpect(jsonPath("$.rows").isArray())
                .andExpect(jsonPath("$.rows", hasSize(3)))
                .andExpect(jsonPath("$.rows[0].name").value("user1"))
                .andExpect(jsonPath("$.rows[0].gender").value("女"))
                .andExpect(jsonPath("$.rows[0].age").value(23))
                .andExpect(jsonPath("$.rows[1].name").value("user2"))
                .andExpect(jsonPath("$.rows[1].age").value(24));        
        //verify Interactions with any mock
        verify(userService, times(1)).getCount();
        verify(userService, times(1)).getUsersPagenition(1, 3);
        verifyNoMoreInteractions(userService);

    }
}