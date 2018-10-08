package com.webserver.servlets;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

/**
 * Servlet是java EE标准定义的内容
 * 
 * @author Gap
 *
 */
public class RegServlet extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response){
		System.out.println("开始处理注册");
		/*
		 * 处理注册流程
		 * 1:通过request获取用户表单提交上来的注册用户信息
		 * 2:将该信息写入到文件user.dat中
		 * 3:设置response对象，将注册成功页面响应给客户端
		 */
		//1
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		int age = Integer.parseInt(request.getParameter("age"));
		
		/*
		 * 2将注册信息写入user.dat文件
		 * 每条记录占用100字节，其中，用户名，密码，昵称为字符串，各占用32字节，年龄为int值占用4字节
		 */
		try(RandomAccessFile raf = new RandomAccessFile("user.dat", "rw")) {
			raf.seek(raf.length());
			byte[] data = username.getBytes("UTF-8");
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			data = password.getBytes("UTF-8");
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			data = nickname.getBytes("UTF-8");
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			raf.writeInt(age);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//3
		File file = new File("webapps/myweb/reg_success.html"); 
		response.setEntity(file);
		
		System.out.println("注册处理完毕");
	}
}
