package action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.sun.mail.iap.Response;

import antlr.build.Tool;
import service.FilesService;
import service.OperlogsService;
import service.UserService;
import test.httpclientForTest;
import util.ResponUtil;
import login.GetResponse;
import login.MyCallable;
import login.MyCallback;
import login.MyExecutor;
import login.ResponseUtil;
import model.Files;
import model.users;
import dao.userDaoImpl;

public class FileAction extends BaseAction {
	// 存放Action处理类
	private FilesService filesService;
	private UserService usersService;
	// 存放Action入参
	private String link;
	private Files file;
	private String username;
	// 存放Action出参
	private String out;
	private Map<String, Object> dataMap;

	public FileAction() {
		// 初始化Map对象
		dataMap = new HashMap<String, Object>();
	}
	public String getDownlink() throws Exception{
		String url = GetResponse.getDownloadLink(link);
		getWriter().write(url);
		return null;
	}

	public String NewFile() throws Exception {
		String res=GetResponse.addTaskLink(link);
		Files newfile = new Files();
		try{
		String IsAddSuccess=ResponseUtil.getInfohashByAdd(res);
		newfile.setCid(IsAddSuccess);//未上传完成的文件的id为infohash
		newfile.setName(ResponseUtil.getNameByAdd(res));
		newfile.setUrl(link);
		newfile.setIsDone("not");
		String user=(String)getHttpSession().getAttribute("username");
		newfile.setUser(user);
		newfile.setPid(user);
		
		if (filesService.insert(newfile))
			getWriter().write("添加成功");
		//异步任务：等待115上传完成->同步到数据库
		//传入一个infohash
		ListenableFuture<String[]> Task = MyExecutor.service.submit(new MyCallable(ResponseUtil.getInfohashByAdd(res)));
		Futures.addCallback(Task,new MyCallback());
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		 	ResponUtil.writeUTF(getHttpResponse(), e.getMessage());
			return null;
		}
		 
		return null;
	}
	
	public String AddFile() throws Exception {
		if (filesService.insert(file))
			getWriter().write("success");
		return null;
	}

	public String DelFile() throws Exception {
		if (filesService.del(file))
			getWriter().write("success");
		return null;
	}

	public String FileList() throws Exception {//只能根据PID来查
		HttpServletResponse response = getHttpResponse();
		HttpServletRequest request = getHttpRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String pid = request.getParameter("pid");
		if (pid == null || pid.length() <= 0)
			return null;// TODO没传入pid时的返回
		List files = filesService.getFileByPid(pid);

		dataMap.put("data", files);

		return "json";
	}

	public String EditFile() throws Exception {
		// 把无需修改的属性置0
		if (file.getCid() == null)
			return null;// 没有ID直接返回

		if (file.getName() == null)
			file.setName("0");
		if (file.getPickcode() == null)
			file.setPickcode("0");
		if (file.getPid() == null)
			file.setPid("0");
		if (file.getUrl() == null)
			file.setUrl("0");
		if (file.getUser() == null)
			file.setUser("0");

		if (filesService.edit(file))
			getWriter().write("success");
		return null;
	}

	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
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

	

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
