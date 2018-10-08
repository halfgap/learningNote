package com.webserver.servlets;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public class LoginServlet extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response){
		System.out.println("开始处理登录");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//登录状态，默认为false
		boolean flag = false;
		try(RandomAccessFile raf = new RandomAccessFile("user.dat", "r")) {
			for(int i=0;i<raf.length()/100;i++){
				raf.seek(100*i);
				byte[] data = new byte[32];
				raf.read(data);
				String name = new String(data,"UTF-8").trim();
				if(name.equals(username)){
					raf.read(data);
					String pwd = new String(data,"UTF-8").trim();
					if(pwd.equals(password)){
						flag = true;
					}
					break;
				}
			}
			if(flag==true){
				File file = new File("webapps/myweb/login_success.html"); 
				response.setEntity(file);
				
			}else{
				File file = new File("webapps/myweb/login_fail.html"); 
				response.setEntity(file);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
