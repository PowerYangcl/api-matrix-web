package com.matrix.controller;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.matrix.service.IBandTestService;

@RestController
@RequestMapping("/api")
//@Scope(value="prototype")
public class BandTestController {

    @Autowired
    private IBandTestService bandTestService;
    
    @GetMapping(value = "/cps/sync")
    public String getInfo(String userName, Integer count) {
    	return bandTestService.doAsyncTest(userName, count);
    }
    
   
    @GetMapping(value = "/cps/async")		 // http://127.0.0.1:8080/api/cps/async?userName=Tom&count=5
    public void  servletReq(String userName, Integer count, HttpServletRequest request, HttpServletResponse response) {
        try {
        	response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("马上开始：<br/>");
			response.getWriter().flush();		// 否则不输出
		} catch (IOException ex) {
			ex.printStackTrace();
		}
        
        
        AsyncContext asyncContext = request.startAsync();
        // 设置超时时间
        asyncContext.setTimeout(30_000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    String doAsyncTest = bandTestService.doAsyncTest(userName, count);
                    asyncContext.getResponse().setCharacterEncoding("utf-8");
                    asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("这是异步的请求返回：" + doAsyncTest);
                    
                } catch (Exception e) {
                	e.printStackTrace();
                }finally {
                	asyncContext.complete();		// 异步请求完成通知，此时整个请求才完成
                }
            }
        });
        
        asyncContext.addListener(new AsyncListener() {		// 设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
            public void onComplete(AsyncEvent event) throws IOException {
            }
            public void onTimeout(AsyncEvent event) throws IOException {
            }
            public void onError(AsyncEvent event) throws IOException {
            }
			public void onStartAsync(AsyncEvent event) throws IOException {
			}
        });
        
        //此时之类 request的线程连接已经释放了
        System.out.println("***************************主线程：" + Thread.currentThread().getName());
    }
    
    
}

























