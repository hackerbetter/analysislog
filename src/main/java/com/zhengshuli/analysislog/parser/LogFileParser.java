package com.zhengshuli.analysislog.parser;

import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.zhengshuli.analysislog.domain.LogFile;

public class LogFileParser {
    /**
     * parse log file.
     */
    @SuppressWarnings("unchecked")
    public static void parse(LogFile logFile){
        LogLineParser parser = new LogLineParser(logFile.getLogFormat());
        try {
            Files.readLines(logFile.getFile(), Charset.defaultCharset(), parser);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
