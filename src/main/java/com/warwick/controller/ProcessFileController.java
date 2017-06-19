package com.warwick.controller;

import com.warwick.service.ProcessFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by jackson on 18/06/17.
 */
@RestController
@RequestMapping("process")
public class ProcessFileController {

    @Autowired
    private ProcessFileDataService processFileDataService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> processDataFile(@RequestParam String fileName) throws IOException {
        return processFileDataService.processFileData(fileName);
    }
}
