package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.webserver.core.ServerContext;

/**
 * 请求对象
 * HttpRequest的每一个实例用于表示客户端发送过来的一个具体的请求内容
 * 一个请求内容由三部分构成：
 * 请求行，消息头，消息正文
 * 
 * @author Gap
 *
 */
public class HttpRequest {
	/*
	 * 请求行相关信息定义
	 */
	//请求方式
	private String method;
	//请求资源路径
	private String url;
	//协议版本
	private String protocol;
	
	//url中的请求部分  url中"?"左侧内容
	private String requestURI;
	//url中的参数部分 url中"?"右侧内容
	private String queryString;
	//所有参数
	private Map<String, String> parameters = new HashMap<String,String>();
	
	/*
	 * 消息头相关信息定义
	 */
	private Map<String, String> headers = new HashMap<String, String>();
	
	
	/*
	 * 消息正文相关信息定义
	 */
	
	
	//对应客户端的Socket
	private Socket socket;
	//用于读取客户端发送过来消息的输入流
	private InputStream in;
	/**
	 * 构造方法，用来初始化HttpRequest
	 * @throws EmptyRequestException 
	 */
	public HttpRequest(Socket socket) throws EmptyRequestException{
		try {
			this.socket = socket;
			this.in = socket.getInputStream();
			/*W
			 * 1:解析请求行
			 * 2:解析消息头
			 * 3:解析消息正文
			 */
			parseRequestLine();
			parseHeaders();
			parseContent();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 解析请求行
	 * @throws EmptyRequestException 
	 */
	private void parseRequestLine() throws EmptyRequestException{
		try {
			String line = readLine();
			System.out.println("解析请求行:"+line);
			/*
			 * 解析请求行的步骤：
			 * 1:将请求行内容按照空格拆分为三部分
			 * 2:分别将三部分内容设置到对应的属性上
			 * method,url,protocol
			 * 
			 * 
			 * 这里将来可能会抛出数组下标越界，原因在于HTTP协议中也有所涉及，允许客户端连接后发送空请求（实际就是什么也没发过来）
			 * 这时候若解析请求行是拆分不出三项的。
			 * 后面遇到再解决
			 */
			String[] lines = line.split("\\s");
			if(lines.length<3){
				//空请求
				throw new EmptyRequestException();
			}
			method = lines[0];
			url = lines[1];
			parseUrl();
			protocol = lines[2];
			System.out.println("method:"+method);
			System.out.println("url:"+url);
			System.out.println("protocol:"+protocol);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 进一步解析url部分
	 */
	public void parseUrl(){
		/*
		 * 首先将url转码，将含有的%XX内容转换为对应的字符
		 * 例如：
		 * 原url的样子大致是：
		 * myweb/login?username=%E8.....
		 * 
		 *  经过uRLDecoder.decode后，得到
		 *  myweb/login?username=范传奇
		 */
		try {
			this.url = URLDecoder.decode(url,ServerContext.URIEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/*
		 * 1:先判断当前url是否含有参数部分(是否含有"?")含有问号才进行下面的操作
		 * 2:按照"?"将url拆分为两部分
		 *   将?之前的内容设置到属性requestURI上
		 *   将?之后的内容设置到属性queryString
		 * 3:将queryString内容进行进一步解析
		 *   首先按照"&"拆分出每一个参数。然后将每个参数按照"="
		 *   拆分为参数名与参数值，并put到属性parameters这个Map中。
		 * 
		 *   username=13&password=123&nickname=1231&age=1231 
		 */
		/*if(url.contains("?")){  
			String[] info = url.split("\\?");
			requestURI = info[0];
			queryString = info[1];
			String[] qs = queryString.split("&");
			for(String str :qs){
				String[] s = str.split("=");
				parameters.put(s[0], s[1]);
			}
		}   */
		if(this.url.indexOf("?")!=-1){
			String[] info = this.url.split("\\?");
			this.requestURI = info[0];
			/*
			 * 这里根据?拆分后要判断数组长度是否>1，原因在于有的请求可能发来
			 * /myweb/reg?
			 * ?后面没有实际参数(页面form表单中所有的输入域都没有指定name属性
			 * 时就会出现这样的情况)
			 * 如果不判断，可能会出现下标越界异常	 
			 */
			if(info.length>1){
				this.queryString = info[1];
				parseParameters(queryString);
			}
		}else{
			this.requestURI = this.url;
		}
		System.out.println("requestURI:"+requestURI);
		System.out.println("queryString:"+queryString);
		System.out.println("parameters:"+parameters);
		
	}
	/**
	 * 解析参数部分，该内容格式应当为:name=value&name=value&...
	 * @param paraLine
	 */
	private void parseParameters(String paraLine){
		String[] paras = paraLine.split("&");
		for(String para :paras){
			String[] s = para.split("=");
			/*
			 * 如果表单中某个输入框没有输入值，那么传递过来的数据可能是：
			 * /myweb/reg?username=&password=123&......
			 * =右边没有内容， 拆分后不判断数组长度会出现下标越界异常
			 */
			if(s.length>1){
				this.parameters.put(s[0], s[1]);
			}else{
				this.parameters.put(s[0], null);
			}
		}
	}
	
	/**
	 * 解析消息头
	 */
	private void parseHeaders(){
		try {
			/*
			 * 循环调用readLine方法读取每一行字符串
			 * 如果读取的字符串是空字符串则表示单独读取到了CRLF，那么表示消息头部分读取完毕，停止循环即可
			 * 否则读取一行字符串后应当是一个消息头的内容，接下来将该字符串按照":"拆分为两项，
			 * 第一项是消息头的名字，第二项为对应的值，存入到属性headers即可。
			 */
			System.out.println("解析消息头...");
			while(true){
				String line = readLine();
				if("".equals(line)){
					break;
				}
					String[] lines = line.split(": ");
					headers.put(lines[0], lines[1]);
			}
			System.out.println(headers);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析消息正文
	 */
	private void parseContent(){
		System.out.println("解析消息正文...");
		/*
		 * 1：根据消息头中是否含有Content-Length来判断当前请求是否含有消息正文
		 */
		if(headers.containsKey("Content-Length")){
			//取得消息正文的长度
			int length = Integer.parseInt(headers.get("Content-Length"));
		/*
		 * 2.根据长度读取消息正文的数据
		 */
			try {
				byte[] data = new byte[length];
				in.read(data);
				
		/*
		 * 3.根据消息头Content-Type判断正文内容
		 */
			String ContentType = headers.get("Content-Type");
			//判断正文类型是否为form表单提交的数据
			if("application/x-www-form-urlencoded".equals(ContentType)){
				System.out.println("开始解析post提交的form表单...");
				//将消息正文转换为字符串(原get请求地址栏"?"右侧的内容)
				String line = new String(data,"ISO8859-1");
				System.out.println("post表单提交数据:"+line);
				try {
					line = URLDecoder.decode(line,ServerContext.URIEncoding);
				} catch (Exception e) {
					e.printStackTrace();
				}
				parseParameters(line);
				
			}
			//将来还可以添加其他分支，判断其他种类数据
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	
	/**
	 * 读取一行字符串，结束以连续读取到了CRLF符号为止。返回的字符串中不含有最后读取到的CRLF
	 * @return
	 * @throws IOException
	 */
	private String readLine() throws IOException{
		StringBuilder str = new StringBuilder();
		int d =-1;
		//c1表示上次读到的字符，c2表示本次读到的字符
		char c1 = 'a'; char c2 = 'a';
		while((d = in.read())!=-1){
			c2 = (char)d;
			if(c1==HttpContext.CR&& c2==HttpContext.LF){
				break;
			}
			str.append(c2);
			c1 = c2;
		}
		
		
		return str.toString().trim();
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getRequestURI() {
		return requestURI;
	}
	public String getQueryString() {
		return queryString;
	}
	/**
	 * 根据给定的参数名获取对应的参数值
	 * @param name
	 * @return
	 */
	public String getParameter(String name){
		return this.parameters.get(name);
	}
	
}










