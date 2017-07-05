package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import model.Files;
import model.Owners;
import model.users;
import dao.OwnersDaoImpl;
import dao.userDaoImpl;

public class OwnersService {
	private OwnersDaoImpl ownerDaoImpl;

	public Owners getownerByKey(String infohash) {
		return (Owners) ownerDaoImpl.get(infohash);

	} 
	public boolean insert(Owners owner) {
		Owners sameowner = getownerByKey(owner.getId());
		if (sameowner == null) {
			ownerDaoImpl.save(owner);
			return true;
		}
		return false;// 由于hibernate在插入数据库中已存在的实体时不报异常，返回false作为提示
	}
 
	public List<Owners> getCidsByOwner(String owner) {
		return ownerDaoImpl.getCurrentSession().createCriteria(Owners.class)
				.add(Restrictions.eq("owner", owner)).list(); 
	}

	public OwnersDaoImpl getownerDaoImpl() {
		return ownerDaoImpl;
	}

	public void setownerDaoImpl(OwnersDaoImpl ownerDaoImpl) {
		this.ownerDaoImpl = ownerDaoImpl;
	}

 
	 

	 
	 
}
