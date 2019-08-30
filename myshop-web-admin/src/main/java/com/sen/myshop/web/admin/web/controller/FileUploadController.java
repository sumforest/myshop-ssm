package com.sen.myshop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "file")
public class FileUploadController {
    /**
     * 文件上传
     *
     * @param dropFile
     * @param request
     * @return
     */
    private static final String UPLOAD_NAME ="/static/upload/";
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(MultipartFile dropFile, MultipartFile[] editorFile, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        if (dropFile != null) {
            map.put("fileName", writeIn(dropFile, request));
        }
        if (editorFile.length > 0) {
            List<String> list = new ArrayList<>();
            for (MultipartFile tempFile : editorFile) {
                list.add(writeIn(tempFile, request));
            }
            map.put("errno", 0);
            map.put("data", list);
        }
        return map;
    }

    /**
     * 把文件写入本地
     * @param multipartFile
     * @param request
     * @return
     */
    public String writeIn(MultipartFile multipartFile, HttpServletRequest request) {
        //获取文件名
        String filename = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String substring = filename.substring(filename.lastIndexOf('.'));
        //获取项目的绝对路径
        String realPaht = request.getSession().getServletContext().getRealPath("/");
        //保存的完整路径
        String targetPath =realPaht + UPLOAD_NAME;
        File file = new File(targetPath);
        //如果不存在则创建相应目录
        if (!file.exists()) {
            file.mkdirs();
        }
        //创建唯一的文件名
        String targetFileName = UUID.randomUUID() + substring;
        //写入到指定目录
        file = new File(targetPath, targetFileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = String.format("%s://%s:%s", request.getScheme(), request.getServerName(), request.getServerPort());
        String fileUrl = host + UPLOAD_NAME + targetFileName;
        return fileUrl;

    }
}
