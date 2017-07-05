package dao;

import java.util.List;

import model.users;

import org.hibernate.SessionFactory;

public class userDaoImpl extends BaseDaoImpl{
	

	
	
	
	@Override
	public Object get(String ID) {
		
		return getCurrentSession().get(users.class, ID);
	}

	@Override
	public List findAll() {
		 
		return null;
	}
	
	
	

}
