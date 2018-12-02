package com.happs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happs.dao.UserDao;
import com.happs.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserDao userDao;

	public List getUserDetails() {
		return userDao.getUserDetails();
	}

}
