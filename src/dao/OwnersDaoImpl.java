package dao;

import java.util.List;

import model.Operlogs;
import model.users;

import org.hibernate.SessionFactory;

public class OwnersDaoImpl extends BaseDaoImpl {

	    @Override
	    public List findAll() {
	        return getCurrentSession().createQuery("from Operlogs").list();
	    }

	    @Override
	    public Object get(String id) {
	        return getCurrentSession().get(Operlogs.class,id);
	    }

}
