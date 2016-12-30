/**
 * @Description
 * @author xguan 2016年6月27日
 */
package org.demoexm.core.util;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

/**
 * @author gxlly
 *
 */
public class ObjectCloneUtil
{
    private static Logger log = Logger.getLogger(ObjectCloneUtil.class);
    
    /**
     * @descript 相同类对象，将源对象中的非空属性赋值给目标对象
     * @param origin 源对象
     * @param destination 目标对象
     * @author gxlly
     *
     */
    public static <T> void mergeSameObject(T origin, T destination)
    {
        if (origin == null || destination == null)
            return;
        if (!origin.getClass().equals(destination.getClass()))
            return;
        
        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            try
            {
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value)
                {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            }
            catch (Exception e)
            {
                log.error(origin.getClass().getName() + "同类对象赋值失败" + e.getMessage(), e);
            }
        }
    }
    
    /**
     * @descript 不同类对象，将源对象中的非空属性赋值给目标对象
     * @param origin 源对象
     * @param destination 目标对象
     * @author gxlly
     *
     */
    public static <T> void mergeDiffObject(T origin, T destination)
    {
        Field[] originField = origin.getClass().getDeclaredFields();
        Field[] destinationField = destination.getClass().getDeclaredFields();
        for (int i = 0; i < originField.length; i++)
            try
            {
                for (int j = 0; j < destinationField.length; j++)
                {
                    
                    if (originField[i].getName().equals(destinationField[j].getName()))
                    {
                        originField[i].setAccessible(true);
                        destinationField[j].setAccessible(true);
                        destinationField[j].set(destination, originField[i].get(origin));
                    }
                }
            }
            catch (Exception e)
            {
                log.error("源对象:" + origin.getClass().getName() + ",目标对象：" + destination.getClass().getName()
                    + "不同对象赋值失败" + e.getMessage(), e);
                
            }
        
    }
}
