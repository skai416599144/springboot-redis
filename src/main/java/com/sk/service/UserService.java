package com.sk.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.sk.entity.ResponseMessage;
import com.sk.entity.UserTable;
import com.sk.mapper.UserTableMapper;
import com.sk.myEnum.ResponseStatus;

import net.sf.json.JSONObject;

@Service
public class UserService {
	@Resource(name="userTableMapper")
	private UserTableMapper userMapper;
	
	
	
	public ResponseMessage<UserTable> login(String params) throws Exception {
		String userName=(String) JSONObject.fromObject(params).get("name");
		String passWord=(String) JSONObject.fromObject(params).get("password");
		Map<String,String> loginInfo=new HashMap<String,String>();
		loginInfo.put("userName", userName);
		loginInfo.put("passWord", passWord);
		UserTable user=userMapper.login(loginInfo);
		ResponseMessage<UserTable> ResponseMessage=null;
		if(null!=user)
		{
	    ResponseMessage=new ResponseMessage<UserTable>(ResponseStatus.SUCCESS,user);
		}else
		{
		throw new Exception("账号或密码错误");	
		}
		return ResponseMessage;
		
	}
}
