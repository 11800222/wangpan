package login;

import java.sql.Timestamp;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class Req {
	public static CloseableHttpClient httpclient;

	public String requset(String url) throws Exception {
		httpclient=ForLogin.login.httpclient;
		HttpResponse r1 = httpclient.execute(new HttpGet(url));
		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		return content1;
	}
}
