package dao;

import java.util.List;

import model.users;

import org.hibernate.SessionFactory;

import basedao.BaseDaoImpl;

public class userDaoImpl extends BaseDaoImpl{
	

	
	
	
	@Override
	public Object get(String ID) {
		
		return getCurrentSession().get(users.class, ID);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
