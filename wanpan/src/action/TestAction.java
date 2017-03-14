package action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import service.FilesService;
import service.OperlogsService;
import service.UserService;
import model.Files;
import model.users;
import dao.userDaoImpl;

public class TestAction extends BaseAction {
	// ���Action������
	private FilesService filesService;
	private UserService usersService;
	// ���Action���
	private String in;
	// ���Action����
	private String out;
	private Map<String, Object> dataMap;

	public TestAction() {
		// ��ʼ��Map����
		dataMap = new HashMap<String, Object>();
	}

	public String test() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		// out.write(Thread.currentThread().toString());

		filesService.getFileByKey("5");
         
		Files file1=new Files();
		file1.setCid("5");
	    file1.setName("oo");
		
	    filesService.edit(file1);

		return null;
	}

	public String file() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		List files = filesService.findAll();
		/*
		 * for(int i=0;i<files.size();++i) { dataMap.put("ww", value) }
		 */
		dataMap.put("data", files);

		return "json";
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public UserService getUsersService() {
		return usersService;
	}

	public void setUsersService(UserService usersService) {
		this.usersService = usersService;
	}

	public FilesService getFilesService() {
		return filesService;
	}

	public void setFilesService(FilesService filesService) {
		this.filesService = filesService;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

}
