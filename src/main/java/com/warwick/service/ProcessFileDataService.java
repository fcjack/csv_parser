package com.warwick.service;

import java.io.IOException;
import java.util.List;

/**
 * Created by jackson on 18/06/17.
 */
public interface ProcessFileDataService {

    List<String> processFileData(String fileName) throws IOException;
}
