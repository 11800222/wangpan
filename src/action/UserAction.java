package action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import service.FilesService;
import service.OperlogsService;
import service.UserService;
import util.ResponUtil;
import model.users;
import dao.userDaoImpl;

public class UserAction extends BaseAction {
	// 存放Action处理类
	private UserService userService;
	private OperlogsService operlogsService;
	// 存放Action入参
	private String ValidateCode;
	private users user;
	// 存放Action出参
	private String msg;

	public String mytest() {
		Logger logger = Logger.getLogger(UserAction.class);
		logger.error("测试2");
		System.out.println("1");
		return null;
	}
	
	public String login() {
		HttpSession session = getHttpSession();

		if (ValidateCode != null
				&& ValidateCode.equals(session.getAttribute("rand"))) {
			msg = userService.isExist(session, user);
		} else {
			msg = "验证码错误!";//Validate code err
		}
		if (msg == null || "".equals(msg)) {
			operlogsService.log(user.getUsername(), "登入系统", getHttpRequest()); //Login in System
			return "success";
		} else {
			ResponUtil.writeUTF(getHttpResponse(), "<script>alert('" + msg
					+ "');history.back();</script>");
			return null;
		}
	}
	
	public String reg() throws Exception{
		
		if(userService.add(user).equals("isExist")){
			getWriter().write("该帐号已存在，请用该帐号登录或另选帐号注册<br>");
			getWriter().write("<a href='register.jsp'>重新注册</a>");
			return null;
		}
		
		getWriter().write("注册成功<br>");
		getWriter().write("<a href='login.jsp'>登录</a>");
		
		return null;
	}

	public String userInsession() throws Exception {
		HttpSession session = getHttpSession();
		PrintWriter writer = getWriter();
		if (session.getAttribute("username") != null)
			writer.write((String) session.getAttribute("username"));
		return null;
	}
	public String userInformation()throws Exception{
		HttpSession session = getHttpSession();
		PrintWriter writer = getWriter();
		if (session.getAttribute("username") != null){
			String username=(String) session.getAttribute("username");
		    users u=userService.getUserByKey(username) ;
			writer.write("用户 ： "+u.getUsername()+"  你好！ 你当前可用的添加文件限额为 ： "+u.getCount());
			}
		return null;
	}
	

	public OperlogsService getOperlogsService() {
		return operlogsService;
	}

	public void setOperlogsService(OperlogsService operlogsService) {
		this.operlogsService = operlogsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getValidateCode() {
		return ValidateCode;
	}

	public void setValidateCode(String validateCode) {
		ValidateCode = validateCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

}
