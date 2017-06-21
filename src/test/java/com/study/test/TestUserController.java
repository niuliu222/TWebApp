package com.study.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import static org.hamcrest.Matchers.hasSize;
import org.junit.runners.MethodSorters;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc.xml")
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserController
{
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static String lastNewId;

    @Before
    public void init()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testReturnPage() throws Exception
    {
        mockMvc.perform(get("/v1/users")).andExpect(status().isOk()).andExpect(view().name("users")).andDo(print());
    }

    @Test
    public void testReturnPageGet() throws Exception
    {
        mockMvc.perform(get("/v1/getRequest/1")).andExpect(status().isOk()).andExpect(view().name("success"))
                .andDo(print());
    }

    @Test
    public void testReturnPagePost() throws Exception
    {
        mockMvc.perform(post("/v1/postRequest")).andExpect(status().isOk()).andExpect(view().name("success"))
                .andDo(print());
    }

    @Test
    public void testReturnPageDelete() throws Exception
    {
        mockMvc.perform(delete("/v1/deleteRequest/1")).andExpect(status().isOk()).andExpect(view().name("success"))
                .andDo(print());
    }

    @Test
    public void testReturnPagePut() throws Exception
    {
        mockMvc.perform(put("/v1/putRequest/1")).andExpect(status().isOk()).andExpect(view().name("success"))
                .andDo(print());
    }

    @Test
    public void testRealApiGetUsers() throws Exception
    {
        // .accept方法是设置客户端可识别的内容类型
        // .contentType,设置请求头中的Content-Type字段,表示请求体的内容类型
        mockMvc.perform(get("/v1/getUsers").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8).param("page", "1").param("rows", "10")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.total").exists()).andExpect(jsonPath("$.rows").isArray())
                .andExpect(jsonPath("$.rows", hasSize(10)));
    }

    @Test
    public void test001RealApiAddUser() throws Exception
    {
        MvcResult ret = mockMvc
                .perform(post("/v1/users").accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8).param("name", "user1").param("age", "23")
                        .param("gender", "女").param("email", "user1@126.com").param("teacherId", "1")
                        .param("createTime", "2017-06-08 13:59:12").param("loginTime", "2017-06-08 13:59:12"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.retCode").value(0)).andExpect(jsonPath("$.newId").exists()).andReturn();
        MockHttpServletResponse resp = ret.getResponse();
        String strContent = resp.getContentAsString();
        JSONObject jsonObject = JSONObject.parseObject(strContent);
        lastNewId = jsonObject.get("newId").toString();
    }

    @Test
    public void test002RealApiUpdateUser() throws Exception
    {
        mockMvc.perform(put("/v1/users/" + lastNewId).accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8).param("name", "user1").param("age", "33")
                .param("gender", "男").param("email", "user1@126.com").param("teacherId", "2")
                .param("createTime", "2017-06-08 13:59:12").param("loginTime", "2017-06-08 13:59:12")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.retCode").value(0)).andExpect(jsonPath("$.status").value("更新成功"));

    }

    @Test
    public void test003RealApiDeleteUser() throws Exception
    {
        mockMvc.perform(delete("/v1/users/" + lastNewId).accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.retCode").value(0)).andExpect(jsonPath("$.status").value("删除成功"));
    }
}