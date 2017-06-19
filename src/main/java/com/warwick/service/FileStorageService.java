package com.warwick.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by jackson on 17/06/17.
 */
public interface FileStorageService {

    void saveFile(MultipartFile file) throws IOException;

    String getFilePath(String fileName);
}
