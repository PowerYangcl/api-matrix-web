package com.matrix.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.matrix.service.ICountryService;


@WebServlet(urlPatterns="/servlet-async" , asyncSupported=true)
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 5392150624972620357L;
	
	@Autowired
    private ICountryService bandTestService;

	@Override			// http://127.0.0.1:8080/servlet-async?userName=Tom&count=5
    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().print("马上开始：<br/>");
//        response.getWriter().flush();		// 否则不输出
//        
//        final AsyncContext asyncContext = request.startAsync(request, response);
//        asyncContext.setTimeout(30_000);  // 设置超时时间
//        asyncContext.start(new Runnable() {
//            public void run() {
//                try {
//                	String userName = request.getParameter("userName");
//                	Integer count = Integer.valueOf(request.getParameter("count"));
//                	String doAsyncTest = bandTestService.doAsyncTest(userName, count);
//                	
//                	response.getWriter().println("这是异步的请求返回：" + doAsyncTest);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }finally {
//                	asyncContext.complete();		// 还可以使用 ac.dispatch() 方法重定向到一个页面  asyncContext.dispatch("/show.jsp");
//				}
//            }
//        });
//        
//        asyncContext.addListener(new AsyncListener() {		// 设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
//            public void onComplete(AsyncEvent event) throws IOException {
//            }
//            public void onTimeout(AsyncEvent event) throws IOException {
//            }
//            public void onError(AsyncEvent event) throws IOException {
//            }
//			public void onStartAsync(AsyncEvent event) throws IOException {
//			}
//        });
    }
	
}



// 参考：
// https://www.cnblogs.com/longestory/archive/2004/01/13/4566977.html
// https://blog.csdn.net/qq_24598601/article/details/82824937  写的不错
































