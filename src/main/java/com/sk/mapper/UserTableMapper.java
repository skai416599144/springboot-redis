package com.sk.mapper;

import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.sk.entity.UserTable;

public interface UserTableMapper {
    int deleteByPrimaryKey(String username);

    int insert(UserTable record);

    int insertSelective(UserTable record);

    UserTable selectByPrimaryKey(String username);
    @Cacheable(cacheNames = "loginInfo")
    UserTable login(Map<String,String> loginInfo);

    int updateByPrimaryKeySelective(UserTable record);

    int updateByPrimaryKey(UserTable record);
}