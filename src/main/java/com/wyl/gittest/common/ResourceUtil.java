package com.wyl.gittest.common;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @ClassName: ResourceUtil
 * @Description: 属性配置工具类
 * @author yonglin_wang
 * @date 2015-1-5 下午9:33:22
 */
public class ResourceUtil {
	
	/********************************************************************
	 **************** 获取属性配置文件值-方案一 ***************************
	 ********** 优点：开发环境和生产环境均能正常使用 ***********************
	 ********** 缺点：如果打jar包，配置文件在jar包中，不能提取出来 **********
	 ********** 适用于webapp环境中****************************************
	 ********************************************************************/
	private static Map<String,String> map = new HashMap<String, String>();
	
	static{
		ResourceBundle rb = ResourceBundle.getBundle("config");
		for (String key : rb.keySet()) {
			map.put(key, rb.getString(key));
		}
	}
	
	public static String getInnerResource(String key){
		return map.get(key);
	}
	
	
	/********************************************************************
	 **************** 获取属性配置文件值-方案二 ***************************
	 ********** 优点：开发环境和生产环境均能正常使用，并解决了方案一的缺点***
	 ********************************************************************/
	private static Configuration configuration = Configuration.getInstance("/config.properties");
	
	public static String getCustomResource(String key){
		return configuration.getValue(key);
	}
	
}
