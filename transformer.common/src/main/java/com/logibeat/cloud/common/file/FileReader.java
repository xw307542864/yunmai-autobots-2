package com.logibeat.cloud.common.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 文件读取
 *
 * @author zhangf
 * @version V1.0
 * @date 2016年11月7日 下午2:40:44
 */
public class FileReader {

	public static byte[] FileBufferedReader(String filepath) {
		// 按字节读取文件
		Path path = Paths.get(filepath);
		byte[] contentBytes;
		try {
			contentBytes = Files.readAllBytes(path);
			// Files.write(path, contentBytes, StandardOpenOption.CREATE);
			return contentBytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static List<String> FileReader(String filepath) {
		// 按字节读取文件
		Path path = Paths.get(filepath);
		try {
			List<String> contentLines = Files.lines(path).collect(java.util.stream.Collectors.toList());
			return contentLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// fileContent.forEach(o->{System.out.println(o);});
		// Files.write(path, contentLines,Charset.defaultCharset(),
		// StandardOpenOption.CREATE);

		return null;
	}
}
