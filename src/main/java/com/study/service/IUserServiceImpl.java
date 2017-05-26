package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dao.UserMapper;
import com.study.model.User;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    
    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(Long.parseLong(id));
    }

}
