package com.example.springbootbigevent.controller;

import com.example.springbootbigevent.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) throws IOException {
        // Save file in local disk
        String originalFileName = file.getOriginalFilename();
        // Ensure the uniqueness of the filenames
        String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        file.transferTo(new File("/Users/hujiewen/Desktop/file/" + fileName));
        return Result.success("...");
    }
}
