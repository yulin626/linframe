package com.ganglion.controller;

import com.ganglion.model.FileDTO;
import com.ganglion.model.KeyQuery;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     */
    @CrossOrigin
    @PostMapping("/upload")
    public ResultResponse<FileDTO> upload(@RequestParam("file") MultipartFile file) {   //HttpServletRequest request
        return fileService.upload(file);
    }

    /**
     * 下载文件
     */
    @CrossOrigin
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String fileId) { //HttpServletRequest request, HttpServletResponse response
        return fileService.download(fileId);
    }

    /**
     * 获取文件列表
     */
    @CrossOrigin
    @RequestMapping("/getFileList")
    public ResultResponse<List<FileDTO>> getFileList(@RequestBody KeyQuery keyQuery){
        return fileService.getFileList(keyQuery.getKeys());
    }

    /**
     * 删除文件
     */
    @CrossOrigin
    @RequestMapping("/deleteFile")
    public ResultResponse deleteFile(@RequestBody KeyQuery keyQuery){
        return fileService.deleteFile(keyQuery.getKeys());
    }
}
