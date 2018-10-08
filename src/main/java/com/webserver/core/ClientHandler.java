package com.webserver.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.webserver.http.EmptyRequestException;
import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;
import com.webserver.servlets.HttpServlet;
import com.webserver.servlets.LoginServlet;
import com.webserver.servlets.RegServlet;

/**
 * 处理客户端请求
 * @author Gap
 *
 */
public class ClientHandler implements Runnable{
	private Socket socket;
	
	public ClientHandler(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run(){
		try {
			/*
			 * 1.准备工作
			 * 2.处理请求
			 * 3.响应客户端
			 */
			//1
			HttpRequest request = new HttpRequest(socket);
			HttpResponse response = new HttpResponse(socket);
			//2
			//处理请求：根据请求的资源路径，从webapps目录中找到对应的资源
			//若资源存在则将该资源响应给客户端，若没有找到资源则响应404页面给客户端
			String url = request.getRequestURI();
			//先判断请求是否是一个业务逻辑
			String servletName = ServerContext.getServletName(url);
			//判断是否处理业务
			if(servletName!=null){
				System.out.println("利用反射加载："+servletName);
				//利用反射实例化该Servlet
				Class cls = Class.forName(servletName);
				//实例化
				HttpServlet servlet = (HttpServlet)cls.newInstance();
				//调用该Servlet的service方法处理业务
				servlet.service(request, response);
			}else{
				File file = new File("webapps"+url);
				if(file.exists()){
					response.setEntity(file);
					System.out.println(file.getName()+"响应完毕");
				}else{
					System.out.println("该资源不存在");
					//响应404页面
					response.setStatusCode(404);
					response.setEntity(new File("webapps/root/404.html"));
				}
			}
			
			
			//3
			response.flush();
		}catch(EmptyRequestException e){ 
			//空指针忽略即可，无需做任何处理
		} 
		catch (Exception e) {
			
		}finally{
			//处理与客户端断开连接的操作
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
