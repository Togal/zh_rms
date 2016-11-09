package com.bms.base.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * Title:MyErrorController
 * Description:自定义错误页
 * @author    zwb
 * @date      2016年9月23日 上午10:49:58
 *
 */
@Controller
public class MyErrorController implements ErrorController {
	
	private final static String PATH = "/error";
	
	/**
	 * 基于springmvc handle的错误页
	 * @return
	 */
	@GetMapping(value = PATH)
	public String myError(){
		return "result";
	}
	
	@Override
	public String getErrorPath() {
		return PATH;
	}
}
