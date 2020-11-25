package com.matrix.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.matrix.service.IBandTestService;

@Service
//@Scope(value="prototype")
public class BandTestServiceImpl implements IBandTestService {

   /**
    * 	异步方法
    * 	有@Async注解的方法，默认就是异步执行的，会在默认的线程池中执行，但是此方法不能在本类调用；
    * 	启动类需添加直接开启异步执行@EnableAsync。
    * */
	public String doAsyncTest(String userName, Integer count) {
		
		try {TimeUnit.MILLISECONDS.sleep(count);} catch (InterruptedException e) {e.printStackTrace();} 
		
		String result = Thread.currentThread().getName()+"：执行完毕，耗时：" + count + " 毫秒";
		System.out.println(result);
		return result;
	}

}
