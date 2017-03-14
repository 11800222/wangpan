package test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class httpclientForTest {
	public static CloseableHttpClient httpclient;
	static {
		// 对httpclient进行初始化
		// 使httpclient支持多线程 (记得调用close关闭response)
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		httpclient = HttpClients.custom().setConnectionManager(cm).build();
	}

	public static String get(URIBuilder url1) {
		CloseableHttpResponse response = null;
		String result = "";
		try {
			response = httpclient.execute(new HttpGet(url1.build()));
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != response)
					response.close();//关闭response
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String addTaskLink(String link)throws Exception{
		//addtask
		URIBuilder url3 = new URIBuilder(
				"http://112.74.94.156:8080/WebTest/servlet/ShowServlet?mode=3");
		String res=httpclientForTest.get(url3);
		return res;
	}
	public static String lixian()throws Exception{
		 //tasklist
		URIBuilder url2 = new URIBuilder(
				"http://112.74.94.156:8080/WebTest/servlet/ShowServlet?mode=2");
		String res=httpclientForTest.get(url2);
		return res;
	}
	
	
    public static void main(String ss[]){
    	try {
    		//filelist
			URIBuilder url1 = new URIBuilder(
					"http://112.74.94.156:8080/WebTest/servlet/ShowServlet?mode=1");
			System.out.println(httpclientForTest.get(url1));
            //tasklist
			URIBuilder url2 = new URIBuilder(
					"http://112.74.94.156:8080/WebTest/servlet/ShowServlet?mode=2");
			System.out.println(httpclientForTest.get(url2));
			//addtask
			URIBuilder url3 = new URIBuilder(
					"http://112.74.94.156:8080/WebTest/servlet/ShowServlet?mode=3");
			System.out.println(httpclientForTest.get(url3));
			//path
			URIBuilder url4 = new URIBuilder(
					"http://112.74.94.156:8080/WebTest/servlet/ShowServlet?mode=4");
			System.out.println(httpclientForTest.get(url4));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
