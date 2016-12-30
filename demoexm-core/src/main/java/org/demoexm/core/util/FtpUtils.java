package org.demoexm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

/**
 * FTP工具类
 * @author yanglei
 * @date 2016年7月20日 上午10:17:30
 *
 */
public class FtpUtils {
	
	/**
	 * 从FTP服务器下载文件
	 * @param server FTP服务器IP
	 * @param port FTP服务器端口
	 * @param username 登录用户名
	 * @param password 密码
	 * @param ftpFolder FTP服务器文件夹路径
	 * @param localFolder 本地存储文件夹路径
	 * @param fileName 文件名
	 * @return
	 */
	public static boolean getFile(String server,int port, String username, String password, 
			String ftpFolder, String localFolder, String fileName) 
	{
		boolean rtnFlag = false;
		FTPClient ftp = new FTPClient();
		FileOutputStream fos = null;

		try {
			System.out.println();
			System.out.println(" connecting FTP server " + server + " : " + port + " ...");
			// 连接FTP服务器
			ftp.connect(server, port);
			System.out.println(" connected -------- " + server + " : " + port);
			
			// 登陆FTP服务器
			if (!ftp.login(username, password)) {
				throw new Exception("登录FTP服务器失败");
			}
			System.out.println(" user login ------- " + username + " : " + password);
			
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			
			// 设置下载目录
			if (!ftp.changeWorkingDirectory(ftpFolder)) {
				throw new Exception("FTP服务器上不存在文件夹 " + ftpFolder);
			}
			
			// 创建文件夹
			File fileFold = new File(localFolder);
			if (!fileFold.exists()) {
				fileFold.mkdirs();
			}
			File file = new File(localFolder, fileName);
			fos = new FileOutputStream(file);
			
			// 下载文件
			if (!ftp.retrieveFile(fileName, fos)) {
				fos.close();
				file.delete();
				return false;
			}
			ftp.logout();
			System.out.println(" recv file -------- " + localFolder + "/" + fileName);

			rtnFlag = true;
		} catch (Exception e) {
			System.out.println("与FTP服务器通讯出错"+ e);
		} finally {
			try{fos.close();}catch(IOException i){
				System.out.println("关闭输出流发生异常"+ i);
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					System.out.println("关闭FTP连接发生异常"+ f);
				}
			}
		}
		return rtnFlag;
	}
	
	/**
	 * 上传文件至FTP服务器
	 * @param server FTP服务器IP
	 * @param port FTP服务器端口
	 * @param username 登录用户名
	 * @param password 密码
	 * @param ftpFolder FTP服务器上传文件夹路径
	 * @param localFolder 本地存储文件夹路径
	 * @param fileName 文件名
	 * @return
	 */
	public static boolean sendFile(String server, int port, String username, String password, 
			String ftpFolder, String localFolder, String fileName)
	{
		boolean rtnFlag = false;
		FTPClient ftp = new FTPClient();
		FileInputStream fis = null;

		try {
			System.out.println(" connecting FTP server " + server + " : " + port + " ...");
			// 连接FTP服务器
			ftp.connect(server,port);
			System.out.println(" connected -------- " + server + " : " + port);
			
			// 登陆FTP服务器
			if (!ftp.login(username, password)) {
				System.out.println("登录FTP服务器" + server + "失败");
				throw new Exception("登录FTP服务器" + server + "失败");
			}
			System.out.println(" user login ------- " + username + " : " + password);
			
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();

			File srcFile = new File(localFolder, fileName);
			fis = new FileInputStream(srcFile);
			 
			// 设置上传目录
            if (null != ftpFolder && 0 < ftpFolder.trim().length()) 
            {
            	//如果不存在目录，则创建目录
            	if (!ftp.changeWorkingDirectory(ftpFolder)) {
            		ftp.makeDirectory(ftpFolder);
            	}
            	ftp.changeWorkingDirectory(ftpFolder);
            	
            } else {
            	System.out.println("FTP服务器上传文件夹不能为空");
            	throw new Exception("FTP服务器上传文件夹不能为空");
            }
            
			// 上传文件
			if (!ftp.storeFile(fileName, fis)) {
				return false;
			}
			ftp.logout();
			System.out.println(" store file ------- " + ftpFolder + "/" + fileName);

			rtnFlag = true;
		} catch (Exception e) {
			System.out.println("与FTP服务器通讯出错"+ e);
		} finally {
			try{fis.close();}catch(IOException i){
				System.out.println("关闭输出流发生异常"+ i);
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					System.out.println("关闭FTP连接发生异常"+ f);
				}
			}
		}
		return rtnFlag;
	}
	
	public static void main(String[] args) {
		
		FtpUtils.sendFile("ftp.sinosafe.com.cn", 
				21, 
				"ucpay", 
				"ucpay2015", 
				"/ucpay/jingronghui/test", 
				"D:/YANGLEI/ftp/", 
				"test_file.txt");
	}
	
}

