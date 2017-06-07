package com.study.service;

import java.util.List;

import com.study.model.User;

public interface IUserService
{

	public User getUserById(String id);

	public List<User> getUsersPagenition(int page, int rows);

	public long getCount();
	
	public int insertUser(User usr);
	
	public int deleteById(String id);
	
	public int updateById(User user);
}
