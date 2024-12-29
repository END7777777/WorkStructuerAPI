package com.tka.workstructure.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.workstructure.entity.Country;

@Repository
public class Cmaindao {

	@Autowired
	SessionFactory factory;

	public String addcountry(Country c) {
		Session session=null;
		Transaction tx=null;
		String msg=null;
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			session.persist(c);
			tx.commit();
			msg="country is added...";
		} catch (Exception e) {
			
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		
		return msg;
	}

	public String updatecountry(Country c, int id) {
		Session session=null;
		Transaction tx=null;
		String msg=null;
		Country country=null;
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			country=session.get(Country.class, id);
			country.setCname(c.getCname());
			session.merge(country);
			
			tx.commit();
			msg="Country is updated...";
		} catch (Exception e) {

			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return msg;
	}

	public String deletecountry(int id) {
		Session session=null;
		Transaction tx=null;
		String msg=null;
		Country country=null;
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			country=session.get(Country.class, id);
			session.remove(country);
			tx.commit();
			msg="Country is deleted";
			
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return msg;
	}

	public List<Country> getallcountry() {
		Session session=null;
		Transaction tx=null;
		List<Country> list=null;
		String hqlquery="from Country";
		
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Country> query=session.createQuery(hqlquery,Country.class);
			list=query.list();
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}

	public Country countrybyid(int id) {
		Session session=null;
		Transaction tx=null;
		
		Country country=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		country=session.get(Country.class, id);
		tx.commit();
		
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return country;
	}
	}

	
