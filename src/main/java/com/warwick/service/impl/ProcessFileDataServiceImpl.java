package com.warwick.service.impl;

import com.warwick.domain.VariableRange;
import com.warwick.service.FileStorageService;
import com.warwick.service.ProcessFileDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service responsible to process data from file requested and return the result lines
 * <p>
 * Created by jackson on 18/06/17.
 */
@Service
public class ProcessFileDataServiceImpl implements ProcessFileDataService {

    private Logger logger = Logger.getLogger(getClass());
    private final FileStorageService fileStorageService;

    @Autowired
    public ProcessFileDataServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    /**
     * This function will get the file and process the first line and send the data for process
     * the values from variables and the parameters based on decision.
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public List<String> processFileData(String fileName) throws IOException {
        String filePath = fileStorageService.getFilePath(fileName);
        Stream<String> stream = Files.lines(Paths.get(filePath));
        String firstLine = stream.findFirst().get();

        return processFile(filePath, firstLine);

    }

    private List<String> processFile(String filePath, String firstLine) throws IOException {
        String[] firstLineSpited = firstLine.split(",");
        try (Stream<String> openStream = Files.lines(Paths.get(filePath))) {
            List<String> dataWithDecisionOne = openStream
                    .filter(line -> line.endsWith("1"))
                    .collect(Collectors.toList());

            Map<String, VariableRange> variablesRangeMap = processVariableRanges(firstLineSpited, dataWithDecisionOne);
            List<String> toReturn = new ArrayList<>();
            toReturn.add(firstLine);
            toReturn.addAll(processResultData(filePath, variablesRangeMap, firstLineSpited));
            return toReturn;
        } catch (Exception e) {
            logger.error("Error while was processing the data from file", e);
            throw e;
        }
    }

    /**
     * Process the variables that we have on first line and create a Map to
     * store the range for each variable, Min and Max value for each one.
     *
     * @param firstLine
     * @param dataWithDecisionOne
     * @return
     */
    private Map<String, VariableRange> processVariableRanges(String[] firstLine, List<String> dataWithDecisionOne) {
        Map<String, VariableRange> toReturn = new HashMap<>();
        for (int i = 1; i < firstLine.length - 1; i++) {
            final int currentIndex = i;
            Integer max = dataWithDecisionOne.stream().mapToInt(value ->
                    Integer.parseInt(value.split(",")[currentIndex]))
                    .max().getAsInt();
            Integer min = dataWithDecisionOne.stream().mapToInt(value ->
                    Integer.parseInt(value.split(",")[currentIndex]))
                    .min().getAsInt();

            toReturn.put(firstLine[i], new VariableRange(min, max));
        }
        return toReturn;
    }

    /**
     * Process all lines from file and analyze only number values to check if is on range for each variable,
     * if decision is 0 and is not in range of variables this line is not added on result data.
     *
     * @param filePath
     * @param variablesRangeMap
     * @param firstLine
     * @return
     * @throws IOException
     */
    private List<String> processResultData(String filePath, Map<String, VariableRange> variablesRangeMap, String[] firstLine) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(filePath));
        List<String> result = stream.filter(line -> {
            String[] values = line.split(",");
            if (values[values.length - 1].equals("1")) return true;

            for (int i = 1; i < firstLine.length - 1; i++) {
                VariableRange variableRange = variablesRangeMap.get(firstLine[i]);
                String curentValue = values[i];
                if (curentValue.matches("-?[0-9]+")) {
                    if (variableRange.isBetweenRange(Integer.valueOf(values[i]))) {
                        return true;
                    }
                } else {
                    break;
                }
            }

            return false;
        }).collect(Collectors.toList());

        return result;
    }
}
