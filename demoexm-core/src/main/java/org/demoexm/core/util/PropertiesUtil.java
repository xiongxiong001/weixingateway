package org.demoexm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**读取config.properties文件
 * 
 * @author chenweixian
 *
 */
public class PropertiesUtil {
	

	private String propertiesFileName = "/application.properties";
	private static PropertiesUtil configPropertiesUtil = null;
	private static Properties prop = null;

	private PropertiesUtil() {
		loadProperties();
	}
	/**获取的实例**/
	public static PropertiesUtil getInstance(){
		if (configPropertiesUtil == null){
			configPropertiesUtil = new PropertiesUtil();
		}
		return configPropertiesUtil;
	}

	public String getProp(String key) {
		if (prop == null){
			loadProperties();
		}
		return prop.getProperty(key);
	}
	/**加载信息
	 * 
	 */
	private void loadProperties() {
		if (configPropertiesUtil == null) {
			try {
				prop = new Properties();
				InputStream in = this.getClass().getResourceAsStream(propertiesFileName);
				prop.load(in);
			} catch (Exception e) {
				System.err.println("读取"+ propertiesFileName +"异常：" + e.toString());
			}
		}
	}
	/**加载配置文件url*/
	public void initLoad(String url){
		try {
			InputStream in = new FileInputStream(new File(url));
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String getPropertiesFileName() {
		return propertiesFileName;
	}
	public void setPropertiesFileName(String propertiesFileName) {
		this.propertiesFileName = propertiesFileName;
	}
}
