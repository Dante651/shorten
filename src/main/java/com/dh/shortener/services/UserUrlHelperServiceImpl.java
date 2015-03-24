package com.dh.shortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dh.shortener.model.UserUrlHelper;
import com.dh.shortener.repository.UserUrlHelperRepository;

@Service
public class UserUrlHelperServiceImpl implements UserUrlHelperService {

	@Autowired
	private UserUrlHelperRepository userUrlHelperRepository;
	
	@Override
	@Transactional
	public void add(UserUrlHelper userUrlhelper) {
		userUrlHelperRepository.save(userUrlhelper);

	}

}
