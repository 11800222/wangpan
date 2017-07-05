package dao;

import java.util.List;

import model.Files;
import model.users;

import org.hibernate.SessionFactory;

public class FilesDaoImpl extends BaseDaoImpl{
	

	
	
	
	@Override
	public Object get(String ID) {
		
		return getCurrentSession().get(Files.class, ID);
	}

	@Override
	public List findAll() {
		
		return getCurrentSession().createQuery("from Files").list();
	}
	
	
	

}
