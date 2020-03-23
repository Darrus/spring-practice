package com.darrus.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.darrus.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers()
	{
		Session curSession = sessionFactory.getCurrentSession();
		
		Query<Customer> query = curSession.createQuery("from Customer order by lastName", Customer.class);
		
		return query.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer)
	{
		Session curSession = sessionFactory.getCurrentSession();
		curSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id)
	{
		Session curSession = sessionFactory.getCurrentSession();
		return curSession.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id)
	{
		Session curSession = sessionFactory.getCurrentSession();
		
		Query query = curSession.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}

}
