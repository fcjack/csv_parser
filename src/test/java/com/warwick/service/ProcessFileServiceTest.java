package com.warwick.service;

import com.warwick.service.impl.FileStorageServiceImpl;
import com.warwick.service.impl.ProcessFileDataServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by jackson on 19/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessFileServiceTest {

    @Mock
    private FileStorageService fileStorageService;

    private static String UPLOADED_FOLDER = System.getProperty("user.home");

    @InjectMocks
    private ProcessFileDataServiceImpl processFileDataService;

    @Before
    public void setup() {
        processFileDataService = new ProcessFileDataServiceImpl(fileStorageService);
    }

    @Test
    public void testProcessFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("exampleC_input.csv").getFile());
        System.out.println(file.getAbsolutePath());

        Mockito.when(fileStorageService.getFilePath(Mockito.anyString())).thenReturn(file.getAbsolutePath());
        processFileDataService.processFileData("exampleC_input.csv");
    }

    @Test(expected = IOException.class)
    public void testFileNotFound() throws IOException {
        Mockito.when(fileStorageService.getFilePath(Mockito.anyString()))
                .thenReturn(UPLOADED_FOLDER.concat(File.separator).concat("csv/").concat("exampleA_input.csv"));
        processFileDataService.processFileData("exampleA_input.csv");
    }
}
