package com.webserver.core;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 服务端环境信息
 * @author Gap
 *
 */
public class ServerContext {
	//服务端使用的协议版本
	public static String protocol="HTTP/1.1";
	
	//服务端使用的端口号
	public static int port=8080;
	
	//服务端解析URI时使用的字符集
	public static String URIEncoding="UTF-8";
	
	//服务端线程池中线程质量
	public static int maxThreads=150;
	
	private static Map<String, String> servletMapping = new HashMap<String, String>();
	static{
		init();
		initServletMapping();
	}
	/**
	 * 初始化Servlet映射
	 */
	private static void initServletMapping(){
		/*
		 * 加载conf/servlets.xml
		 * 将每个Servlet的url作为key,className作为value
		 * 用于初始化servletMapping
		 */
		/*servletMapping.put("/myweb/reg", "com.webserver.servlets.RegServlet");
		servletMapping.put("/myweb/login", "com.webserver.servlets.LoginServlet");*/
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new FileInputStream("./conf/servlets.xml"));
			Element root = document.getRootElement();
			List<Element> webList = root.elements("servlet");
			for(Element mm : webList){
				servletMapping.put(mm.attributeValue("url"), mm.attributeValue("className"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析conf/server.xml，将所有配置项用于初始化ServerContext对应属性
	 */
	private static void init(){
		/*
		 * 解析conf/server.xml文件，将根标签下的子标签
		 * <Connector>中各属性的值得到，并用于初始化对应的属性
		 */
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new FileInputStream("./conf/server.xml"));
			Element root = document.getRootElement();
			Element webList = root.element("Connector");
			protocol = webList.attributeValue("protocol");
			port = Integer.parseInt(webList.attributeValue("port"));
			URIEncoding = webList.attributeValue("URIEncoding");
			maxThreads = Integer.parseInt(webList.attributeValue("maxThreads"));
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	/**
	 * 根据请求获取对应的Servlet名字，若该请求没有任何Servlet则返回值为null
	 * @param uri
	 * @return
	 */
	public static String getServletName(String uri){
		return servletMapping.get(uri);
	} 
	public static void main(String[] args) {
		System.out.println(ServerContext.port);
		System.out.println(servletMapping);
		
	}
}
