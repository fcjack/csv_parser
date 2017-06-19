package com.warwick.service.impl;

import com.warwick.service.FileStorageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jackson on 17/06/17.
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private Logger logger = Logger.getLogger(getClass());
    private static String UPLOADED_FOLDER = System.getProperty("user.home");

    @Override
    public void saveFile(MultipartFile file) throws IOException {
        String directory_path = UPLOADED_FOLDER.concat(File.separator).concat("csv");
        File directory = new File(directory_path);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Error creating directory to save file");
            }
        }

        logger.info(String.format("Saving file in folder %s", UPLOADED_FOLDER.concat(File.separator).concat("csv")));

        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path path = Paths.get(directory_path.concat(File.separator).concat(file.getOriginalFilename()));
        Files.write(path, bytes);
    }

    @Override
    public String getFilePath(String fileName) {
        return UPLOADED_FOLDER.concat(File.separator).concat("csv/").concat(fileName);
    }
}
