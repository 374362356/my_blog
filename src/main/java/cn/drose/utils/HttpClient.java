package cn.drose.utils;

import com.qiniu.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class HttpClient {
    /**
     * 发送post请求 json
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendPostJson(String url, String param) {
        //创建client和post对象
        HttpPost post = new HttpPost(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000).build();
        //json形式
        post.addHeader("content-type", "application/json;charset=utf-8");
        post.addHeader("accept", "application/json");
        //json字符串以实体的实行放到post中
        post.setEntity(new StringEntity(param, Charset.forName("utf-8")));
        post.setConfig(requestConfig);
        HttpResponse response = null;
        try {
            //获得response对象
            response = httpclient.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = "";
        try {
            //获得字符串形式的结果
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("http请求返回参数-》"+result);
        return result;
    }

    public static String sendPutJson(String url, String param) {
        HttpPut httpPut = new HttpPut(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000).build();
        httpPut.setEntity(new StringEntity(param, Charset.forName("utf-8")));
        httpPut.addHeader("content-type", "application/json;charset=utf-8");
        httpPut.addHeader("Authorization", "token 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");
        httpPut.addHeader("Accept", "application/vnd.github.v3+json");
        httpPut.setConfig(requestConfig);
        System.out.println("Executing request " + httpPut.getRequestLine());

        ResponseHandler responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();

            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();

                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        Object result = null;
        try{
            result = httpclient.execute(httpPut, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("http请求返回参数-》"+result);
        return result.toString();
    }

}
