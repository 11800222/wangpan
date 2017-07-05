package Core;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import util.*;
import model.Tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

import service.FilesService;
 
import dao.FilesDaoImpl;

public class MyCallable implements Callable<String> {
	private final Tasks task;

	public MyCallable(Tasks t) {
		this.task = t;
	} 
	public String call() throws Exception {
		try{
			FilesService filesService = (FilesService) util.BeanFactory
					.getbean("myFilesService");
			filesService.CompleteFilesByID(task); 
		}catch(Exception e){
			//把这一文件完善的任务的失败log到日志
			Logger.getLogger("while completing id : ").error(task.getId()+" cause "+e.getMessage());
		}
		return null;
	}

}
