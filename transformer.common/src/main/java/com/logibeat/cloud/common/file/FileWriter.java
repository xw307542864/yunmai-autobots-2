package com.logibeat.cloud.common.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件写入
 *
 * @author zhangf
 * @version V1.0
 * @date 2016年11月7日 下午2:40:44
 */
public class FileWriter {

	/**
	 * 用buffered写 nio最低JAVA8
	 * @param filepath 绝对路径
	 * @param text
	 * @return
	 * @throws IOException 
	 */
	public static File FileBufferedWriter(String filepath, String text){
		Path path = Paths.get(filepath);  
		
		//Use try-with-resource to get auto-closeable writer instance  
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {  
		    writer.write(text);  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return path.toFile();
	}
	
	/**
	 * 用文件方式写  nio最低JAVA8
	 * @param filepath 绝对路径
	 * @param text
	 * @return
	 */
	public static File FileWriter(String filepath, String text){
		Path path = Paths.get(filepath);
		
		try {
			if(!path.toFile().exists()){
				FileUtil.makeFile(filepath);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Files.write(path, text.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return path.toFile();
	}
	
	public static void main(String[] args) throws Exception {
		File f=  new File("D:\\uploadfile\\gps\\starsoft\\GpsCar\\516755\\20161101000000~20161102000000.txt");
		if(!f.exists()){
			FileUtil.makeFile("D:\\uploadfile\\gps\\starsoft\\GpsCar\\516755\\20161101000000~20161102000000.txt");
		}
		
//		FileWriter("D:\\uploadfile\\gps\\starsoft\\GpsCar\\516755\\20161101000000~20161102000000.txt", "asasas");
	}
	
}
