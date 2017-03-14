package login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Login implements Runnable {
	public static CloseableHttpClient httpclient;
	public static String session_id;
	public static String uid;
	public static String tsign;
	public static String ttime;
	public static String user_id;
	public static String cid;
	public static boolean isonline=false;

	public File getQRcode() throws Exception {
		// ���ô��?����fiddler����
		/*HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
				proxy);
*/
		// ��������ͷ
		Header header1 = new BasicHeader(
				HttpHeaders.USER_AGENT,
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2663.0 Safari/537.36");
		List<Header> headers = new ArrayList<Header>();
		headers.add(header1);
		httpclient = HttpClients.custom().setDefaultHeaders(headers)
//				.setRoutePlanner(routePlanner)
				.build();

		// ȡ��uid��sign
		URIBuilder url1 = new URIBuilder("http://passport.115.com");
		url1.setParameter("ct", "login").setParameter("ac", "qrcode_token")
				.setParameter("is_ssl", "0");
		HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));
		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new Exception("UncleDrew:" + "ȡ��uidsignʧ��");
		}
		String content = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content);
		Login.uid = json1.getString("uid");
		String uidtime = json1.getString("time");
		String sign = json1.getString("sign");

		// sessionid
		URIBuilder url2 = new URIBuilder(
				"http://msg.115.com/proapi/anonymous.php");
		url2.setParameter("ac", "signin")
				.setParameter("user_id", uid)
				.setParameter("sign", sign)
				.setParameter("time", uidtime)
				.setParameter(
						"_",
						String.valueOf(new Timestamp(System.currentTimeMillis())
								.getTime()));
		HttpResponse r2 = httpclient.execute(new HttpGet(url2.build()));
		if (r2.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new Exception("Uncledrew:" + "ȡ��session-idʧ��");
		}
		String content2 = EntityUtils.toString(r2.getEntity(), "UTF-8");
		JSONObject json2 = JSONObject.fromObject(content2);
		Login.session_id = json2.getString("session_id");
		// ȡ�ö�ά��
		URIBuilder url3 = new URIBuilder("http://qrcode.115.com/api/qrcode.php");
		url3.setParameter("qrform", "1")
				.setParameter("uid", uid)
				.setParameter("_" + uidtime, "")
				.setParameter(
						"_t",
						String.valueOf(new Timestamp(System.currentTimeMillis())
								.getTime()));
		HttpResponse r3 = httpclient.execute(new HttpGet(url3.build()));
		if (r2.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new Exception("UncleDrew:" + "ȡ�ö�ά��ʧ��");
		}
		File qr = null;

		InputStream in = r3.getEntity().getContent();
		// qr = new File("C:\\Users\\Administrator\\Desktop\\QR.png");
		qr = new File("../webapps/WebTest/images/QR.png");
		FileOutputStream out = new FileOutputStream(qr);
		byte[] d = new byte[100];
		int m;
		while ((m = in.read(d)) != -1)
			out.write(d, 0, m);
		out.close();

		return qr;
	}

	// ���������༴�����߳�
	public void keepOnRequesting() throws Exception {
		Thread req = new Thread(this);
		req.start();
	}

	public void run() {// ���󷽷�

		try {
			while (true) {
				URIBuilder url1 = new URIBuilder("http://im37.115.com/chat/r");
				url1.setParameter("VER", "2")
						.setParameter("c", "b0")
						.setParameter("s", session_id)
						.setParameter(
								"_t",
								String.valueOf(new Timestamp(System
										.currentTimeMillis()).getTime()));
				HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));
				if (r1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					isonline = true;
					System.out.println("һ���������");
				} else {
                    isonline =false;
					System.out.println("���˳���¼״̬");
					break;
				}
				Thread.sleep(1000 * 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �ȴ���������ش����Դ�����¼����Ӧ
	public void waitlogin() {
		while (true) {
			try {
				URIBuilder url1;
				url1 = new URIBuilder("http://im37.115.com/chat/r");
				url1.setParameter("VER", "2")
						.setParameter("c", "b0")
						.setParameter("s", session_id)
						.setParameter(
								"_t",
								String.valueOf(new Timestamp(System
										.currentTimeMillis()).getTime()));
				HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));

				// ���ͷ��������ص���Ӧ���ǵ�¼�ɹ�����ʧ��
				/*
				 * String content1 = EntityUtils.toString(r1.getEntity(),
				 * "UTF-8"); System.out.println(content1);
				 * 
				 * JSONArray content = JSONArray.fromObject(content1); JSONArray
				 * p=((JSONObject)content.get(0)).getJSONArray("p"); String
				 * status=((JSONObject)p.get(0)).getString("status");
				 * 
				 * System.out.println(status);
				 */
			} catch (Exception e) {
				// ����ʧ��
				e.printStackTrace();
			}
			return;
		}

	}

	public void login() throws Exception {// ������¼����
		URIBuilder url1 = new URIBuilder("http://passport.115.com/");
		url1.setParameter("ct", "login").setParameter("ac", "qrcode")
				.setParameter("key", uid).setParameter("v", "android")
				.setParameter("goto", "http%3A%2F%2Fwww.J3n5en.com");

		httpclient.execute(new HttpGet(url1.build()));
		System.out.println("login return");
	}

	public void getsign_time_cid() throws Exception {// ��ȡ�ϴ�����������Ϣ

		URIBuilder url1 = new URIBuilder("http://115.com/");
		url1.setParameter("ct", "offline")
				.setParameter("ac", "space")
				.setParameter(
						"_",
						String.valueOf(new Timestamp(System.currentTimeMillis())
								.getTime()));

		HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content1);

		Login.tsign = json1.getString("sign");
		System.out.println("sign=" + tsign);
		Login.ttime = json1.getString("time");
		System.out.println("time=" + ttime);

		URIBuilder url2 = new URIBuilder("http://115.com/");
		url2.setParameter("ct", "lixian")
				.setParameter("ac", "get_id")
				.setParameter("torrent", "1")
				.setParameter(
						"_",
						String.valueOf(new Timestamp(System.currentTimeMillis())
								.getTime()));

		HttpResponse r2 = httpclient.execute(new HttpGet(url2.build()));

		String content2 = EntityUtils.toString(r2.getEntity(), "UTF-8");
		JSONObject json2 = JSONObject.fromObject(content2);

		Login.cid = json2.getString("cid");
		System.out.println("cid=" + cid);
	}

	public void getUserId() throws Exception {// �õ��û���
		URIBuilder url1 = new URIBuilder("http://passport.115.com/");

		url1.setParameter("ct", "ajax")
				.setParameter("ac", "islogin")
				.setParameter("is_ssl", "0")
				.setParameter(
						"_",
						String.valueOf(new Timestamp(System.currentTimeMillis())
								.getTime()));

		HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content1);

		Login.user_id = json1.getJSONObject("data").getString("USER_ID");
		System.out.println("user_id=" + user_id);
	}

	public String addTaskLink(String link) throws Exception {// ��ӵ����������񣬷���������

		URIBuilder url1 = new URIBuilder(
				"http://115.com/web/lixian/?ct=lixian&ac=add_task_url");
		// ����post�������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("url", link));
		params.add(new BasicNameValuePair("uid", user_id));
		params.add(new BasicNameValuePair("sign", tsign));
		params.add(new BasicNameValuePair("time", ttime));
		// ��Ӳ���
		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = httpclient.execute(post);

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content1);

		String filename = json1.getString("name");

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "�����������ʧ��");

		return filename;
	}

	

	public String getDownloadLink(String pickcode) throws Exception {// ���pickcode�õ���������
		String DownloadLink = "";
		URIBuilder url1 = new URIBuilder("http://webapi.115.com/files/download");
		url1.setParameter("pickcode", pickcode);

		HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content1);

		DownloadLink = json1.getString("file_url");
		return DownloadLink;
	}

}
