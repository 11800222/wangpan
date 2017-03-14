package login;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class GetResponse {
	public static CloseableHttpClient httpclient ;
	static {
		// 对httpclient进行初始化
		// 使httpclient支持多线程 (记得调用close关闭response)
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		httpclient = HttpClients.custom().setConnectionManager(cm).build();
	}
	
	
	public static String openfile(String cid,String type) throws Exception {
		URIBuilder url1 = new URIBuilder(
				"http://112.74.94.156:8080/WebTest/proxy");
		url1.setParameter("mode", "1")
		.setParameter("cid", cid)
		.setParameter("type", type);
		
		HttpResponse r1 =httpclient.execute(new HttpGet(url1.build()));
		String content = EntityUtils.toString(r1.getEntity(), "UTF-8");
		System.out.println(content);//测试
		return content;
	}

	public static synchronized String lixian() throws Exception {
		URIBuilder url1 = new URIBuilder(
				"http://112.74.94.156:8080/WebTest/proxy");
		url1.setParameter("mode", "2");
 
		HttpResponse r1 =httpclient.execute(new HttpGet(url1.build()));
		String content = EntityUtils.toString(r1.getEntity(), "UTF-8");
		System.out.println(content);//测试
		return content;
	}

	public static synchronized String addTaskLink(String link) throws Exception {// ��ӵ����������񣬷���������
		URIBuilder url1 = new URIBuilder(
				"http://112.74.94.156:8080/WebTest/proxy");
		url1.setParameter("mode", "3")
		.setParameter("url", link);
		
		HttpResponse r1 =httpclient.execute(new HttpGet(url1.build()));
		String content = EntityUtils.toString(r1.getEntity(), "UTF-8");
		System.out.println(content);//测试
		return content;
	}
	public static synchronized String getDownloadLink(String pickcode) throws Exception {// ���pickcode�õ���������
		URIBuilder url1 = new URIBuilder(
				"http://112.74.94.156:8080/WebTest/proxy");
		url1.setParameter("mode", "4")
			.setParameter("pc", pickcode);
		
		HttpResponse r1 =httpclient.execute(new HttpGet(url1.build()));
		String content = EntityUtils.toString(r1.getEntity(), "UTF-8");
		System.out.println(content);//测试
		return content;
	}

}
