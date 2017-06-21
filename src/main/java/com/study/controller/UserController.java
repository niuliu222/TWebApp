package com.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.model.User;
import com.study.service.ITeacherService;
import com.study.service.IUserService;
import com.study.utils.ApiVersion;

@RequestMapping("/{version}/")
@ApiVersion(1)
@Controller
public class UserController
{
	private static final Logger logger = LogManager.getLogger(UserController.class.getName());

	@Autowired
	IUserService userService;
	
	@Autowired
	ITeacherService teacherService;
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateUserProc(@PathVariable("id") long id, 
	        User user)
	{
	    int iRet = userService.updateById(user);
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (iRet == 0)
	    {
	        logger.error("updateUserProc: 更新失败");
	        map.put("status", "更新失败");
	        map.put("retCode", -1);
	    } else
	    {
	        logger.info("updateUserProc: 更新成功");
	        map.put("status", "更新成功");
	        map.put("retCode", 0);
	    }
	    return map;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteUserProc(@PathVariable("id") String id)
	{
	    int iRet = userService.deleteById(id);
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (iRet == 0)
	    {
	        logger.error("deleteUserProc: 删除失败");
	        map.put("status", "删除失败");
	        map.put("retCode", -1);
	    } else
	    {
	        logger.info("deleteUserProc: 删除成功");
	        map.put("status", "删除成功");
	        map.put("retCode", 0);
	    }
	    return map;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addUserProc(User user)
	{
	    // 处理新id生成
	    // 处理teacher类对象
	    user.setTeacher(teacherService.getTeacherById(user.getTeacherId()));
	    int iRet = userService.insertUser(user);
	    long newId = user.getId();
	    JSONObject jsonObject = new JSONObject();
	    if (iRet == 0)
	    {
	        logger.info("addUsersProc: " + JSON.toJSON("新建失败：" + JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss")));
	        jsonObject.put("status", "新建失败");
	        jsonObject.put("retCode", -1);
	    } else
	    {
	        logger.info("addUsersProc: " + JSON.toJSON("新建用户：" + JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss")));
	        jsonObject.put("status", "新建成功");
	        jsonObject.put("retCode", 0);
	        jsonObject.put("newId", newId);
	    }
	    return jsonObject;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsersProc()
	{
		logger.info("getUsersProc");
		return "users";
	}

	@ResponseBody
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public JSONObject users(HttpServletRequest request, HttpServletResponse response)
	{
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);// 默认值为1
		int rows = ServletRequestUtils.getIntParameter(request, "rows", 0);

		long rowCount = userService.getCount();
		List<User> users = userService.getUsersPagenition(page, rows);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", users);
		jsonObject.put("total", rowCount);

		logger.info("getUsers api: page:" + page + " rows:" + rows + "jsonObj:" + jsonObject.toJSONString());
		return jsonObject;
	}

	@RequestMapping(value = "/putRequest/{id}", method = RequestMethod.PUT)
	public String putRequestProc(@PathVariable("id") Integer id)
	{
		System.out.println("REST Request PUT: " + id);
		return "success";
	}

	@RequestMapping(value = "/deleteRequest/{id}", method = RequestMethod.DELETE)
	public String deleteRequestProc(@PathVariable("id") Integer id)
	{
		System.out.println("REST Request DELETE: " + id);
		return "success";
	}

	@RequestMapping(value = "/postRequest", method = RequestMethod.POST)
	public String postRequestProc()
	{
		System.out.println("postRequestProc");
		return "success";
	}

	@RequestMapping("/getRequest/{id}")
	public String getRequestProc(@PathVariable("id") Integer id)
	{
		System.out.println("getRequestProc" + id);
		return "success";
	}
	
	@ApiVersion(3)
    @RequestMapping("/getRequest/{id}")
    public String getRequestProc3(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("getRequestProc3: " + id);
        return "success";
    }
    
    @ApiVersion(5)
    @RequestMapping("getRequest/{id}")
    public String getRequestProc5(@PathVariable("id") Integer id)
    {
        System.out.println("getRequestProc5: " + id);
        return "success";
    }
}