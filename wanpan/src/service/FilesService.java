package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import login.GetResponse;
import login.ResponseUtil;
import model.Files;
import model.users;
import dao.FilesDaoImpl;
import dao.userDaoImpl;

public class FilesService {
	private FilesDaoImpl filesdaoimpl;

	public Files CompleteFileByItem(JSONObject item) throws Exception {// 把JsonObject转化为文件
		// 先判断是文件还是文件夹
		Files file = null;
		if (item.has("ico")) {
			file=(Files)filesdaoimpl.get(item.getString("fid"));
			if(file==null)file=new Files();
			file.setCid(item.getString("fid"));
			file.setPid(item.getString("cid"));// 文件的pid为cid
			file.setType(item.getString("ico"));
		} else {
			file=(Files)filesdaoimpl.get(item.getString("cid"));
			if(file==null)file=new Files();
			file.setCid(item.getString("cid"));
			file.setPid(item.getString("pid"));
			file.setType("folder");
		}
		file.setIsDone("yes");
		file.setName(item.getString("n"));
		file.setPickcode(item.getString("pc"));
		return file;
	}

	public void CompleteFilesByID(String infohash, String id) throws Exception {
		// 已经上传完成,就先删除id为infohash的文件（因为主键不能修改），删除前提取离线任务信息
		Files thefile = (Files) filesdaoimpl.get(infohash);
		String user = thefile.getUser();
		String url = thefile.getUrl();
		boolean flag = true;
		filesdaoimpl.delete(infohash);
		// 在离线下载文件夹下找到该文件;
		JSONObject item = ResponseUtil.getItemByid(
				GetResponse.openfile("437789382553092060","0"), id);
		//修改根目录下文件的pid为user
		item.put("pid", user);
		// 宽搜同步该文件及其所有子文件
		Queue<JSONObject> queue = new LinkedList<JSONObject>();
		queue.offer(item);
		while (!queue.isEmpty()) {
			JSONObject currentitem = queue.poll();
			Files currentfile = CompleteFileByItem(currentitem);

			if (flag) {// 用户主目录下的文件应该有user和url属性
				currentfile.setUser(user);
				currentfile.setUrl(url);
				flag = false;
			}
			filesdaoimpl.save(currentfile);// 保存到数据库
			if (currentitem.has("ico"))// 不是文件夹的话就不要往下搜了
				continue;

			JSONArray items = ResponseUtil.getSubItemsByid(GetResponse.openfile(item.getString("cid"),"0"));
			
			Iterator<Object> it = items.iterator();
			while (it.hasNext()) {
			queue.offer((JSONObject) it.next());
			}
			
		}

	}

	public Files getFileByKey(String key) {
		return (Files) filesdaoimpl.get(key);

	}

	public List<Files> getFileByPid(String pid) {
		return filesdaoimpl.getCurrentSession().createCriteria(Files.class)
				.add(Restrictions.eq("pid", pid)).list();

	}

	public List<Files> findAll() {
		return filesdaoimpl.findAll();

	}

	public FilesDaoImpl getFilesdaoimpl() {
		return filesdaoimpl;
	}

	public void setFilesdaoimpl(FilesDaoImpl filesdaoimpl) {
		this.filesdaoimpl = filesdaoimpl;
	}

	public boolean insert(Files files) {
		Files samefile = getFileByKey(files.getCid());
		if (samefile == null) {
			filesdaoimpl.save(files);
			return true;
		}
		return false;// 由于hibernate在插入数据库中已存在的实体时不报异常，返回false作为提示
	}

	public boolean del(Files files) {//
		files = getFileByKey(files.getCid());
		if (files == null)
			return false;// 由于hibernate在删除数据库中不存在的实体时不报异常，返回false作为提示
		filesdaoimpl.delete(files);
		return true;
	}

	public boolean edit(Files files) throws Exception {// 传入对象的0属性被视为保持原数值
		Files samefile = getFileByKey(files.getCid());
		if (samefile == null) {
			return false; // 要修改的数据不存在时尝试插入
		}
		if (samefile != files) {
			// 如果session中已有该持久化对象，转为修改session中持久化对象。（应避免情况，因为已有持久化对象时应直接修改持久化对象）
			// 复制传进来的对象的属性到持久化对象，null属性不会被复制。
			if (files.getName() != "0")
				samefile.setName(files.getName());
			if (files.getPickcode() != "0")
				samefile.setPickcode(files.getPickcode());
			if (files.getPid() != "0")
				samefile.setPid(files.getPid());
			if (files.getUrl() != "0")
				samefile.setUrl(files.getUrl());
			if (files.getUser() != "0")
				samefile.setUser(files.getUser());
		}
		filesdaoimpl.update(samefile);
		return true;
	}

}
