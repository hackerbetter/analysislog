package com.zhengshuli.analysislog.parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.io.Files;
import com.zhengshuli.analysislog.domain.LogFile;
import com.zhengshuli.analysislog.reporter.CSVReporter;
import com.zhengshuli.analysislog.reporter.ICSVView;
import com.zhengshuli.analysislog.reporter.IView;

public class LogFileAnalyzer {
    /**
     * parse log file.
     */
    @SuppressWarnings("unchecked")
    public static void report(LogFile logFile){
        LogLineParser parser = new LogLineParser(logFile.getLogFormat());
        try {
            List<IView> views = Files.readLines(logFile.getFile(), 
                    Charset.defaultCharset(), parser);
            System.out.println(views.size());
            for(IView view : views){
                if(view instanceof ICSVView){
                    CSVReporter.report((ICSVView) view);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
