package com.happs.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happs.model.UserDetails;
import com.happs.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	

	@Autowired
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseEntity userDetails() {
		List userDetails = userService.getUserDetails();
		return new ResponseEntity(userDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/save1")
	public ResponseEntity save1(){
		Session session = sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    UserDetails ud = new UserDetails();
	    ud.setFirstName("Naresh");
	    ud.setEmail("naresh229@gmail.com");
	    session.save(ud);
	    tx.commit();
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> save(@RequestBody UserDetails userDetails){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(userDetails);
		tx.commit();
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ResponseEntity<UserDetails> update(@RequestBody UserDetails userDetails){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(userDetails);
		tx.commit();
		session.close();
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}
	
	@GetMapping(value="/get/{id}")
	public ResponseEntity<UserDetails> get(@PathVariable int id){
		Session session = sessionFactory.openSession();
		UserDetails userDetails = session.get(UserDetails.class, id);
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
	}
}
