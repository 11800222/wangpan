package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;

import model.Files;
import model.Tasks;
import model.users;
import dao.TasksDaoImpl;
import dao.userDaoImpl;

public class TasksService {
	private TasksDaoImpl taskDaoImpl;

	public Tasks getTaskByKey(String infohash) {
		return (Tasks) taskDaoImpl.get(infohash);

	}
	
	public boolean insert(Tasks task) {
		Tasks sametask = getTaskByKey(task.getInfohash());
		if (sametask == null) {
			taskDaoImpl.save(task);
			return true;
		}
		return false;// 由于hibernate在插入数据库中已存在的实体时不报异常，返回false作为提示
	}
 
	public Tasks getTaskByinfohash(String infohash) {
		return (Tasks) taskDaoImpl.get(infohash);
	}

	public TasksDaoImpl getTaskDaoImpl() {
		return taskDaoImpl;
	}

	public void setTaskDaoImpl(TasksDaoImpl taskDaoImpl) {
		this.taskDaoImpl = taskDaoImpl;
	}

 
	 

	 
	 
}
