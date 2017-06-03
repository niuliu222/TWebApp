package it.pkg.service;

import java.util.List;

import it.pkg.model.User;

public interface IUserService
{

	public User getUserById(String id);

	public List<User> getUsersPagenition(int page, int rows);

	public long getCount();
}
