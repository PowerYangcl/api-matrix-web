package com.matrix.utils;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description: Nice job
 * 		PoolingHttpClientConnectionManager解决httpclient的多线程请求问题：https://www.cnblogs.com/JoeyWong/p/9056346.html
 *
 * @author zhanghang
 * @date 2020年6月20日 下午7:35:34 
 * @version 1.0.0.1
 */
public class HttpClientPoolingUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientPoolingUtils.class);
    public static boolean STORE_COOKIE = true;
    private static PoolingHttpClientConnectionManager poolingManager;
    static CookieStore cookieStore = null;
    private static String UTF_8 = "UTF-8";

    public HttpClientPoolingUtils() {}
    
    private Map<String , String> header = new HashMap<String , String>();

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
    

    private JSONObject getCommonResult(HttpRequestBase request, String requestParam) {
        JSONObject json = new JSONObject();
        CloseableHttpResponse response = null;
        int requestTimes = 1;
        try {
        	if(header != null && header.size() != 0) {
        		for(Map.Entry<String,String> entry : header.entrySet()) {
        			request.setHeader(entry.getKey() , entry.getValue());
        		}
        	}
        	
            String requestURL = request.getURI().toURL().toString();
            String requsetMethod = request.getMethod();
            while(requestTimes < 3) {
                HttpClientContext context = HttpClientContext.create();
                CloseableHttpClient httpClient = getHttpClient();
                response = httpClient.execute(request, context);
                HttpEntity entity = response.getEntity();
                cookieStore = context.getCookieStore();
                if (response.getStatusLine().getStatusCode() == 408) {
                    logger.info(requestURL + "第" + requestTimes + "次请求");
                    ++ requestTimes;
                } else if (entity != null) {
                    Header[] headers = response.getAllHeaders();
                    Map<String, String> headerMap = new HashMap<String, String>(10);
                    Header[] var16 = headers;
                    int var17 = headers.length;
                    for(int i = 0; i < var17; ++i) {
                        Header header = var16[i];
                        headerMap.put(header.getName() , header.getValue());
                    }
                    json.put("status", "success");
                    json.put("data", JSONObject.parse(EntityUtils.toString(entity, UTF_8)));
                    json.put("method", requsetMethod);
                    json.put("code", String.valueOf(response.getStatusLine().getStatusCode()));
                    json.put("header", headerMap);
                    json.put("url", requestURL);
                    if (!StringUtils.isEmpty(requestParam)) {
                        json.put("param", requestParam);
                    }
                    JSONObject json_ = json;
                    return json_;
                }
            }
            return null;
        } catch (IOException var29) {
            logger.info("http处理异常 ");
            var29.printStackTrace();
            return null;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var28) {
                    logger.info("http处理异常 ");
                    var28.printStackTrace();
                }
            }

        }
    }

    
    
    public JSONObject request(String url, String para) {
        HttpGet httpGet;
        if (StringUtils.isEmpty(url)) {
            return null;
        } else {
            if (StringUtils.isEmpty(para)) {
                httpGet = new HttpGet(url);
            } else {
                httpGet = new HttpGet(url + "?" + para);
            }
            return this.getResult(httpGet, "");
        }
    }
    
    public JSONObject requestPost(String url, List<NameValuePair> list) {
    	try {
    		HttpPost post = new HttpPost(url);
        	post.addHeader("Content-type", "application/json; charset=utf-8");
        	post.setHeader("Accept", "application/json");
        	if(list != null && list.size() != 0) {
        		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, UTF_8);
        		post.setEntity(entity);
        		return this.getResult(post, "");
        	}
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    
    public JSONObject getResult(HttpRequestBase request, String param) {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(600000).setConnectionRequestTimeout(600000).setSocketTimeout(600000).build();
        request.setConfig(config);
        try {
            return this.getCommonResult(request, param);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getResult(String url, String param) {
        JSONObject json = new JSONObject();
        String body;
        HttpClient httpClient = null;
        HttpPost method = null;
        if (url != null) {
            httpClient = new DefaultHttpClient();
            method = new HttpPost(url);
        }
        if (method != null & param != null && !"".equals(param.trim())) {
            try {
                method.addHeader("Content-type", "application/json; charset=utf-8");
                method.setHeader("Accept", "application/json");
                method.setEntity(new StringEntity(param, Charset.forName("UTF-8")));
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

    public ArrayList<NameValuePair> covertParams2NVPS(JSONObject paraValue) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        Iterator<String> pv = paraValue.keySet().iterator();
        while(pv.hasNext()) {
            String key = pv.next().toString();
            pairs.add(new BasicNameValuePair(key, paraValue.getString(key)));
        }
        return pairs;
    }


	public Map<String, String> getHeader() {
		return header;
	}
    
}