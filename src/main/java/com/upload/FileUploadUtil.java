package com.upload;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @author zhangjun
 */
public class FileUploadUtil {

    public static void upload(MultipartFile multipartFile) throws IOException {
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File file = new File("D:\\file\\tt" + suffix);
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
    }
}
