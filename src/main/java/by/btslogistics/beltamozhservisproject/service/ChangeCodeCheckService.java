package by.btslogistics.beltamozhservisproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class ChangeCodeCheckService {

    private static Logger LOGGER = LoggerFactory.getLogger(ChangeCodeCheckService.class);

    public void findCodeChecks(File excelFile) {
        Map<String, String> checks = ExcelService.oldAndNewChecksFromExcel(excelFile);
        for (Map.Entry<String, String> check: checks.entrySet()) {
            LOGGER.info(check.getKey() + " : " + check.getValue());
        }
        LOGGER.info("------------------------------------------- : " + checks.size());
        replaceCodeCheck(checks);
    }

    private void replaceCodeCheck(Map<String, String> checks) {

    }
}
