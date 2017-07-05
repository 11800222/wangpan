package Core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import service.TasksService;
import model.Tasks;

public class TaskConsumer {
	private final static ConcurrentLinkedDeque<Tasks> works = new ConcurrentLinkedDeque<Tasks>();// 多线程容器
	private final static LinkedList<Tasks> failworks = new LinkedList<Tasks>();
	private TasksService mytaskService;// 由spring注入
	private MyExecutor myExecutor;
	private Jedis jedis = new Jedis("112.74.94.156", 6379);
	private JSONObject uptodateTaskList;
	private Logger logger = Logger.getLogger(TaskConsumer.class);
	private static volatile boolean isShutDown = false;
	private volatile boolean isShutdown = false;

	public void pollForTasks() {
		// 另开一个线程，不能占用spring的生命周期
		Thread pollthread = new Thread(new Runnable() {
			public void run() {
				while (!isShutdown) {
					try {
					while (!works.isEmpty() || !failworks.isEmpty()) {
						// 手动开启事务
						 
						mytaskService.getTaskDaoImpl().getCurrentSession()
								.beginTransaction();
						try {
							// 从redis里拿tasklist来更新uptodateTaskList,没有(过期)就自己获得tasklist并更新到redis;
							if (jedis.exists("tasklist"))
								uptodateTaskList = JSONObject.fromObject(jedis
										.get("tasklist"));
							else {
								uptodateTaskList = GetResponse.lixian();
								jedis.set("tasklist",
										uptodateTaskList.toString());
								jedis.expire("tasklist",3);// 设置过期时间为3s,轮询时间也是3s
							}
							// tasklist准备好了，开始遍历，先对上次失败的任务进行遍历
							for (Tasks t : failworks)
								if (consume(t))
									failworks.remove(t);
							// 获取当前任务列表的“快照”——以当前能获取的tail为标记
							Tasks SnapTail = works.getLast();// works为空时抛出NoSuchElementException
							while (true) {// 从head遍历到SnapTail就是上一行获得的快照
								Tasks curr = works.poll();
								if (!consume(curr)) {
									failworks.add(curr);
								}
								if (curr == SnapTail)
									break;
							}
						
						} catch (NoSuchElementException e) {
							// 若此时任务队列为空，获取tail会抛NoSuchElementException，捕捉它只是为了不要跳出轮询。
						} catch (InterruptedException e) {
							// 获取tasklist的过程中被中断了，跳出取消这次遍历
							logger.error("GetResponse.lixian() or waitForPoll was interrupted");
							break;
						} finally {
							// 手动提交事务到数据库、
							mytaskService.getTaskDaoImpl().getCurrentSession()
									.getTransaction().commit();
						}
						Thread.sleep(3000);
					} 
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						logger.error(Thread.currentThread().getName()+"was interrupted");
					} 
				}
			}
		});
		pollthread.setName("MypollingForTask-Thread");
		pollthread.start();// 开始线程
	}

	private boolean consume(Tasks t) {
		t.setPollTime(t.getPollTime() + 1);
		try {
			String res = ResponseUtil.getIdByInfohash(uptodateTaskList, t);
			if (res.equals("notDone")) {
				if (t.getPollTime() > 8)// 轮询超过8次的task入数据库
				{
					mytaskService.insert(t);
					return true;
				} else
					return false;// 可以进入下一轮轮询
			}
		} catch (Exception e) {
			// 把错误原因入日志。
			e.printStackTrace();
			logger.error("while getting id by infohash : " + t.getInfohash()
					+ " occurs " + e.getMessage());
			// 把出错的Task持久化也相当于消费成功。
			mytaskService.insert(t);
			return true;
		}
		// 提交文件完善任务到线程池
		
		myExecutor.submit(new MyCallable(t));
		return true;
	}

	public static void addTask(Tasks t) {
		works.add(t);
	}

	public void PersistTasks() {// 服务器关闭时，对所有还没完成的task进行持久化
		isShutdown = true;
		
		mytaskService.getTaskDaoImpl().getCurrentSession().beginTransaction();
		for (Tasks t : works)
			mytaskService.insert(t);
		for (Tasks t : failworks)
			mytaskService.insert(t);
		
		System.out.println(works.size()+failworks.size()+" Tasks are going to persisit in database");
		
		mytaskService.getTaskDaoImpl().getCurrentSession().getTransaction()
				.commit();
		mytaskService.getTaskDaoImpl().getSessionFactory().close();

	}

	public static int TasksInConsume(){
		return works.size()+failworks.size();
	}
	
	public MyExecutor getMyExecutor() {
		return myExecutor;
	}

	public void setMyExecutor(MyExecutor myExecutor) {
		this.myExecutor = myExecutor;
	}

	public TasksService getMytaskService() {
		return mytaskService;
	}

	public void setMytaskService(TasksService mytaskService) {
		this.mytaskService = mytaskService;
	}

}
