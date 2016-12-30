package org.demoexm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**读取config.properties文件
 * 
 * @author chenweixian
 *
 */
public class ConfigPropertiesUtil extends PropertyPlaceholderConfigurer {
	/**信息内容
	 * 
	 */
    private static Map<String, String> ctxPropertiesMap; 
 
    /**将配置文件内容放入到配置中
     * 
     */
    @Override 
    protected void processProperties( ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException { 
        super.processProperties(beanFactoryToProcess, props); 
        ctxPropertiesMap = new HashMap<String, String>(); 
        for (Object key : props.keySet()) { 
            String keyStr = key.toString(); 
            String value = props.getProperty(keyStr); 
            ctxPropertiesMap.put(keyStr, value); 
        }
    } 
 
    /**获取配置信息
     * 
     * @param name
     * @return
     * @author 陈惟鲜
     * @date 2016年6月22日 上午11:17:34
     */
    public static String getProp(String name) { 
        return ctxPropertiesMap.get(name); 
    } 
}
