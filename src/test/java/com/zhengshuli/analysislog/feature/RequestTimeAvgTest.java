package com.zhengshuli.analysislog.feature;

import java.util.List;

import org.junit.Test;

import com.zhengshuli.analysislog.FileFixture;
import com.zhengshuli.analysislog.TestConstants;
import com.zhengshuli.analysislog.feature.view.RequestTimeAvgView.RequestTimeAvg;
import com.zhengshuli.analysislog.parser.LogLineParser;

public class RequestTimeAvgTest {
    private RequestTimeAnalyzer analyzer = new RequestTimeAnalyzer();
    
    @Test
    public void should_get_correct_request_time(){
        LogLineParser logLineParser = new LogLineParser(TestConstants.DEFAULT_LOG_FORMAT, analyzer);
        
        List<String> lines = FileFixture.loadLines("/lotserver.log");
        for (String line : lines){
            logLineParser.processLine(line);
        }
        
        List<RequestTimeAvg> requestTimeAvgs = analyzer.toView().result();
    }
}
