package com.happs.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.happs.dao.UserDao;
import com.happs.model.UserDetails;

@Repository
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	SessionFactory sessionFactory;

	
	public List getUserDetails() {
		Session session = sessionFactory.openSession();//entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery("select * from user_details");
		List list = query.list();
		Criteria criteria = session.createCriteria(UserDetails.class);
		return criteria.list();
	}
	
	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}
}
