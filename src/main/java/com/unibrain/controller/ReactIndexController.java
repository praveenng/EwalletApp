package com.unibrain.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReactIndexController extends BaseController implements ErrorController{

	private static final String ERROR_PATH = "/error";

	// Here three URLs
	// 1.Default URL slash(/)
	// 2.Next URL pathX
	// 3.Another URL PathX and PathY

	@RequestMapping(value = { "/" })
	public String getReactIndexPage(HttpServletRequest request) {
		return "/index.html";
	}

	@RequestMapping(value = ERROR_PATH)
	public ModelAndView forwardToErrorPage() {
		return new ModelAndView("forward:/");
	}

}
