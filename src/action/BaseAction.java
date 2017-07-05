package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import Core.GetResponse;

public class BaseAction {
	public HttpServletResponse getHttpResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpServletRequest getHttpRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpSession getHttpSession() {
		return getHttpRequest().getSession();
	}
	public PrintWriter getWriter() throws Exception {
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		return ServletActionContext.getResponse().getWriter();
	}
}
