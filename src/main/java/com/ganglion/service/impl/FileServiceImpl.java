package com.ganglion.service.impl;

import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.File;
import com.ganglion.model.FileDTO;
import com.ganglion.model.JwtUser;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.FileService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl extends BaseService<File> implements FileService {

    @Transactional
    @Override
    public ResultResponse<FileDTO> upload(MultipartFile file) { //HttpServletRequest request
        ResultResponse result = new ResultResponse();
        if (!file.isEmpty()) {
            //获取文件名
            String saveFileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = saveFileName.substring(saveFileName.lastIndexOf("."));
            Long size = file.getSize();

            String fileUUID = UUID.randomUUID().toString();
            //request.getSession().getServletContext().getRealPath("/")
            java.io.File saveFile = new java.io.File(this.getClass().getResource("/").getPath() + "\\fileUpload\\" + fileUUID + suffixName);
            //检测是否存在目录
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();

                JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                File dbFile = new File();
                dbFile.setFileName(saveFileName);
                dbFile.setSize(size);
                dbFile.setExtensionName(suffixName);
                dbFile.setId(fileUUID);
                dbFile.setDelete(false);
                dbFile.setCreatedBy(userDetails.getEmplId());
                dbFile.setCreatedDept("0");
                dbFile.setCreatedTime(new Date());
                dbFile.setUpdatedBy(userDetails.getEmplId());
                dbFile.setUpdatedDept("0");
                dbFile.setUpdatedTime(new Date());
                this.insert(dbFile);

                //深度拷贝
                MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
                MapperFacade mapper = mapperFactory.getMapperFacade();
                FileDTO fileDTO = mapper.map(dbFile, FileDTO.class);

                result.setStatus(1);
                result.setData(fileDTO);
                result.setMessage(saveFile.getName() + " 上传成功!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();

                result.setStatus(0);
                result.setMessage("上传失败:" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();

                result.setStatus(0);
                result.setMessage("上传失败:" + e.getMessage());
            }
        } else {
            result.setStatus(0);
            result.setMessage("上传失败，因为文件为空!");
        }
        return result;
    }

    /**
     * 下载文件
     */
    @Override
    public ResponseEntity<byte[]> download(String fileId) { //HttpServletRequest request, HttpServletResponse response
        File fileEntity = this.selectByKey(fileId);
        String fileName = fileEntity.getId() + fileEntity.getExtensionName();
        String filePath = this.getClass().getResource("/").getPath() + "\\fileUpload\\";
                //request.getSession().getServletContext().getRealPath("/") + "\\fileUpload\\";

        java.io.File file = new java.io.File(filePath, fileName);

        HttpHeaders headers = new HttpHeaders();
        String downloadFileName= new String();
        try {
            downloadFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  //下载显示的文件名，解决中文名称乱码问题
        }catch (Exception e){

        }
        headers.setContentDispositionFormData("attachment", downloadFileName);  //告知浏览器以下载方式打开
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //设置MIME类型application/octet-stream ： 二进制流数据
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }catch (Exception e){

        }
        return new ResponseEntity<byte[]>(null);
    }

    /**
     * 获取文件列表
     */
    @Override
    public ResultResponse<List<FileDTO>> getFileList(List<String> keys) {
        List<FileDTO> list=new ArrayList<>();

        for (String key:keys){
            File file = this.selectByKey(key);
            if (file!=null){
                //深度拷贝
                MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
                MapperFacade mapper = mapperFactory.getMapperFacade();
                FileDTO fileDTO = mapper.map(file, FileDTO.class);
                list.add(fileDTO);
            }
        }
        ResultResponse<List<FileDTO>> result = new ResultResponse<>();
        result.setData(list);
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 删除文件
     */
    @Override
    public ResultResponse deleteFile(List<String> keys) {
        for (String key:keys){
            File fileEntity = this.selectByKey(key);
            if (fileEntity!=null){
               String filePath =this.getClass().getResource("/").getPath() + "\\fileUpload\\" + fileEntity.getFileName();
                java.io.File file = new java.io.File(filePath);
                if (file.exists()){
                    file.delete();
                }
                this.deleteByKey(fileEntity);
            }
        }
        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }
}
