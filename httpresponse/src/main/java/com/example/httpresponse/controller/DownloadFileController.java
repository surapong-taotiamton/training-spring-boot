package com.example.httpresponse.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

@RestController
public class DownloadFileController {

    @GetMapping("/download-file")
    public ResponseEntity<InputStreamResource> downloadFile() {



        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("DATA IN FILE".getBytes());
        InputStreamResource inputStreamResource = new InputStreamResource(byteArrayInputStream);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"data.txt\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(inputStreamResource);
    }

}
