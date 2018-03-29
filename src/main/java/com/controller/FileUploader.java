package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileuploader")
public class FileUploader {
	@RequestMapping(value="/upload")
	@ResponseBody
	@CrossOrigin(origins="*")//解决跨域问题
	public String upload(HttpServletRequest req, HttpServletResponse response,MultipartFile file) {
		  if (file.isEmpty()) {
	            return "文件为空";
	        }
		  
		  // 获取文件名
	        String fileName = file.getOriginalFilename();
	      
	        // 获取文件的后缀名
	        String suffixName = fileName.substring(fileName.lastIndexOf("."));
	        // 文件上传后的路径
	        String filePath = "D://test//";
	        // 解决中文问题，liunx下中文路径，图片显示问题
	        // fileName = UUID.randomUUID() + suffixName;
	        File dest = new File(filePath + fileName);
	        // 检测是否存在目录
	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }
	        try {
	            file.transferTo(dest);
	            return "上传成功";
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "上传失败";
		
		
	}
	
	

}
