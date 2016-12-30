package org.demoexm.core.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.demoexm.core.contants.CommonConstant;
import org.demoexm.core.contants.ControllerContants;
import org.springframework.stereotype.Service;


@Service
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);

	/**
	 * 
	 * @param url
	 * @return
	 */
	public String sendHttpGet(String url) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(CommonConstant.HTTP_TIMEOUT_MILLS_5000)
				.setConnectTimeout(CommonConstant.HTTP_TIMEOUT_MILLS_5000)
				.build();
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);
		String responseContent = null; // 返回
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, ControllerContants.CHARSET_UTF8);
			}
		} finally {
			try {
				if (response != null)
					response.close();

			} catch (Exception e) {
				logger.error(
						"类HttpClientUtil的sendHttpGet方法CloseableHttpResponse对象关闭时出现异常,异常信息："
								+ e.getMessage(), e);
			} finally {
				try {
					if (client != null)
						client.close();
				} catch (IOException e) {
					logger.error(
							"类HttpClientUtil的sendHttpGet方法CloseableHttpClient对象关闭时出现异常:IOException,异常信息："
									+ e.getMessage(), e);
				}
			}
		}
		return responseContent;
	}

	/**
	 * 
	 * @param sendurl
	 * @param data
	 * @return
	 */
	public String sendHttpPostJson(String sendurl, String data)
			throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(CommonConstant.HTTP_TIMEOUT_MILLS_5000)
				.setConnectTimeout(CommonConstant.HTTP_TIMEOUT_MILLS_5000)
				.build(); //
		HttpPost post = new HttpPost(sendurl);
		post.setConfig(requestConfig);
		StringEntity strEntity;
		strEntity = new StringEntity(data, ContentType.APPLICATION_JSON);//
		post.setEntity(strEntity);//
		String responseContent = null; //
		CloseableHttpResponse response = null;
		try {
			response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, ControllerContants.CHARSET_UTF8);

			}
		} finally {
			try {
				if (response != null)
					response.close();

			} catch (Exception e) {
				logger.error(
						"类HttpClientUtil的sendHttpPostJson方法CloseableHttpResponse对象关闭时出现异常,异常信息："
								+ e.getMessage(), e);
			} finally {
				try {
					if (client != null)
						client.close();
				} catch (Exception e) {
					logger.error(
							"类HttpClientUtil的sendHttpPostJson方法CloseableHttpClient对象关闭时出现异常,异常信息："
									+ e.getMessage(), e);
				}
			}
		}
		return responseContent;
	}

	private class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/*
	 * get方式请求服务器(https协议)
	 */
	public String sendHttpsGet(String url)
			throws Exception {
		HttpsURLConnection con = null;
		BufferedReader reader = null;
		DataOutputStream out = null;
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());

			@SuppressWarnings("restriction")
			URL console = new URL(null, url,
					new sun.net.www.protocol.https.Handler());
			con = (HttpsURLConnection) console.openConnection();
			X509TrustManager xtm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}

				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}
			};

			TrustManager[] tm = { xtm };

			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tm, null);
			con.setSSLSocketFactory(ctx.getSocketFactory());
			con.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});

			// 读取响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream(), ControllerContants.CHARSET_UTF8));
			String temp = null;
			StringBuffer buffer = new StringBuffer();
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
			}
			return buffer.toString();
		} catch (MalformedURLException e) {
			logger.error(
					"类HttpClientUtil的sendHttpsGet方法SSLContext对象初始化时出现异常,异常信息："
							+ e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(
					"类HttpClientUtil的sendHttpsGet方法SSLContext对象初始化时出现异常,异常信息："
							+ e.getMessage(), e);
		} catch (IOException e) {
			logger.error(
					"类HttpClientUtil的sendHttpsGet方法SSLContext对象初始化时出现异常,异常信息："
							+ e.getMessage(), e);
		} finally {
			if (reader != null)
				reader.close();
			if (con != null)
				con.disconnect();
		}
		return null;

	}

	/**
	 * post方式请求服务器(https协议)
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public String sendHttpsPostJson(String url, byte[] data)
			throws Exception {

		HttpsURLConnection con = null;
		BufferedReader reader = null;
		DataOutputStream out = null;
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());

			@SuppressWarnings("restriction")
			URL console = new URL(null, url,new sun.net.www.protocol.https.Handler());
			con = (HttpsURLConnection) console.openConnection();
			con.setSSLSocketFactory(sc.getSocketFactory());
			con.setHostnameVerifier(new TrustAnyHostnameVerifier());
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST"); 
			con.connect();
			out = new DataOutputStream(con.getOutputStream());
			out.write(data);
	//		out.writeBytes(sendjson.toString());
			// 刷新、关闭
			out.flush();
			out.close();
			// 读取响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream(), ControllerContants.CHARSET_UTF8));
			String temp = null;
			StringBuffer buffer = new StringBuffer();
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
			}
			return buffer.toString();
		} catch (MalformedURLException e) {
			logger.error(
					"类HttpClientUtil的sendHttpsPostJson方法SSLContext对象初始化时出现异常,异常信息："
							+ e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(
					"类HttpClientUtil的sendHttpsPostJson方法SSLContext对象初始化时出现异常,异常信息："
							+ e.getMessage(), e);
		} catch (IOException e) {
			logger.error(
					"类HttpClientUtil的sendHttpsPostJson方法SSLContext对象初始化时出现异常,异常信息："
							+ e.getMessage(), e);
		} finally {
			if (out != null)
				out.close();
			if (reader != null)
				reader.close();
			if (con != null)
				con.disconnect();
		}
		return null;
	}
	
	
	public static String transVoToParamforGet(Object obj) throws Exception{
		String paramStr = "";
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        
        String fieldName = null;
        String fieldNameUpper = null;
        Method getMethod = null;
        String value = null;
        for (int i = 0; i < fields.length; i++)
        {
            fieldName = fields[i].getName();
            fieldNameUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            getMethod = clazz.getMethod("get" + fieldNameUpper);
            value = (String)getMethod.invoke(obj);
            if (value != null)
            {
                // GET请求，进行UTF-8编码
                sb.append("&").append(fieldName).append("=").append(URLEncoder.encode(value, ControllerContants.CHARSET_UTF8));
            }
        }
        
        if (!sb.equals(""))
        {
            paramStr = sb.toString().replaceFirst("&", "?");
        }
        return paramStr;
	}
 
	
	/**
	 * 发送微信永久素材
	 * @param filepath
	 * @param type  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param url url中需包含交互token与 type
	 * @param title 文件标题
	 * @param introduction  文件描述
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 * @author zhangfeng
	 * @date 2016年12月2日 下午5:35:03
	 */
	public   String sendWeChatMaterial( String filepath, String type, String url,String title,String introduction) 
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filepath);
		
	     //上传素材
		URL urlObj = new URL(url);
	    //连接
	    HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	    String result = null;
	    con.setDoInput(true);
	    con.setRequestMethod("POST");  
	    con.setDoOutput(true);

	    con.setUseCaches(false); // post方式不能使用缓存

	    // 设置请求头信息
	    con.setRequestProperty("Connection", "Keep-Alive");
	    con.setRequestProperty("Charset", ControllerContants.CHARSET_UTF8);
	    // 设置边界
	    String BOUNDARY = "----------" + System.currentTimeMillis();
	    con.setRequestProperty("Content-Type", "multipart/form-data; boundary="  + BOUNDARY);

	    // 请求正文信息
	    // 第一部分：
	    StringBuilder sb = new StringBuilder();
	    sb.append("--"); // 必须多两道线
	    sb.append(BOUNDARY);
	    sb.append("\r\n");
	    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""   + file.getName() + "\"\r\n");
	    sb.append("Content-Type:application/octet-stream\r\n\r\n");
	    byte[] head = sb.toString().getBytes(ControllerContants.CHARSET_UTF8);
	    // 获得输出流
	    OutputStream out = new DataOutputStream(con.getOutputStream());
	    // 输出表头
	    out.write(head);

	    // 文件正文部分
	    // 把文件已流文件的方式 推入到url中
	    DataInputStream in = new DataInputStream(new FileInputStream(file));
	    int bytes = 0;
	    byte[] bufferOut = new byte[1024];
	    while ((bytes = in.read(bufferOut)) != -1) {
	        out.write(bufferOut, 0, bytes);
	    }
	    in.close();
	    // 结尾部分
	   // byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(ControllerContants.CHARSET_UTF8);// 定义最后数据分隔线
	    byte[] foot =    ("\r\n--" + BOUNDARY + "\r\n"
				+"Content-Disposition: form-data; name=\"description\";\r\n\r\n"
				+String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title,introduction)+"\r\n--"
				+ BOUNDARY + "--\r\n").getBytes(ControllerContants.CHARSET_UTF8);
	    out.write(foot);
	    out.flush();
	    out.close();
	    StringBuffer buffer = new StringBuffer();
	    BufferedReader reader = null;
	    try {
	        // 定义BufferedReader输入流来读取URL的响应
	        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        if (result == null) {
	            result = buffer.toString();
	        }
	       
	    } catch (IOException e) {
			logger.error("类HttpClientUtil的sendWeChatMaterial方法发送POST请求出现异常！"+ e.getMessage(), e);
	    } finally {
	        if (reader != null) {
	            reader.close();
	        }
	        if(null != out){
	        	out.close();
	        }
	        if(null != in){
	        	in.close();
	        }
	        
	    }
	    return result;
	}
 
}
