package dao;

import java.util.List;

import model.Operlogs;
import model.users;

import org.hibernate.SessionFactory;

import basedao.BaseDaoImpl;

public class OperlogsDaoImpl extends BaseDaoImpl {

	    @Override
	    public List findAll() {
	        return getCurrentSession().createQuery("from Operlogs").list();
	    }

	    @Override
	    public Object get(String id) {
	        return getCurrentSession().get(Operlogs.class,id);
	    }

}
