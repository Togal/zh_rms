package com.rms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.rms.service.IUserService;

public class BaseController {
	
	@Autowired
	protected IUserService iUserService;
}