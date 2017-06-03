package com.study.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dao.UserMapper;
import com.study.model.User;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService
{

	@Autowired
	UserMapper userMapper;

	@Override
	public User getUserById(String id)
	{
		return userMapper.selectByPrimaryKey(Long.parseLong(id));
	}

	@Override
	public List<User> getUsersPagenition(int page, int rows)
	{
		HashMap<String, Integer> offsetLimit = new HashMap<String, Integer>();
		// page mysql start from 0, param page start from 1
		offsetLimit.put("offset", (page - 1) * rows);
		offsetLimit.put("pagesize", rows);
		return userMapper.selectUsersPaginition(offsetLimit);
	}

	@Override
	public long getCount()
	{
		return userMapper.getCount();
	}
}
