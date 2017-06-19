package com.warwick.controller;

import com.warwick.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by jackson on 17/06/17.
 */
@RestController
@RequestMapping("upload")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(method = RequestMethod.POST)
    public void singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        fileStorageService.saveFile(file);
    }
}
