package org.demoexm.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.demoexm.core.contants.ControllerContants;

/**
 * 发送请求
 * @author xiongxiong
 * @date 2016年11月24日 下午2:46:05
 */
public class HttpUtil {
	/**
	 * 发送post请求
	 * @param url
	 *      发送请求的 URL
	 * @param params 
	 *       请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param charset
	 *       设置字符(如 UTF-8)
	 * @return
	 *       远方资源返回的数据
	 * @author xiongxiong
	 * @date 2016年11月24日 下午3:33:47
	 */
	public static String sendPost(String url,String params,String charset){
		String str = null;
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpClient = HttpClients.createDefault(); 
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);  
		try {
			if (params != null) {
				StringEntity entity = new StringEntity(params, charset);
				entity.setContentEncoding(charset);
				entity.setContentType("application/x-www-form-urlencoded");
				httppost.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(httppost);
			if (result.getStatusLine().getStatusCode() == 200) {
				str = EntityUtils.toString(result.getEntity());
			}else{
				str = EntityUtils.toString(result.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
                httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}		
		return str;
	}
	
	/**
	 * 发送get请求
	 * @param url
	 * @return
	 *
	 * @author xiongxiong
	 * @date 2016年11月24日 下午3:41:20
	 */
	public static String sendGet(String url){
		String str = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(url);  
            // 执行get请求.    
            CloseableHttpResponse response = httpClient.execute(httpget);  
            try { 
            	if (response.getStatusLine().getStatusCode() == 200) {
    				str = EntityUtils.toString(response.getEntity());
    			}else{
    				str = EntityUtils.toString(response.getEntity());
    			} 
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } 
        return str;
	}
	
	/**获取请求Body
	 * 
	 * @param request
	 * @return
	 * @author : chewneixian 陈惟鲜
	 * @create_date 2016年12月6日 下午12:54:07
	 */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(ControllerContants.CHARSET_UTF8)));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
