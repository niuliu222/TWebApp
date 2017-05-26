package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping(value = "/putRequest/{id}", method = RequestMethod.PUT)
	public String putRequestProc(@PathVariable("id") Integer id) {
		System.out.println("REST Request PUT: " + id);
		return "success";
	}

	@RequestMapping(value = "/deleteRequest/{id}", method = RequestMethod.DELETE)
	public String deleteRequestProc(@PathVariable("id") Integer id) {
		System.out.println("REST Request DELETE: " + id);
		return "success";
	}

	@RequestMapping(value = "/postRequest", method = RequestMethod.POST)
	public String postRequestProc() {
		System.out.println("postRequestProc");
		return "success";
	}

	@RequestMapping("/getRequest/{id}")
	public String getRequestProc(@PathVariable("id") Integer id) {
		System.out.println("getRequestProc" + id);
		return "success";
	}
}