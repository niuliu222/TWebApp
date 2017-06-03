#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;

import ${package}.model.User;

public interface IUserService
{

	public User getUserById(String id);

	public List<User> getUsersPagenition(int page, int rows);

	public long getCount();
}
