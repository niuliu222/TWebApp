package com.study.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController
{
	private static final Logger logger = LogManager.getLogger(AdminController.class.getName());

	@RequestMapping("/")
	public String index(ServletRequest req, ServletResponse res)
	{
		logger.info("controller index enter.");

		return "login";
	}

	@RequestMapping(value = "/v1/login", method = RequestMethod.POST)
	public String loginProc(ServletRequest req, ServletResponse res)
	{
		logger.info("controller loginProc enter.");

		return "main";
	}

}