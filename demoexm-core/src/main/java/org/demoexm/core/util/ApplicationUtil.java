package org.demoexm.core.util;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/***获取javabean工具类
 * 
 * @author : chewneixian 陈惟鲜
 * @create_date 2016年11月28日 下午1:07:03
 *
 */
public class ApplicationUtil implements ApplicationContextAware{
	
	private static Logger logger = Logger.getLogger(ApplicationUtil.class);

    private static ApplicationContext applicationContext;
 
    //实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
    public void setApplicationContext(ApplicationContext applicationContext) {
    	ApplicationUtil.applicationContext = applicationContext;
    }
    
    //取得存储在静态变量中的ApplicationContext.
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }
     
    //从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        if (applicationContext == null){
        	return null;
        }
        return (T) applicationContext.getBean(name);
    }
 
     
    //从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
    //如果有多个Bean符合Class, 取出第一个.
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        @SuppressWarnings("rawtypes")
                Map beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps!=null && !beanMaps.isEmpty()) {
            return (T) beanMaps.values().iterator().next();
        } else{
            return null;
        }
    }
 
    private static void checkApplicationContext() {
        if (applicationContext == null) {
           logger.warn("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
 }
