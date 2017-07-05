package Core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.omg.SendingContext.RunTime;

import service.FilesService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class MyExecutor extends ThreadPoolExecutor {
	private FilesService myfileService;
	
	public MyExecutor() {
		super(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime()
				.availableProcessors(), 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	// 任务执行前，开启事务
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		myfileService.getFilesdaoimpl().getCurrentSession().beginTransaction();
	}

	// 任务结束后，提交事务
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		Session session = myfileService.getFilesdaoimpl().getCurrentSession();
		if (t == null){
			session.getTransaction().commit();
			
			}
		else {
            //虽然任务是幂等的，但为了文件夹的一致性，不要失败（半完成）的文件夹。
			session.getTransaction().rollback();
		}
		
	}
    //关闭线程池时释放连接
	 public void shutdown() {
		 super.shutdown();
		 myfileService.getFilesdaoimpl().getSessionFactory().close();
	 }
	
	
	public FilesService getMyfileService() {
		return myfileService;
	}

	public void setMyfileService(FilesService myfileService) {
		this.myfileService = myfileService;
	}
}
