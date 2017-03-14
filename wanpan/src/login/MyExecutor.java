package login;

import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class MyExecutor {
	// 注册处理器数量个线程的线程池
	public final static ListeningExecutorService service = MoreExecutors
			.listeningDecorator(Executors.newFixedThreadPool(Runtime
					.getRuntime().availableProcessors()));

}
