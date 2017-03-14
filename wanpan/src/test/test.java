package test;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.concurrent.Executors;

import login.GetResponse;
import login.MyCallable;
import login.MyCallback;
import login.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

import service.FilesService;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class test {
	// 注册处理器数量个线程的线程池
	final static ListeningExecutorService service = MoreExecutors
			.listeningDecorator(Executors.newFixedThreadPool(Runtime
					.getRuntime().availableProcessors()));

	public static void main(String[] args) {
		try {
			// ListenableFuture<String> Task = service.submit(new
			// MyCallable("a2268ac454db849678ea2e753a43af46666dc153"));
			// 已完成任务 ListenableFuture<String> Task2 = service.submit(new
			// MyCallable("5c762d331a01b0af462f1848c84891da"));

			// Futures.addCallback(Task,new MyCallback());
			// Futures.addCallback(Task2,new MyCallback());

			// CloseableHttpClient httpclient = HttpClients.custom().build();
			// HttpResponse r1 =httpclient.execute(new HttpGet(url1.build()));
			// String content = EntityUtils.toString(r1.getEntity(), "UTF-8");
			// 根据（已上传完成的）infohash找(找name再)ID
			// MyCallable myCallable = new
			// MyCallable("a20ee5d0fa47acf49a756e147983d016");
			// String ss[] = myCallable.call();
			// System.out.println(ss[0]);
			// System.out.println(ss[1]);
			// 根据ID找到文件并与115同步
			/*String content ="{ \"name\": \"文件\" }";
			JSONObject json1 = JSONObject.fromObject(content);
		    json1.put("name", "1");
		    System.out.println(json1.getString("name"));*/
			GetResponse.getDownloadLink("cpw2lcrj5s9h4dxac");
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
