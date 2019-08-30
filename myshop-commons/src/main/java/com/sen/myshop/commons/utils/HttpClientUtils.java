package com.sen.myshop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 01:45
 * @Description:
 */
public class HttpClientUtils {

    public static final String REQUEST_GET = "get";
    public static final String REQUEST_POST = "post";
    public static String doGet(String url) {
        return createRequest(REQUEST_GET, url,null,null);
    }

    public static String doGet(String url,String cookie) {
        return createRequest(REQUEST_GET, url,cookie,null);
    }

    public static String doPost(String url) {
        return createRequest(REQUEST_POST, url,null,null);
    }

    public static String doPost(String url,String cookie) {
        return createRequest(REQUEST_POST, url,cookie,null);
    }

    public static String doPost(String url,String cookie,List<BasicNameValuePair> params) {
        return createRequest(REQUEST_POST, url,cookie,params);
    }

    /**
     * 创建请求参数
     * @param requestMethod 请求的类型GET/POST
     * @param url
     * @param cookie
     * @param params
     * @return
     */
    private static String createRequest(String requestMethod, String url, String cookie, List<BasicNameValuePair> params) {
        //创建默认的httpClient，类似于打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = null;
        HttpPost httpPost = null;
        try {
            if (REQUEST_GET.equals(requestMethod)) {
                //设置get方法的请求地址
                httpGet = new HttpGet(url);
                httpGet.setHeader("Cookie", cookie);
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
                httpGet.setHeader("Connection","keep-alive");
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);

            } else if (REQUEST_POST.equals(requestMethod)) {
                httpPost = new HttpPost(url);
                //设置Post请求头部信息
                httpPost.setHeader("Cookie", cookie);
                httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
                httpPost.setHeader("Connection","keep-alive");
                if (params != null && params.size() > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                }
                CloseableHttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
