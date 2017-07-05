package Core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

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
import org.apache.log4j.Logger;

 

public class GetResponse {
	private final static CloseableHttpClient httpclient;
	private final static ReentrantLock NoValidateLock;// 代理服务器请求无需验证码的内容时用的锁
	private final static ReentrantLock ValidateLock;// 代理服务器请求需要验证码的内容时用的锁
	private final static Logger logger;
	static {
		// 使httpclient支持多线程 (request后调用close关闭response)
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		 
		httpclient = HttpClients.custom().setConnectionManager(cm).build();
	    
		NoValidateLock = new ReentrantLock();
		ValidateLock = new ReentrantLock(); 
		logger = Logger.getLogger(GetResponse.class);
	}
	
	public static JSONObject openfile(String cid, String type)throws InterruptedException  {
		JSONObject jsonContent;
		try {
			while (true) {
				NoValidateLock.lock();// 代理服务器请求文件目录时无需验证码
				String strContent=null;
				try {
					URIBuilder url1 = new URIBuilder(
							"http://119.29.148.102:8080/WebTest/proxy");
					url1.setParameter("mode", "1").setParameter("cid", cid)
							.setParameter("type", type);
					HttpResponse r1 = httpclient.execute(new HttpGet(url1
							.build()));
					strContent = EntityUtils.toString(r1.getEntity(), "UTF-8");
					System.out.println(strContent);
					jsonContent = JSONObject.fromObject(strContent);
					if (jsonContent.getString("state").equals("false")) {
						Thread.sleep(3300);// 已知失败：未验证或未登录，阻塞33秒后重试。
						continue;
					}
					break;
				} catch (Exception e) {// 未知失败，记录下失败原因后阻塞330s后重试。
					logger.error("未知错误原因： "+strContent+e.getMessage());
					Thread.sleep(330000);
				}
			}
		} finally {// 无论请求失败与否，释放锁
			NoValidateLock.unlock();
		}
		return jsonContent;
	}

	public static JSONObject lixian() throws InterruptedException {
		JSONObject jsonContent;
		try {
			while (true) {
				ValidateLock.lock();
				String strContent=null;
				try {
					URIBuilder url1 = new URIBuilder(
							"http://119.29.148.102:8080/WebTest/proxy");
					url1.setParameter("mode", "2");
					HttpResponse r1 = httpclient.execute(new HttpGet(url1
							.build()));
					strContent = EntityUtils.toString(r1.getEntity(),
							"UTF-8");
					System.out.println(strContent);
					jsonContent = JSONObject.fromObject(strContent);

					if (jsonContent.getString("state").equals("false")
							&& jsonContent.getString("errcode").equals("99")) {
						Thread.sleep(3300);// 已知失败：未验证或未登录，阻塞33秒后重试。
						continue; 
					}
					break;
				} catch (Exception e) {//  未知失败，记录下失败原因后阻塞330s后重试。
					logger.error("未知错误原因： "+strContent+e.getMessage());
					Thread.sleep(330000);
				}
			}
		} finally {
			Thread.sleep(1000);//敏感操作不要太频繁，容易触发验证码
			ValidateLock.unlock();
		}
		return jsonContent;
	}

	public static JSONObject addTaskLink(String link)throws InterruptedException  {// ��ӵ����������񣬷���������
		JSONObject jsonContent;
		try {
			while (true) {
				ValidateLock.lock();
				String strContent=null;
				try {
					URIBuilder url1 = new URIBuilder(
							"http://119.29.148.102:8080/WebTest/proxy");
					url1.setParameter("mode", "3").setParameter("url", link);

					HttpResponse r1 = httpclient.execute(new HttpGet(url1
							.build()));
					 strContent = EntityUtils.toString(r1.getEntity(),
							"UTF-8");
					System.out.println(strContent);
					jsonContent = JSONObject.fromObject(strContent);

					if (jsonContent.getString("state").equals("false")
							&& jsonContent.getString("errcode").equals("99")) {
						Thread.sleep(3300);// 已知失败：未验证或未登录，阻塞33秒后重试。
						continue;
					}
					break;
				} catch (Exception e) {// 未知失败，记录下失败原因后阻塞330s后重试。
					logger.error("未知错误原因： "+strContent+e.getMessage());
					Thread.sleep(330000);
				}
			}
		} finally {
			Thread.sleep(1000);//敏感操作不要太频繁，容易触发验证码
			ValidateLock.unlock();
		}
		return jsonContent;
	}

	public static String getDownloadLink(String pickcode) throws InterruptedException {// ���pickcode�õ���������
		String strContent=null;
		try {
			while (true) {
				ValidateLock.lock();
			
				try {
					URIBuilder url1 = new URIBuilder(
							"http://119.29.148.102:8080/WebTest/proxy");
					url1.setParameter("mode", "4")
						.setParameter("pc", pickcode);
					
					HttpResponse r1 =httpclient.execute(new HttpGet(url1.build()));
					strContent = EntityUtils.toString(r1.getEntity(), "UTF-8");
					System.out.println(strContent);
					if(strContent.substring(0, 4).equals("http"))
						break;
					JSONObject jsonContent = JSONObject .fromObject(strContent);
						if (jsonContent.getString("state").equals("false")
								&& jsonContent.getString("errcode")
										.equals("99")) {
							Thread.sleep(3300);// 已知失败：未验证或未登录，阻塞33秒后重试。
							continue; 
						} 
				} catch (Exception e) {// 未知失败 ，记录下失败原因后阻塞330s后重试。
					logger.error("未知错误原因： "+strContent+e.getMessage());
					Thread.sleep(330000);
				}
			}
		} finally {
			Thread.sleep(1000);//敏感操作不要太频繁，容易触发验证码
			ValidateLock.unlock();
			
		}
		return strContent;
	}

}
