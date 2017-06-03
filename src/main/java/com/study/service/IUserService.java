package com.study.service;

import java.util.List;

import com.study.model.User;

public interface IUserService
{

	public User getUserById(String id);

	public List<User> getUsersPagenition(int page, int rows);

	public long getCount();
}
