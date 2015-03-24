package com.dh.shortener.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dh.shortener.model.User;
import com.dh.shortener.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public boolean removeUser(long userId) {
		userRepository.delete(userId);
		return false;
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		userRepository.save(user);
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<User> getAllUsers() {
		return new HashSet<User>(userRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<User> findOnlyUsers() {
		return userRepository.findOnlyUsers();
	}

	@Override
	@Transactional
	public void updateUserName(String username, long id) {
		userRepository.updateUserName(username, id);
	}

	@Override
	@Transactional
	public void updateEmail(String email, long id) {
		userRepository.updateEmail(email, id);
	}

	@Override
	@Transactional
	public void updateRole(String role, long id) {
		userRepository.updateRole(role, id);	
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserToEdit(long id) {
		return userRepository.findUserToEdit(id);
	}

	@Override
	@Transactional
	public void updatePassword(String password, long id) {
		userRepository.updatePassword(password, id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public long findIdByUserName(String userName) {
		return userRepository.findIdByUserName(userName);
	}

}
