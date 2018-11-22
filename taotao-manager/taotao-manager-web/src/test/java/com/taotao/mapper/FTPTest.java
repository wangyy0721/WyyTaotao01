package com.taotao.mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {

	@Test
	public void testFtpClient() throws SocketException, IOException{
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接
		ftpClient.connect("192.168.247.129", 21);
		//登录ftp服务器，使用用户名密码
		ftpClient.login("ftpuser", "wangyy-ftp");
		//上传文件
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("D:\\360\\a.JPG"));
		//设置上传路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//设置上传格式二进制
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务器端文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("hello1.jpg", inputStream);
		//关闭连接
		ftpClient.logout();	
		
		//浏览器测试
		//ftp://192.168.247.128/home/ftpuser/www/images/hello1.jpg
	}
}
