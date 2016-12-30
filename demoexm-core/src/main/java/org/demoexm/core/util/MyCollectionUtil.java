package org.demoexm.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.demoexm.core.vo.PageVo;
/**对象填充类
 * 
 * @author 陈惟鲜
 * @date 2016年11月10日 下午12:51:04
 *
 */
public class MyCollectionUtil {
	/**map转换为对象
	 * 
	 * @param map map信息
	 * @param beanClass 对象类
	 * @return
	 * @throws Exception
	 * @author 陈惟鲜
	 * @date 2016年4月11日 上午11:05:40
	 */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null){
            return null;  
        }  
        Object obj = beanClass.newInstance();  
        Field[] fields = obj.getClass().getDeclaredFields();   
        for (Field field : fields) {    
            int mod = field.getModifiers();    
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            }    
            field.setAccessible(true);    
            field.set(obj, map.get(field.getName()));   
        }   
        return obj;    
    }  
    
    /**map转换为对象,包括对象父类全部赋值
	 * 
	 * @param map map信息
	 * @param beanClass 对象类
	 * @return
	 * @throws Exception
	 * @author 陈惟鲜
	 * @date 2016年4月11日 上午11:05:40
	 */
    public static Object mapToObjectAll(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null){
            return null;  
        }  
        Object obj = beanClass.newInstance();  
        // 获取所有父类的信息定义
        for(Class<?> clazz = obj.getClass(); clazz != Object.class ; clazz = clazz.getSuperclass()) {  
              Field[] fields = clazz.getDeclaredFields();   
	          for (Field field : fields) {    
	              int mod = field.getModifiers();    
	              if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
	                  continue;    
	              }    
	              field.setAccessible(true);    
	              field.set(obj, map.get(field.getName()));   
	          } 
        }  
   
        return obj;    
    }    
   
    /**对象转换为map
     * 
     * @param obj
     * @return
     * @throws Exception
     * @author 陈惟鲜
     * @date 2016年4月11日 上午11:05:59
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
        Map<String, Object> map = new HashMap<String, Object>();    
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
        return map;  
    }
    
    /**对象转换为map,b
     * 
     * @param obj
     * @return
     * @throws Exception
     * @author 陈惟鲜
     * @date 2016年4月11日 上午11:05:59
     */
    public static Map<String, Object> objectToMapAll(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
        Map<String, Object> map = new HashMap<String, Object>();       
        for(Class<?> clazz = obj.getClass(); clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            Field[] fields = clazz.getDeclaredFields();   
	          for (Field field : fields) {    
	              field.setAccessible(true);    
	              map.put(field.getName(), field.get(obj));  
	          } 
      }
        return map;  
    }
    
    public static void main(String[] args) {
    	PageVo pageVo = new PageVo();
    	pageVo.setCount(50);
    	pageVo.setPageSize(500);
    	try {
    		Map map = objectToMap(pageVo);
			System.out.println(map);
			PageVo pageVo2 = (PageVo) mapToObject(map, PageVo.class);
			System.out.println(pageVo2.getPageSize());
			PageVo pageVo3 = (PageVo) mapToObjectAll(map, PageVo.class);
			System.out.println(pageVo3.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
