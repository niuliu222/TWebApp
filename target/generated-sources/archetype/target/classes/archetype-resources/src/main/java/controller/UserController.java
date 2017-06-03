#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import java.util.List;

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

import com.alibaba.fastjson.JSONObject;
import ${package}.model.User;
import ${package}.service.IUserService;

@Controller
public class UserController
{
	private static final Logger logger = LogManager.getLogger(UserController.class.getName());

	@Autowired
	IUserService userService;

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
}