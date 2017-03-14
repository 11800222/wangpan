package login;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import test.httpclientForTest;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class MyCallable implements Callable<String[]> {
	public String infohash;

	public String[] call() throws Exception {
		String[] InfohashAndId = {infohash,""};
		for (int i = 0; i < 5; ++i) {
			Thread.sleep(5000);
		    String lixian=GetResponse.lixian();
		 
			InfohashAndId[1] = ResponseUtil.getIdByInfohash(lixian, infohash);
			if (InfohashAndId[1].equals("notfound"))//根据infohash不能找到离线任务
				throw new Exception("notfound");
			if (!InfohashAndId[1].equals("notDone"))//已经找到id
				break;
		}
		if (InfohashAndId[1].equals("notDone"))//过了25s还未上传完成
			throw new Exception("notDone");
		return InfohashAndId;
	}

	public MyCallable(String infohash) {
		this.infohash = infohash;
	}
}
