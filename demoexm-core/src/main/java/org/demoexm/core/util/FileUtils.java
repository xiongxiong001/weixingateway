package org.demoexm.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class FileUtils {
    /**
     *  MIME类型:application/octet-stream
     */
    public static final String HTML_HEAD_STREAM = "application/octet-stream";
    
    private static Logger logger = Logger.getLogger(FileUtils.class);

    /**
	 * 设置下载头部信息
	 * @param response HttpServletResponse响应
	 * @param exportFileName 输出的文件名称，如：a.xls
	 * @throws java.io.UnsupportedEncodingException 文件转码异常
	 */
	public static void setResponseHeader(HttpServletResponse response,
			 String exportFileName) throws UnsupportedEncodingException {
		response.setContentType(HTML_HEAD_STREAM);
		response.setHeader("Content-Disposition", "attachment;filename=\""+new String(exportFileName.getBytes("gb2312"),"iso8859-1")+"\"");
		response.setHeader("Cache-Control", "must-revalidate,post-check=0,pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", (System.currentTimeMillis()+1000));
	}
	
	/**
	 * 文件下载
	 * @param file         创建的文件对象 new File()
	 * @param response     HttpServletResponse响应
	 * @throws java.io.IOException 当文件不存在或者创建文件流时 会抛此异常
	 */
	public static void downloadFile(File file,HttpServletResponse response) throws IOException{
		downloadFile(file, null, response);
	}
	
	/**
	 * 文件下载
	 * @param file         创建的文件对象 new File()
	 * @param response     HttpServletResponse响应
	 * @param fileName     指定下载文件的名称
	 * @throws java.io.IOException 当文件不存在或者创建文件流时 会抛此异常
	 */
	public static void downloadFile(File file,String fileName,HttpServletResponse response) throws IOException{
		if(!file.exists()){
			throw new FileNotFoundException("file not found:"+file.getAbsolutePath());
		}
		BufferedInputStream bufferStream =new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[1024];
		int n;
		if(fileName!=null)
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("gb2312"), "iso-8859-1"));
		while(-1!=(n=bufferStream.read(buffer))){
			response.getOutputStream().write(buffer,0,n);
		}
		response.getOutputStream().flush();
		bufferStream.close();
	}
	
	/**
	 * 获取文件字节数组
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings("resource")
	public static byte[] getFileBytes(String fileName){
		try {
			File f = new File(fileName);
			FileInputStream  fis = new FileInputStream(f);        		
			ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
			BufferedInputStream in = new BufferedInputStream(fis);  
			int buf_size = 1024;  
			byte[] buffer = new byte[buf_size];  
			int len = 0;  
			while(-1 != (len = in.read(buffer,0,buf_size))){  
			    bos.write(buffer,0,len);  
			}  
			return bos.toByteArray();
		} catch (FileNotFoundException e) {
			logger.error("文件不存在,异常："+e.getMessage(),e);
		} catch (IOException e) {
			logger.error("IO异常："+e.getMessage(),e);
		}
		return null;
	}
	
	/**保存文件
	 * 
	 * @param filePath 保存文件全路径
	 * @param fileName 文件名称
	 * @param charset 字符编码
	 * @param content 内容信息
	 * @author : chewneixian 陈惟鲜
	 * @create_date 2016年9月9日 上午10:42:20
	 */
	public static void saveFile(String filePath, String fileName, String charset, String content){
		PrintWriter pw = null;
		File filename = new File(filePath+"/"+fileName);
		try {
			if (!filename.getParentFile().exists()){
				filename.getParentFile().mkdirs();
			}
			filename.createNewFile();
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), charset)));
			pw.write(content);
			pw.flush();
		} catch (Exception e) {
			logger.error("保存文件异常", e);
		}finally{
			pw.close();
		}
	}
	
	/**读取文件
	 * 
	 * @param filePath 路径
	 * @param fileName 文件名
	 * @param charset 字符编码
	 * @return
	 * @throws IOException
	 * @author : chewneixian 陈惟鲜
	 * @create_date 2016年9月19日 上午11:24:09
	 */
	public static String readFile(String filePath, String fileName, String charset ){
		File file=new File(filePath+"/"+fileName);
		StringBuffer content = new StringBuffer(100);
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(file),charset);
			bufferedReader=new BufferedReader(inputStreamReader);

			String line=null;
	        while((line=bufferedReader.readLine())!=null){
	        	content.append(line);
	        }
		} catch (Exception e) {
			logger.error("保存文件异常", e);
		}finally{
			try {
				inputStreamReader.close();
				bufferedReader.close();
			} catch (IOException e) {
			}
		}
        return content.toString();
	}
	
	/**
     * 计算文件的MD5摘要值 
     * @param file 文件路劲
     * @return 32位的MD5摘要
     */
    public static String getFileMD5(File file) {
      if (!file.isFile()){
        return null;
      }
      MessageDigest digest = null;
      FileInputStream in=null;
      byte buffer[] = new byte[1024];
      int len;
      try {
        digest = MessageDigest.getInstance("MD5");
        in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, 1024)) != -1) {
          digest.update(buffer, 0, len);
        }
        in.close();
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
      String bytes2hex03 = bytes2hex03(digest.digest());
      return bytes2hex03;
    }
    
    public static String bytes2hex03(byte[] bytes){  
        final String HEX = "0123456789abcdef";  
        StringBuilder sb = new StringBuilder(bytes.length * 2);  
        for (byte b : bytes){  
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt((b >> 4) & 0x0f));  
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt(b & 0x0f));  
        }  
        return sb.toString();  
    }
	
	public static void main(String[] args) {
		
	}
}
