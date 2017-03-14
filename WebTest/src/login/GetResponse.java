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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class GetResponse {
	public static CloseableHttpClient httpclient;

	public static String openfile(String cid,String type) throws Exception {
		httpclient=ForLogin.login.httpclient;
		URIBuilder url1 = new URIBuilder("http://web.api.115.com/files");

		url1.setParameter("aid", "1").setParameter("cid", cid)
				.setParameter("o", "user_ptime").setParameter("asc", "0")
				.setParameter("offset", "0")// �ӵ�offset���ļ���ʼ����
				.setParameter("show_dir", "1").setParameter("limit", "5000")// �ӵ�offset���ļ���ʼ����limit��,Ĭ��50��
				.setParameter("type", type);//99为文件，0为全部
		HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "���ļ�ʧ��");

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		
		return content1;
	}
	//暂时用不到
	public static String order(String cid) throws Exception {
		httpclient=ForLogin.login.httpclient;
		URIBuilder url1 = new URIBuilder(
				"http://web.api.115.com/files/order");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_order", "user_ptime"));
		params.add(new BasicNameValuePair("file_id", cid));
		params.add(new BasicNameValuePair("user_asc", "0"));

		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = httpclient.execute(post);
		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "��ѯ��������ʧ��");

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");

		return content1;
	}
	
	public static synchronized String lixian() throws Exception {
		httpclient=ForLogin.login.httpclient;
		URIBuilder url1 = new URIBuilder(
				"http://115.com/web/lixian/?ct=lixian&ac=task_lists");
		// ����post�������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("page", "1"));// Ĭ��һҳ
		params.add(new BasicNameValuePair("uid", Login.user_id));
		params.add(new BasicNameValuePair("sign", Login.tsign));
		params.add(new BasicNameValuePair("time", Login.ttime));
		// ��������ӵ�post��
		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = httpclient.execute(post);

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "��ѯ��������ʧ��");

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");

		return content1;
	}

	public static synchronized String addTaskLink(String link) throws Exception {// ��ӵ����������񣬷���������
		httpclient=ForLogin.login.httpclient;
		URIBuilder url1 = new URIBuilder(
				"http://115.com/web/lixian/?ct=lixian&ac=add_task_url");
		// ����post�������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("url", link));
		params.add(new BasicNameValuePair("uid", Login.user_id));
		params.add(new BasicNameValuePair("sign", Login.tsign));
		params.add(new BasicNameValuePair("time", Login.ttime));
		// ��Ӳ���
		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = httpclient.execute(post);

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "�����������ʧ��");

		return content1;
	}
	public static synchronized String getDownloadLink(String pickcode) throws Exception {// ���pickcode�õ���������
		httpclient=ForLogin.login.httpclient;
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
