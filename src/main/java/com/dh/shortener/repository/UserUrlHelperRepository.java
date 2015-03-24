package com.dh.shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.shortener.model.UserUrlHelper;

@Repository
public interface UserUrlHelperRepository extends JpaRepository<UserUrlHelper, Long> {
	
}
