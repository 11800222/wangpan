package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import login.Login;
import login.Req;

public class Rp extends HttpServlet {

	public Rp() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!Login.isonline){
			response.sendRedirect("WebTest/getqr.jsp");
		}
		
		String url = request.getParameter("url");
		String content = null;
		
		try {
			HttpResponse r1 = Login.httpclient.execute(new HttpGet(url));
			content = EntityUtils.toString(r1.getEntity(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.write(content);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
