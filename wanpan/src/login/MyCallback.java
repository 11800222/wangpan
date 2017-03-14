package login;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import util.*;
import org.springframework.beans.factory.BeanFactory;

import service.FilesService;
import test.httpclientForTest;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import dao.FilesDaoImpl;
public class MyCallback implements FutureCallback<String[]> {

	public void onSuccess(String[] InfohashAndId)  {
		//成功，根据id把文件入数据库
		try{
		FilesService filesService=(FilesService)util.BeanFactory.getbean("FilesService");
		filesService.CompleteFilesByID(InfohashAndId[0], InfohashAndId[1]);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onFailure(Throwable t) {
		//失败，
		String errMessage=t.getMessage();
		System.out.println(errMessage);
			
	}

}
