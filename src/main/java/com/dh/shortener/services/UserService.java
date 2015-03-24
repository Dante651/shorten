package com.dh.shortener.services;

import java.util.Set;

import org.springframework.data.repository.query.Param;

import com.dh.shortener.model.User;

public interface UserService {
	
	public void addUser(User user);
	public boolean removeUser(long userId);
	public boolean updateUser(User user);
	public User getUser(long userId);
	public User findByUserName(String userName);
	public User findByEmail(String email);
	public Set<User> getAllUsers();
	public Set<User> findOnlyUsers();
	public void updateUserName(String username, long id);
	public void updateEmail(String email, long id);
	public void updateRole(String role,  long id);
	public User findUserToEdit(long id);
	public void updatePassword(String password, long id);
	public long findIdByUserName(String userName);

}
