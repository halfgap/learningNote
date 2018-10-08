package com.webserver.demo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ��ʾuser.dat�ļ��������û���Ϣ
 * @author Gap
 *
 */
public class ShowAllUserDemo {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile("user.dat", "r");
		for(int i=0;i<raf.length()/100;i++){
			
			byte[] data = new byte[32];
			raf.read(data);
			String username = new String(data,"UTF-8").trim();
			
			raf.read(data);
			String pwd = new String(data,"UTF-8").trim();
			
			raf.read(data);
			String nickname = new String(data,"UTF-8").trim();
			
			int age = raf.readInt();
			
			System.out.println(username+","+pwd+","+nickname+","+age);
		}
		
		raf.close();
	}
}
