package com.sk.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.sk.config.RestPublish;
import com.sk.entity.ResponseMessage;
import com.sk.entity.UserTable;
import com.sk.mapper.UserTableMapper;
import com.sk.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "用戶管理接口", description = "用戶管理接口", position = 1)
@RestPublish
@RestController
public class UserController {
	@Resource
	private UserService userService;
	
	
	@ApiOperation(value = "登錄", notes = "登錄")
	@ApiImplicitParams({
			@ApiImplicitParam() })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMessage<UserTable> login(@RequestBody String params) {
		
		ResponseMessage responseMessage = null;
		try {
			responseMessage = userService.login(params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return responseMessage;
      
    }
}
