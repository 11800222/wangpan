package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.GetResponse;
import login.Login;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class proxy extends HttpServlet {
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GetResponse.httpclient=Login.httpclient;
		try {
			String mode=request.getParameter("mode");
			int mod = Integer.parseInt(mode);
			String content="";
			Scanner sc;
			switch (mod) {
			case 1://查询文件
				String cid=request.getParameter("cid");
				String type=request.getParameter("type");
				if(cid.length()!=0)
				content = GetResponse.openfile(cid,type);
				break;
			case 2://查询离线任务
				content = GetResponse.lixian();
				break;
			case 3://添加离线任务
				String url=request.getParameter("url");
				if(url.length()!=0)
				content = GetResponse.addTaskLink(url);
				break;
			case 4://得到下载链接
				String pc=request.getParameter("pc");
				if(pc.length()!=0)
				content = GetResponse.getDownloadLink(pc);
				break;
			}
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			out.write(content);
			out.flush();
			out.close();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.flush();
		out.close();
	}

}
