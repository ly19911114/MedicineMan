package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {
	
	/**
	 * 文件大小换算
	 * @param size
	 * @return
	 */
	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
 
        if (size >= gb) {
            return String.format("%.1fGB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0fMB" : "%.1fMB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0fKB" : "%.1fKB", f);
        } else
            return String.format("%dB", size);
    }
	
	/**
	 * 获得以当月命名的文件夹
	 * @param uploadPath
	 * @return
	 */
	public static String getChildDirectory(String uploadPath) {
		// TODO Auto-generated method stub
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String str = df.format(now);
		
		File file = new File(uploadPath,str);
		if(!file.exists()){
			file.mkdirs();
		}
		
		return str;
	}
	
	/**
	 * 文件下载
	 * @param response
	 * @param fileName
	 * @param downloadPath
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response,String fileName,String downloadPath) throws Exception{
		
		fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		
		response.setContentType( "application/x-msdownload");
		response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
		
		InputStream in = new FileInputStream(downloadPath);
		OutputStream out = response.getOutputStream();
		int len = -1;
		byte[] buff = new byte[1024];
		while((len=in.read(buff))!=-1){
			out.write(buff, 0, len);
		}
		in.close();
		out.close();
	}
	
}
