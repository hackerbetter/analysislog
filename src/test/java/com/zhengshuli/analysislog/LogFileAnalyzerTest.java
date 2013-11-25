package com.zhengshuli.analysislog;

import org.junit.After;
import org.junit.Test;

import com.zhengshuli.analysislog.domain.LogFile;
import com.zhengshuli.analysislog.parser.LogFileAnalyzer;

public class LogFileAnalyzerTest {
    @After
    public void tearDown(){
        FileFixture.deleteFile("top10_unique_visitors.csv");
        FileFixture.deleteFile("request_spend_time_avg.csv");
    }
    
    @Test
    public void should_analyze_to_csv(){
        LogFile logFile = new LogFile(TestConstants.DEFAULT_LOG_FORMAT, FileFixture.loadFile("/lotserver.log"));
        LogFileAnalyzer.report(logFile);
    }
}
