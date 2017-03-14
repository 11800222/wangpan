package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;

import model.users;
import dao.userDaoImpl;

public class UserService {
	private userDaoImpl userdaoimpl;

	public users getUserByKey(String ID) {
		return (users) userdaoimpl.get(ID);

	}

	// TODO
	public String isExist(HttpSession session, users user) {
		String msg = "";
		Query query = userdaoimpl.getCurrentSession().createQuery(
				"from users where username=? and password=?");

		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPassword());

		List list = query.list();

		if (list != null && list.size() > 0) {
			user = (users) list.get(0);
			/*
			 * dif ("1".equals(user.getIsOk())) {
			 * user.setLastLoginTime(user.getLastestLoginTime());
			 * user.setLastestLoginTime(new Date()); userDaoImpl.update(user);
			 */
			session.setAttribute("username", user.getUsername());
			// session.setAttribute("currentFolder","/"+user.getLoginName()+"/");

		} else {
			msg = "账号或密码错误";//account or password err
		}
		return msg;

	}

	public userDaoImpl getUserdaoimpl() {
		return userdaoimpl;
	}

	public void setUserdaoimpl(userDaoImpl userdaoimpl) {
		this.userdaoimpl = userdaoimpl;
	}

	public String add(users user) {
		if (getUserByKey(user.getUsername()) != null)
			return "isExist";
		userdaoimpl.save(user);
		return "success";
	}
	public boolean del(users user) {
		user = getUserByKey(user.getUsername());
		if (user == null)
			return false;
		userdaoimpl.delete(user);
		return true;
	}

}
