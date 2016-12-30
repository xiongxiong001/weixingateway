/*package org.demoexm.core.util;

import java.util.Date;
*//**缓存工具类*//*
public class BaseCacheSingleton extends GeneralCacheAdministrator {     

	private static final long serialVersionUID = -4397192926052141162L;
//	private String keyPrefixPrefix; //关键字前缀字符，区别属于哪个模块
//	private int refreshPeriod = 10*24*60*60; //过期时间(单位为秒);负数为永不过期
	private int refreshPeriod = -1; //过期时间(单位为秒);负数为永不过期
	
	private static BaseCacheSingleton baseCacheSingleton = null;
	
	private BaseCacheSingleton(){
		
	}

	public static BaseCacheSingleton getInstance(){
		if (baseCacheSingleton == null){
			baseCacheSingleton = new BaseCacheSingleton();
		}
		return baseCacheSingleton;
	}

    *//**
     *     
     * put(添加被缓存的对象)
     * @param keyPrefix
     * @param value 
     * @return void
     * @createDate 2013-1-31 下午04:16:46
     * @since  1.0.0
     *//*
    public void put(String keyPrefix,Object value){    
        this.putInCache(keyPrefix,value);    
    }    

    *//**
     * remove(删除被缓存的对象)
     * @param keyPrefix 
     * @return void
     * @createDate 2013-1-31 下午04:17:06
     * @since  1.0.0
     *//*
    public void remove(String keyPrefix){    
        this.flushEntry(keyPrefix);    
    }    

    *//**
     * removeAll(删除指定日期所有被缓存的对象)
     * @param date 
     * @return void
     * @createDate 2013-1-31 下午04:17:29
     * @since  1.0.0
     *//*
    public void removeAll(Date date){    
        this.flushAll(date);    
    }           

    *//**
     * removeAll(删除所有被缓存的对象) 
     * @return void
     * @createDate 2013-1-31 下午04:18:01
     * @since  1.0.0
     *//*
    public void removeAll(){
        this.flushAll();    
    }

    *//**
     * get(获取被缓存的对象)
     * @param keyPrefix
     * @return
     * @throws Exception 
     * @return Object
     * @createDate 2013-1-31 下午04:18:45
     * @since  1.0.0
     *//*
    public Object get(String keyPrefix) throws Exception{    
        try{    
            return this.getFromCache(keyPrefix,this.refreshPeriod);    
        } catch (NeedsRefreshException e) {    
            this.cancelUpdate(keyPrefix);    
            e.printStackTrace();
            throw e;
        }      
    }            
}   
*/