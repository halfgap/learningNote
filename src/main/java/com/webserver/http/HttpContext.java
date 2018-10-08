package com.webserver.http;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/**
 * Http协议相关信息定义
 * @author Gap
 *
 */
public class HttpContext {
	public static final int CR = 13;
	public static final int LF = 10;
	/*
	 * 状态代码与描述的关系
	 * key:状态代码
	 * value:状态描述
	 */
	private static Map<Integer,String> statusCode_Reason_Mapping = new HashMap<Integer, String>();
	
	/*
	 * 介质类型的映射
	 * key:资源后缀名
	 * value:Content-Type对应的值
	 */
	private static Map<String, String> mimeMapping = new HashMap<String, String>();
	
	static{
		initStatusCodeReasonMapping();
		initMimeMapping();
	}
	/**
	 * 初始化介质类型
	 */
	private static void initMimeMapping(){
		/*mimeMapping.put("html", "text/html");
		mimeMapping.put("css", "text/css");
		mimeMapping.put("js", "application/javascript");
		mimeMapping.put("png", "image/png");
		mimeMapping.put("jpg", "image/jpeg");
		mimeMapping.put("gif", "image/gif");*/
		
		/*
		 * 通过解析conf/web.xml文件来完成初始化操作
		 * 
		 * 1将web.xml文档中根标签下所有名为：
		 * <mime-mapping>的子标签解析出来
		 * 
		 * 2并将其对应的两个子标签：
		 * <extension>中间的文本作为key
		 * <mime-type>中剑的文本作为value
		 * 来初始化mimeMapping这个Map
		 */
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new FileInputStream("./conf/web.xml"));
			Element root = document.getRootElement();
			List<Element> webList = root.elements("mime-mapping");
			for(Element mm : webList){
				String ex = mm.elementText("extension");
				String mt = mm.elementText("mime-type");
				mimeMapping.put(ex, mt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化状态代码与对应描述的关系
	 */
	private static void initStatusCodeReasonMapping(){
		statusCode_Reason_Mapping.put(200,"OK");
		statusCode_Reason_Mapping.put(201,"Created");
		statusCode_Reason_Mapping.put(202,"Accepted");
		statusCode_Reason_Mapping.put(203,"No Content");
		statusCode_Reason_Mapping.put(301,"Moved Permanently");
		statusCode_Reason_Mapping.put(302,"Moved Temporarily");
		statusCode_Reason_Mapping.put(304,"Not Modified");
		statusCode_Reason_Mapping.put(400,"Bad Request");
		statusCode_Reason_Mapping.put(401,"Unauthorized");
		statusCode_Reason_Mapping.put(403,"Forbidden");
		statusCode_Reason_Mapping.put(404,"Not Found");
		statusCode_Reason_Mapping.put(500,"Internal Server Error");
		statusCode_Reason_Mapping.put(501,"Not Implemented");
		statusCode_Reason_Mapping.put(502,"Bad Gateway");
		statusCode_Reason_Mapping.put(503,"Service Unavailable");
	}
	
	/**
	 * 根据给定的状态代码获取对应的状态描述
	 * @param statusCode
	 * @return
	 */
	public static String getStautsReason(int statusCode){
		return statusCode_Reason_Mapping.get(statusCode);
	}
	
	/**
	 * 根据资源后缀获取对应的介质类型
	 * @param ext
	 * @return
	 */
	public static String getContentType(String ext){
		return mimeMapping.get(ext);
	}
	
	//测试用
	public static void main(String[] args) {
		String line =getContentType("potm");
		System.out.println(line);
		
	}
}
