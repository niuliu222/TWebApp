package com.study.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.study.model.Teacher;
import com.study.service.ITeacherService;

@Controller
public class TeacherController
{
	private static final Logger logger = LogManager.getLogger(TeacherController.class.getName());

	@Autowired
	ITeacherService teacherService;

	@ResponseBody
	@RequestMapping(value = "/getTeacherComboData", method = RequestMethod.GET)
	public List<Teacher> teachers(HttpServletRequest request, HttpServletResponse response)
	{
		List<Teacher> teachers = teacherService.getTeachers();
		logger.info("getUsers api jsonObj:" + JSON.toJSONString(teachers));
		return teachers;
	}
}