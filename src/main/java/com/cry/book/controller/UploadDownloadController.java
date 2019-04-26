package com.cry.book.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploadDownload")
public class UploadDownloadController {
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public String uploadImage(@RequestParam(value = "file") MultipartFile file) throws RuntimeException {
        if (file.isEmpty()) {
            System.out.println("文件不能为空");
            return "";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "D:\\bishe_bookPic\\";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            System.out.println("上传成功后的文件路径未：" + filePath + fileName);
            return fileName;
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping(value = "/uploadMultpie", method = RequestMethod.POST)
    public List<String> uploadMultpie(@RequestBody MultipartFile[] files) throws RuntimeException {
        List<String> filleNames = new ArrayList<String>();
        if (files.length==0) {
            System.out.println("文件不能为空");
            return null;
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            
            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("上传的后缀名为：" + suffixName);
            // 文件上传后的路径
            String filePath = "D:\\bishe_bookPic\\";
            // 解决中文问题，liunx下中文路径，图片显示问题
            // fileName = UUID.randomUUID() + suffixName;
            File dest = new File(filePath + fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                System.out.println("上传成功后的文件路径未：" + filePath + fileName);
                filleNames.add(fileName);
            }
            catch (IllegalStateException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filleNames;
    }

    // 文件下载相关代码
    @RequestMapping(value = "/downloadImage", method = RequestMethod.GET)
    public void downloadImage(String imageName, HttpServletRequest request, HttpServletResponse response) {
        // String fileName = "123.JPG";
        String fileUrl = "D:\\bishe_bookPic\\" + imageName;
        if (fileUrl != null) {
            // 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            /*
             * String realPath = request.getServletContext().getRealPath( "//WEB-INF//");
             */
            /* File file = new File(realPath, fileName); */
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}