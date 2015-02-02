package com.wyl.gittest.common;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * 属性配置文件操作类
 * 读取jar文件外的属性配置文件
 * @author yonglin_wang
 */
public class Configuration {
	private static Configuration instance;
	public static synchronized Configuration getInstance(String filename){
		if(instance==null){
			instance = new Configuration(filename);
		}
		return instance;
	}
	
	private Properties properties;
	private Reader reader;
	//private FileOutputStream fos;
	
	/**
	 * 初始化Configuration类
	 */
	private Configuration(){
		properties = new Properties();
	}
	/**
	 * 初始化Configuration类
	 * @param filename	配置文件的文件名（前置加上斜杠，如："/config.properties"）
	 */
	private Configuration(String filename){
		properties = new Properties();
		try {
			String appPath = getAppPath(Configuration.class);
			reader = new FileReader(appPath+filename);
			//jar包内部加载
			//inputStream = Object.class.getResourceAsStream(filename);
			properties.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取配置文件中对应key的值
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		if(properties.containsKey(key)){
			return properties.getProperty(key);
		}else{
			return "";
		}
	}
	/**
	 * 获取指定的配置文件中对应key的值
	 * @param filename	配置文件的文件名（前置加上斜杠，如："/config.properties"）
	 * @param key
	 * @return
	 */
	public String getValue(String filename,String key){
		try {
			String appPath = getAppPath(Configuration.class);
			reader = new FileReader(appPath+filename);
			//jar包内部加载
			//inputStream = Object.class.getResourceAsStream(filename);
			properties.load(reader);
			reader.close();
			if(properties.containsKey(key)){
				return properties.getProperty(key);
			}else{
				return "";
			}
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * getAppPath需要一个当前程序使用的Java类的class属性参数，它可以 返回打包过的
	 * Java可执行文件（jar，war）所处的系统目录名或非打包Java程 序所处的目录
	 * @param cls为Class类型
	 * @return 返回值为该类所在的 Java程序运行的目录
	 */
	@SuppressWarnings("rawtypes")
	public static String getAppPath(Class cls) {
		ClassLoader loader = cls.getClassLoader();
		String clsName = cls.getName() + ".class";
		Package pack = cls.getPackage();
		String path = "";
		if (pack != null){
			String packName = pack.getName();
			clsName = clsName.substring(packName.length() + 1);
			if (packName.indexOf(".") < 0)
				path = packName + "/";
			else {
				int start = 0, end = 0;
				end = packName.indexOf(".");
				while (end != -1) {
					path = path + packName.substring(start, end) + "/";
					start = end + 1;
					end = packName.indexOf(".", start);
				}
				path = path + packName.substring(start) + "/";
			}
		}
		java.net.URL url = loader.getResource(path + clsName);
		String realPath = url.getPath();
		int pos = realPath.indexOf("file:");
		boolean isPackage = false;//是否jar包环境
		if (pos > -1){
			realPath = realPath.substring(pos + 5);
			isPackage = true;
		}
		pos = realPath.indexOf(path + clsName);
		realPath = realPath.substring(1, pos-1);
		if(isPackage){
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
		}
		return realPath;
	}
	
	/**
	 * 清除properties文件中所有的key和其值
	 
	public void clear(){
		properties.clear();
	}*/
	/**
	 * 改变或添加一个key的值;
	 * 当key存在于properties文件中时该key的值被value所代替,
	 * 当key不存在时，该key的值是value
	 * @param key
	 * @param value
	 
	public void setValue(String key,String value){
		properties.setProperty(key, value);
	}*/
	/**
	 * 将更改后的文件数据存入指定的配置文件中，该文件可以事先不存在
	 * @param filename	配置文件的文件名（前置加上斜杠，如："/config.properties"）
	 * @param description	对该文件的描述
	 
	public void saveFile(String filename,String description){
		try {
			String url = Object.class.getResource(filename).toString().replace("file:/","");
			fos = new FileOutputStream(new File(url));
			properties.store(fos, description);
			fos.close();
		} catch (IOException e) {
		}
	}
	 * @throws ParseException */
	
	/*public static void main(String[] args) {
//		Configuration configuration = new Configuration("/config.properties");
//		System.out.println(configuration.getValue("username"));
//		configuration.setValue("min", "12");
//		configuration.setValue("max", "100");
//		configuration.saveFile("/config.properties", "测试保存");
		
		// 一般推荐用此方法  
		// 获取当前ClassPath的绝对URI路径
//		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
//		// 获取当前类文件的URI目录  
//		System.out.println(Object.class.getResource(""));
//		// 获取当前的ClassPath的绝对URI路径。  
//		System.out.println(Object.class.getResource("/"));
//		// 获取当前ClassPath的绝对URI路径
//		System.out.println(ClassLoader.getSystemResource(""));
//		System.out.println(Object.class.getResource("/config.properties"));
//		String aaa = Object.class.getResource("/config.properties").toString();
//		System.out.println(aaa);
//		String bbb = aaa.substring(aaa.indexOf(":")+1);
//		System.out.println(bbb);
//		String ccc = bbb.substring(bbb.indexOf(":")-1);
//		System.out.println(ccc);
//		String ddd = ccc.substring(0, ccc.indexOf("monitor")-1);
//		System.out.println(ddd);
//		String eee = ddd.substring(0, ddd.lastIndexOf("/"));
//		System.out.println(eee+"/conf/config.properties");
//		String ffff = "/config.properties";
//		System.out.println(ffff.substring(ffff.lastIndexOf("/")+1));
//		System.out.println(getAppPath(Configuration.class));
//		System.out.println(Object.class.getResource("/"));
//		System.out.println(Configuration.class.getResource("/"));
		getAppPath(Configuration.class);
	}*/
	
}
