package com.webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebServer主类
 * @author Gap
 *
 */
public class WebServer {
	private ServerSocket server;
	//管理用于处理客户端请求的线程
	private ExecutorService threadPool;
	/**
	 * 构造方法，用来初始化服务端
	 */
	public WebServer(){
		try {
			server = new ServerSocket(ServerContext.port);
			threadPool = Executors.newFixedThreadPool(ServerContext.maxThreads);
			System.out.println("启动服务器完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 服务器启动方法
	 */
	public void start(){
		try {
			while(true){
				Socket socket = server.accept();
				System.out.println("一个客户端连接了！");
				
				//启动一个线程处理该客户端请求
				ClientHandler handler = new ClientHandler(socket);
				threadPool.execute(handler);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		WebServer server =  new WebServer();
		server.start();
	}
}
