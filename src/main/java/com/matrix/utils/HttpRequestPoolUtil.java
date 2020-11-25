package com.matrix.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @description: 解决httpclient的多线程请求问题，对PoolingHttpClientConnectionManager进行友好封装。
 *			https://blog.csdn.net/u014133299/article/details/80676147
 *			http://www.tanrd.com/2019/05/20/httpclient/
 *			https://www.yeetrack.com/?p=782
 *			研究中，尚未实际使用
 *
 * @author Yangcl
 * @date 2020年6月20日 下午8:42:47 
 * @version 1.0.0.1
 */
public class HttpRequestPoolUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestPoolUtil.class);
    
	private static PoolingHttpClientConnectionManager poolingManager;
	private static boolean STORE_COOKIE = true;
    private static CookieStore cookieStore = null;
    private static String charset = "UTF-8";
    
    /**
     * @description: 返回HttpClientConnection池化对象
     *
     * @return CloseableHttpClient
     * @author Yangcl
     * @date 2020年6月21日 上午12:15:57 
     * @version 1.0.0.1
     */
    private static CloseableHttpClient getHttpClient() {
    	if (poolingManager == null) {
            poolingManager = new PoolingHttpClientConnectionManager();
            poolingManager.setMaxTotal(800);		// 设置最大连接数
            poolingManager.setDefaultMaxPerRoute(5);  // 设置每个路由基础的连接，认值2。
//            poolingManager.setMaxPerRoute(new HttpRoute(httpHost), max);
        }
        LaxRedirectStrategy reStrategy = new LaxRedirectStrategy();		// 自动处理重定向链接
        HttpClientBuilder builder = HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setRedirectStrategy(reStrategy);
        if (!STORE_COOKIE) {
            cookieStore = null;
        }
        return builder.setDefaultCookieStore(cookieStore).setConnectionManager(poolingManager).build();
    }
    
    /**
     * @description: 获取返回结果，如果尝试请求三次(间隔1秒)均失败，则返回null。调用者需要判空操作。
     *
     * @param request
     * @param param
     * @author Yangcl
     * @date 2020年6月20日 下午11:40:40 
     * @version 1.0.0.1
     */
    private JSONObject getResponse(HttpRequestBase request, String param) {
        JSONObject json = new JSONObject();
        CloseableHttpResponse response = null;
        int requestTimes = 1;
        try {
        	RequestConfig config = RequestConfig.custom().setConnectTimeout(10_000)
        																							.setConnectionRequestTimeout(10_000)
        																							.setSocketTimeout(60_000).build();
            request.setConfig(config);
        	String requestURL = request.getURI().toURL().toString();
            String requsetMethod = request.getMethod();
            while(requestTimes < 4) {
                HttpClientContext context = HttpClientContext.create();
                CloseableHttpClient httpClient = HttpRequestPoolUtil.getHttpClient();
                response = httpClient.execute(request, context);
                HttpEntity entity = response.getEntity();
                cookieStore = context.getCookieStore();
                if (response.getStatusLine().getStatusCode() == 408) {
                    logger.info(requestURL + " 第：" + requestTimes + " 次请求");
                    requestTimes = requestTimes +1;
                    try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                } else if (entity != null) {
                    Header[] headers = response.getAllHeaders();
                    Map<String, String> headerMap = new HashMap<String, String>(10);
                    for(int i = 0; i < headers.length; ++i) {
                        Header header = headers[i];
                        headerMap.put(header.getName() , header.getValue());
                    }
                    json.put("status", "success");
                    json.put("data", JSONObject.parse(EntityUtils.toString(entity, charset)));
                    json.put("method", requsetMethod);
                    json.put("code", String.valueOf(response.getStatusLine().getStatusCode()));
                    json.put("header", headerMap);
                    json.put("url", requestURL);
                    if (!StringUtils.isEmpty(param)) {
                        json.put("param", param);
                    }
                    return json;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex_) {
                    ex_.printStackTrace();
                }
            }
        }
        
        return null;	// 三次请求全部失败则返回null
    }
    
    /**
     * @deprecated
     * @description: 
     *
     * @param paraValue
     * @author Yangcl
     * @date 2020年6月21日 上午12:01:46 
     * @version 1.0.0.1
     */
    public ArrayList<NameValuePair> covertParams2NVPS(JSONObject paraValue) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        Iterator<String> pv = paraValue.keySet().iterator();
        while(pv.hasNext()) {
            String key = pv.next().toString();
            pairs.add(new BasicNameValuePair(key, paraValue.getString(key)));
        }
        return pairs;
    }
    
    /**
     * @description: get方式提交请求数据
     *
     * @param url 
     * @param param 键值对key1=val1&key2=val2.....
     * @author Yangcl
     * @date 2020年6月21日 上午12:02:57 
     * @version 1.0.0.1
     */
    public JSONObject HttpGet(String url, String param) {
        HttpGet httpGet = null;
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        if (StringUtils.isEmpty(param)) {
            httpGet = new HttpGet(url);
        } else {
            httpGet = new HttpGet(url + "?" + param);
        }
        return this.getResponse(httpGet, "");
    }
    
    public JSONObject HttpPost(String url, String param) {
        JSONObject json = new JSONObject();
        String body;
        HttpClient httpClient = null;
        HttpPost method = null;
        if (url != null) {
            httpClient = new DefaultHttpClient();
            method = new HttpPost(url);
//            JSONObject response = this.getResponse(method, null);    // TODO 可以参考复用消息
        }
        if (method != null & param != null && !"".equals(param.trim())) {
            try {
                method.addHeader("Content-type", "application/json; charset=utf-8");
                method.setHeader("Accept", "application/json");
                method.setEntity(new StringEntity(param, Charset.forName(charset)));
                HttpResponse response = httpClient.execute(method);
                body = EntityUtils.toString(response.getEntity());
                if (body != null) {
                	json.put("status", "success");
                    json.put("data", body);
                    json.put("code", String.valueOf(response.getStatusLine().getStatusCode()));
                    json.put("url", url);
                    if (!StringUtils.isEmpty(param)) {
                        json.put("param", param);
                    }
                }
            } catch (IOException e) {
            	e.printStackTrace();
            } finally {
            	
            }
        }
        return json;
    }
    
}








































