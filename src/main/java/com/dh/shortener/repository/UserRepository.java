package com.dh.shortener.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dh.shortener.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUserName(String userName);
	
	public User findByEmail(String email);
	
	@Query("SELECT user.id FROM User user WHERE user.userName = :userName")
	public long findIdByUserName(@Param("userName") String userName);
	
	@Query("SELECT user.userName, user.email, user.role FROM User user ORDER BY user.id")
	public Set<User> findOnlyUsers();
	
	@Query("SELECT user.email, user.password FROM User user WHERE user.id = :id")
	public User findUserToEdit(@Param("id") long id);
	
	@Modifying
	@Query("UPDATE User user SET user.userName = :userName WHERE user.id = :id")
	public void updateUserName(@Param("userName") String username, @Param("id") long id);
	
	@Modifying
	@Query("UPDATE User user SET user.email = :email WHERE user.id = :id")
	public void updateEmail(@Param("email") String email, @Param("id") long id);
	
	@Modifying
	@Query("UPDATE User user SET user.role = :role WHERE user.id = :id")
	public void updateRole(@Param("role") String role, @Param("id") long id);
	
	@Modifying
	@Query("UPDATE User user SET user.password = :password WHERE user.id = :id")
	public void updatePassword(@Param("password") String password, @Param("id") long id);
	
}
