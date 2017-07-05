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

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.sun.mail.iap.Response;

import Core.GetResponse;
import Core.MyCallable;
import Core.MyExecutor;
import Core.ResponseUtil;
import Core.TaskConsumer;
import antlr.build.Tool;
import service.FilesService;
import service.OperlogsService;
import service.TasksService;
import service.UserService;
import util.ResponUtil;
import model.Files;
import model.Tasks;
import model.users;
import dao.userDaoImpl;

public class FileAction extends BaseAction {
	// 存放Action处理类
	private FilesService filesService;
	private UserService usersService;
	private TasksService taskService;
	// 存放Action入参
	private String link;
	private Files file;
	private String username;
	// 存放Action出参
	private String out;
	private Map<String, Object> dataMap;
	// 日志类
	private static Logger logger = Logger.getLogger(FileAction.class);

	public FileAction() {
		// 初始化Map对象
		dataMap = new HashMap<String, Object>();
	}

	public String getDownlink() throws Exception {
		String url = GetResponse.getDownloadLink(link);
		getWriter().write(url);
		return null;
	}

	// 新的newFile方式
	public String NewFile() throws Exception {
		String username = (String) getHttpSession().getAttribute("username");
		if (!usersService.countDown(username)) {
			getWriter().write("你本月的添加下载任务次数已超出限制");
			return null;
		}
		// 没有超过提交任务限额
		JSONObject res = GetResponse.addTaskLink(link);

		try { // 把下载链接提交到代理服务器并取回infohash构造一个Task，过程中失败会抛出Exception
			Tasks task = new Tasks();
			String IsAddSuccess = ResponseUtil.getInfohashByAdd(res);

			if (IsAddSuccess.equals("任务已存在")) {
				OwnFile(res.getString("url"));
			} else {
				task.setInfohash(IsAddSuccess);// 未上传完成的文件的id为infohash
				task.setName(ResponseUtil.getNameByAdd(res));// 这时候其实还未能得到name
				task.setUrl(link);
				task.setPercentDone("justbegin");
				task.setUser(username);
				TaskConsumer.addTask(task);// 提交到TaskConsumer
			}
			getWriter().write("添加成功");
		} catch (Exception e) {
			// 过程失败,把失败信息返回并入日志
			e.printStackTrace();
			ResponUtil.writeUTF(getHttpResponse(), "提交任务失败:");
			logger.error("usern :[" + username + "] add " + link + "  cause: "
					+ e.getMessage());
			return null;
		}
		return null;
	}

	public String OwnFile(String url) throws Exception { 
		String username = (String) getHttpSession().getAttribute("username");
		Files own = filesService.getFileByurl(url);
		if (own == null)
			throw new Exception("任务已存在远程服务器，但本服务器没有");
		own.setUser(own.getUser() + "." + username + ".");
		filesService.insert(own);
		return null;
	}

	public String AddFile() throws Exception {
		if (filesService.insert(file))
			getWriter().write("success");
		return null;
	}

	public String DelFile() {
		try {
			String username = (String) getHttpSession()
					.getAttribute("username");
			Files del = filesService.getFileByKey(file.getCid());
			//把该文件夹下的该user除去。
			del.setUser(del.getUser().replaceAll("." + username + ".", ""));
			if (del.getUser().equals("")) {//如果这个文件夹已经没有user：
				List<Files> files = filesService.getFileByPid(del.getPid());
				for (Files f : files)
					// 删除子文件
					filesService.del(f);
				filesService.del(del);
			}
			else {
				filesService.insert(del);
			} 
				getWriter().write("success");
		} catch (Exception E) {
			logger.error(E.getStackTrace());
		}
		return null;
	}

	public String FileList() throws Exception {// 只能根据PID来查
		HttpServletResponse response = getHttpResponse();
		HttpServletRequest request = getHttpRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String user = request.getParameter("user");
		if (user == null || user.length() <= 0) {
			String pid = request.getParameter("pid");
			if (pid == null || pid.length() <= 0)
				return null;// TODO没传入pid时的返回
			List files = filesService.getFileByPid(pid);
			dataMap.put("data", files);
		} else { // 查找用户文件
			List files = filesService.getFileByuser(user);
			dataMap.put("data", files);
		}
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

	public TasksService getTaskService() {
		return taskService;
	}

	public void setTaskService(TasksService taskService) {
		this.taskService = taskService;
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
