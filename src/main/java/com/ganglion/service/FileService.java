package com.ganglion.service;

import com.ganglion.model.FileDTO;
import com.ganglion.msg.ResultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * 上传文件
     */
    ResultResponse<FileDTO> upload(MultipartFile file); //HttpServletRequest request
    /**
     * 下载文件
     */
    ResponseEntity<byte[]> download(String fileId); //HttpServletRequest request, HttpServletResponse response
    /**
     * 获取文件列表
     */
    ResultResponse<List<FileDTO>> getFileList(List<String> keys);
    /**
     * 删除文件
     */
    ResultResponse deleteFile(List<String> keys);
}
