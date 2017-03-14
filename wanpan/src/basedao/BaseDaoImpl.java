package basedao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseDaoImpl implements BaseDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getCurrentSession() {
		Session session = sessionFactory.getCurrentSession();
		// TODO check session
		/*System.out.println(session.hashCode());*/
		return session;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Object object) {
		sessionFactory.getCurrentSession().save(object);
	}

	public abstract Object get(String ID);

	public abstract List findAll();


	public void update(Object object) {
		getCurrentSession().update(object);
	}

	public void delete(Object object) {
		getCurrentSession().delete(object);
	}

	public void delete(String id) {
		getCurrentSession().delete(get(id));
	}
}
