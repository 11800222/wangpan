package dao;

import java.util.List;

import model.Tasks;
import model.users;

import org.hibernate.SessionFactory;

public class TasksDaoImpl extends BaseDaoImpl{
	

	
	
	
	@Override
	public Object get(String infohash) {
		
		return getCurrentSession().get(Tasks.class, infohash);
	}

	@Override
	public List findAll() {
		 
		return null;
	}
	
	
	

}
