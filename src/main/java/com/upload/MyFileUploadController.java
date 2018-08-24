package com.upload;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author someone
 */
@RestController

public class MyFileUploadController {

    @PostMapping(value = "/myUpload")
    void upload(@RequestParam(value = "file") MultipartFile multipartFile, User user) throws IOException {
        System.out.println(user);
        FileUploadUtil.upload(multipartFile);
    }

    @PostMapping(value = "/download1")
    public ResponseEntity<FileSystemResource> downLoad() {
        File file = new File("d:\\ccc.txt");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
//                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource("d:\\ccc.txt"));

    }

    @GetMapping(value = "/download")
    public void downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "ccc.txt";
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
        BufferedInputStream bis = null;
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buff = FileUtils.readFileToByteArray(new File("d:\\" + fileName));
            outputStream.write(buff);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
